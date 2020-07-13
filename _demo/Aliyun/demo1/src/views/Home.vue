<template>
  <div id="homeWrapper" ref="homeWrapper">
    <div id="title" @click="openPrimeWindow">
      Hello, ahhTou
    </div>
    <div class="safeRoom">
      <prime-menu :isOpen="isPrimeWindowOpen" />
    </div>
    <div class="safeRoom">
      <msg-board />
      <bg-changer />
    </div>
  </div>
</template>

<script>
import PrimeMenu from 'src/components/PrimeMenu'
import BgChanger from 'src/components/BgChanger'
import MsgBoard from 'src/components/MsgBoard'

export default {
  name: 'Home',
  components: {
    PrimeMenu,
    BgChanger,
    MsgBoard
  },
  data() {
    return {
      bgImageList: {
        bg1: 'http://39.99.154.145/img/hello/bg1.jpg',
        bg2: 'http://39.99.154.145/img/hello/bg2.jpg',
        bg3: 'http://39.99.154.145/img/hello/bg3.jpg',
        bg4: 'http://39.99.154.145/img/hello/bg4.jpg'
      },
      changeBgStyle: {
        isOpen: false
      },
      isPrimeWindowOpen: false
    }
  },
  methods: {
    openPrimeWindow() {
      this.isPrimeWindowOpen = !this.isPrimeWindowOpen
    }
  }
}
</script>

<style lang="scss" scoped>
@import 'src/assets/css/style';
@import 'src/assets/css/base';

#homeWrapper {
  height: 100vh;
  width: 100%;
  box-sizing: border-box;
  @media screen and (min-width: 768px) {
    padding: 10px;
  }

  #title {
    @include flex;
    @include blurWindow;
    border-radius: 10px;
    position: fixed;
    z-index: 20;

    @media screen and (min-width: 768px) {
      font-size: 40px;
      width: 400px;
      height: 150px;
      bottom: 15px;
      left: 15px;
    }

    @media screen and (max-width: 768px) {
      bottom: 5px;
      left: 5px;
      font-size: 20px;
      height: 100px;
      width: 200px;
    }
  }
}
</style>
