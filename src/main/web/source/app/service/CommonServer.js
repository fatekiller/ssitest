var md=require('../module/app');
md.service('commonServer',['bookServer',function(bookServer){
    this.getUserName=function(call){
        bookServer.userLogin({username:'username',password:'password'},{}, function (result) {
            if(result.success){
                call(result);
            }else{
                debugger
            }
        }, function () {
            debugger
        });
    }
}]);
