<template>
  <div id="just-tips-wrapper" :style="wrapperStyle">
    <transition name="up-out">
      <div id="content" :class="contentClass" v-show="isShow" @mouseup="upToGo"> {{ msg }}</div>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'JustTips',
  data: function () {
    return {
      msg: '请不要重复登陆',
      isShow: false,
      style: 'fresh',
      time: 600,
      timer: null,
    }
  },
  computed: {
    contentClass() {
      return this.style
    },
    wrapperStyle() {
      return {
        '--timer': this.time
      }
    }
  },
  methods: {
    upToGo() {
      this.isShow = false
    },
    toShow(msg, style) {
      console.log('show')
      this.msg = msg
      this.isShow = true
      this.style = style
    },
    showByCondition(msg, style) {
      if (this.isShow) {
        this.isShow = false
        setTimeout(() => {
          this.isShow = true
          this.msg = msg
          this.style = style
        }, this.time)
      } else {
        this.isShow = true
        this.msg = msg
        this.style = style
      }
    }
  },
  mounted() {
    this.isShow = true
  },
  watch: {
    isShow(val) {
      if (val) {
        this.timer = null
        this.timer = setTimeout(() => {
          this.isShow = false
        }, 5000)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#just-tips-wrapper {
  --timer: 1000;
  position: fixed;
  top: 0;
  left: 50vw;
  z-index: 1000;
  transform: translateX(-50%);
  max-width: 80vw;
  height: 100px;
  @include flex();

  #content {
    @include shadow-mid;
    box-sizing: border-box;
    $size: 50px;
    max-width: 80vw;
    min-width: 150px;
    height: $size;
    border-radius: $size;
    line-height: $size;
    padding: 0 20px 0 20px;
    text-align: center;
    text-overflow: ellipsis;
    overflow: hidden;
  }

  .normal {
    color: black;
    background: white;
  }

  .alert {
    background: $light-red-a;
    color: white;
  }

  .fresh {
    background: $light-blue-a;
    color: white;
  }

  /* 水平上升消失 */
  .up-out-enter-active, {
    transition: all calc(var(--timer) * 1ms);
  }

  .up-out-leave-active {
    transition: all calc(var(--timer) * 0.4ms);
  }

  .up-out-enter,
  .up-out-leave-to {
    transform: translateY(-100px);
  }

}
</style>
