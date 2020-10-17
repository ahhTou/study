<template>
    <shade-slots>
        <template #center>
            <div id="changerWrapper">
                <!-- 标题 -->
                <div id="title">更换背景</div>
                <!-- 内容 -->
                <div id="content" ref="bgChangerContent" @click.stop>
                    <!-- pc -->
                    <div
                        v-if="group === 'pc' "
                        v-for="item in bgImageList.pc"
                        class="skin"
                        :style="[computeSkin,skinBg(item)]"
                        @click.stop="clickToChangeBg(item)"
                    ></div>

                    <!-- mobile -->
                    <div
                        v-if="group === 'mobile' "
                        v-for="item in bgImageList.mobile"
                        class="skin"
                        :style="[computeSkin,skinBg(item)]"
                        @click.stop="clickToChangeBg(item)"
                    ></div>

                </div>
                <!-- 控件 -->
                <slider-bar/>

            </div>
        </template>
    </shade-slots>
</template>

<script>
import ShadeSlots from '../ShadeSlots'
import UtilsFactory from 'assets/factory/UtilsFactory'
import Base from 'assets/Bean/Base'
import SliderBar from 'components/BG/SliderBar'
import BgBus from 'components/BG/BgBus'
import Device from 'assets/utils/Device'

export default {
    name: 'BgChanger',
    components: {SliderBar, ShadeSlots},
    data() {
        return {
            bgData: null,
            group: 'pc',
            bgImageList: {
                size: {
                    width: null,
                    height: null,
                },
                pc: null,
                mobile: null,
            },
            skinWidth: null,
            skinHeight: null,
            skinItemStyle: null,
            skinItemBg: null,
            isPrimeWindowOpen: false,

        }
    },
    mounted() {
        // 两次判断，以防bug *
        if (Device.isPc()) {
            this.group = 'pc'
        } else {
            this.group = 'mobile'
        }

        BgBus.$on('changeGP', gp => {
            console.log(gp)
            this.group = gp
        })

        UtilsFactory.getAxios().cGet(Base.BgListApiUrl).then(res => {

            let base = new Base(res.data)
            this.bgImageList.mobile = base.getMobileUrl()
            this.bgImageList.pc = base.getPcUrl()
            this.skinWidth = base.getWidth()
            this.skinHeight = base.getHeight()

        })


    },
    computed: {
        computeSkin() {
            return {
                '--item-width': this.skinWidth + 'px',
                '--item-height': this.skinHeight + 'px',
                backgroundPosition: 'center',
                backgroundSize: 'cover'
            }
        },
        childSliderClassName() {
            console.log(this.$children)
            return this.$children
        }

    },
    methods: {
        skinBg(item) {
            return {
                backgroundImage: 'url("' + item + '")',
            }
        },

        clickToChangeBg(url) {
            BgBus.$emit('changeBG', Base.getTrueUrl(url))
        },
    },
}
</script>

<style lang="scss" scoped>
#changerWrapper {
    @include flex;
    position: fixed;
    z-index: 200;
    width: 70vw;

    flex-direction: column;
    flex-shrink: 0;

    box-sizing: border-box;

    #title {
        @include flex;
        font-size: 1.5rem;
        letter-spacing: 0.3rem;
        color: white;
    }

    #content {
        margin: 10px;
        background-color: rgba(241, 243, 244, .6);
        border-radius: 15px;
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
            height: 15px
        }

        &::-webkit-scrollbar-thumb {
            background-color: white;
        }

        &::-webkit-scrollbar-button {
            width: 8px;
            height: 20px;
        }
    }
}

.skin {

    flex-shrink: 0;
    --item-width: 0px;
    --item-height: 0px;
    position: relative;
    width: var(--item-width);
    height: var(--item-height);
    margin: 10px;
    cursor: pointer;
    border-radius: 15px;

}


</style>
