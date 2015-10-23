servicesModule = require './index'


ExampleService = ($q, $http, AppSettings)->
  service = {}
  service.get = ->
    deferred = $q.defer()

    $http.get AppSettings.apiUrl + '/getinfo'
    .success (data)->
      deferred.resolve data
    .error (err, status)->
      deferred.reject err, status
    return deferred.promise

  return service

ExampleService.$inject = ['$q', '$http', 'AppSettings']

servicesModule.service 'ExampleService', ExampleService