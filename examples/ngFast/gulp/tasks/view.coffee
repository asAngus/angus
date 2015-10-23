gulp = require 'gulp'
templateCache = require 'gulp-angular-templatecache'
htmlmin = require 'gulp-htmlmin'
gulpif = require 'gulp-if'
htmlreplace = require 'gulp-html-replace'
duration = require 'gulp-duration'
source = require 'vinyl-source-stream'
browserify = require 'browserify'
transform = require 'vinyl-transform'
browserSync = require 'browser-sync'
rename = require 'gulp-rename'
vinylPaths = require 'vinyl-paths'
del = require 'del'

config = require '../config'


gulp.task 'views', (cb)->
  {isDebug} = global

  vp = vinylPaths()

  browserified = transform (filename)->
    b = browserify()
    b.require 'templates.js'
    b.bundle()

  name = 'templates.js'

  if not isDebug
    name = "templates_v#{global.versionTag}.js"


  gulp.src config.views.src
  .pipe gulpif isDebug, htmlmin
    removeComments: true
    collapseWhitespace: true
  .pipe templateCache name,
    standalone: true
  .pipe gulp.dest './.temp/templates'
  .pipe vp
  .pipe browserified
  .pipe gulp.dest config.scripts.dest
  .pipe browserSync.reload
    stream: true
  .on 'end', ->
    del vp.paths


gulp.task 'index', ->
  {isDebug, versionTag} = global
  gulp.src 'app/index.html'
  .pipe gulpif !isDebug, htmlreplace
    'css': "/css/main_v#{versionTag}.css"
    'vendor': "/js/vendor_v#{versionTag}.js"
    'app': "/js/app_v#{versionTag}.js"
    'templates': "/js/templates_v#{versionTag}.js"
  .pipe gulpif !isDebug, htmlmin
    removeComments: true
    collapseWhitespace: true
  .pipe gulp.dest config.dist.root
  .pipe browserSync.reload
    stream: true
