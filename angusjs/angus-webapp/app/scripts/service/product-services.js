'use strict';

/* Services */

var productServices = angular.module('productServices', ['ngResource']);

productServices.factory('Product', ['$resource',
  function ($resource) {
    return $resource('http://localhost:8080/cmmtpro/find', {}, {
      query: {method: 'GET', isArray: true}
    });
  }]);
