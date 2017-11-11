/**
 * Created by liuchenfei on 2017/10/26.
 */
var md=require('../module/app');
md.factory('userServer',['$resource',function($resource){
    return $resource('/api/user/:p1',{},{
        login:{method: 'post', params: {p1:'login.do'}, isArray: false},
        register:{method: 'post', params: {p1:'register.do'}, isArray: false}
    });
}])
