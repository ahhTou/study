<template>
    <div id="managerWrapper">
        <router-view></router-view>
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
</style>