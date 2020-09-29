<template>
    <div id="controlWrapperForSliderBar">
        <div id="bar" @click.stop>
            <div id="barWrapper">
                <div @click="changeGroup('pc')"
                     class="icon iconfont icon-electronics pointer"
                     id="pc"></div>


                <div @click="changeGroup('mobile')"
                     class="icon iconfont icon-mobile-phone pointer"
                     id="mobile"></div>

                <div class="slider" :class="sliderClassName"></div>
            </div>
        </div>
    </div>
</template>

<script>
import Device from 'assets/utils/Device'
import BgBus from 'components/BG/BgBus'

export default {
    name: 'SliderBar',
    data() {
        return {
            sliderClassName: null,
        }
    },
    methods: {
        changeGroup(gp) {
            this.sliderClassName = gp
            BgBus.$emit('changeGP', gp)
        }
    }
    ,
    mounted() {
        if (Device.isPc()) {
            this.changeGroup('pc')
        } else {
            this.changeGroup('mobile')
        }
    }
}
</script>
<style lang="scss" scoped>

#controlWrapperForSliderBar {
    $cW: 10rem;
    $cH: 3rem;
    @include flex;
    flex-direction: column;

    #bar {
        @include flex;
        position: relative;
        border-radius: $cH;
        width: $cW;
        height: $cH;
        background-color: rgba(241, 243, 244, .6);

        #barWrapper {
            @include flex;
            position: relative;
            width: $cW;
            height: $cH;
            transform: scale(0.9);

            div {
                @include flex;
                flex: 1;
                z-index: 10;
                font-size: $cH * 0.7;
            }


            div.slider {
                position: absolute;
                border-radius: $cH;
                width: 50%;
                height: 100%;
                background-color: dodgerblue;
                left: 0;
                z-index: 1;
                transition: all .3s;
            }

            &:hover > .slider {
                transform: translateX(0);
            }

            .mobile {
                transform: translateX(100%);
            }

            #mobile:hover + .slider {
                transform: translateX(100%);
            }
        }
    }
}
</style>