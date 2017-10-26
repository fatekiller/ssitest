/**
 * Created by liuchenfei on 2017/3/1.
 */
/**
 * Created by liuchenfei on 2017/3/1.
 */
var md=angular.module('testModule',[]);
md.config(testConfig);
testConfig.$inject=['$stateProvider'];
function testConfig($stateProvider) {
    var routeList=[
        {
            name:'admin.test',
            url:"/test",
            templateUrl: "view/common/subMenu.html",
            controller:'subMenuCtrl',
            data:{
                name:'测试',
                parent:'bigMenu',
                role:['admin']
            }
        },{
            name:'admin.test.fileUpload',
            url:"/fileUpload",
            templateUrl: "view/test/fileUpload.html",
            controller:'fileUploadCtrl',
            data:{
                name:'文件上传',
                parent:'admin.test',
                role:['admin']
            }
        },{
            name:'admin.test.weui',
            url:"/weui",
            templateUrl: "view/test/weui.html",
            controller:'weuiCtrl',
            data:{
                name:'微信ui',
                parent:'admin.test',
                role:['admin']
            }
        },{
            name:'admin.test.not_empty',
            url:"/not_empty",
            templateUrl: "view/test/not_empty.html",
            controller:'not_emptyCtrl',
            data:{
                name:'非空directive测试',
                parent:'admin.test',
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
