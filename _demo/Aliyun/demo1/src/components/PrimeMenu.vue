<template>
    <shade-slots>
        <template #center>
            <div id="wrapperForPrimeMenu" @click.stop>
                <div id="title"><strong>曾经的demo</strong></div>
                <div id="content">
                    <a v-for="item in websiteData" :href="item.url">
                        {{item.title}}
                    </a>
                </div>
            </div>
        </template>
    </shade-slots>
</template>

<script>
    import ShadeSlots from './ShadeSlots'

    export default {
        name: 'PrimeMenu',
        components: {
            ShadeSlots,
        },
        props: {
            isOpen: Boolean,
        },
        data() {
            return {
                websiteData: {
                    web1: {
                        title: '彩虹六号',
                        about: '大一网页设计课期末作业',
                        url: 'http://39.99.154.145/prime/r6s/'
                    },
                    web2: {
                        title: 'Hello,ahhTou',
                        about: '第一次学习Jquery的小Demo',
                        url: 'http://39.99.154.145/prime/jq/'
                    }
                },
            }
        },
    }
</script>

<style lang="scss" scoped>
    @import "src/assets/css/style";
    @import "src/assets/css/base";


    #wrapperForPrimeMenu {
        background: white;
        border-radius: 35px;
        width: 300px;
        display: flex;
        flex-direction: column;
        #title {
            @include flex;
            height: 70px;
        }

        #content {
            flex: 1;
            border-radius: 0 0 35px 35px;
            overflow-y: scroll;
            overflow-x: hidden;
            background-color: rgb(237, 237, 238);

            &::-webkit-scrollbar {
                display: none;
            }

            a {
                @include flex;
                height: 75px;
                font-size: 20px;
                width: 100%;
                color: black;
            }

        }
    }
</style>