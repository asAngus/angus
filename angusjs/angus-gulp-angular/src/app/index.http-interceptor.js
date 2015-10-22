/**
 *  http 拦截
 */

(function () {
    'use strict';

    angular
        .module('httpInterceptor')
        .config(httpInterceptor);

    /** @ngInject */
    function httpInterceptor($httpProvider,$rootScope, $q, $location, config) {
        // 增加http拦截器
        $httpProvider.interceptors.push(httpInterceptor);

        return {

            // 拦截 request
            request: function (req) {

                // 全部api请求加上token
                if (req.url.indexOf(config.baseUrl) > -1) {
                    req.headers.Authorization = 'Basic ' + window.localStorage.token;
                }

                return req;
            },

            // 拦截 response
            response: function (res) {

                // 当是请求接口时
                if (res.config.url.indexOf(config.baseUrl) > -1) {

                    // api数据正确
                    if (res.data && res.data.data) {
                        if (res.data.code === 1) {
                            return res.data.data;
                        } else if (res.data.data.msg) {
                            return $q.reject(res.data.data.msg);
                        }
                        return res.data;
                    } else {
                        console.log('invalid response format');
                        return res;
                    }
                }

                if (res.config.method === 'POST') {

                    console.log('calue');
                }

                // alert(3);
                return res;
            },

            responseError: function (rejection) {

                return $q.reject(rejection);
            }

        };
    }
})();
