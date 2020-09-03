<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>

import MyAxios from 'assets/utils/axios'
import UtilsFactory from 'assets/factory/UtilsFactory'

export default {
    name: 'Home',
    mounted() {
    },
}
</script>

<style lang="scss">
* {
    padding: 0;
    margin: 0;
}

body::-webkit-scrollbar {
    display: none;
}
</style>