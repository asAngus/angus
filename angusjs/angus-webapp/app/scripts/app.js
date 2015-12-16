'use strict';

/**
 * @ngdoc overview
 * @name hisun
 * @description
 * # hisun
 *
 * Main module of the application.
 */
angular
  .module('hisun', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'phonecatControllers',
    'phonecatServices',
    'phonecatFilters',
    'phonecatAnimations',

  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/main', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      }).
      when('/phones', {
        templateUrl: 'views/phone-list.html',
        controller: 'PhoneListCtrl'
      }).
      when('/phones/:phoneId', {
        templateUrl: 'views/phone-detail.html',
        controller: 'PhoneDetailCtrl'
      })
      .otherwise({
        redirectTo: '/main'
      });
  });
