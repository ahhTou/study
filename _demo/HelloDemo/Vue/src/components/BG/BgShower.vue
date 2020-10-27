<template>
  <div id="bgShower" :style="[base, bgData]" :class="bgShowerClassName"/>
</template>

<script>
import {isPc} from '@/utils/Device'
import BgBus from '@/components/BG/BgBus'

export default {
  name: 'BgShower',
  data() {
    return {
      localUrl: 'localUrl_ForHelloBg',
      bgData: null,
      bgShowerClassName: null,
      base: {
        backgroundSize: 'cover',
        backgroundPosition: 'center'
      },
    }
  },

  mounted() {

    BgBus.$on('changeBG', (url => {
      this.bgShowerClassName = null
      this.bgData = {
        backgroundImage: 'url("' + url + '")'
      }
      window.localStorage.setItem(this.localUrl, url)
    }))

    if (isPc())
      this.bgShowerClassName = 'pc'
    else
      this.bgShowerClassName = 'mobile'

    let bg = window.localStorage.getItem(this.localUrl)
    if (bg !== null && bg !== '' && bg !== undefined) {

      this.bgShowerClassName = null
      this.bgData = {
        backgroundImage: 'url("' + bg + '")'
      }
    }
  },
}
</script>

<style scoped>
#bgShower {
  position: fixed;
  right: 0;
  top: 0;
  height: 100vh;
  width: 100vw;
  z-index: -100;

}

.mobile {
  background: url("~src/assets/image/defaultBg_Mobile.webp");
}

.pc {
  background: url("~src/assets/image/defaultBg_Pc.webp");
}


</style>
