var md=require('../module/app');
md.directive('notEmpty', function () {
    return{
        restrict:"A",
        scope:{
            check:'=check'
        },
        link:function(scope,element){
            var inputList=element[0].getElementsByTagName('input');
            var inputListArr=Array.prototype.slice.call(inputList,0);
            inputListArr.filter(function (item) {
                return item.hasAttribute('required');
            });
            scope.check=function(){
                var result=true;
                angular.forEach(inputListArr,function(item){
                    if(!item.value){
                        item.style.border='solid 1px red';
                        result = false;
                    }
                });
                return result;
            }
        }
    }
});
