ip = process.env.ip

production =
  baseApi: 'http://lingyong.me'

devOnline =
  baseApi: 'http://test.lingyong.me'

devLocal =
  baseApi: "http://#{ip}:3000"

module.exports = switch process.env.NODE_ENV
  when 'dev-online' then devOnline
  when 'production' then production
  else
    devLocal