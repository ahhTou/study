<template>
  <transition name="fade">
    <div id="img-loading-wrapper" v-show="isShow">
      <div id="progress-bar" :style="progressBarStyle"></div>
    </div>
  </transition>
</template>

<script>
// import Text from '@/assets/Text'

import Text from '@/assets/Text'

export default {
  name: 'ImgLoading',
  data: function() {
    return {
      isShow: true,
      total: Text.size,
      loaded: 0
    }
  },
  computed: {
    progressBarStyle() {
      return {
        '--num': (this.loaded / this.total) * 100 + '%'
      }
    }
  },
  watch: {
    loaded(val) {
      if (val === this.total) this.isShow = false
    }
  },
  mounted() {
    let list = this.getImgList(Text.list)
    list.forEach(l => this.imgLoad(l))
  },
  methods: {
    getImgList(map) {
      let list = []
      for (let k in map) {
        if (Object.prototype.hasOwnProperty.call(map, k)) list.push(map[k].img)
      }
      return list
    },
    imgLoad(url) {
      let img = new Image()
      img.src = url
      img.onload = () => {
        this.loaded++
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#img-loading-wrapper {
  position: fixed;
  z-index: 100;
  font-size: 30px;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  #progress-bar {
    --num: 20%;
    position: relative;
    z-index: 2;
    $w: 250px;
    $h: 20px;
    width: $w;
    height: $h;
    background: #42b983;
    border-radius: 30px;
    &::before {
      content: '';
      position: absolute;
      top: 0;
      right: 0;
      border-radius: 30px;
      z-index: 1;
      width: $w;
      height: $h;
      transition: all 0.3s;
      background: linear-gradient(to right, #9ce3c5, #9ce3c5) no-repeat;
      background-size: var(--num) 100%;
    }
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
