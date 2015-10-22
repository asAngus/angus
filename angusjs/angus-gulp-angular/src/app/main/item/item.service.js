(function () {
    'use strict';

    angular.module("angusGulpAngular").factory("Item", ItemFactory);

    /** @ngInject */
    function ItemFactory ($resource) {
        return $resource('http://127.0.0.1:8080/items/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    };


})();