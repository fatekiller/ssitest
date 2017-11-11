/**
 * Created by liuchenfei on 2017/10/26.
 */
var md = angular.module('adminModule', []);
md.config(adminConfig);
adminConfig.$inject = ['$stateProvider'];
function adminConfig($stateProvider) {
    var routeList = [
        {
            name: 'admin.admin',
            url: "/admin",
            templateUrl: "view/admin/adminFileList.html",
            controller: 'adminFileListCtrl',
            data: {
                name: '管理员',
                parent: 'bigMenu',
                role: ['admin'],
                seq: 0
            },
            isIndex: true
        },
        //{
        //    name:'admin.admin.userList',
        //    url:"/userList",
        //    templateUrl: "view/admin/userList.html",
        //    controller:'userListCtrl',
        //    data:{
        //        name:'用户列表',
        //        parent:'admin.admin',
        //        role:['admin'],
        //        seq:0
        //    },
        //    isIndex:true
        //},
        //{
        //    name:'admin.admin.adminFileList',
        //    url:"/adminFileList",
        //    templateUrl: "view/admin/adminFileList.html",
        //    controller:'adminFileListCtrl',
        //    data:{
        //        name:'文件列表',
        //        parent:'admin.admin',
        //        role:['admin'],
        //        seq:1
        //    }
        //}
    ];

    //添加项目到路由里面去
    angular.forEach(routeList, function (item) {
        $stateProvider.state(item.name, item);
    })
};
module.exports = md;