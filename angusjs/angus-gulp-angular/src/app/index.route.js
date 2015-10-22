(function () {
    'use strict';

    angular
        .module('angusGulpAngular')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('angus', {
                url: '/angus',
                templateUrl: 'app/main/index.html'
            })
            .state('angus.login', {
                url: '/login',
                templateUrl: 'app/main/login/login.html',
                controller: 'LoginController',
                controllerAs: 'login'
            }).state('angus.main', {
                url: "/main",
                templateUrl: 'app/main/main.html',
                controller: 'MainController',
                controllerAs: 'main'
            }).state('angus.item', {
                url: "/item",
                templateUrl: 'app/main/item/item.html',
                controller: 'ItemController',
                controllerAs: 'item'
            }).state('test', {
                url: "/test",
                templateUrl: 'app/main/item/item.html',
                controller: 'ItemController',
                controllerAs: 'item'
            });
        $urlRouterProvider.when("","/test")
        $urlRouterProvider.otherwise('/angus');

    }

})();
