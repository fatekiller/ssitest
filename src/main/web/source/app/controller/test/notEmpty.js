var md = require("../../module/test");
md.controller('not_emptyCtrl', not_emptyCtrl);
not_emptyCtrl.$inject = ["$scope","$state"];
function not_emptyCtrl($scope,$state) {
    $scope.a='';
    $scope.b='';
    $scope.c='';
    $scope.d='';

    $scope.check;

    $scope.submit= function () {
        var a=$scope.check();
        debugger
        if(a){
            debugger
        }else{
            debugger
        }
    }
};
