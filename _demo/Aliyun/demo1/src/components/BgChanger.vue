<template>
    <div id="bgChangerWrapper" :style="bgData" ref="bgChangerWrapperRef" @click="closeBgWindow">
        <transition name="fade">
            <div id="shade" v-show="isOpen"></div>
        </transition>
        <div id="changeBgBtn"
             ref="skinWindow"
             @click.stop="changeBg"
             :style="changerStyle"
        >
            <transition>
                <div id="title" v-show="isOpen">
                    更 换 背 景
                </div>
            </transition>

            <div id="content" ref="bgChangerContent">
                <div v-show="isOpen"
                     v-for="item in bgImageList"
                     class="skin"
                     :style="[skinItemStyle,skinBg(item)]"
                     @click.stop="clickToChangeBg(item)"
                ></div>
            </div>

        </div>
        <div style="position: absolute;z-index: -100"></div>
    </div>
</template>

<script>
    export default {
        name: "BgChanger",
        data() {
            return {
                bgData: null,
                bgImageList: {
                    bg1: "http://39.99.154.145/img/hello/bg1.jpg",
                    bg2: "http://39.99.154.145/img/hello/bg2.jpg",
                    bg3: "http://39.99.154.145/img/hello/bg3.jpg",
                    bg4: "http://39.99.154.145/img/hello/bg4.jpg",
                },
                isOpen: false,
                skinItemStyle: null,
                skinItemBg: null,
                isPrimeWindowOpen: false,
                changerStyle: {
                    '--hx': 1,
                    '--hw': 1,
                }

            }
        },
        mounted() {
            this.computeWindow()

            let $dom = this.$refs.skinWindow;
            $dom.className = 'animeOut'

            let bg = window.localStorage.getItem("theHelloBg")
            if (bg === null || bg === '' || bg === undefined) {
                this.bgData = {
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    backgroundImage: "url(\"" + this.bgImageList.bg1 + "\")",
                }
            } else {
                this.bgData = {
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    backgroundImage: "url(\"" + bg + "\")",
                }
            }

            window.addEventListener("resize", () => {
                this.computeWindow()
            })
        },
        methods: {
            skinBg(item) {
                return {
                    backgroundImage: "url(\"" + item + "\")",
                }
            },
            computeSkin() {
                let $dom = this.$refs.bgChangerContent
                let height = Math.round($dom.offsetHeight * 0.8)
                let width = Math.round($dom.offsetWidth * 0.3)
                this.skinItemStyle = {
                    '--item-width': width + 'px',
                    '--item-height': height + 'px',
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                }
            },
            computeWindow() {
                let $window = this.$refs.bgChangerWrapperRef;
                let height = $window.offsetHeight * 0.4
                let width = $window.offsetWidth * 0.7
                let h = 50 / height
                let w = 50 / width
                this.changerStyle = {
                    '--hx': h,
                    '--hw': w,
                }
            },
            changeBg() {
                if (!this.isOpen) {
                    this.isOpen = true

                    let $dom = this.$refs.skinWindow;
                    $dom.className = 'animeTo'
                    this.computeWindow()
                    this.computeSkin()
                } else {
                    this.closeBgWindow();
                }
            },
            closeBgWindow() {
                this.isOpen = false

                let $dom = this.$refs.skinWindow;
                $dom.className = 'animeOut'
            },
            clickToChangeBg(item) {
                this.bgData = {
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    backgroundImage: "url(\"" + item + "\")",
                }
                window.localStorage.setItem("theHelloBg", item)
            },
        }
    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import "src/assets/css/base";

    #bgChangerWrapper {
        @include wrapper;
        position: absolute;

        #shade {
            @include wrapper;
            @include bgBlur;
            z-index: 99;
            position: absolute;
        }


        #changeBgBtn {
            @include flex;
            position: fixed;
            z-index: 100;
            transform-origin: top right;
            width: 70vw;
            height: 60vh;

            flex-direction: column;
            flex-shrink: 0;

            transition: all .3s;
            box-sizing: border-box;

            #title {
                position: absolute;
                font-size: 2rem;
                width: 70vw;
                height: 50px;
                top: 0;
                margin-bottom: 30px;
                color: white;
            }

            #content {
                @include blurWindow;
                cursor: pointer;
                position: relative;
                box-shadow: $shadow;
                width: 70vw;
                height: 40vh;
                display: flex;
                flex-direction: row;
                align-items: center;
                flex-shrink: 0;

                overflow-x: scroll;
                overflow-y: hidden;

                &::-webkit-scrollbar {
                    height: 10px;
                }

                &::-webkit-scrollbar-thumb {
                    background-color: white;
                    border-radius: 10px;
                }
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
            }
        }
    }

    .animeTo {
        animation: anmTo .3s forwards;
    }

    .animeOut {
        animation: anmOut .3s forwards;
    }

    @keyframes anmTo {
        0% {
            top: 30px;
            right: 30px;
            transform: scale(var(--hw), var(--hx)) translate(50%, -50%);
        }
        100% {
            top: 50%;
            right: 50%;
            transform: translate(50%, -50%);
        }
    }

    @keyframes anmOut {
        0% {
            top: 50%;
            right: 50%;
            transform: translate(50%, -50%);
        }
        100% {
            top: 30px;
            right: 30px;
            transform: scale(var(--hw), var(--hx)) translate(50%, -50%);
        }
    }
</style>