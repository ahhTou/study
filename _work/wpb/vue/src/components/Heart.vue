<template>
    <div id="heartWrap">
        <transition name="fade">
            <div v-if="openMyDemoMsg" class="myDemoMsg">
                <button @click="closeMyDemoMsg">X</button>
                <h3>前端</h3>
                <a>基础:HTML,css,JavaScript</a><br/>
                <a>前端框架:Vue</a><br/><br/>
                <h3>请求</h3>
                <a>ajax的框架:axios</a><br/><br/>
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
            <h1 id="title" :style="titleColor" v-if="!changeToSidBar">{{title}}</h1>
        </transition>
        <div id="changeWrapper" class="loginStyle">
            <transition name="fade">
                <div id="content" v-if="!isSidBar">
                    <form id="form">
                        <label for="iUser"><input id="iUser" type="text" placeholder="管理员账户"
                                                  v-model="username"/></label>

                        <label for="iPass"><input id="iPass" type="password" placeholder="管理员密码"
                                                  v-model="password"/></label>

                        <div><input id="submit" @click.prevent="clickToLogin" type="button" value="进入管理界面"/></div>
                    </form>
                    <div id="loading">
                        <loading-animation :start-loading="start_LA"/>
                    </div>
                </div>
            </transition>
            <transition name="fade">
                <div id="sideBar">
                    <img id="profilePhoto" src="../assets/img/ProfilePhoto.jpg"/>
                    <div id="username">未登录</div>
                    <div class="options">影片档案</div>
                    <div class="options">站位</div>
                    <div class="options">站位</div>
                    <div class="options exit" id="exit" @click="toExitLogin">退出</div>
                    <div class="ahhTou" @click="toOpenMyDemoMsg">
                        <div>@偷偷呼吸的死肥宅</div>
                        <div>By ahhTou</div>
                    </div>
                </div>
            </transition>
        </div>
    </div>
</template>

<script>
    import LoadingAnimation from "../components/LoadingAnimation.vue"

    export default {
        name: "Heart",
        components: {
            LoadingAnimation
        },
        data() {
            return {
                changeToSidBar: false,
                isSidBar: false,
                title: "追番列表",
                username: null,
                password: null,
                start_LA: false,
                titleColor: "color : white;",
                openMyDemoMsg: false,
            }
        },
        methods: {
            clickToLogin() {
                this.$router.push('/login')
            },
            toLoginByRouter() {
                let $form = document.getElementById("content")
                $form.style.transition = "all .3s"
                $form.style.transform = "translateX(-450px)"

                this.changeToSidBar = true

                this.start_LA = true

                let isLogin;

                new Promise((resolve, reject) => {
                    let _this = this
                    setTimeout(function () {
                        if (_this.$store.state.isLogin) {
                            resolve()
                        } else {
                            reject()
                        }
                    }, 1000)

                }).then(data => {
                    this.$store.state.isLogin = true
                    if (this.$route.path !== '/') {
                        this.$router.push("/")
                    }
                    this.start_LA = false
                    let $changeWrapper = document.getElementById("changeWrapper");
                    $changeWrapper.className = 'sidebarStyle'
                    this.isSidBar = true

                }, err => {
                    this.$store.state.isLogin = true
                    this.$router.push("/login")
                    this.start_LA = false
                    $form.style.transform = "translateX(0px)"
                    this.changeToSidBar = false
                    this.title = "密码错误"
                    this.titleColor = "color: indianred;"
                })

            },
            toExitLogin() {
                this.$store.state.isLogin = false
                this.$router.push("/login")
            },
            toOpenMyDemoMsg() {
                this.openMyDemoMsg = true
            },
            closeMyDemoMsg() {
                this.openMyDemoMsg = false
            },
        },
        watch: {
            $route: {
                handler: function (val, oldVal) {
                    let path = val.path
                    if (path === '/login' && this.$store.state.isLogin) {
                        this.$router.push('/')
                    }
                    if (path !== '/login' && this.$store.state.isLogin) {
                        this.toLoginByRouter();
                    }
                },
                // 深度观察监听
                deep: true
            }
        },
    }
</script>

<style lang="scss" scoped>
    @mixin flex {
        display: flex;
        align-items: center;
        justify-content: center;
    }


    #heartWrap {
        @include flex;
        position: absolute;
        width: 100%;
        height: 100%;

        #title {
            position: absolute;
            top: 140px;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            color: white;
            font-size: 50px;
        }

        #changeWrapper {
            transition: all 1s;


            display: flex;
            /*            justify-content: flex-start;
                        align-items: flex-start;*/

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