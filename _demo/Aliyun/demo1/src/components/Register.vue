<template>
    <shade-slots>
        <template v-slot:card="slotIsOpen" @click.stop>
            <div id="register-wrapper" @click.stop>

                <div id="main-title" :style="main_title.style" class="block">
                    <div style="font-size: 30px;font-weight: bolder;" v-show="!isErrTips">
                        {{ status[main_title.step].title }}
                    </div>
                    <div style="margin: 10px; color: rgb(142, 142, 146)" v-show="!isErrTips">
                        {{ status[main_title.step].describe }}
                    </div>
                    <div style="color: indianred;font-size: 40px; font-weight: bolder;" v-show="isErrTips">
                        {{ errTips }}
                    </div>
                </div>

                <div id="inputLine" class="block">
                    <div id="long-line" :style="inputStyle">
                        <div class="inputBox" v-for="item in status">
                            <label>
                                <input type="text" :placeholder="item.placeholder" v-model="item.data">
                            </label>
                        </div>
                    </div>
                </div>

                <div id="btnLine" class="block">
                    <span @click="stepController(true)">
                        <btn-circle :is-active="nextBtn_isActive" :is-loading="isLoading"/>
                    </span>

                    <div class="back">
                        <button class="icon iconfont icon-back back-btn pointer"
                                @click="stepController(false)"></button>
                    </div>
                </div>

            </div>
        </template>
    </shade-slots>
</template>

<script>
import ShadeSlots from './ShadeSlots'
import BtnCircle from './BtnCircle'
import RegxChecker from '../assets/utils/RegxChecker'
import User from '../assets/pojo/User'

export default {
    name: 'Register',
    components: {BtnCircle, ShadeSlots},

    data() {
        return {
            status: [
                {
                    title: '输入邮箱',
                    describe: '输入正确的邮箱并接受验证码以完成验证',
                    placeholder: '请输入邮箱',
                    data: null,
                    already: false,

                },
                {
                    title: '输入验证码',
                    describe: '输入正确以继续注册',
                    placeholder: '请输入六位验证码',
                    data: null,
                    already: false,

                },
                {
                    title: '用户名',
                    describe: '4-16位,可以使用中文、字母、数字、下划线、减号）',
                    placeholder: '输入用户名',
                    data: null,
                    already: false,
                },
                {
                    title: '密码',
                    describe: '至少6位，必须是字母+数字的组合',
                    placeholder: '第一次输入密码',
                    data: null,
                    already: false,
                },
                {
                    title: '重新输入密码',
                    describe: '密码要保证和上一次一样',
                    placeholder: '第二次输入密码',
                    data: null,
                    already: false,
                },
            ],

            main_title:
                {
                    step: 0,
                    style: {
                        opacity: '1',
                        transition: 'opacity .1s',
                    },
                },
            step: 1,

            isLoading: false,

            isErrTips: false,
            errTips: null,

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
        max_step() {
            return this.status.length
        },
        nextBtn_isActive() {
            let isActive = false
            switch (this.step) {
                case 1:
                    isActive = RegxChecker.checkEmail(this.status[0].data)
                    break
                case 2:
                    isActive = this.status[1].data !== null ? this.status[1].data.length === 6 : false
                    break
                case 3:
                    isActive = RegxChecker.checkUsername(this.status[2].data)
                    break
                case 4:
                    isActive = RegxChecker.checkPassword(this.status[3].data)
                    break
                case 5:
                    isActive = this.status[3].data === this.status[4].data
            }
            return isActive
        }
    },
    methods: {
        stepController(isNext) {
            /* 切换title */
            let changeTitle = (isAdd, errT) => {
                let timer = 100
                this.main_title.style.opacity = '0'
                setTimeout(() => {
                    if (isAdd !== null) {
                        isAdd ? this.main_title.step++ : this.main_title.step--
                        this.main_title.style.opacity = '1'
                    } else {
                        console.log(isAdd)
                        this.isErrTips = true
                        this.errTips = errT
                        this.main_title.style.opacity = '1'
                        setTimeout(() => {
                            this.main_title.style.opacity = '0'
                            setTimeout(() => {
                                this.isErrTips = false
                                this.errTips = null
                                this.main_title.style.opacity = '1'
                            }, timer)
                        }, 3000)
                    }
                }, timer)
            }

            /* 下一步 */
            let next = () => {
                if (this.step !== this.max_step) {
                    this.isLoading = false
                    changeTitle(true)
                    this.step++
                } else {
                    this.$children[0].exit()
                    this.$myTipsViews('open', {
                        type: 'regSuc',
                        data: this.status[2].data,
                    })
                }
            }

            let stepFuc = async (apiFuc, args, step, err) => {
                let isAlready = this.status[step]
                if (apiFuc !== null && !isAlready.already) { // apiFuc和already为true时直接跳转
                    this.isLoading = true
                    let api_res = await apiFuc(args)
                    switch (api_res) {
                        case true:
                            isAlready.already = true
                            this.isLoading = false
                            next()
                            break
                        default:
                            changeTitle(null, err !== null ? err : api_res)
                            this.isLoading = false
                            break
                    }
                } else next()


            }

            /* 下一步 */
            if (isNext && this.step <= this.max_step && this.nextBtn_isActive) {
                switch (this.step) {
                    case 1:
                        stepFuc(User.sendVerificationEmail, this.status[0].data, 0, null)
                        break
                    case 2:
                        let user1 = new User()
                        user1.setEC(this.status[0].data, this.status[1].data)
                        let err1 = '验证码错误'
                        stepFuc(User.checkVerification, user1, 1, err1)
                        break
                    case 3:
                        let args = this.status[2].data // 用户名输入的
                        let err2 = '用户名已存在'
                        stepFuc(User.checkUsernameUnique, args, 2, err2)
                        break
                    case 4:
                        next()
                        break
                    case 5:
                        let err4 = '注册失败'
                        let user2 = new User()
                        user2.email = this.status[0].data
                        user2.code = this.status[1].data
                        user2.username = this.status[2].data
                        user2.password = this.$md5(this.status[3].data)
                        stepFuc(User.register, user2, 4, err4)
                        break
                }
            } else if (!isNext && this.step > 1) {
                changeTitle(false)
                this.step--
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import 'src/assets/css/style';
@import 'src/assets/css/base';

#register-wrapper {
    width: 100%;
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
                    width: 300px;
                    box-sizing: border-box;
                    height: 40px;
                    border-radius: 5px;
                    background-color: rgb(238, 238, 239);
                    font-size: 16px;
                    padding: 0 10px 0 10px;
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