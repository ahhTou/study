<template>
  <shade-slots>
    <template #card @click.stop>
      <div id="register-wrapper" @click.stop>

        <div id="main-title" :style="inputTitleStyle" class="block">
          <div style="font-size: 30px;font-weight: bolder;" v-show="!isErrTips">
            {{ status[step - 1].title }}
          </div>
          <div style="margin: 10px; color: rgb(142, 142, 146)" v-show="!isErrTips">
            {{ status[step - 1].describe }}
          </div>
          <div style="color: indianred;font-size: 40px; font-weight: bolder;" v-show="isErrTips">
            {{ errTips }}
          </div>
        </div>

        <div id="inputLine" class="block">
          <div id="long-line" :style="inputStyle">
            <div class="inputBox" v-for="item in status">
              <label>
                <input :type="item.inputType" :placeholder="item.placeholder" v-model="USER[item.type]">
              </label>
            </div>
          </div>
        </div>

        <div id="btnLine" class="block">
                    <span @click="clickController(true)">
                        <btn-circle :is-active="nextBtnIsActive" :is-loading="isLoading"/>
                    </span>

          <!--                    <div class="back">-->
          <!--                        <button class="icon iconfont icon-back back-btn pointer"-->
          <!--                                @click="clickController(false)"></button>-->
          <!--                    </div>-->
        </div>

      </div>
    </template>
  </shade-slots>
</template>

<script>
import ShadeSlots from '../ShadeSlots'
import BtnCircle from '@/components/button/BtnCircle'
import RegxChecker from 'assets/utils/RegxChecker'
import User from 'assets/Bean/User'
import {isEmpty} from 'assets/utils/Utils'

