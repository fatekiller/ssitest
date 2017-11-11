/**
 * Created by liuchenfei on 2017/8/7.
 */
var md=require('../../module/app');
md.controller('subMenuCtrl',subMenuCtrl);
subMenuCtrl.$inject=['$scope','$state','$rootScope'];
function subMenuCtrl($scope,$state,$rootScope){
    $scope.go=$state.go;
};
