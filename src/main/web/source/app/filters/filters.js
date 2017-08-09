var md=require("../module/app");
md.filter('roleFilter',['$rootScope',function($rootScope){
    return function (items) {
        var filtered=[];
        angular.forEach(items,function(menu){
            angular.forEach(menu.data.role, function (role) {
                if(role==$rootScope.role){
                    filtered.push(menu);
                }
            });
        });
        return filtered;
    }
}]);
