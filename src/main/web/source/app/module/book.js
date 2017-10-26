/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=angular.module('bookModule',[]);
md.config(bookConfig);
bookConfig.$inject=['$stateProvider'];
function bookConfig($stateProvider) {
    var routeList=[
        {
            name:'admin.book',
            url:"/BookList",
            abstract:true,
            templateUrl: "view/common/subMenu.html",
            controller:'subMenuCtrl',
            isIndex:true,
            data:{
                name:'书籍',
                parent:'bigMenu',
                role:['user','admin']
            }
        },
        {
            name:'admin.book.bookInfo',
            url:"/BookInfo",
            templateUrl: "view/book/bookInfo.html",
            controller:'bookInfoCtrl',
            isIndex:true,
            data:{
                name:'书籍信息',
                parent:'admin.book',
                role:['user','admin']
            }
        },
        {
            name:'admin.book.bookList',
            url:"/BookList",
            templateUrl: "view/book/BookList.html",
            controller:'bookListCtrl',
            data:{
                name:'书籍列表',
                parent:'admin.book',
                role:['admin']
            }
        }
    ];

    //添加项目到路由里面去
    angular.forEach(routeList,function (item) {
        $stateProvider.state(item.name,item);
    })
};
module.exports=md;
