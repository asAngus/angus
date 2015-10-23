gulp = require 'gulp'
runSequence = require 'run-sequence'


gulp.task 'build', ['clean'], (cb)->
  cb = cb || ()->

  global.isDebug = false
  if process.env.NODE_ENV == 'production'
    global.env = 'production'
  else if process.env.NODE_ENV == 'dev-online'
    global.env = 'dev-online'
  runSequence 'styles', 'images', 'index', 'views', 'vendor.js', 'app.js', cb