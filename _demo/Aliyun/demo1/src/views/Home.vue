<template>
    <div id="homeWrapper" :style="bgData" @click="closeBgWindow">
        <div id="title">
            Hello, ahhTou
        </div>
        <div id="changeBgBtn"
             ref="skinWindow"
             @click.stop="changeBg"
             :style="changeBgStyle.isOpen?changeBgStyle.open:changeBgStyle.close">
            <div v-if="changeBgStyle.isOpen" v-for="item in bgImageList" class="skin"
                 :style="[skinItemStyle,skinBg(item)]"
            ></div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                bgData: null,
                bgImageList: {
                    bg1: "http://39.99.154.145/img/hello/bg1.jpg",
                    bg2: "http://39.99.154.145/img/hello/bg2.jpg",
                    bg3: "http://39.99.154.145/img/hello/bg3.jpg",
                    bg4: "http://39.99.154.145/img/hello/bg4.jpg",
                },
                changeBgStyle: {
                    isOpen: false,
                    close: {
                        width: "50px",
                        height: "50px",
                        top: "30px",
                        right: "30px",
                        transform: "translate(50%, -50%)",
                    },
                    open: {
                        width: "70vw",
                        height: "40vh",
                        top: "50%",
                        right: "50%",
                        transform: "translate(50%, -50%)",
                    }

                },
                skinItemStyle: null,
                skinItemBg: null,

            }
        },
        mounted() {
            this.bgData = {
                backgroundSize: "cover",
                backgroundPosition: "center",
                backgroundImage: "url(\"" + this.bgImageList.bg1 + "\")",
            }
        },
        computed:{

        },
        methods: {
            skinBg(item){
                return{
                    backgroundImage: "url(\"" + item + "\")",
                }
            },
            computeSkin() {
                return new Promise((resolve, reject) => {
                    setTimeout(() => {
                        let $dom = this.$refs.skinWindow
                        console.log($dom.offsetHeight)
                        let height = Math.round($dom.offsetHeight * 0.8)
                        let width = Math.round($dom.offsetWidth * 0.3)
                        this.skinItemStyle = {
                            '--item-width': width + 'px',
                            '--item-height': height + 'px',
                            backgroundSize: "cover",
                            backgroundPosition: "center",
                        }
                        resolve()
                    }, 300)
                })

            },
            changeBg() {
                if (!this.changeBgStyle.isOpen) {
                    this.changeBgStyle.isOpen = true
                    this.computeSkin()
                }
            },
            closeBgWindow() {
                this.changeBgStyle.isOpen = false
            }
        }
    }
</script>

<style lang="scss" scoped>
    $shadow : 10px 10px 30px rgba(100, 100, 100, 0.5);

    @mixin flex {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    @mixin blurWindow {
        color: black;
        backdrop-filter: blur(15px);
        background-color: rgba(255, 255, 255, .6);
        box-shadow: $shadow;
    }



    #homeWrapper {
        height: 100vh;
        width: 100%;
        box-sizing: border-box;

        @media screen and (min-width: 768px) {
            padding: 30px;
        }

        #title {
            @include flex;
            position: relative;
            @include blurWindow;

            @media screen and (min-width: 768px) {
                border-radius: 30px;
                font-size: 40px;
                width: 400px;
                height: 150px;
            }

            @media screen and (max-width: 768px) {
                position: absolute;
                bottom: 5px;
                left: 5px;
                font-size: 20px;
                height: 100px;
                width: 200px;
            }

        }

        #changeBgBtn {
            box-shadow: $shadow;
            display: flex;
            flex-direction: row;
            flex-shrink: 0;
            position: absolute;
            z-index: 100;
            cursor: pointer;
            @include blurWindow;
            transition: all .3s;
            padding: 20px;
            align-items: center;
            box-sizing: border-box;
            overflow-x: scroll;
            overflow-y: hidden;

            &::-webkit-scrollbar {
                height: 10px;

            }

            &::-webkit-scrollbar-thumb {
                background-color: white;
            }

            .skin {
                flex-shrink: 0;
                --item-width: 0px;
                --item-height: 0px;
                position: relative;
                width: var(--item-width);
                height: var(--item-height);
                background-color: black;
                margin: 10px;
                box-shadow: $shadow;

            }
        }

    }
</style>