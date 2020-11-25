let wx

let baseUrl = "http://119.29.12.250:3000/"

// 精选大图
let cover = baseUrl + 'home/cover'
let date = baseUrl + 'home/month'
let hot = baseUrl + 'home/hot'
let category = baseUrl + 'category'
let categoryByID = baseUrl + 'category/'
let album = baseUrl + 'album/'


function getBaseUrl() {
  return baseUrl
}

let req = (url, data = null) => {
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

let getAlbum = (data) => {
  return req(album, data)
}

let getCover = () => {
  return req(cover)
}

let getCategory = () => {
  return req(category)
}

let getCategoryByID = (data) => {
  return req(categoryByID + data.id, data)
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
  getCategory,
  getCategoryByID,
  setWx,
  getBaseUrl,
  getAlbum,
  baseUrl
}