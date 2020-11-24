let wx

let baseUrl = "http://119.29.12.250:3000/"

// 精选大图
let cover = baseUrl + 'home/cover'
let date = baseUrl + 'home/month'
let hot = baseUrl + 'home/hot'
let category = baseUrl + 'category'

function getBaseUrl() {
  return baseUrl
}

function req(url, data = null) {
  return new Promise((reslove, reject) => {
    wx.request({
      url,
      data,
      success: res => {
        reslove(res)
      },
      fail: err => {
        reject(err)
      }
    })
  })
}

let getCover = () => {
  return req(cover)
}

let getGategory = () => {
  return req(category)
}

let getDate = () => {
  return req(date)
}

let getHot = (data) => {
  return req(hot, data)
}

let setWx = (root) => {
  wx = root
}

module.exports = {
  getCover,
  getDate,
  getHot,
  getGategory,
  setWx,
  getBaseUrl
}