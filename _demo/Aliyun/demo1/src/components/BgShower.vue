<template>
    <div id="bgShower" :style="[base,bgData]"></div>
</template>

<script>
    export default {
        name: 'BgShower',
        data() {
            return {
                bgData: null,
                base: {
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                }
            }
        },
        mounted() {
            this.check()
        },
        methods: {
            check() {
                let bg = window.localStorage.getItem('theHelloBg')
                if (bg === null || bg === '' || bg === undefined) {
                    this.bgData = {

                        backgroundImage: 'url("' + this.$store.state.bgUrl + '")',
                    }
                } else {
                    this.bgData = {
                        backgroundImage: 'url("' + bg + '")',
                    }
                }
            }
        },
        computed: {
            bgDataFromVuex() {
                return this.$store.state.bgUrl
            }
        },
        watch: {
            bgDataFromVuex(val) {
                this.bgData = {
                    backgroundImage: 'url("' + val + '")',
                }
                window.localStorage.setItem('theHelloBg', val)
            }

        }
    }
</script>

<style scoped>
    #bgShower {
        position: fixed;
        right: 0;
        top: 0;
        height: 100vh;
        width: 100vw;
        z-index: -100;
    }
</style>