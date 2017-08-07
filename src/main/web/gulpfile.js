var gulp = require('gulp');
var jade = require("gulp-jade");//编译jade为html
var cached=require('gulp-cached');//将文件暂存在内存里面的，可以使用MD5优化内存占用
var browserSync=require('browser-sync').create();//服务器插件
var connect = require('gulp-connect');//一个服务器插件，现在没用了
var seq = require("gulp-sequence");//可以顺序执行gulptask
var del = require("del");//删除文件
var proxyMiddleware=require("http-proxy-middleware");//后端代理过滤，中间件
var prefixer=require('gulp-autoprefixer');//自动添加适应各种浏览器的css头
var less=require('gulp-less');//编译less为css
var plumber=require('gulp-plumber');//使编译出错的时候不会像默认的情况那样自动中断
var watchify=require('gulp-watchify');//加速browseify的打包，使每次修改后只重新打包修改部分，不重新打包所有的文件
var browserify=require('gulp-browserify');//编译cmd规范的代码使得能被浏览器使用
var sourcemaps=require('gulp-sourcemaps');//调试定位到源代码
var buffer=require('gulp-buffer');
var streamify = require('gulp-streamify');

var JADES='source/**/*.jade';
var FOLDER='tmp/';
var ENTRIES='source/app/entries/appMain.js';
var JS='source/**/*.js';
var LIBS='libs/**/*.js';
var CSSMAIN='source/assets/style.less';
var SERVER_PROXY='http://localhost:8080';

//watchify用到的配置信息
var config = {
    watch: true,
    cache: {},
    packageCache: {},
    setup: function (bundle) {
        bundle.transform('bulkify');
    }
};
//把所有需要用到的js用cmd规范打包为一个js，配置在src/enter目录内部
gulp.task("bundle", watchify(function (wf) {
    return gulp.src(ENTRIES)
        .pipe(sourcemaps.init({loadMaps: true}))
        .pipe(plumber())
        .pipe(wf(config))
        .on("error", function (error) {
            console.log(error)
            this.emit('close');
            this.emit('end');
        })
        .pipe(streamify(plumber())) //fixed browserify update too early.
        .pipe(buffer())
        .on('error', function (error) {
            this.emit('end');
        })
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest(FOLDER + "js"));
}));

//创建watch任务去检测html文件,其定义了当html改动之后，去调用一个Gulp的重新编译
gulp.task('watch', function () {
	gulp.watch(JADES, ['compile-jade']);
	gulp.watch(['./source/**/*.js'], ['js']);
});

//代理配置
var proxy = proxyMiddleware("/api", {
    target: SERVER_PROXY,
    changeOrigin: true
});

//删除tmp目录文件
gulp.task("clear", function (cb) {
    del([FOLDER], cb);
});

gulp.task('compile-libs',function () {
    gulp.src(LIBS).pipe(gulp.dest(FOLDER+'libs'));
});

gulp.task('compile-jade',function () {
    return gulp.src(JADES)
        .pipe(cached('debug',{optimizeMemory:true}))
        .pipe(jade({locals:{time:'?v='+new Date().getTime()}}))
        .on('error',function (error) {
            this.emit('end');
        })
        .pipe(gulp.dest(FOLDER));
});

gulp.task('js',function(){
	gulp.src('./source/**/*.js').pipe(connect.reload());
});

gulp.task('compile-style',function () {
    gulp.src(CSSMAIN)
        .pipe(less())
        .pipe(prefixer())
        .pipe(gulp.dest(FOLDER+'style'))
});

gulp.task('connect', ['default'],function () {
    browserSync.init({
        port: 8088,
        ghostMode: false,
        server: './tmp',
        middleware:[proxy]
    });
    gulp.start('watch');
});

//运行Gulp时，默认的Task
gulp.task('default',['compile-jade','compile-libs','compile-style','bundle']);

gulp.task('server',seq('clear','connect'));

