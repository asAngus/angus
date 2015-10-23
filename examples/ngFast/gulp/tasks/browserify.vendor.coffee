gulp = require 'gulp'
gulpif = require 'gulp-if'
gutil = require 'gulp-util'
source = require 'vinyl-source-stream'
streamify = require 'gulp-streamify'
browserify = require 'browserify'
uglify = require 'gulp-uglify'
browserSync = require 'browser-sync'
versionTag = require 'gulp-version-tag'
duration = require 'gulp-duration'
watchify = require 'watchify'
coffeeify = require 'caching-coffeeify'


config = require '../config'
libs = require '../../app/coffee/libs'
handleErrors = require '../util/handleErrors'


gulp.task 'vendor.js', ->
  {isDebug, ip} = global


  b = browserify()

  libs.forEach (lib)->
    b.require lib

  b.transform coffeeify


  browserifyTimer = duration 'Browserify bundler vendor time'


  b.bundle()
  .on 'error', handleErrors
  .pipe source config.libs.bundleName
  .pipe gulpif !isDebug, streamify uglify()
  .pipe gulpif !isDebug, versionTag __dirname, '../../package.json',
    reuse: true
    autoTagVersion: global.autoTagVersion
  .pipe browserifyTimer
  .pipe gulp.dest config.scripts.dest
  .pipe browserSync.reload
    stream: true







