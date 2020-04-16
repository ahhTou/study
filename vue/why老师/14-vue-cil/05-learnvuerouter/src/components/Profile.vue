<template>
  <div>
    <h1>我是Profile档案</h1>
    <h5 v-for="(item, index) in $route.query " :key="index">{{ item }}</h5>
  </div>
</template>

<script>
export default {
  name: "Profile",
  created() {
    console.log('档案被创建了');
  },
  destroyed() {
    console.log('档案被销毁了')
  },
};
</script>


<style scoped>
</style>