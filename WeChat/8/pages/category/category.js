// pages/category/category.js
let utils = require('../../assets/request.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: '未知',
    type: 'hot',
    baseUrl: utils.baseUrl,
    total: 0,
    list: [],
    args: {
      id: null,
      skip: 0,
      limit: 15,
      order: 'hot',
    }
  },
  change(e) {s
    this.setData({
      type: e.currentTarget.dataset.type,
      list: [],
      total: 0,
      ['args.order']: 'new',
      ['args.skip']: 0,
    })
    wx.showLoading()
    this.getData()


  },
  getData() {

    if (this.data.args.skip > this.data.total) return

    let data = { ...this.data.args
    }

    this.data.args.skip += this.data.args.limit

    utils.getCategoryByID(data).then(res => {
      this.setData({
        list: [...this.data.list, ...res.data.data.list],
        total: res.data.data.total
      })
      wx.hideLoading()
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading()

    utils.setWx(wx)
    this.data.args.id = options.id
    this.setData({
      ...options
    })

    this.getData()
  },
})