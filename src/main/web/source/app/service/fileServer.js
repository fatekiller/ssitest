/**
 * Created by liuchenfei on 2017/11/9.
 */
var md = require('../module/app');
md.factory('fileServer', ['$resource', function ($resource) {
    return $resource('/api/file/:p1', {}, {
        getFiles: {method: 'post', params: {p1: 'getFilesAndDirectoriesByDirectoryId.do'}, isArray: false},
        addDir: {method: 'post', params: {p1: 'addDir.do'}, isArray: false}
    });
}]);