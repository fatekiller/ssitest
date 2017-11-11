var md=require("../module/app");
md.filter('roleFilter',['session',function(session){
    return function (items) {
        var filtered=[];
        angular.forEach(items,function(menu){
            angular.forEach(menu.data.role, function (role) {
                if(role==session.get('role')){
                    filtered.push(menu);
                }
            });
        });
        return filtered;
    }
}]);
