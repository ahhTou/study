//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    title: '首页推荐',

    // recommend latey
    tabsSelection: "recommend",
    move: '0vw',

    // banner-block 数据
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
    ],

    // 选项卡数据
    selectionData: [{
        id: 0,
        val: 'recommend',
        title: '推荐',
      },
      {
        id: 1,
        val: 'aaga',
        title: '分类',
      },
      {
        id: 2,
        val: 'latey',
        title: '最新',
      }
    ]
  },

  changeShow(val) {
    
    let move = val.detail.id * 100 + 'vw'
    
    this.setData({
      tabsSelection: val.detail.val,
      move
    })
  }
})