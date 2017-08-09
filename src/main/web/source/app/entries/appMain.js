/**
 * Created by liuchenfei on 2017/3/1.
 */
var bulk = require('bulk-require');
var sections = bulk(__dirname, [
    '../module/**/*.js',
    '../controller/**/*.js',
    '../server/**/*.js',
    '../filters/**/*.js'
]);
module.exports={};
