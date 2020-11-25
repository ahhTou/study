// components/_index/album/album.js
let utils = require('../../../assets/request.js')

Component({
  properties: {

  },

  data: {
    list: [],
    args: {
      skip: 0,
      limit: 12,
    },
    total: 0,
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
        console.log(this.data)
      })
    },

  },
  lifetimes: {
    attached: function() {
      utils.setWx(wx)

      this.getData()
    },
  },

})