/**
 * Created by Administrator on 2017/3/6.
 */
var myApp = angular.module("BookApp",
    [
        'ui.router',
        'ngResource',

        'bookModule',
        'borrowModule',
        'ngFileUpload'
    ]);

myApp.config(function ($stateProvider, $urlRouterProvider) {
    //$urlRouterProvider.when('','book')

});
myApp.run(["$rootScope","$state",function($rootScope,$state){
    $rootScope.menu=[];
    $rootScope.subMenus={}
    var routeList=$state.get();
    angular.forEach(routeList,function(item){
        if(item.name){
            if(item.data.parent=="bigMenu"){
                $rootScope.menu.push(item);
            }else{
                if($rootScope.subMenus[item.data.parent]){
                    $rootScope.subMenus[item.data.parent].push(item);
                }else{
                    $rootScope.subMenus[item.data.parent]=[];
                    $rootScope.subMenus[item.data.parent].push(item);
                }
            }

        }
    })
}]);
module.exports=myApp;