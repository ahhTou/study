<template>
    <div id="primeMenuWrapper" v-if="isOpenInner" ref="primeMenuWrapper" @click.stop="close">
        <div id="wrapper">
            <div id="title"><strong>曾经的demo</strong></div>
            <div id="content">
                <a v-for="item in websiteData" :href="item.url">
                    {{item.title}}
                </a>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PrimeMenu",
        props: {
            isOpen: Boolean,
        },
        data() {
            return {
                websiteData: {
                    web1: {
                        title: '彩虹六号',
                        about: '大一网页设计课期末作业',
                        url: 'http://39.99.154.145/prime/r6s/'
                    },
                    web2: {
                        title: 'Hello,ahhTou',
                        about: '第一次学习Jquery的小Demo',
                        url: 'http://39.99.154.145/prime/jq/'
                    }
                },
                isOpenInner: false,
            }
        },
        methods: {
            close() {
                this.$nextTick(() => {
                    let $dom = this.$refs.primeMenuWrapper
                    $dom.style.opacity = "0"
                    $dom.style.transform = "scale(1.3)"
                    setTimeout(() => {
                        this.isOpenInner = false
                        $dom.style.transform = "scale(1.3)"
                    }, 300)
                })
            },
            open() {
                this.isOpenInner = true
                this.$nextTick(() => {
                    setTimeout(() => {
                        let $dom = this.$refs.primeMenuWrapper
                        $dom.style.transition = 'all 0.3s'
                        $dom.style.opacity = "1"
                        $dom.style.transform = "scale(1.0)"
                    }, 1)
                })
            }
        },
        watch: {
            isOpen() {
                this.open()
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import "src/assets/css/style";
    @import "src/assets/css/base";

    #primeMenuWrapper {
        @include wrapper;
        @include flex;
        position: absolute;
        top: 0;
        right: 0;
        z-index: 100000;
        @include bgBlur;
        opacity: 0;
        transition: all .3s;
        overflow: hidden;
        transform: scale(1.3);

        #wrapper {
            background: white;
            border-radius: 35px;
            width: 300px;
            display: flex;
            flex-direction: column;

            #title {
                @include flex;
                height: 70px;
            }

            #content {
                flex: 1;
                border-radius: 0 0 35px 35px;
                overflow-y: scroll;
                overflow-x: hidden;
                background-color: rgb(237, 237, 238);

                &::-webkit-scrollbar {
                    display: none;
                }

                a {
                    @include flex;
                    height: 75px;
                    font-size: 20px;
                    width: 100%;
                    color: black;
                }

            }
        }
    }
</style>