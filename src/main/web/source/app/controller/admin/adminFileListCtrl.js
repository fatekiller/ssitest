/**
 * Created by liuchenfei on 2017/10/26.
 */
var md = require("../../module/admin");
md.controller('adminFileListCtrl', adminFileListCtrl);
adminFileListCtrl.$inject = ["$scope", "$state", "session", "commonServer"];
function adminFileListCtrl($scope, $state, session, commonServer) {
    $scope.totalSize = 0;
    $scope.typeList = [
        {name: "全部", id: -1},
        {name: "文档", id: 1},
        {name: "图片", id: 2},
        {name: "音乐", id: 3},
        {name: "视频", id: 4},
        {name: "其他", id: 5},
    ];
    $scope.statusList = ["正常", "锁定"];
    $scope.filterRule = {
        type: -1,
        hideLock: false
    };
    $scope.loadFiles = function () {
        commonServer.get('file', 'getAllFiles.do', {}, {}, function (value) {
            $scope.AllfileList = value;
            $scope.fileList = $scope.AllfileList;
            $scope.totalSize = $scope.AllfileList.reduce(function (sum, item) {
                    return sum.fileSize + item.fileSize;
                }) / 1024 + ' kb';
        })
    };
    $scope.filterFile = function () {
        $scope.fileList = $scope.AllfileList.filter(function (item) {
            var type = true;
            var status = true;
            if ($scope.filterRule.type != -1) {
                type = item.fileType == $scope.filterRule.type;
            }
            if ($scope.filterRule.hideLock) {
                status = item.fileStatus == 0
            }
            return type && status;
        });
    };
    $scope.lockBatch = function () {
        var fileIds = []
        $scope.fileList.map(function (item) {
            if (item.isSelect) {
                fileIds.push(item.fileId);
            }
        });
        if (fileIds.length) {
            commonServer.post('file', 'lockFile.do', {
                ids: fileIds.join(',')
            }, {}, function (value) {
                console.log(value);
                $scope.loadFiles(null, true);
            });
        }
    };
};

