/**
 * Created by liuchenfei on 2017/10/26.
 */
var md = angular.module('userModule', []);
md.config(userConfig);
userConfig.$inject = ['$stateProvider'];
function userConfig($stateProvider) {
    var routeList = [
        {
            name: 'admin.user',
            url: "/user",
            templateUrl: "view/user/fileList.html",
            controller: 'fileListCtrl',
            data: {
                name: '我的文件',
                parent: 'bigMenu',
                role: ['user'],
                seq: 0
            },
            isIndex: true
        }
    ];

    //添加项目到路由里面去
    angular.forEach(routeList, function (item) {
        $stateProvider.state(item.name, item);
    })
};
module.exports = md;