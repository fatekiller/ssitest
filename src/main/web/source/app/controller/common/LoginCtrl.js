var md=require('../../module/app');
md.controller('loginCtrl',loginCtrl);
loginCtrl.$inject = ['$scope', '$state', '$rootScope', 'session', 'commonServer'];
function loginCtrl($scope, $state, $rootScope, session, commonServer) {
    $scope.isRegister = 0;

    $scope.submit = function () {
        if ($scope.isRegister) {
            commonServer.post('user', 'register.do', {
                username: $scope.username,
                password: $scope.password
            }, {}, function (value) {
                swal("注册成功")
            });
        } else {
            commonServer.post('user', 'login.do', {
                username: $scope.username,
                password: $scope.password
            }, {}, function (value) {
                goPage(value);
            });
        }
    };

    function goPage(user) {
        $rootScope.role = user.role;
        if (session.get('subMenus') == null || session.get('role') != user.role) {
            session.set('subMenus', JSON.stringify($rootScope.index[user.role].subMenus));
            $rootScope.subMenus = $rootScope.index[user.role].subMenus;
        }
        session.set('role', user.role);
        session.set('userId', user.userId);
        if (user.role == "user") {
            session.set('rootDir', JSON.stringify(user.rootDir));
        }
        //存在二级目录则跳转二级目录，不存在则跳转一级目录
        if ($rootScope.index[user.role].index) {
            $state.go($rootScope.index[user.role].index);
        } else {
            $state.go($rootScope.index[user.role].name);
        }
    }
};