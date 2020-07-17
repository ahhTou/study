<template>
    <div id="homeWrapper" ref="homeWrapper">
        <div id="title" @click="$router.push('/PrimeMenu')">
            Hello, ahhTou
        </div>
        <div id="btnLine">
            <div class="btn" id="board" @click="$router.push('/MsgBoard')">留言板</div>
            <div class="btn" @click="$router.push('/BgChanger')">切换背景</div>
        </div>
        <!-- 背景图片 -->
        <bg-shower/>

        <router-view/>
    </div>
</template>

<script>
    import BgChanger from 'src/components/BgChanger'
    import BgShower from '../components/BgShower'

    export default {
        name: 'Home',
        components: {
            BgShower,
            BgChanger,
        },
        data() {
            return {
                isPrimeWindowOpen: false
            }
        },
        methods: {
            openPrimeWindow() {
                this.isPrimeWindowOpen = !this.isPrimeWindowOpen
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import 'src/assets/css/style';
    @import 'src/assets/css/base';

    #btnLine {
        position: fixed;
        display: flex;
        width: 100vw;
        justify-content: flex-end;
        align-items: center;
        box-sizing: border-box;

        #board {
            background: rgb(234, 73, 95);
            color: white;
        }

        .btn {
            @include flex;
            box-shadow: $shadow;
            float: left;
            cursor: pointer;
            z-index: 98;
            box-sizing: border-box;
            padding: 10px;
            height: 50px;
            background-color: white;
            margin: 5px;
            border-radius: 5px;
        }

    }

    #homeWrapper {
        width: 100vw;
        box-sizing: border-box;


        @media screen and (min-width: 768px) {
            padding: 10px;
        }

        #title {
            @include flex;
            @include blurWindow;
            border-radius: 10px;
            position: fixed;
            z-index: 98;
            height: 100px;
            width: 200px;
            font-size: 20px;

            @media screen and (min-width: 768px) {
                bottom: 15px;
                left: 15px;
            }

            @media screen and (max-width: 768px) {
                bottom: 5px;
                left: 5px;

            }
        }
    }
</style>
