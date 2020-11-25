// pages/category/category.js
let utils = require('../../assets/request.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: utils.baseUrl,
    title: '未知',
    type: '未知',
    list: [],
    total: 0,
    args: {
      id: null,
      skip: 0,
      limit: 15,
      order: 'hot',
    }
  },

  getData() {

    if (this.data.args.skip > this.data.total) return

    let data = { ...this.data.args
    }

    this.data.args.skip += this.data.args.limit

    utils.getCategoryByID(data).then(res => {
      this.setData({
        list: [...this.data.list, ...res.data.data.list],
        total: res.data.data.list
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    utils.setWx(wx)
    this.data.args.id = options.id
    this.setData({
      ...options
    })

    this.getData()
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