//index.js
//获取应用实例
const app = getApp()

Page({
  touchShake(e) {
    let domID = e.currentTarget.dataset.id
    let name = 'imgList[' + domID + '].className'
    if (this.data.imgList[domID].className === '') {
      this.setData({
        [name]: 'image-bigger'
      })
    } else {
      this.setData({
        [name]: ''
      })
    }
  },
  data: {
    title: '首页推荐',
    imgList: [{
        id: 0,
        title: "二次元",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      },
      {
        id: 1,
        title: "三次元",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      },
      {
        id: 2,
        title: "四次元",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      }, {
        id: 3,
        title: "五次元",
        className: '',
        url: 'https://s3.ax1x.com/2020/11/20/DQrl9J.jpg'
      }
    ]
  }
})