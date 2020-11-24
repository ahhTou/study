// components/_index/index-recommend-page/IndexRecommendPage.js
let reqUtils = require('../../../assets/request.js')

Component({
  /**
   * 组件的属性列表
   */
  properties: {

    // banner-block
    imgList: {
      type: JSON,
      value: [{
        id: 0,
        title: "未定义",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      }]
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    baseUrl: "http://119.29.12.250:3000/",
    cover: null,
    date: {
      title: '正在载入...',
      timeStr: '00 / 00 '
    },
    hot: [],
    total: 0,
    isLoading: false,
    hotArgs: {
      skip: 0,
      limit: 12,
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getHot() {
      if (this.data.total !== 0 && this.data.hotArgs.skip >= this.data.total) return
      this.data.isLoading = true

      this.data.hotArgs.skip += this.data.total === 0 ? 9 : this.data.hotArgs.limit

      reqUtils.getHot(this.data.hotArgs).then(res => {
        let need = res.data.data
        this.setData({
          hot: [...this.data.hot, ...need.list],
          total: need.total
        })
      })
    
    },
    getCover() {
      reqUtils.getCover().then(res => {
        this.setData({
          cover: res.data.data
        })
      })
    },
    getDate() {
      reqUtils.getDate().then(res => {
        let date = res.data.data
        let time = new Date(date.date)
        date.timeStr = time.getFullYear() + '.' + (time.getMonth() + 1) + '.' + time.getDate()
        this.setData({
          date,
        })
      })
    }
  },

  lifetimes: {
    attached: function() {

      reqUtils.setWx(wx)

      this.getCover()
      this.getDate()
      this.getHot()

    }
  }


})