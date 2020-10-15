<template>
  <div id="root-for-search-wrapper">
    <br>
    <div>
      图片搜索状态
      <status-tab3/>
    </div>
    <div>
      <h5>已经到的搜索内容</h5>
      <div id="core-content">
        <p v-for="(li, index) in coreContent" :key="index">{{ li }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import UtilsFactory from 'assets/factory/UtilsFactory'
import StatusTab3 from 'components/tab/StatusTab3'

let axios = UtilsFactory.getAxios()

export default {
  name: 'RootForSearch',
  components: {StatusTab3},
  data: function () {
    return {
      isSearchOn: false,
      coreContent: [],
    }
  },
  mounted() {
    axios.cPost('/search/core/isActive').then(res => {
      if (res.data) {
        this.isSearchOn = res.data
      }
    })
    axios.cPost('/search/core/getContent').then(res => {
      if (res.data) {
        this.coreContent = res.data
      }
    })
  }
}
</script>

<style lang="scss" scoped>
#root-for-search-wrapper {
  #core-content {
    background: rgba(76, 73, 73, .8);
    padding: 20px;
    border-radius: 10px;
  }
}
</style>
