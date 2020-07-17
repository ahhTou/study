<template>
    <div id="wrapper">
        <!-- 遮罩 -->
        <transition name="fade">
            <div id="shade" @click="exit" v-if="isOpen"></div>
        </transition>

        <!-- 单窗口内容 -->
        <transition name="bigger">
            <div id="center" @click="exit" v-if="isOpen">
                <slot name="center"></slot>
            </div>
        </transition>

        <!-- 带控件可滚动 -->
        <transition name="fade">
            <div id="centerWithScroll" @click="exit" v-if="isOpen">
                <slot name="content"></slot>
                <div id="controller" @click="exit">
                    <slot name="controller"></slot>
                </div>
            </div>
        </transition>

    </div>
</template>

<script>
    export default {
        name: 'ShadeSlots',
        data() {
            return {
                classData: 'close',
                isOpen: false,
            }
        },
        methods: {
            exit() {
                this.$nextTick(() => {
                    this.isOpen = false
                    setTimeout(() => {
                        this.$router.push('/')
                    }, 300)
                })
            },
        },
        mounted() {
            this.$nextTick(() => {
                this.isOpen = true
            })
        },

    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import 'src/assets/css/base';

    #wrapper {
        position: absolute;
        left: 0;
        top: 0;
        z-index: 200;
        width: 100vw;
        display: flex;

        #shade {
            position: fixed;
            height: 100vh;
            width: 100vw;
            top: 0;
            right: 0;
            color: black;
            backdrop-filter: blur(15px);
            background-color: rgba(42, 42, 42, 0.6);
        }

        /* 位于中央 */
        #center {
            position: fixed;
            top: 0;
            right: 0;
            height: 100vh;
            width: 100vw;

            display: flex;
            justify-content: center;
            align-items: center;
        }

        #centerWithScroll {
            position: absolute;
            left: 0;
            width: 100vw;
            display: flex;
            justify-content: center;

            #controller {
                position: fixed;

                width: 100vw;
                bottom: 0;
                left: 0;
                z-index: 200;

                display: flex;
                justify-content: center;
            }
        }

    }


</style>