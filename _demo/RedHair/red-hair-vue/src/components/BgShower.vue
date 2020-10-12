<template>
  <div id="bg-shower-wrapper" :style="vars">
    <div
      class="bg"
      v-for="(item, i) in imgList"
      :style="setBg(item.img)"
      :key="i"
      :class="bgShowerClassName"
    ></div>
  </div>
</template>

<script>
import Text from '@/assets/Text'

export default {
  name: 'BgShower',
  props: {
    step: {
      default: 0
    },
    isBlur: {
      default: false
    }
  },
  data: function() {
    return {
      imgList: Text.list
    }
  },
  computed: {
    bgShowerClassName() {
      return {
        blur: this.isBlur
      }
    },
    vars() {
      return {
        '--move': -100 * (this.step > 1 ? this.step - 1 : 0) + 'vh'
      }
    }
  },
  methods: {
    setBg: val => {
      return {
        backgroundImage: "url('" + val + "')"
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#bg-shower-wrapper {
  z-index: -1;
  --move: 0vh;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  overflow: hidden;
  position: fixed;
  top: 0;
  right: 0;
  transition: all 0.5s;
  transform: translateY(var(--move));

  .bg {
    width: 100vw;
    height: 100vh;
    background: center no-repeat;
    background-size: cover;

    transition: all 1s;
    filter: blur(0);
    transform: scale(1);
  }

  .blur {
    filter: blur(15px);
    transform: scale(1.3);
  }
}
</style>
