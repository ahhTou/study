<template>
  <div id="status-tab3-wrapper" :style="style">
    <div id="tab">
      <div id="btn-line">
        <button @click="fun1" class="icon iconfont icon-prompt"></button>
        <button @click="fun2" class="icon iconfont icon-shut-down"></button>
        <button @click="fun3" class="icon iconfont icon-ok"></button>
      </div>
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
        '--move': this.size * (this.status - 1) + 'px'
      }
    }
  },
  methods: {},

}
</script>

<style lang="scss" scoped>
#status-tab3-wrapper {
  --tab-height: 50px;
  --move: 0px;
  $h: var(--tab-height);

  display: inline-block;

  #tab {
    height: $h;
    width: $h;

    border-radius: $h;
    //overflow: hidden;
    transition: all .3s;

    #btn-line {
      transform: translateX(var(--move));
      flex-wrap: nowrap;
      @include flex;

      button {
        width: $h;
        height: $h;
        font-size: $h;
        border-radius: $h;
        line-height: $h;
        color: red;
      }

    }
  }

}

</style>
