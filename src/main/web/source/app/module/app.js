/**
 * Created by Administrator on 2017/3/6.
 */
var myApp = angular.module("BookApp",
    [
        'ui.router',
        'ngResource',
        'session',
        'borrowModule',
        'testModule',
        'bookModule',
        'ngFileUpload'
    ]);

myApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    var routeList=[
        {
            name:'admin',
            url:"/admin",
            abstract:true,
            templateUrl: "view/common/bigMenu.html",
            controller:'bigMenuCtrl',
        },
        {
            name: "login",
            url: '/login',
            templateUrl: 'view/common/login.html',
            controller: 'loginCtrl',
            data: {
                name: '登陆',
                parentNode: false
            }
        }
    ];
    angular.forEach(routeList,function(item){
        $stateProvider.state(item.name,item);
    });
});
myApp.run(["$rootScope","$state",function($rootScope,$state){
    $rootScope.bigMenus=[];
    var routeList=$state.get();
    angular.forEach(routeList,function(bigMenu){
        //排除默认空路由
        if(bigMenu.name){
            //选取所有一级菜单
            if(bigMenu.data&&bigMenu.data.parent=="bigMenu"){
                $rootScope.bigMenus.push(bigMenu);
                bigMenu.subMenus=[];
                //为一级菜单选取二级菜单
                angular.forEach(routeList, function (subMenu) {
                    if(subMenu.data&&subMenu.data.parent==bigMenu.name){
                        bigMenu.subMenus.push(subMenu);
                    }
                });
            }
        }
    });
    //设置首页
    angular.forEach($rootScope.bigMenus,function(menu){
        if(menu.isIndex){
            //todo: 这个地方要考虑首页的权限问题
            //设置首选二级目录
            $rootScope.subMenus=menu.subMenus;
            //获取首页路由值
            angular.forEach(menu.subMenus, function (route) {
                if(route.isIndex){
                    $rootScope.index=route.name;
                }
            })
        }
    });
}]);
module.exports=myApp;