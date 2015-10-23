gulp = require 'gulp'
gulpif = require 'gulp-if'
gutil = require 'gulp-util'
source = require 'vinyl-source-stream'
streamify = require 'gulp-streamify'
watchify = require 'watchify'
browserify = require 'browserify'
uglify = require 'gulp-uglify'
browserSync = require 'browser-sync'
coffeeify = require 'caching-coffeeify'
sourcemap = require 'gulp-sourcemaps'
buffer = require 'vinyl-buffer'
envify = require 'envify/custom'
versionTag = require 'gulp-version-tag'
filter = require 'gulp-filter'
clearDeadCode = require 'unreachable-branch-transform'
intreq = require 'intreq-stream'
duration = require 'gulp-duration'
plumber = require 'gulp-plumber'


config = require '../config'
libs = require '../../app/coffee/libs'
handleErrors = require '../util/handleErrors'


buildScript = (file)->
  {isDebug, env, ip} = global

  b = browserify
    entries: config.browserify.entries
    extensions: ['.coffee', '.js']
    debug: isDebug
    cache: {}
    packageCache: {}
    fullPaths: true
  , watchify.args

  libs.forEach (lib)->
    b.external lib

  b.external 'templates.js'

  rebundler = ()->
    browserifyTimer = duration 'Browserify app bundler time'

    b.bundle()
    .once 'data', ()->
      gutil.log 'Rebundle...'
    .on 'error', handleErrors
    .pipe gulpif !isDebug, intreq()
    .pipe source file
    .pipe plumber
      errorHandler: handleErrors
    .pipe gulpif isDebug, buffer()
    .pipe gulpif isDebug, sourcemap.init
      loadMaps: true
    .pipe gulpif isDebug, sourcemap.write('./')
    .pipe gulpif !isDebug, streamify uglify()
    .pipe gulpif !isDebug, versionTag __dirname, '../../package.json',
      reuse: true
      autoTagVersion: global.autoTagVersion
    .pipe browserifyTimer
    .pipe plumber.stop()
    .pipe gulp.dest config.scripts.dest
    .pipe filter '**/*.js'
    .pipe browserSync.reload
      stream: true


  b.transform coffeeify
  .transform envify
    NODE_ENV: env
    ip: ip
  .transform clearDeadCode
  .transform 'brfs'


  if isDebug
    b = watchify b
    b.on 'update', rebundler
  rebundler()

gulp.task 'app.js', ->
  buildScript 'app.js'

