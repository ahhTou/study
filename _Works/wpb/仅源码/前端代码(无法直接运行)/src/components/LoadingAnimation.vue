<template>
    <div id="animation" ref="thisWrapper">
        <span v-for="count in 6">·</span>
    </div>
</template>

<script>
    export default {
        name: "LoadingAnimation",
        props: {
            startLoading: Boolean
        },
        data() {
            return {
                timerInterval: null
            }
        },
        mounted() {
            console.log("> 动画组件载入了", this.startLoading)
        },
        methods: {
            toStartLoading() {
                this.$nextTick(() => {
                    console.log("> 加载动画启动了")

                    let $animation = this.$refs.thisWrapper

                    $animation.className = 'animationIn'

                    let timerOut = null

                    this.timerInterval = setInterval(() => {

                        timerOut = null

                        $animation.className = 'animationOut'

                        timerOut = setTimeout(() => {
                            $animation.className = 'animationIn'
                        }, 200)

                    }, 1400)

                })

            },


            toStopLoading() {
                console.log("> 加载动画停止了\r\n")
                this.timerInterval = null
                this.startLoading = false
            }
        },
        watch: {
            startLoading: {
                handler: function (val) {
                    if (val === true) this.toStartLoading();
                    if (val === false) this.toStopLoading();
                },
                immediate: true
            }
        }

    }
</script>

<style lang="scss" scoped>

    @mixin anmPackageIn($time,$delay) {
        opacity: 0;
        animation-name: anmIn;
        animation-duration: $time;
        animation-delay: $delay;
        animation-fill-mode: forwards;
        @keyframes anmIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }


    }

    @mixin anmPackageOut() {
        animation: anmOut .2s forwards;
        @keyframes anmOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
    }

    .animationIn {
        span {
            @for $i from 1 through 6 {
                &:nth-child(#{$i}) {
                    @include anmPackageIn(0.2s * $i, 0.2s * $i -0.2s);
                }
            }
        }
    }

    .animationOut {
        span {
            &:nth-child(n) {
                opacity: 1;
                @include anmPackageOut()
            }

        }
    }
</style>