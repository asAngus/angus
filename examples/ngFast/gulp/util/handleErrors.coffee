notify = require 'gulp-notify'

module.exports = (error)->
  if global.isDebug
    args = Array.prototype.slice.call arguments

    notify.onError
      title:'Compile Error'
      messages:'<%= error.message %>'
    .apply @, args

    @emit 'end'
  else
    console.log error
    process.exist 1