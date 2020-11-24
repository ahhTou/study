// components/_index/index-category-page/IndexCategoryPage.js
let reqUtils = require('../../../assets/request')
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    baseUrl: "http://119.29.12.250:3000/",

    category: [{
      '_id': '0',
      ename: 'Loading',
      rname: '加载中',
      cover: '5f4f61d7e7bce761dca38156.jpg'
    }]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getCategory() {
      reqUtils.getGategory().then(res => {
        this.setData({
          category: res.data.data
        })
      })
    }
  },

  lifetimes: {
    attached: function () {
      reqUtils.setWx(wx)

      this.getCategory()
    },
  },

})