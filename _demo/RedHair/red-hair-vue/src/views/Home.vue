<template>
  <div id="home-wrapper" @click="doHandler" v-touch="doTouch">
    <bg-shower :is-blur="isBlur" :step="step" />
    <title-shower :step="step" />
    <total-view :step="step" />
    <img-loading />
  </div>
</template>

<script>
// @ is an alias to /src

import BgShower from '@/components/BgShower'
import TitleShower from '@/components/TitleShower'
import Text from '@/assets/Text'
import TotalView from '@/components/TotalView'
import ImgLoading from '@/components/ImgLoading'

export default {
  name: 'Home',
  components: { ImgLoading, TotalView, TitleShower, BgShower },
  data: function() {
    return {
      step: 0,
      isAdd: true,
      isBlur: true
    }
  },
  directives: {
    touch: (e, bindings) => {
      e.addEventListener(
        'touchstart',
        e => {
          e.preventDefault()
          let startY = e.changedTouches[0].pageY
          window.ontouchmove = e => {
            let moveEndY = e.changedTouches[0].pageY
            let Y = moveEndY - startY
            if (Y < -100) {
              window.ontouchmove = null
              bindings.value(true)
            }
            if (Y > 100) {
              window.ontouchmove = null
              bindings.value(false)
            }
          }
        },
        { passive: false }
      )
    }
  },
  methods: {
    doTouch(isTrue) {
      if (isTrue) {
        if (this.step !== Text.size + 1) {
          if (this.step === 0) this.isBlur = false
          this.step++
        }
      }
      if (!isTrue) {
        if (this.step >= 1) {
          this.step--
        }
        if (this.step === 0) {
          this.isBlur = true
        }
      }
    },
    doHandler() {
      if (this.step === 0 && this.isAdd) {
        this.isBlur = false
      }
      if (this.step === Text.size + 1) this.isAdd = false
      // 如果回到1

      this.isAdd ? this.step++ : this.step--
      if (this.step === 0 && !this.isAdd) {
        this.isBlur = true
        this.isAdd = true
      }
    }
  }
}
</script>

<style lang="scss"></style>
