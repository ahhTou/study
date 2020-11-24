// components/select-bar/SelectBar.js
Component({

  properties: {
    selectionData: {
      type: JSON,
      value: [{
        id: 0,
        val: 'unset',
        title: 'unset',
      }]
    }
  },

  data: {
    currentSelection: 0,
  },

  methods: {
    toSelect(e) {
      let currentSelection = e.currentTarget.dataset.id
      let id = currentSelection

      this.setData({
        currentSelection,
      })

      let val = e.currentTarget.dataset.val
      this.triggerEvent('ToChangeShow', {
        val,
        id
      })
    }
  }
})