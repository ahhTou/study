// components/_index/album/album.js
let utils = require('../../../assets/request.js')

Component({
  properties: {

  },

  data: {
    list: [],
    args: {
      skip: 0,
      limit: 6,
    },
    total: 0,
    baseUrl: null,
  },
  methods: {
    getData() {

      if (this.data.args.skip > this.data.total) return

      let data = {
        ...this.data.args
      }

      this.data.args.skip += this.data.args.limit

      utils.getAlbum(data).then(res => {
        let data = res.data.data
        this.setData({
          list: [...this.data.list, ...data.list],
          total: data.total
        })
        wx.hideLoading()
      })
    },
    toPage(e) {
      let index = e.currentTarget.dataset.index
      let album = this.data.list[index]

      let app = getApp()
      app.albumData = album
      wx.navigateTo({
        url: "/pages/album/album"
      })
    }
  },
  lifetimes: {
    attached: function() {
      console.log('> Load Album')

      wx.showLoading()

      this.setData({
        baseUrl: utils.getBaseUrl(),
      })

      utils.setWx(wx)

      this.getData()
    },
  },

})