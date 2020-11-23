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
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {

  },

  lifetimes: {
    attached: function () {
      reqUtils.setWx(wx)

      reqUtils.getCover().then(res => {

        this.setData({
          cover: res.data.data
        })

      })

      reqUtils.getDate().then(res => {
        let date = res.data.data
        let time = new Date(date.date)
        let month = time.getMonth() + 1
        let day = time.getDate()
        let year = time.getFullYear()
        date.timeStr = year + '.' + month + '.' + day
        this.setData({
          date,
        })
      })
    }
  }


})