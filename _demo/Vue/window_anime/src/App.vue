<template>
  <div id="app">
    <div id="bg" :class="bgClassName"></div>
    <div id="nav">
      <button @click="changeView('HelloBannerStyleX')">焦点图 样式 X</button>
      <button @click="changeView('HelloBlockStyleX')">单独窗口 控制</button>
    </div>
    <!--    <hello-banner-style-x/>-->
    <div style="display: flex">
      <hello-block-style-x></hello-block-style-x>
      <hello-block-style-x></hello-block-style-x>
      <hello-block-style-x></hello-block-style-x>
      <hello-block-style-x></hello-block-style-x>
    </div>
  </div>
</template>

<script>

import Vue from 'vue'
import HelloBannerStyleX from '@/components/HelloBannerStyleX'
import HelloBlockStyleX from '@/components/HelloBlockStyleX'
import Bus from '@/assets/js/Bus'
// eslint-disable-next-line no-unused-vars
let HelloBannerStyleX_ = HelloBannerStyleX
// eslint-disable-next-line no-unused-vars
let HelloBlockStyleX_ = HelloBlockStyleX

export default {
  name: 'App',
  components: {HelloBlockStyleX},
  data: function () {
    return {
      instance: null,
      bgClassName: [],
    }
  },
  mounted() {
    Bus.$on('bgChange', () => {
      console.log('xx')
      this.toSmaller()
    })
  },
  methods: {
    toSmaller() {
      if (this.bgClassName.isInclude('blur')) {
        this.bgClassName.remove('blur')
      } else {
        this.bgClassName.push('blur')
      }
    },
    changeView(view) {
      if (this.instance !== null) {
        document.body.removeChild(this.instance.$el)
      }
      let Constructor = Vue.extend(eval(view + '_'))
      this.instance = new Constructor()
      this.instance.$mount()
      document.body.appendChild(this.instance.$el)
    }
  }
}
</script>

<style lang="scss">
body {
  height: 100vh;
  overflow: hidden;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Avenir, Helvetica, Arial, sans-serif;
}

#app {
  #bg {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background: url("~@/assets/img/1.jpg") center;
    background-size: cover;
    transition: all .5s;
    z-index: -1;
  }

  .blur {
    transform: scale(1.5);
  }

  #nav {
    position: fixed;
    left: 30px;
    bottom: 30px;
    width: 100px;
    z-index: 100;

    button {
      cursor: pointer;
      width: 150px;
      height: 30px;
      margin: 10px;
    }
  }
}


</style>
