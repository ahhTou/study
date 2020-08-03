<template>
    <div id="LrBtnLineWrapper">
        <button id="login" disabled="disabled">
            <span class="icon iconfont icon-close-bold"></span>
            登录
        </button>
        <button id="register" @click="$router.push('/Register')">
            <span class="icon iconfont icon-register"></span>
            注册
        </button>
    </div>
</template>

<script>
export default {
    name: 'LrBtnLine'
}
</script>

<style lang="scss" scoped>
@import 'src/assets/css/style';
@import 'src/assets/css/base';

#LrBtnLineWrapper {
    width: 100%;
    height: 100px;
    margin: auto;
    @include flex;
    position: fixed;
    bottom: 20px;

    button {
        margin: 5px;
        height: 40px;
        border-radius: 5px;
        width: 170px;
        background: white;
    }

    #login {
        cursor: not-allowed;

        color: white;
        font-size: 16px;
        background: rgb(115, 118, 119);
    }

    #register {
        cursor: pointer;

        color: white;
        font-size: 16px;
        background: rgb(75, 181, 220);
        box-shadow: 3px 3px 10px rgba(60, 63, 65, .4);
    }

}

</style>