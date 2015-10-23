changed = require 'gulp-changed'
gulp = require 'gulp'
gulpif = require 'gulp-if'
imagemin = require 'gulp-imagemin'


config = require '../config'


gulp.task 'images', ->
  dest = config.images.dest
  gulp.src config.images.src
  .pipe changed dest
  .pipe gulpif !global.isDebug, imagemin()
  .pipe gulp.dest dest