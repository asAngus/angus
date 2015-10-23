config = require '../config'
gulp = require 'gulp'

gulp.task 'watch', ['server'], ->
  gulp.watch config.styles.src, ['styles']
  gulp.watch config.images.src, ['images', 'reload']
  gulp.watch config.views.watch, ['views']
  gulp.watch config.index.src, ['index']
  gulp.watch config.libs.entries, ['vendor.js']