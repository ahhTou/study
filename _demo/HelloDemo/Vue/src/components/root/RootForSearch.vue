<template>
  <div id="root-for-search-wrapper">
    <br>
    <div id="status">
      <div id="show-status">
        <status-tab3
          :fun1="searchToStart"
          :fun2="searchToShutDown"
          :fun3="searchToStart"
          size="100"
          :status="status"/>
      </div>
      <div id="describe-content">
        <div id="status-title">图片搜索状态</div>
        <div id="status-desc">{{ statusText[status] }}</div>
      </div>
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
      coreContent: [],
      status: 1,
      statusText: {
        1: '出现异常',
        2: '运行中',
        3: '未运行'
      }
    }
  },
  methods: {
    searchToStart() {
      axios.cPost('/search/core/start').then(res => {
        if (res.data === true) {
          this.status = 2
        }
      })
    },
    searchToShutDown() {
      axios.cPost('/search/core/shutDown').then(res => {
        if (res.data === true) {
          this.status = 3
        }
      })
    }
  },
  mounted() {
    axios.cPost('/search/core/isActive').then(res => {
      if (res.data === true) {
        console.log('123')
        this.status = 2
      }
      if (res.data === false) {
        console.log('123')
        this.status = 3
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


  @mixin aBlock() {
    background: rgba(76, 73, 73, .8);
    border-radius: 10px;
    box-sizing: border-box;
    padding: 20px;
    box-shadow: $shadow-mini;
  }
  @mixin middle-size() {
    width: 320px;
    height: 140px;
  }

  #status {
    position: relative;
    z-index: 2;
    @include middle-size;
    @include aBlock;
    display: inline-block;

    &::after {
      @include after();
      border-radius: 10px;
      background: url("~assets/image/sticker/miku-fight.webp") no-repeat;
      background-position: bottom right;
      z-index: 1;
    }

    #show-status {
      float: left;
      position: relative;
      z-index: 2;
    }

    #describe-content {
      position: relative;
      z-index: 2;
      width: 150px;
      float: left;
      @include flex;
      flex-direction: column;

      #status-desc {
        font-weight: bolder;
        font-size: 30px;
      }

      #status-title {
        margin: 10px;
      }

    }

  }

  #core-content {
    @include aBlock;
    max-width: 500px;
  }
}
</style>
