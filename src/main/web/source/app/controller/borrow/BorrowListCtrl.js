/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=require('../../module/borrow');
md.controller("borrowctrl",borrowctrl);
borrowctrl.$inject=['$scope','$rootScope','$state'];
function borrowctrl($scope,$rootScope,$state){
    console.log($state);
    console.log($rootScope);
    console.log("sdfasd")
};