/**
 * Created by liuchenfei on 2017/10/26.
 */
var md = require("../../module/user");
md.controller('fileListCtrl', fileListCtrl);
fileListCtrl.$inject = ["$scope", "$state", "session", "commonServer", 'Upload'];
function fileListCtrl($scope, $state, session, commonServer, Upload) {
    var parentFolder = {};
    $scope.dirName = '/';
    $scope.typeList = [
        {name: "全部", id: -1},
        {name: "文件夹", id: 0},
        {name: "文档", id: 1},
        {name: "图片", id: 2},
        {name: "音乐", id: 3},
        {name: "视频", id: 4},
        {name: "其他", id: 5},
    ];
    $scope.currentDir = JSON.parse(session.get("rootDir"));
    $scope.loadFiles = function (id, isRefresh) {
        var id = id || $scope.currentDir.directoryId;
        commonServer.post('file', 'getFilesAndDirectoriesByDirectoryId.do', {
            DirectoryId: id
        }, {}, function (value) {
            if (value.directory.directoryName != '/' && !isRefresh) {
                var sepreator = '/'
                if ($scope.dirName == '/') sepreator = '';
                $scope.dirName += sepreator + value.directory.directoryName;
            }
            $scope.currentDir = value.directory;
            $scope.AllfileAndDirList = value.files;
            $scope.fileAndDirList = $scope.AllfileAndDirList;
        })
    };

    $scope.returnToParent = function () {
        if ($scope.currentDir.directoryName == '/') return;
        $scope.loadFiles($scope.currentDir.parentDirectoryId, true);
        $scope.dirName = $scope.dirName.substr(0, $scope.dirName.lastIndexOf('/'));
        if ($scope.dirName.length == 0) $scope.dirName = '/'
    };

    //预添加目录
    $scope.addDir = function () {
        console.log("addDir");
        $scope.dirToAdd = {
            isNew: true,
            status: 0,
            name: "",
            fileType: 0,
            size: "200kb",
            updateTime: "2017-10-10 11:10",
            isSelect: false
        };
        $scope.fileAndDirList.push($scope.dirToAdd);
    };

    //发送添加目录请求
    $scope.doAddDir = function () {
        commonServer.post('file', 'addDirectory.do', {
            dirName: $scope.dirToAdd.name,
            parentDirId: $scope.currentDir.directoryId,
            userId: session.get('userId')
        }, {}, function (value) {
            swal(value);
            $scope.loadFiles(null, true);
        })
    };
    $scope.filterType = function (typeid) {
        if (typeid == -1) {
            $scope.fileAndDirList = $scope.AllfileAndDirList;
            return;
        }
        $scope.fileAndDirList = $scope.AllfileAndDirList.filter(function (item) {
            return item.fileType == typeid;
        });
    }

    $scope.download = function (file) {
        if (file.status) {
            swal("文件被管理员锁定，无法下载");
        }
    };
    $scope.deleteBatch = function () {
        var dirids = []
        var fileIds = []
        $scope.fileAndDirList.map(function (item) {
            if (item.isSelect) {
                if (item.fileType == 0) {
                    dirids.push(item.id);
                } else {
                    fileIds.push(item.id);
                }
            }
        });
        if (dirids.length) {
            commonServer.post('file', 'deleteDir.do', {
                ids: dirids.join(',')
            }, {}, function (value) {
                console.log(value);
                $scope.loadFiles(null, true);
            });
        }
        if (fileIds.length) {
            commonServer.post('file', 'deleteFile.do', {
                ids: fileIds.join(',')
            }, {}, function (value) {
                console.log(value);
                $scope.loadFiles(null, true);
            });
        }
    };

    $scope.giveUpAddDir = function (dirId) {
        $scope.fileAndDirList.pop();
    };

    $scope.upload = function (file) {
        Upload.upload({
            url: 'http://localhost:8081/api/file/addFile.do?userId=' + session.get('userId') + "&dirId=" + $scope.currentDir.directoryId,
            data: {
                file: file
            }
        }).then(function (resp) {
            if (resp.data.success) {
                swal(resp.data.value);
            } else {
                swal(resp.data.error);
            }
            $scope.loadFiles(null, true);
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            $scope.vm = {}
            $scope.vm.value = progressPercentage;
            $scope.vm.style = 'progress-bar-info';
            $scope.vm.showLabel = true;
            $scope.vm.striped = true;
        });
    };
}