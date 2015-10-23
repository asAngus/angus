config = require '../config'
gulp = require 'gulp'
del = require 'del'


gulp.task 'clean', (cb)->
  del [config.dist.root], cb