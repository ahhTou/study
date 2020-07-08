<template>
    <div id="app">
        <button @click="send">
            点击我
        </button>
        <button @click="send2">
            点击我2
        </button>
        <button @click="send3">
            点击我3
        </button>
    </div>
</template>
<script>
    import {ajax} from './network/test'

    export default {
        name: "App",
        methods: {
            send() {
                ajax().then(res => {
                    console.log("成功了" + res.data)
                }, error => {
                    console.log("失败了")
                })
            },
        }
    }
</script>

<style>
</style>
