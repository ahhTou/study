<template>
    <div id="app">
        <heart/>
        <transition name="fade">
            <router-view></router-view>

        </transition>
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

    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }

    .fade-enter, .fade-leave-to {
        opacity: 0;
    }
</style>
