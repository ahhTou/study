<template>
    <div id="wrapper">
        <!-- 遮罩 -->
        <transition name="fade">
            <div id="shade" @click="exit" v-if="isOpen"></div>
        </transition>

        <!-- 单窗口内容 -->
        <transition name="bigger">
            <div id="center" @click="exit" v-if="isOpen && enterType.center">
                <slot name="center"></slot>
            </div>
        </transition>

        <!-- 带控件可滚动 -->
        <transition name="fade">
            <div id="centerWithScroll" @click="exit" v-if="isOpen && enterType.centerWithScroll">
                <slot name="content"></slot>
                <div id="controller" @click="exit">
                    <slot name="controller"></slot>
                </div>
            </div>
        </transition>
        <!-- 卡片式 -->
        <transition name="card-with-slider">
            <div id="cardWithSlider" @click="exit" v-if="isOpen && enterType.card">
                <slot name="card" :slotIsOpen="isOpen"></slot>
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
            timer: 200,
            enterType: {
                centerWithScroll: false,
                center: false,
                card: false,
            }
        }
    },
    methods: {
        exit() {
            this.$nextTick(() => {
                this.isOpen = false
                setTimeout(() => {
                    this.$router.push('/')
                }, this.timer)
            })
        },
    },
    mounted() {
        this.$on('exit', () => {
            console.log('exit')
        })
        this.$nextTick(() => {
            this.isOpen = true
            switch (this.$route.meta.EnterType) {
                case 'card':
                    this.enterType.card = true
                    break
                case 'center':
                    this.enterType.center = true
                    break
                case 'centerWithScroll':
                    this.enterType.centerWithScroll = true
                    break
            }
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

    #cardWithSlider {
        height: 550px;
        width: 100vw;
        position: fixed;
        background-color: white;
        bottom: 0;
        left: 0;
        box-sizing: border-box;
        border-radius: 40px 40px 0 0;
    }
}


</style>