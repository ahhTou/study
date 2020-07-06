<template>
    <div id="homeWrapper">
        <div id="title">
            Hello, ahhTou
        </div>
    </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style lang="scss" scoped>
    @mixin flex {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    @mixin blurWindow {
        color: black;
        backdrop-filter: blur(15px);
        background-color: rgba(255, 255, 255, .6);
        box-shadow: 10px 10px 30px rgba(100, 100, 100, 0.5);
    }

    #homeWrapper {
        height: 100vh;
        width: 100%;
        box-sizing: border-box;
        background-size: cover;
        background-position: center;
        background-image: url("../assets/img/bg1.jpg");
        @media screen and (min-width: 768px) {
            padding: 30px;
        }

        #title {
            @include flex;
            position: relative;
            @include blurWindow;

            @media screen and (min-width: 768px) {
                border-radius: 30px;
                font-size: 40px;
                width: 400px;
                height: 150px;
            }

            @media screen and (max-width: 768px) {
                position: absolute;
                bottom: 5px;
                left: 5px;
                font-size: 20px;
                height: 100px;
                width: 200px;
            }

        }

    }
</style>