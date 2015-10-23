config = require '../config'
browserSync = require 'browser-sync'
gulp = require 'gulp'

gulp.task 'browserSync', ->
  browserSync
    proxy: 'localhost:' + config.serverPort
    open: "external"
    logConnections: true
    ghostMode: false
    minify: false