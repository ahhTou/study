<template>
  <div id="title-shower-wrapper" :style="wrapperStyle">
    <transition
      name="out-down"
      v-on:before-enter="titleEnter"
      v-on:after-leave="titleLeave"
      appear
    >
      <div id="title" v-if="step === 0">
        <div>
          <p style="font-size: 40px;text-align: left;">{{ t1 }}</p>
          <span style="font-size: 100px" class="bolder red">{{ t2 }}</span>
          <br />
          <p style="font-size: 20px;text-align: right;">↓ 滑动查看 ↓</p>
        </div>
      </div>
    </transition>
    <!---->
    <transition name="bigger">
      <div class="step-title" v-if="isNextTitleShow" :style="stepTitleStyle">
        <p>{{ desc[step - 1 === -1 ? 0 : step - 1] }}</p>
      </div>
    </transition>
  </div>
</template>

<script>
import Text from '@/assets/Text'

export default {
  name: 'TitleShower',
  props: {
    step: {
      default: 0
    }
  },
  data: function() {
    return {
      isNextTitleShow: false,
      // desc: ['低能', '流氓', '不雅', '滥交']
      desc: null,
      size: Text.size,
      t1: Text.titleMin,
      t2: Text.titleMain
    }
  },
  mounted() {
    let map = Text.list
    let list = []
    for (let mapKey in map) {
      list.push(map[mapKey].title)
    }
    this.desc = list
  },
  methods: {
    titleLeave() {
      this.isNextTitleShow = true
    },
    titleEnter() {
      this.isNextTitleShow = false
    }
  },
  watch: {
    step(val) {
      if (val !== this.size + 1) {
        this.isNextTitleShow = false
        setTimeout(() => {
          this.isNextTitleShow = val !== 0
        }, 300)
      } else this.isNextTitleShow = false
    }
  },
  computed: {
    wrapperStyle() {
      return {
        '--title-main-color': Text.color_title_Main,
        '--every-title-color': Text.color_every_title
      }
    },
    stepTitleStyle() {
      let num = this.step > this.size ? this.size : this.step
      return {
        '--every-title-is-bolder': Text.isBolder ? 'bolder' : 'normal',
        '--every-title-font-size': Text.font_size_every_title,
        '--every-title-bg': "url('" + Text.list[num].img + "')"
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#title-shower-wrapper {
  --title-main-color: red;
  --every-title-color: white;
  --every-title-is-bolder: bolder;
  --every-title-font-size: 50px;
  --every-title-bg: pink;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: fixed;
  box-sizing: border-box;
  top: 0;
  left: 0;
  font-family: 'Microsoft YaHei UI Light', sans-serif;

  #title {
    box-sizing: border-box;
    animation: in 0.5s;
    position: fixed;
    display: flex;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    flex-direction: column;

    .bolder {
      font-weight: bolder;
    }

    .red {
      color: var(--title-main-color);
    }

    p,
    span {
      text-shadow: #000000 5px 5px 20px;
    }

    p {
      color: wheat;
    }

    @keyframes in {
      0% {
        transform: scale(3);
      }
      100% {
        transform: scale(1);
      }
    }
  }

  .step-title {
    position: fixed;
    bottom: 0;
    left: 0;

    margin: 10px;
    width: 180px;
    height: 100px;
    box-sizing: border-box;
    z-index: 3;
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    background-size: cover;
    box-shadow: #424242 5px 5px 20px;
    border-radius: 30px;
    background: var(--every-title-bg) center;
    background-size: cover;
    font-family: '微软雅黑 Light', sans-serif;
    padding: 15px;
    p {
      font-weight: var(--every-title-is-bolder);
      color: var(--every-title-color);
      font-size: var(--every-title-font-size);
    }
  }

  .out-down-enter-active,
  .out-down-leave-active {
    transition: all 0.5s;
  }

  .out-down-enter,
  .out-down-leave-to {
    opacity: 0;
    transform: translateY(100vw);
  }

  .bigger-enter-active {
    animation: bigger 0.5s;
  }

  .bigger-leave-active {
    animation: bigger 0.3s reverse;
  }

  @keyframes bigger {
    0% {
      transform: scale(1.1);
      opacity: 0;
    }
    100% {
      transform: scale(1);
      opacity: 1;
    }
  }
}
</style>
