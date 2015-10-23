gulp = require 'gulp'
browserSync = require 'browser-sync'

gulp.task 'reload', ->
  browserSync.reload()