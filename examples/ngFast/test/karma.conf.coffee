module.exports = (config)->
  config.set
    basePath: '../'
    frameworks: ['jasmine', 'browserify']
    preprocessors:
      'app/coffee/**/*.coffee': ['browserify']

    browsers: ['Chrome']
    reporters: ['dots', 'growl']

    autoWatch: true

    plugins: [
      'karma-jasmine'
      'karma-bro'
      'karma-chrome-launcher'
      'karma-firefox-launcher'
      'karma-growl-reporter'
    ]

    proxies:
      '/': 'http://localhost:9876/'

    urlRoot: '/__karma__/'

    files: [
      'app/coffee/app.coffee'

      'node_modules/angular-mocks/angular-mocks.js'

      '.temp/test/unit/**/*.js'
    ]

    browserify:
      transform: [
        'coffeeify'
        'brfs'
      ]
      extensions: ['.coffee', '.js']
