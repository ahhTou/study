<template>
<div>
  <h2>HelloVuex: {{ $store.state.counter }}</h2>
</div>
</template>


<script>
    export default {
    name:"HelloVuex",
    props:['counter']
    }

</script>


<style scoped>


</style>