/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=require("../module/app");
md.factory('bookServer',['$resource',function($resource){
    return $resource('api/user/:param1',{},{
        userLogin:{method:"get",params:{param1:'login.do'}}
    });
}]);