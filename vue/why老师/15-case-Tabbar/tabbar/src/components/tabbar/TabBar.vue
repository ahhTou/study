<template>
<div>
  <div class="tab-bar">
    <slot></slot>
  </div>
</div>
</template>


<script>
    export default {
    name:"",
    }

</script>


<style scoped>


  .tab-bar{
    display: flex;
    position: fixed;
    right: 0;
    left: 0;
    bottom: 0;
    box-shadow: 0px -2px 1px rgba(100 , 100, 100, 0.5);
    background: #dadada;
    height: 49px;
  }

  

</style>