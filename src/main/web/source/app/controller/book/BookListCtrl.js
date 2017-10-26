var md=require('../../module/book');
md.controller("bookListCtrl",bookListCtrl);
//var wangEditor=require('../../../../libs/wangEditor/release/wangEditor.min.js');
bookListCtrl.$inject=['$scope','$rootScope','$sce','commonServer'];
function bookListCtrl($scope,$rootScope,$sce,commonServer){
    var editor = new wangEditor('#editor');
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();


    //$scope.html="";
    $scope.copy= function () {
        $scope.html=editor.txt.html();
        $scope.safeHtml=$sce.trustAsHtml($scope.html);
    }
    commonServer.getUserName(function(result){
       debugger
    });
};