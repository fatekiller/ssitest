/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=angular.module('bookModule',[]);
md.config(bookConfig);
bookConfig.$inject=['$stateProvider'];
function bookConfig($stateProvider) {
    var routeList=[
        {
            name:'book',
            url:"/BookList",
            templateUrl: "view/book/BookList.html",
            //controller:'bookctrl',
            data:{
                name:'书籍',
                parent:'bigMenu'
            }
        },
        {
            name:'book.bookinfo',
            url:"/BookInfo",
            templateUrl: "view/book/BookInfo.html",
            controller:'bookInfoCtrl',
            data:{
                name:'书籍信息',
                parent:'book'
            }
        }
    ];

    //添加项目到路由里面去
    angular.forEach(routeList,function (item) {
        $stateProvider.state(item.name,item);
    })
};
module.exports=md;
