<template>
    <div id="heartWrap">
        <transition name="fade">
            <div v-if="openMyDemoMsg" class="myDemoMsg">
                <button @click="closeMyDemoMsg">X</button>
                <h3>前端</h3>
                <a>基础:HTML,css(sass),JavaScript(es6)</a><br/>
                <a>前端框架:Vue</a><br/><br/>
                <h3>请求</h3>
                <a>ajax（axios)</a><br/><br/>
                <h3>登录验证</h3>
                <a>Token</a><br/><br/>
                <h3>后端</h3>
                <a>Java</a><br/>
                <a>Spring、SpringMVC、Mybatis基础</a><br/><br/>
                <h4>个人信息</h4>
                <a>王鹏博</a><br/>
                <a>学号 18560640526</a><br/>
                <a href="http://wpa.qq.com/msgrd?v=3&uin=1197977498&site=qq&menu=yes">QQ 1197977498</a>
            </div>
        </transition>
        <transition name="fade">
            <div id="title" v-if="!changeToSidBar&&!isSidBar&&isLoad"><h3>{{title}}</h3>
                <div>{{loginMsg}}</div>
            </div>
        </transition>
        <div id="changeWrapper">
            <transition name="fade">
                <div id="content" v-show="!isSidBar&&isLoad">
                    <form id="form">
                        <label for="iUser"><input id="iUser" type="text" placeholder="管理员账户"
                                                  value="123"
                                                  v-model="username"/></label>

                        <label for="iPass"><input id="iPass" type="password" placeholder="管理员密码"
                                                  value="123"
                                                  v-model="password"/></label>

                        <div><input id="submit" @click.prevent="clickToLogin" type="button" value="进入管理界面"/></div>
                    </form>
                    <div id="loading">
                        <loading-animation :start-loading="start_LA"/>
                    </div>
                </div>
            </transition>
            <transition name="fade">
                <div id="sideBar" v-show="isSidBar&&isLoad">
                    <img id="profilePhoto" src="../assets/img/ProfilePhoto.jpg" alt="图片加载失败"/>
                    <div id="username">{{userBasicData.nickname}}</div>
                    <div class="options" @click="goTo('/myAnime')">我的番剧</div>
                    <div class="options" @click="goTo('/anime/1')">番剧库</div>
                    <div class="options exit" id="exit" @click="toExitLogin">退出</div>
                    <div class="ahhTou" @click="toOpenMyDemoMsg">
                        <div>@偷偷呼吸的死肥宅</div>
                        <div>当前登录: {{ userBasicData.username }}</div>
                        <div>By ahhTou</div>
                    </div>
                </div>
            </transition>
        </div>
    </div>
</template>

<script>
    import LoadingAnimation from "../components/LoadingAnimation.vue"
    import api_login_authorized from "../network/authorized"
    import {getBasicUserData} from "../network/userData"

    export default {
        name: "Heart",
        components: {
            LoadingAnimation
        },
        mounted() {
            this.localLoginCheck().then(() => {
                console.log("> 本地验证成功, 获取基本信息")
                this.getUserBasicDataService()
            }, () => {
                console.log("> 本地验证失败, 返回登录页")
            })
        },
        data() {
            return {
                userBasicData: {
                    username: null,
                    nickname: "未登录",
                },

                username: null,
                password: null,

                loginMsg: ' ',

                isLoad: false,
                changeToSidBar: false,
                isSidBar: null,
                title: "追番列表",


                start_LA: false,
                openMyDemoMsg: false,
            }
        },
        methods: {
            clickToLogin() {
                this.toLoginByRouter()
            },

            toLoginByRouter() {
                let $changeWrapper = document.getElementById("changeWrapper");
                let $form = document.getElementById("content")
                let isLogin = window.localStorage.getItem("isLogin")
                if (isLogin === false || isLogin === "false") {
                    $form.style.transition = "all .3s"
                    $form.style.transform = "translateX(-450px)"
                    this.changeToSidBar = false
                    this.start_LA = true

                    setTimeout(() => {
                        this.loginService()
                    }, 1000)

                } else if (isLogin === true || isLogin === "true") {

                    $changeWrapper.style.transition = 'all 0s'
                    $changeWrapper.className = 'sidebarStyle'
                    $changeWrapper.style.transition = ''
                    this.changeToSidBar = true
                    this.isSidBar = true
                    this.$store.state.isLogin = true

                    this.getUserBasicDataService();
                }


            },
            toExitLogin() {
                console.log("> 退出了登录")
                let $changeWrapper = document.getElementById("changeWrapper");
                $changeWrapper.className = 'loginStyle'

                let $form = document.getElementById("content")
                $form.style.transform = "translateX(0px)"

                window.localStorage.setItem("token", "")
                window.localStorage.setItem("isLogin", "false")

                this.changeToSidBar = false
                this.isSidBar = false
                this.$store.state.isLogin = false

                this.$router.push("/login")
            },

            loginService() {
                console.log("> 进行远程登录验证")
                let $form = document.getElementById("content")
                let $changeWrapper = document.getElementById("changeWrapper");

                api_login_authorized(this.username, this.password).then(res => {
                    window.localStorage.setItem("token", res.data)
                    window.localStorage.setItem("isLogin", "true")

                    this.$store.state.isLogin = true

                    if (this.$route.path !== '/') {
                        this.$router.push("/")
                    }

                    this.start_LA = false
                    this.isSidBar = true


                    this.loginMsg = ' '

                    $changeWrapper.className = 'sidebarStyle'

                    this.getUserBasicDataService()
                }, err => {

                    window.localStorage.setItem("token", "")
                    window.localStorage.setItem("isLogin", "false")

                    $form.style.transform = "translateX(0px)"

                    let $changeWrapper = document.getElementById("changeWrapper");
                    $changeWrapper.className = 'loginStyle'

                    this.$store.state.isLogin = false

                    if (this.$route.path !== "/login") {
                        this.$router.push("/login")
                    }

                    this.start_LA = false
                    this.changeToSidBar = false

                    this.loginMsg = err
                })
            },

            getUserBasicDataService() {
                getBasicUserData().then(res => {
                    console.log("> 得到了基础账户信息")
                    window.localStorage.setItem("token", res.data.token)
                    this.userBasicData.username = res.data.username
                    this.userBasicData.nickname = res.data.nickname

                }, err => {
                    let $changeWrapper = document.getElementById("changeWrapper");
                    $changeWrapper.className = 'loginStyle'

                    window.localStorage.setItem("token", "")
                    window.localStorage.setItem("isLogin", "false")

                    this.changeToSidBar = false
                    this.isSidBar = false
                    this.$store.state.isLogin = false

                    this.loginMsg = err

                    this.$router.push("/login")
                })
            },

            localLoginCheck() {
                return new Promise((resolve, reject) => {
                    console.log("> 进行本地验证")
                    let $changeWrapper = document.getElementById("changeWrapper");
                    let _isLogin = window.localStorage.getItem("isLogin")
                    this.isLoad = true
                    if (_isLogin === "true" || _isLogin) {
                        this.isSidBar = true
                        $changeWrapper.className = 'sidebarStyle'
                        resolve()
                    } else {
                        this.isSidBar = false
                        $changeWrapper.className = 'loginStyle'
                        reject()
                    }
                })
            },

            toOpenMyDemoMsg() {
                this.openMyDemoMsg = true
            },
            closeMyDemoMsg() {
                this.openMyDemoMsg = false
            },
            goTo(url) {
                if (this.$route.path !== url) {
                    this.$router.push(url)
                }
            }
        },
    }
