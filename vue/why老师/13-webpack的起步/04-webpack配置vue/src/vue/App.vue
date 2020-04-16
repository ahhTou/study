<template>
    <div class="title">
    {{ message }}
    </div>
</template>


<script>
export default {
    name:"App",
    data() {
        return {
            message: 'Vue终极解决方案'
        }
    }
}
</script>


<style scoped>
    .title{
        color: green;
    }

</style>