export default {
  name: 'Register',
  components: {BtnCircle, ShadeSlots},

  data() {
    return {
      USER: new User(),
      inputTitleStyle: {
        opacity: '1',
        transition: 'opacity .1s',
      },
      step: 1,
      vars: 'username',

      isLoading: false,
      isErrTips: false,
      errTips: null,
      status: [
        {
          title: '输入邮箱',
          describe: '输入正确的邮箱并接受验证码以完成验证',
          placeholder: '请输入邮箱',
          data: null,
          already: false,
          type: 'email',
          inputType: 'text'

        },
        {
          title: '输入验证码',
          describe: '输入正确以继续注册',
          placeholder: '请输入六位验证码',
          data: null,
          already: false,
          type: 'code',
          inputType: 'text'


        },
        {
          title: '用户名',
          describe: '4-16位,可以使用中文、字母、数字、下划线、减号）',
          placeholder: '输入用户名',
          data: null,
          already: false,
          type: 'username',
          inputType: 'text',
        },
        {
          title: '首次输入密码',
          describe: '至少6位，必须是字母+数字的组合',
          placeholder: '请输入密码',
          data: null,
          already: false,
          type: 'password',
          inputType: 'password',
        },
        {
          title: '再次输入密码',
          describe: '密码要保证和上一次一样',
          placeholder: '请再次输入密码',
          data: null,
          type: 'passwordR',
          already: false,
          inputType: 'password',
        },
      ],

    }
  },
  computed: {
    inputStyle() {
      return {
        transition: 'all .2s',
        opacity: this.step,
        transform: 'translateX(-' + (this.step - 1) * 100 + 'vw)',
        '--length': this.status.length
      }
    },
    isMaxStep() {
      return this.step === this.status.length
    },
    nextBtnIsActive() {
      let isActive = false
      switch (this.step) {
        case 1:
          isActive = RegxChecker.checkEmail(this.USER.email)
          break
        case 2:
          isActive = this.USER.code !== null ? this.USER.code.toString().length === 6 : false
          break
        case 3:
          isActive = RegxChecker.checkUsername(this.USER.username)
          break
        case 4:
          isActive = RegxChecker.checkPassword(this.USER.password)
          break
        case 5:
          isActive = this.USER.password === this.USER.passwordR
      }
      return isActive
    }
  },
  methods: {
    toRegSucView() {
      this.$children[0].exit()
      this.$myTipsViews('open', {
        type: 'regSuc',
        data: this.USER.username,
      })
    },


    btnToLoading() {
      this.isLoading = true

    },

    btnNotLoading() {
      this.isLoading = false

    },

    /* 下一步 */
    clickController(isNext) {
      if (isNext && this.step <= this.status.length && this.nextBtnIsActive)
        this.toReq()
      else if (!isNext && this.step > 1)
        this.stepController('incr')
    }
    ,

    toReq() {
      switch (this.step) {
        case 1:
          this.apiHandler(this.USER.sendVerificationEmail)
          break

        case 2:
          this.apiHandler(this.USER.checkVerification)
          break

        case 3:
          this.apiHandler(this.USER.checkUsernameUnique)
          break

        case 4:
          this.stepController('incr')
          break

        case 5:
          this.USER.password = this.$md5(this.USER.password)
          this.apiHandler(this.USER.register)
          break
      }
    },

    apiHandler(apiFuc) {
      this.btnToLoading()

      apiFuc()
        .then(res => {
          // 成功的情况
          console.log(res)
          this.btnNotLoading()
          this.stepController('incr')
        })
        .catch(err => {
          // 失败的情况
          this.btnNotLoading()
          this.stepController('err', err)
        })
    },

    stepController(to, errT) {
      switch (to) {
        case 'incr':
          if (!this.isMaxStep) {
            this.nextInputView()
            this.step++
          } else
            this.toRegSucView()
          break

        case 'decr':
          this.step--
          break

        case 'err':
          this.changeTitle(errT)
          break

        default:
          break
      }
    },


    nextInputView() {
      this.changeTitle()
      this.isLoading = false
    },

    // 负责改变标题
    inputText(status = 'normal', timer = 0, text = null) {
      return new Promise(resolve => {
        setTimeout(() => {
          switch (status) {
            case 'err':
              this.isErrTips = true
              this.errTips = text
              this.inputTitleStyle.opacity = '1'
              break

            case 'normal':
              this.isErrTips = false
              this.errTips = null
              this.inputTitleStyle.opacity = '1'
              break

            case 'opacity':
              this.inputTitleStyle.opacity = '0'
              break
          }
          resolve()
        }, timer)
      })
    },

    changeTitle(errT) {
      let timer = 100
      this.inputText('opacity')
      if (isEmpty(errT)) {

        this.inputText('err', timer, errT)
          .then(() => {
            return this.inputText('opacity', 3000)
          })
          .then(() => {
            this.inputText('normal', timer)
          })

      } else {

        this.inputText('opacity', timer)
          .then(() => {
            this.inputText('normal', timer)
          })

      }
    },

  },
}
</script>

<style lang="scss" scoped>

#register-wrapper {
  max-width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .block {
    height: 150px;
    box-sizing: border-box;
    @include flex;
    flex-direction: column;
  }

  #inputLine {
    display: flex;
    align-items: unset;
    justify-content: flex-start;
    overflow: hidden;

    #long-line {
      --length: 0;
      width: calc(100vw * var(--length));
      height: 150px;
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;

      .inputBox {
        width: 100vw;
        height: 150px;
        @include flex;

        input {
          @include input-txt;
        }
      }
    }

  }


  #btnLine {
    position: relative;

    button {
      .active {
        transform: scale(.95);
      }

    }

    .back {
      @include flex;
      width: 100px;
      height: 100%;
      position: absolute;
      bottom: 0;
      left: 0;

    }

    .back-btn {
      background-color: rgb(238, 238, 239);
      width: 100px;
      margin-left: 30px;
      height: 50px;
      font-size: 30px;
      border-radius: 10px;
      transition: all .2s;
    }
  }

}
</style>
