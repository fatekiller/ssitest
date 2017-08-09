/**
 * Created by liuchenfei on 2017/8/7.
 */
var md=require('../../module/app');
md.controller('subMenuCtrl',subMenuCtrl);
subMenuCtrl.$inject=['$scope','$state'];
function subMenuCtrl($scope,$state){
    $scope.go=$state.go;
};
