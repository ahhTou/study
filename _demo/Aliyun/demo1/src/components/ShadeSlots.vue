<template>
    <div id="wrapper">

        <!-- 遮罩 -->
        <div id="shade"></div>

        <!-- 内容 -->
        <div id="content">
            <slot></slot>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'ShadeSlots'
    }
</script>

<style lang="scss" scoped>

    #shade {
        position: fixed;
        height: 100vh;
        width: 100vw;
        top: 0;
        right: 0;

        color: black;
        backdrop-filter: blur(15px);
        background-color: rgba(42, 42, 42, 0.6);

    }

</style>