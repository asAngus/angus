(function () {
    'use strict';

    angular
        .module('angusGulpAngular')
        .controller('LoginController', LoginController);
    /** @ngInject */
    function LoginController($scope, $http, $resource, $q, toastr) {
        $scope.master = {firstName: "John", lastName: "Doe"};


        $scope.loginPotin = function () {
            console.log($scope.user.firstName)
            toastr.info("你好", "标题");
        }
        $scope.reset = function () {
            $scope.user.firstName = '';
            $scope.user.lastName = '';
        }

    }
})();