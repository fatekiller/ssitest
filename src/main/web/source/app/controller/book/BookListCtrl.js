/**
 * Created by liuchenfei on 2017/3/1.
 */
//var md=require('../module/app');
//md.controller("bookctrl",['$scope','$rootScope'], function ($scope,$rootScope) {
//    console.log('book');
//});
var md=require('../../module/book');
md.controller("bookctrl",bookctrl);
bookctrl.$inject=['$scope','$rootScope'];
function bookctrl($scope,$rootScope){
    console.log("sdfasd")
};