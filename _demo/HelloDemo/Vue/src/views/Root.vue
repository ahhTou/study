<template>
  <div id="root-wrapper">
    <img src="~src/assets/image/sticker/miku-happy.webp" alt="logo"/>
    <root-for-search />

    <div id="bg"></div>
  </div>
</template>

<script>
import RootForSearch from 'components/root/RootForSearch'

export default {
  name: 'Root',
  components: {RootForSearch},
  data: function () {
    return {
    }
  },
  mounted() {

  }
}
</script>

<style lang="scss" scoped>
#root-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  color: rgb(186, 186, 184);



  #bg {
    position: fixed;
    z-index: -1;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background: rgb(43, 43, 43);
  }
}
</style>
