OnRun = ($rootScope, AppSettings)->
  $rootScope.$on '$stateChangeSuccess', (event, toState)->
    $rootScope.pageTitle = ''

    if toState.title
      $rootScope.pageTitle += toState.title
      $rootScope.pageTitle += ' · '

      $rootScope.pageTitle += AppSettings.appTitle

OnRun.$inject= ['$rootScope','AppSettings']


module.exports = OnRun
