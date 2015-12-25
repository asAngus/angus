'use strict';
/**
 * Created by weipeng on 15/12/17.
 */



var productControllers = angular.module('productControllers', []);

productControllers.controller('ProductListCtrl', ['$scope', 'Product',
  function ($scope, Product) {
    $scope.products = Product.query();
    $scope.orderProp = 'prdId';
  }]);

productControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product',
  function ($scope, $routeParams, Product) {
    $scope.product = Product.get({prdId: $routeParams.prdId}, function (phone) {
      $scope.mainImageUrl = Product.prdTyp;
    });

    $scope.setImage = function (imageUrl) {
      $scope.mainImageUrl = imageUrl;
    };
  }]);
