// pages/album/album.js
let utils = require("../../assets/request.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: null,
    data: {},
    total: 0,
    list: [],
    args: {
      id: null,
      skip: 0,
      limit: 6,
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading()

    let data = getApp().albumData
    this.setData({
      data,
      baseUrl: utils.getBaseUrl()
    })
    this.data.args.id = data.id

    this.getData()
  },

  getData() {

    if (this.data.args.skip > this.data.total) return

    let data = {
      ...this.data.args
    }

    this.data.args.skip += this.data.args.limit

    utils.getAlbumByID(data).then(res => {
      this.setData({
        list: [...this.data.list, ...res.data.data.list],
        total: res.data.data.list
      })

      wx.hideLoading()
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})