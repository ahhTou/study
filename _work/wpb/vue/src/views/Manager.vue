<template>
    <div id="managerWrapper">
        <transition name="fade">
            <router-view></router-view>
        </transition>
    </div>
</template>

<script>
    import Heart from "../components/Heart"

    export default {
        name: "Manger",
        components: {
            Heart
        },
    }
</script>

<style lang="scss" scoped>
    #managerWrapper {
        box-sizing: border-box;
        margin-left: 300px;
        padding: 20px;
        height: 100vh;
    }
    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }

    .fade-enter, .fade-leave-to {
        opacity: 0;
    }
</style>