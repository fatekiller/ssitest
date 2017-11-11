/**
 * Created by Administrator on 2017/3/6.
 */
var myApp = angular.module("NetDiskApp",
    [
        'ui.router',
        'ngResource',
        'session',
        'ngFileUpload',
        "oitozero.ngSweetAlert",

        'adminModule',
        'userModule'
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
myApp.run(["$rootScope", "$state", "session", function ($rootScope, $state, session) {
    $rootScope.bigMenus=[];
    var routeList=$state.get();
    $rootScope.$on('$stateChangeStart', function (ev, toState, toStateParams) {

    });
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
    $rootScope.index = {};
    angular.forEach($rootScope.bigMenus,function(menu){
        angular.forEach(menu.subMenus, function (route) {
            if (route.isIndex) {
                //设置每个一级目录的首选二级目录
                menu.index = route.name;
            }
        });
    });
    angular.forEach($rootScope.bigMenus, function (menu) {
        if(menu.isIndex){
            //设置当前二级目录列表
            $rootScope.subMenus=menu.subMenus;
            //设置当前用户的二级目录列表
            $rootScope.index[menu.data.role] = menu;
        }
    });
    if (session.get('subMenus') != null) {
        $rootScope.subMenus = JSON.parse(session.get('subMenus'));
    }

    function sortMenus(menus) {
        for (var i = 0; i < menus.length; i++) {
            var min = i;
            for (var j = i + 1; j < menus.length; j++) {
                if (menus[j].data.seq < menus[min].data.seq) {
                    min = j;
                }
            }
            if (min != i) {
                var temp = menus[min];
                menus[min] = menus[i];
                menus[i] = temp;
            }
        }
    }

    sortMenus($rootScope.bigMenus);
    angular.forEach($rootScope.bigMenus, function (menu) {
        //存在二级目录则对二级目录进行排序
        if (menu.subMenus) {
            sortMenus(menu.subMenus);
        }
    });
}]);
module.exports=myApp;