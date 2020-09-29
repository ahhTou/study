<template>
    <button id="btnCircle" class="pointer" :style="[btnStyle,bgColor]">
        <transition name="fade">
            <span id="svg" class="icon iconfont" :class="btnSvgStyle" v-show="!isLoading"></span>
        </transition>
        <transition name="fade">
            <loading-animation :style="{position: 'absolute'}" v-show="isLoading"/>
        </transition>
    </button>
</template>

<script>
import LoadingAnimation from './LoadingAnimation'

export default {
    name: 'BtnCircle',
    components: {LoadingAnimation},
    props: {
        /* className */
        btnSvgStyle: {
            type: String,
            default: 'icon-direction-right'
        },
        size: {
            type: String,
            default: '100px',
        },
        color: {
            type: String,
            default: 'black',
        },
        bgColorActive: {
            type: String,
            default: 'rgb(75,181,220)',
        },
        bgColorDisable: {
            type: String,
            default: 'rgb(163,163,163)'
        },
        isActive: {
            type: Boolean,
            default: false,
        },
        isLoading: {
            type: Boolean,
            default: false,
        },
        showShadow: {
            type: String,
            default: 'none',
        }

    },
    computed: {
        btnStyle() {
            return {
                '--size': this.size,
                '--color': this.color,
                '--font-size': this.size,
                '--bs': this.showShadow,
            }
        },
        bgColor() {
            if (this.isActive) {
                return {
                    '--bgColor': this.bgColorActive,
                }
            } else {
                return {
                    '--bgColor': this.bgColorDisable,
                }
            }
        }
    },
}
</script>

<style lang="scss" scoped>

#btnCircle {
    @include flex;
    --size: 100px;
    --color: black;
    --bgColor: none;
    --bs: none;
    width: var(--size);
    height: var(--size);
    color: var(--color);
    border-radius: calc(var(--size) / 2);
    font-size: calc(var(--font-size) / 2.5);
    background-color: var(--bgColor);
    transition: all .2s;
    box-shadow: var(--bs);

    #svg {
        @include flex;
        width: var(--size);
        height: var(--size);
        font-size: calc(var(--font-size) / 2.5);

    }
}
</style>