<template>
  <div id="app">
    <!-- <ahh></ahh> -->
    <router-view></router-view>
    <tab-bar>
      <tab-bar-item v-for="(item, index) in items" v-bind:key="index" :path="item.link">
        <template v-slot:item-icon>
            <img src="./assets/img/tabbar/1.svg">
        </template>

        <template v-slot:item-icon-active>
            <img src="./assets/img/tabbar/2.svg">
        </template>

        <template v-slot:item-text>
            <div>{{ item.title }}</div>
        </template>

      </tab-bar-item>
    </tab-bar>
  </div>
</template>

<script>
import TabBar from './components/tabbar/TabBar.vue'
import TabBarItem from './components/tabbar/TabBarItem.vue'
import TabBarSetting from './components/tabbar/Setting.vue'
// import Ahh from './components/mytest/Ahh.vue'
const itemAll = TabBarSetting.items;
export default {
  name: 'App',
  data() {
    return {
      items :itemAll
    }
  },
  components:{
    TabBar,
    TabBarItem,
    // Ahh

  }
}
</script>

<style>
  @import "./assets/css/base.css";
  .active{
    color: rgb(23,104,220);
  }

</style>
