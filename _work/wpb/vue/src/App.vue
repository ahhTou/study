<template>
    <div id="app">
        <heart/>
        <router-view></router-view>
    </div>
</template>
<script>
    import Heart from "./components/Heart"

    export default {
        name: "App",
        components: {
            Heart
        },
    }
</script>
<style>
    * {
        padding: 0;
        margin: 0;
    }

    html,
    body {
        height: 100vh;
        width: 100vw;
    }

    body {
        background: url("assets/img/bg1.jpg");
        background-size: cover;

    }

    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>
