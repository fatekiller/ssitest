var md=require('../../module/app');
md.controller('bigMenuCtrl',bigMenuCtrl);
bigMenuCtrl.$inject=['$scope','$state','$rootScope'];
function bigMenuCtrl($scope,$state,$rootScope){
    $scope.jump= function (item) {
        //设置当前副菜单并，跳转到当前目录的主页
        $rootScope.subMenus=item.subMenus;
        var target="";
        angular.forEach(item.subMenus,function(subMenu){
            if(subMenu.isIndex){
                target=subMenu.name;
            }
        });
        if(target!=""){
            $state.go(item.subMenus[0].name);
        }else{
            $state.go(target);
        }

    }
};