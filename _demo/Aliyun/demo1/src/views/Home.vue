<template>
    <div id="homeWrapper" ref="homeWrapper">
<!--        <div id="title" @click="$router.push('/PrimeMenu')">
            Hello, ahhTou
        </div>-->
        <div id="btnLine">
<!--            <div class="btn" @click="$router.push('/Register')">注册</div>-->
            <div class="btn" id="board" @click="$router.push('/MsgBoard')">留言板</div>
            <div class="btn" @click="$router.push('/BgChanger')">
                <span class="icon iconfont icon-skin"></span>
            </div>
        </div>
        <lr-btn-line/>

        <!-- 背景图片 -->
        <bg-shower/>

        <router-view/>
    </div>
</template>

<script>
import BgChanger from 'src/components/BgChanger'
import BgShower from '../components/BgShower'
import LrBtnLine from '../components/LrBtnLine'

export default {
    name: 'Home',
    components: {
        LrBtnLine,
        BgShower,
        BgChanger,
    },
    data() {
        return {
            isPrimeWindowOpen: false
        }
    },
    methods: {
        exit() {
            console.log('exit')
        },
        openPrimeWindow() {
            this.isPrimeWindowOpen = !this.isPrimeWindowOpen
        }
    },
    mounted() {

    },
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
        float: left;
        cursor: pointer;
        z-index: 98;
        box-sizing: border-box;
        padding: 10px;
        height: 50px;
        min-width: 50px;
        background-color: white;
        margin: 5px;
        border-radius: 5px;

        span {
            font-size: 25px;
            background-image: linear-gradient(135deg, deeppink, dodgerblue);
            -webkit-background-clip: text;
            box-decoration-break: clone;
            color: transparent;
        }

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
