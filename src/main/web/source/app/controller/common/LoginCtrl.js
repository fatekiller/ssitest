var md=require('../../module/app');
md.controller('loginCtrl',loginCtrl);
loginCtrl.$inject=['$scope','$state','$rootScope','session'];
function loginCtrl($scope,$state,$rootScope,session){
    $scope.goAdmin=function(){
        session.set('role',"admin");
        $state.go($rootScope.index);
    };
    $scope.goUser=function(){
        session.set('role',"user");
        $state.go($rootScope.index);
    };
};