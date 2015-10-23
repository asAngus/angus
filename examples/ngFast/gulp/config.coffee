process.env.NODE_ENV ?= 'dev'

module.exports =

  serverPort: 3054

  styles:
    src: ['app/styles/**/*.scss']
    dest: 'build/' + process.env.NODE_ENV + '/css'

  scripts:
    src: 'app/coffee/**/*.coffee'
    dest: 'build/' + process.env.NODE_ENV + '/js'

  coffee:
    src: ['app/coffee/**/*.coffee', 'test/**/*.coffee']
    dest: '.temp'

  views:
    watch: [
      'app/views/**/*.html'
    ]
    src: 'app/views/**/*.html'
    dest: 'app/coffee/templates'

  index:
    src: 'app/index.html'

  images:
    src: 'app/images/**/*'
    dest: 'build/' + process.env.NODE_ENV + '/images'

  dist:
    root: 'build/' + process.env.NODE_ENV

  browserify:
    entries: ['./app/coffee/app.coffee']
    bundleName: 'app.js'

  libs:
    entries: ['./app/coffee/libs.coffee']
    bundleName: 'vendor.js'

  test:
    karma: 'test/karma.conf.coffee'
    protractor: 'test/protractor.conf.coffee'

