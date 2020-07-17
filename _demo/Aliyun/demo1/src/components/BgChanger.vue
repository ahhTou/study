<template>
    <shade-slots>
        <template #center>
            <div id="changerWrapper">
                <div id="title">
                    更 换 背 景
                </div>
                <div id="content" ref="bgChangerContent" @click.stop>
                    <div
                            v-for="item in bgImageList"
                            class="skin"
                            :style="[skinItemStyle,skinBg(item)]"
                            @click.stop="clickToChangeBg(item)"
                    ></div>
                </div>

            </div>
        </template>
    </shade-slots>
</template>

<script>
    import ShadeSlots from './ShadeSlots'

    export default {
        name: 'BgChanger',
        components: {ShadeSlots},
        data() {
            return {
                bgData: null,
                bgImageList: {
                    bg1: 'http://39.99.154.145/img/hello/bg1.jpg',
                    bg2: 'http://39.99.154.145/img/hello/bg2.jpg',
                    bg3: 'http://39.99.154.145/img/hello/bg3.jpg',
                    bg4: 'http://39.99.154.145/img/hello/bg4.jpg',
                },
                skinItemStyle: null,
                skinItemBg: null,
                isPrimeWindowOpen: false,

            }
        },
        mounted() {
            setTimeout(() => {
                this.computeSkin()
            })

        },
        methods: {
            skinBg(item) {
                return {
                    backgroundImage: 'url("' + item + '")',
                }
            },
            computeSkin() {
                let $dom = this.$refs.bgChangerContent
                this.skinItemStyle = {
                    '--item-width': Math.round($dom.offsetWidth * 0.3) + 'px',
                    '--item-height': Math.round($dom.offsetHeight * 0.8) + 'px',
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                }
            },
            clickToChangeBg(item) {
                this.$store.commit('changeBg', item)
                console.log(this.$store.state.bgUrl)

            },
        }
    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import "src/assets/css/base";


    #changerWrapper {
        @include flex;
        position: fixed;
        z-index: 200;
        width: 70vw;
        height: 60vh;

        flex-direction: column;
        flex-shrink: 0;

        box-sizing: border-box;

        #title {
            @include flex;
            position: absolute;
            font-size: 2rem;
            width: 70vw;
            height: 50px;
            top: 0;
            margin-bottom: 30px;
            color: white;
        }

        #content {
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
        background-color: black;
        margin: 10px;
        cursor: pointer;
    }


</style>