var md=require('../../module/app');
md.controller('loginCtrl',loginCtrl);
loginCtrl.$inject=['$scope','$state','$rootScope'];
function loginCtrl($scope,$state,$rootScope){
    $scope.goAdmin=function(){
        debugger
        $rootScope.role="admin";
        $state.go($rootScope.index);
    };
    $scope.goUser=function(){
        debugger
        $rootScope.role="user";
        $state.go($rootScope.index);
    };
};