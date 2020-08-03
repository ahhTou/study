<template>
    <div id="LoadingBlockWrapper" :style="wrapperStyle">
        <transition name="bigger">
            <div v-show="!isLoading">
                <slot></slot>
            </div>
        </transition>
        <transition name="fade">
            <div style="font-size: 30px;position: absolute;" v-show="isLoading">
                <div :style="loadingStyle">
                    <loading-animation/>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import LoadingAnimation from './LoadingAnimation'

export default {
    name: 'LoadingBlock',
    components: {LoadingAnimation},
    props: {
        isLoading: {
            type: Boolean,
            default: false,
        },
        blockWidth: {
            type: String,
            default: '300px'
        },
        blockHeight: {
            type: String,
            default: 'unset'
        },
        loadingColor: {
            type: String,
            default: 'white',
        },
    },
    computed: {
        wrapperStyle() {
            return {
                '--wrapper-with': this.blockWidth,
                '--wrapper-height': this.blockHeight,
            }
        },
        loadingStyle() {
            return {
                'color': 'white',
            }
        }
    }
}
</script>

<style lang="scss" scoped>
#LoadingBlockWrapper {
    @import 'src/assets/css/style';
    @import 'src/assets/css/base';

    --wrapper-with: 10px;
    --wrapper-height: 10px;

    width: var(--wrapper-with);
    height: var(--wrapper-height);

    display: flex;
    justify-content: center;
    align-items: center;
    flex: 1;
    position: relative;
    transition: all .5s;
}
</style>