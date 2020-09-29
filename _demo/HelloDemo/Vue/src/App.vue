<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>


export default {
    name: 'Home',
    mounted() {

    },
}

</script>

<style>
body {
    font-family: Helvetica, sans-serif, Arial, 'Microsoft YaHei UI';
}

* {
    padding: 0;
    margin: 0;
}

div#wrapper {

    width: 100vw;
    height: 100vh;

}

body::-webkit-scrollbar {
    display: none;
}
</style>