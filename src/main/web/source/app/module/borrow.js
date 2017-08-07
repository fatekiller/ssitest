/**
 * Created by liuchenfei on 2017/3/1.
 */
/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=angular.module('borrowModule',[]);
md.config(borrowConfig);
borrowConfig.$inject=['$stateProvider'];
function borrowConfig($stateProvider) {
    var routeList=[
        {
            name:'borrow',
            url:"/BorrowList",
            templateUrl: "view/borrow/borrowList.html",
            controller:'borrowctrl',
            data:{
                name:'借阅',
                parent:'bigMenu'
            }
        }
    ];

    //添加项目到路由里面去
    angular.forEach(routeList,function (item) {
        $stateProvider.state(item.name,item);
    })
};
module.exports=md;
