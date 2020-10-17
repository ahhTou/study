<template>
  <div id="LrBtnLineWrapper">
    <div id="btn-line">
      <button id="login" @click="$router.push('/Login')">
        <!--        <span class="icon iconfont icon-close-bold"></span>-->
        登陆账号
      </button>
      <button id="register" @click="$router.push('/Register')">
        <span class="icon iconfont icon-register"></span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LrBtnLine'
}
</script>

<style lang="scss" scoped>

#LrBtnLineWrapper {
  @include flex;
  width: 100%;
  height: 100px;
  margin: auto;
  position: fixed;
  bottom: 20px;

  #btn-line {
    width: 320px;
    @include flex;

    button {
      margin: 5px;
      height: 50px;
      border-radius: 5px;
      background: white;
      @include shadow-mini;

      span {
        font-size: 20px;
      }
    }

    #login {
      cursor: pointer;
      flex: 1;
      color: black;
      background: rgb(187, 207, 94);
    }

    #register {
      cursor: pointer;
      width: 50px;
      color: white;
      background: rgb(75, 181, 220);
    }
  }

}

</style>
