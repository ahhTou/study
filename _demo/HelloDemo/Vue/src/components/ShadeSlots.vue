<template>
    <div id="shadeSlotsWrapper">
        <!-- 遮罩 -->
        <transition name="fade"
                    v-on:after-leave="leave">
            <div id="shade" @click="exit" v-show="isOpen"></div>
        </transition>

        <!-- 单窗口内容 -->
        <transition name="bigger"
                    v-on:after-leave="leave">
            <div id="center" @click="exit" v-show="isOpen && enterType.center">
                <slot name="center"></slot>
            </div>
        </transition>

        <!-- 可滚动 -->
        <transition name="left"
                    v-on:after-leave="leave">
            <div id="centerWithScroll" @click="exit" v-show="isOpen && enterType.centerWithScroll">
                <slot></slot>
            </div>
        </transition>
        <!-- 卡片式 -->
        <transition name="card-with-slider"
                    v-on:after-leave="leave">
            <div id="cardWithSlider" @click="exit" v-show="isOpen && enterType.card">
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
        leave: function () {
            this.$router.push('/')
        },
        exit() {
            this.$nextTick(() => {
                this.isOpen = false
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
#shadeSlotsWrapper {
    position: absolute;
    overflow: hidden;
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
        top: 0;
        left: 0;
        width: 100vw;
        display: flex;
        justify-content: center;
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