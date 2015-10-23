gulp = require 'gulp'
karma = require 'gulp-karma'

handleErrors = require '../util/handleErrors'
config = require '../config'

gulp.task 'unit', ->
  gulp.src './thisdoesntexist'
  .pipe karma
    configFile: config.test.karma
    action: 'run'

  .on 'error', handleErrors

gulp.task 'tdd', ->
  gulp.src './adfadf'
  .pipe karma
    configFile: config.test.karma
    action: 'watch'