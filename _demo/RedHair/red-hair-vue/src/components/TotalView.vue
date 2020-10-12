<template>
  <div id="total-view-wrapper" @click="isShow = !isShow">
    <transition name="trans">
      <div id="wrapper" v-if="isShow">
        <div
          v-for="(li, index) in list"
          :key="index"
          :class="whatSize(li.size)"
          :style="setStyle(li.img)"
        ></div>
      </div>
    </transition>
  </div>
</template>

<script>
import Text from '@/assets/Text'

export default {
  name: 'TotalView',
  props: ['step'],
  data: function() {
    return {
      list: null
    }
  },
  computed: {
    isShow() {
      return this.step === Text.size + 1
    }
  },
  mounted() {
    this.list = Text.list
  },
  methods: {
    setStyle: function(img) {
      return {
        backgroundImage: "url('" + img + "')"
      }
    },
    whatSize(size) {
      return {
        min1: 'min1' === size,
        min2: 'min2' === size,
        large: 'large' === size
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#total-view-wrapper {
  box-sizing: border-box;

  overflow: hidden;

  position: fixed;
  padding: 10px;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: white;
  display: flex;
  justify-content: center;
  background: url('~@/assets/totalBg.jpg') center;
  background-size: cover;
  z-index: -2;

  perspective: 50px;
  perspective-origin: top;

  // 尝试解决
  backface-visibility: hidden;
  transform-style: preserve-3d;
  -webkit-backface-visibility: hidden;
  -webkit-transform-style: preserve-3d;

  #wrapper {
    width: 350px;
    perspective: 50px;
    perspective-origin: top;
    // 尝试解决
    backface-visibility: hidden;
    transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    -webkit-transform-style: preserve-3d;

    --time: 5s;
    div {
      height: 150px;
      box-shadow: #424242 5px 5px 20px;
      border-radius: 30px;
      margin: 10px;
      display: inline-block;
      float: left;
      background-position: center;
      background-size: cover;
    }

    $cb: cubic-bezier(0.59, 1.38, 0, 0.97);
    @mixin zoom() {
      @keyframes in {
        0% {
          transform: translateZ(30px);
        }
        40% {
          transform: translateZ(-5px);
        }
        100% {
          width: 330px;
        }
      }
    }
    .large {
      transform: translateZ(0px);
      animation: in 1.3s;
      @include zoom();
    }
    .min1 {
      width: 150px;
      animation: in 1.5s;
      @include zoom();
    }
    .min2 {
      width: 150px;
      animation: in 2s;
      @include zoom();
    }
  }

  .trans-leave-active {
    animation: inAni 0.3s reverse;
  }
  @keyframes inAni {
    0% {
      transform: translateZ(60px);
    }
    100% {
      transform: translateZ(0px);
    }
  }
}
</style>
