<template>
  <div id="status-tab3-wrapper" :style="style">
    <div id="btn-line">
      <button @click="fun1" class="isErr icon iconfont icon-RectangleCopy1"></button>
      <button @click="fun2" class="isWorking icon iconfont icon-RectangleCopy"></button>
      <button @click="fun3" class="isDown icon iconfont icon-desc"></button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'StatusTab3',
  props: {
    fun1: {
      type: Function,
      default: () => {
        console.log('1')
      }
    },
    fun2: {
      type: Function,
      default: () => {
        console.log('2')
      }
    },
    fun3: {
      type: Function,
      default: () => {
        console.log('3')
      }
    },
    status: {
      default: 1
    },
    size: {
      default: 50,
    }
  },
  data: function () {
    return {}
  },
  computed: {
    style() {
      return {
        '--tab-height': this.size + 'px',
        '--move': -this.size * (this.status - 1) + 'px'
      }
    }
  },
  methods: {},

}
</script>

<style lang="scss" scoped>
#status-tab3-wrapper {
  position: relative;
  --tab-height: 50px;
  --move: 0px;
  $h: var(--tab-height);

  height: $h;
  border-radius: $h;
  width: $h;
  display: flex;
  overflow: hidden;

  #btn-line {
    @include flex;
    box-sizing: border-box;
    flex-wrap: nowrap;
    height: $h;
    transition: all .3s;
    transform: translateX(var(--move));

    button {
      @include flex;
      box-sizing: border-box;
      width: $h;
      height: $h;
      font-size: $h;
    }

    .isErr {
      color: #ea495f;
    }
    .isWorking {
      color: #4bb5dc;
    }
    .isDown {
      color: gray;
    }

  }
}


</style>