</script>

<style lang="scss" scoped>
    @mixin flex() {
        display: flex;
        align-items: center;
        justify-content: center;
    }


    #heartWrap {
        @include flex();
        position: absolute;
        width: 100%;
        height: 100%;

        #title {
            position: absolute;
            top: 140px;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            color: white;
            font-size: 50px;

            div {
                margin: 20px 0 20px 0;
                position: relative;
                font-size: 20px;
                color: indianred;
            }
        }

        #changeWrapper {
            transition: all 1s;
            display: flex;
            backdrop-filter: blur(30px);
            overflow: hidden;

            #content {
                display: flex;
                width: 900px;
                height: 350px;

                #form {
                    @include flex;
                    width: 450px;
                    height: 350px;
                    position: relative;
                    flex-direction: column;

                    input {
                        border-radius: 3px;
                        border: none;
                        outline: none;
                        padding: 0 5px 0 5px;
                        font-size: 18px;
                        width: 350px;
                        height: 50px;
                        margin: 10px;
                    }

                    input[type='button'] {
                        cursor: pointer;
                        transition: all 0.4s;
                        color: black;
                        background-color: white;
                        border: none;

                        &:hover {
                            color: white;
                            background-color: rgba(99, 172, 231, 0.9);
                            border: none;
                        }
                    }
                }

                #loading {
                    @include flex;
                    font-size: 40px;
                    color: white;
                    width: 450px;
                    height: 350px;
                }

            }

            #sideBar {
                #profilePhoto {
                    width: 60%;
                    margin-top: 30px;
                    border-radius: 30px;
                }

                #username {
                    font-size: 30px;
                    color: white;
                    margin-top: 10px;
                    margin-bottom: 30px;
                }

                .options {
                    @include flex();
                    height: 100px;
                    width: 100%;
                    color: white;
                    font-size: 20px;
                    cursor: pointer;
                    margin-top: 10px;
                    margin-bottom: 10px;
                    box-sizing: border-box;
                    transition: all 0.5s;

                    &:hover {
                        color: black;
                        background-color: white;
                        box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);

                    }

                }

                .exit {
                    &:hover {
                        color: black;
                        background-color: #f86262;
                        box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);
                    }
                }

                .ahhTou {
                    cursor: pointer;
                    font-size: 10px;
                    width: 100%;
                    position: absolute;
                    bottom: 10px;
                    color: white;
                    display: flex;
                    align-items: center;
                    flex-direction: column;
                }
            }
        }

        .loginStyle {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 450px;
            height: 350px;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);
        }

        .sidebarStyle {
            position: fixed;
            top: 0;
            left: 0;
            margin: 0;
            width: 300px;
            height: 100vh;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 10px 0 40px rgba(100, 100, 100, 0.5);

        }

        .myDemoMsg {
            font-size: 20px;
            padding: 20px;
            backdrop-filter: blur(30px);
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);

            a {
                text-decoration: none;

                &:visited {
                    color: rgb(44, 62, 80);
                }
            }

            button {
                background-color: rgba(255, 255, 255, 0);
                position: absolute;
                top: 0;
                right: 0;
                border: none;
                outline: none;
                cursor: pointer;
                width: 50px;
                height: 30px;
                transition: all .3s;

                &:hover {
                    background-color: #f86262;
                }

            }

        }

    }


    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }

    .fade-enter, .fade-leave-to {
        opacity: 0;
    }
</style>