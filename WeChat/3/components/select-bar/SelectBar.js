// components/select-bar/SelectBar.js
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
    currentSelection: 0,
    selectionData: [{
        id: 0,
        title: '推荐',
      },
      {
        id: 1,
        title: '关注',
      },
      {
        id: 2,
        title: '最新',
      }
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    toSelect(e) {
      let domID = e.currentTarget.dataset.id
      this.setData({
        currentSelection: domID,
      })
    }
  }
})