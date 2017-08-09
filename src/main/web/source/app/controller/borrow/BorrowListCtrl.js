/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=require('../../module/borrow');
md.controller("borrowListCtrl",borrowListCtrl);
borrowListCtrl.$inject=['$scope','$rootScope','$state'];
function borrowListCtrl($scope,$rootScope,$state){
    console.log($state);
    console.log($rootScope);
    console.log("sdfasd")
};