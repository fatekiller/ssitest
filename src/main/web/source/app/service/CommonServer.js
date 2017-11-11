/**
 * Created by liuchenfei on 2017/11/9.
 */
var md = require('../module/app');
md.factory('commonServer', ['$resource', function ($resource) {
    var server = $resource('/api/:p1/:p2', {}, {
        post: {method: 'post', params: {}, isArray: false},
        get: {method: 'get', params: {}, isArray: false}
    });
    var _this = {};
    _this.post = function (p1, p2, formP, entity, call) {
        formP.p1 = p1;
        formP.p2 = p2;
        server.post(formP, entity, function (result) {
            if (result.success) {
                call(result.value)
            } else {
                swal(result.error || "null");
            }
        }, function () {
            swal("服务器错误");
        })
    },
        _this.get = function (p1, p2, formP, entity, call) {
            formP.p1 = p1;
            formP.p2 = p2;
            server.get(formP, entity, function (result) {
                if (result.success) {
                    call(result.value)
                } else {
                    swal(result.error || "null");
                }
            }, function () {
                swal("服务器错误");
            })
        }
    return _this;
}]);