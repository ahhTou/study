<template>
  <div>
    <h1>我是首页</h1>
    <router-link to="/home/news">新闻</router-link>
    <router-link to="/home/message">信息</router-link>
    <router-view></router-view>
  </div>
</template>


<script>
export default {
  name: "Home",
  data() {
    return {
      path:'/home/news'
    }
  },
  created() {
    console.log('主页被创建了');
    
  },
  destroyed() {
    console.log('主页销毁了');
  },
  //activated和deactivated仅在<keep-alive>中有效
  activated() {
    //活跃的
    console.log('主页活跃的');
    this.$router.push(this.path);
  },
  deactivated() {
    
  },
  beforeRouteLeave (to, from, next) {
    this.path = this.$route.path;
    next()
  } 
  // deactivated() {
  //   //不活跃的
  //   console.log('主页不活跃了')
  //   this.path = push(this.$router.path);
  // },
};
</script>


<style scoped>
</style>