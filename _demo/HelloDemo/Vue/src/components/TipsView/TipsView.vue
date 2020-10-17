<template>
  <transition name="fade">
    <div id="tipsViewsWrapper" v-show="isOpen">
      <!-- type= -->
      <div id="register-block"
           v-if="cType==='regSuc'">
        <loading-block :is-loading="isLoading">
          <div style="width: 300px;padding: 10px;box-sizing: border-box;" class="normalStyle">
            <div id="title">注册成功</div>
            <div id="welcomeUser">欢迎<span>{{ userData.username }}</span>注册
            </div>
            <div id="id">
              您是本网站第<span>{{ userData.id }}</span>个注册的用户
            </div>
            <div id="btnLine">
                        <span @click="exit" style="margin: 10px;">
                            <btn-circle :is-active="true"
                                        :size="'70px'"
                                        btn-svg-style="icon-ok"/>
                        </span>
            </div>
            <div style="text-align: right;color: rgb(142, 142, 146);font-size: 12px">
              {{ userData.createTime }}
            </div>
          </div>
        </loading-block>
      </div>
    </div>
  </transition>
</template>

<script>
import LoadingBlock from '../LoadingBlock'
import BtnCircle from '@/components/button/BtnCircle'
import User from '../../assets/Bean/User'

export default {
  name: 'TipsViews',
  components: {BtnCircle, LoadingBlock},
  data() {
    return {
      isOpen: false,
      isLoading: true,
      args: {
        type: null,
        data: null,
      },
      userData: new User()
    }
  },
  mounted() {
    this.isOpen = true
  },
  computed: {
    cType() {
      if (this.args.type !== null) {
        this.make()
        return this.args.type
      } else return null
    }
  },
  methods: {
    exit() {
      this.$myTipsViews('close')
    },
    async make() {
      let type = this.args.type
      let data = this.args.data
      switch (type) {
        case 'regSuc':
          // 发送请求
          this.userData.username = data
          let res = await this.userData.regSuc()
          if (res !== null && res !== false) {
            this.userData = res
          }
          this.isLoading = false
          break
      }
    },
  }
}
</script>

<style lang="scss" scoped>
#tipsViewsWrapper {
  @include shade;
  @include flex;

  #register-block {
    @include flex;

    #title {
      margin: 10px;
      text-align: center;
      color: black;
      font-size: 40px;
    }

    #welcomeUser {
      text-align: center;

      span {
        color: #4bb5dc;
        font-size: 30px;
        margin: 5px;
      }

    }

    #id {
      text-align: center;
      font-size: 10px;

      span {
        font-weight: bold;
        margin: 5px;
        font-size: 16px;
      }
    }

    #btnLine {
      @include flex;
    }
  }

  .normalStyle {
    width: 300px;
    background: white;
    border-radius: 30px;
    min-height: 200px;
  }

}

.fade-enter-active,
.fade-leave-active {
  transition: all .2s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
