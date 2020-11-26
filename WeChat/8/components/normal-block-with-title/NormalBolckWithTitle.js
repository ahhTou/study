// components/normal-block-with-bolck/NormalBolckWithTitle.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    blockData: {
      type: JSON,
      value: {
        id: 0,
        title: "标题",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    touchShake() {
      let name = 'blockData.className'
      if (this.data.blockData.className === '')
        this.setData({
          [name]: 'image-bigger'
        })
      else
        this.setData({
          [name]: ''
        })

    },
  }
})