<template>
  <shade-slots>
    <template #center>
      <div id="login-wrapper" @click.stop :class="wrapperClassName" v-get-e="shakeE">
        <h1>登陆</h1>
        <br>
        <p>账户 | 邮箱 | 用户名</p>
        <label><input type="text" v-model="inputUsr"></label>
        <p>密码</p>
        <label><input type="password" v-model="inputPwd"></label>
        <p style="text-align: right" id="forget-pwd" @click="forgetPwd">忘记密码?</p>
        <div style="display: flex;align-items: center;justify-content:left;width: 100%">
          <div @click="clickToLogin">
            <btn-circle :is-active="btnIsActive" :is-loading="isLoading" size="80px"/>
          </div>
          <span id="errTip"
                style="margin: 10px;color: #ea495f;overflow: hidden;font-size: 16px;height: 20px;">{{ errText1 }}</span>
        </div>
      </div>
    </template>
  </shade-slots>
</template>

<script>
import ShadeSlots from 'components/ShadeSlots'
import BtnCircle from 'components/button/BtnCircle'
import RegxChecker from '@/utils/RegxChecker'
import User from '@/utils/bean/User'
import {HelloDom} from '@/utils/AnimeUtils'
import {isEmpty} from '@/utils/Utils'
import {saveAuthToken} from '@/utils/LocalSaver'
import {checkLogin} from '@/utils/auth'
import authBus from 'components/auth/authBus'

export default {
  name: 'Login',
  components: {
    BtnCircle,
    ShadeSlots
  },
  data: function () {
    return {
      inputPwd: null,
      inputUsr: null,
      shakeE: new HelloDom(),
      wrapperClassName: null,

      isLoading: false,
      errText: null
    }
  },
  mounted() {
    checkLogin().then(res => {
      this.$justTips('请不要重复登陆', 'alert')
      this.$router.push('/')
    })
  },
  computed: {
    btnIsActive() {
      if (RegxChecker.checkEmail(this.inputUsr) || RegxChecker.checkUsername(this.inputUsr)) {
        return RegxChecker.checkPassword(this.inputPwd)
      } else return false
    },
    errText1() {
      return isEmpty(this.inputPwd) ? '' : this.errText
    }
  },
  directives: {
    'get-e': {
      bind: (el, binding) => {
        binding.value.el = el
      }
    }
  },
  methods: {
    forgetPwd() {
      alert('还没弄好')
      console.log(this.shakeE)
    },
    clickToLogin() {
      if (this.btnIsActive) {
        let user = new User()
        if (RegxChecker.checkEmail(this.inputUsr)) {
          user.email = this.inputUsr
        } else user.username = this.inputUsr
        user.password = this.$md5(this.inputPwd)

        this.isLoading = true

        user.login().then(data => {
          this.isLoading = false
          // 保存 auth token
          saveAuthToken(data)
          this.$justTips('欢迎回来', 'fresh')
          // 通知另一组件检测
          authBus.$emit('toLogin')
          this.$children[0].exit()
        }).catch(err => {
          this.isLoading = false
          this.inputPwd = null
          this.shakeE.doShake()
          this.errText = err
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#login-wrapper {
  position: relative;
  z-index: 1;

  width: 350px;
  height: 350px;
  box-sizing: border-box;
  background: white;
  border-radius: 30px;
  padding: 30px 20px 30px 20px;
  display: flex;
  flex-direction: column;

  &::after {
    @include after;
    background: url("~@/assets/image/sticker/miku-sleepy.webp") no-repeat bottom right;
    z-index: -1;
  }

  p {
    color: $light-grey;
  }

  #forget-pwd {
    cursor: pointer;

    &:hover {
      color: black;
    }
  }

  input[type='text'], input[type='password'] {
    background: rgb(238, 238, 239);
    @include input-txt-div(100%, 40px);
    margin: 5px 0 5px 0;
  }
}
</style>
