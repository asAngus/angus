require 'angular'

# angular modules
require 'angular-ui-router'
require 'templates.js'
require './controllers'
require './services'
require './directives'

angular.element(document).ready ->
  requires = [
    'ui.router'
    'templates'
    'app.controllers'
    'app.services'
    'app.directives'
  ]


  angular.module 'app', requires
  .constant 'AppSettings', require './constants'
  .config require './routes'
  .run require './on_run'


  angular.bootstrap document, ['app']
