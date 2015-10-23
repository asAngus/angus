gulp = require 'gulp'
coffee = require 'gulp-coffee'
plumber = require 'gulp-plumber'
changed = require 'gulp-changed'
gutil = require 'gulp-util'

config = require '../config'

gulp.task 'coffee', ->
  gulp.src config.coffee.src
  .pipe plumber()
  .pipe changed config.coffee.dest,
    extension: 'js'
  .pipe coffee(coffee({bare: true}).on('error', gutil.log))
  .pipe gulp.dest config.coffee.dest
