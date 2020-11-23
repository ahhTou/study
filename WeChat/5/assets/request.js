let wx

let baseUrl = "http://119.29.12.250:3000/"

// 精选大图
let cover = baseUrl + 'home/cover'
let date = baseUrl +　'home/month'

function req(url) {
  return new Promise((reslove, reject) => {
    wx.request({
      url,
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


let getDate = () => {
  return req(date)
}

let setWx = (root) => {
  wx = root
}

module.exports = {
  getCover,
  getDate,
  setWx
}