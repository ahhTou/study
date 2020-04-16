<template>
  <div id="app">
    <!-- <router-link to='/home' tag="button" replace active-class='active'>首页</router-link>
    <router-link to='/about' tag="button" replace active-class='active'>关于</router-link> -->
    <router-link to='/home' tag="button" replace >首页</router-link>
    <router-link to='/about' tag="button" replace >关于</router-link>
    <router-link :to="'/user/' + userId" tag="button" replace >用户</router-link>
    <router-link :to="{path:'/profile', query:{age:18, gender:'男'}}" tag="button" replace >档案</router-link>
    <!-- <button @click="ckHome">首页</button>
    <button @click="ckAbout">首页</button> -->
    <!-- 排除档案 -->
    <keep-alive  include="" exclude="Profile,User">
    <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      userId:'偷偷呼吸的死肥宅'
    }
  },
  methods: {
    ckHome() {
      if(this.$router.history.current.path!='/home'){
      this.$router.push('/home')
      }else {
         this.$router.go(0);
      }
      console.log('跳转到首页');
      
      },
    ckAbout(){
      if(this.$router.history.current.path!='/about'){
      this.$router.push('/about')
      }else {
         this.$router.go(0);
      }
      console.log('跳转到关于');
    }
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.active{
  color: red;
}
</style>
