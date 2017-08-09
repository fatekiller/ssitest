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
            name:'admin.borrow',
            url:"/Borrow",
            templateUrl: "view/common/subMenu.html",
            controller:'subMenuCtrl',
            data:{
                name:'借阅',
                parent:'bigMenu',
                role:['admin']
            }
        },{
            name:'admin.borrow.borrowList',
            url:"/BorrowList",
            templateUrl: "view/borrow/borrowList.html",
            controller:'borrowListCtrl',
            data:{
                name:'借阅列表',
                parent:'admin.borrow',
                role:['admin']
            }
        },{
            name:'admin.borrow.borrowInfo',
            url:"/BorrowList",
            templateUrl: "view/borrow/borrowInfo.html",
            controller:'borrowInfoCtrl',
            data:{
                name:'借阅信息',
                parent:'admin.borrow',
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
