config = require '../config'
gulp = require 'gulp'
jshint = require 'gulp-jshint'

gulp.task 'lint', ->
  gulp.src [config.scripts.src, '!app/coffee/templates.js']
  .pipe jshint()
  .pipe jshint.reporter 'jshint-stylish'