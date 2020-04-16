<template>
  <div id="app">
    <h1>{{  message }}</h1>
    <h1>modulesA中的 {{  $store.state.a.message }}</h1>
    <button @click="ckname">修改↑</button>
    <h1>modulesA中的getters: {{ $store.getters.fullName }}</h1>
    <h2>App: {{ $store.state.counter }}</h2>
    <button @click="updatedIt">改变</button>
    <hello-vuex></hello-vuex>
    <button @click="addition" >+</button>
    <button @click="subtraction" >-</button>
    <button @click="addCount(5)" ><h2>+</h2></button>
    <button @click="addCount(10)" ><h1>+</h1> </button>
    <h2>Getters: {{ $store.getters.powerCounter }}</h2>
    <h3>Students.age>20: {{ $store.getters.more20stu }}</h3>
    <h4>大于20岁人的个数: {{ $store.getters.more20stuLength }}</h4>
    <h4>自定义人数过滤: {{ $store.getters.moreAgeStu(1) }}</h4>
  </div>
</template>
T
<script>
import HelloVuex from './components/HelloVuex'
import * as ct  from './store/mutations-types'

export default {
  name: 'App',
  data() {
    return {
      message:'我是主页',
    }
  },
  methods: {
    ckname() {
      this.$store.commit('updateName', '我吊你妈的')
    },
    addition() {
      this.$store.commit(ct.INCREMENT)
    },
    subtraction() {
      this.$store.commit('decrement')
    },
    addCount(count) {
      //标准传参，也可以是对象
      // this.$store.commit('incrementCount', count)
      //或者这样，直接提交一个对象
      this.$store.commit({
        type:'incrementCount',
        count
      })
      
    },
    updatedIt() {
      //payLoad携带参数
      //  this.$store.dispatch('aUpdateInfo','我是payload')

      //payload携带函数
      //  this.$store.dispatch('aUpdateInfo',() =>{
        //    console.log('我是payload携带的函数中的log');
      //  })

      //携带对象啊
      //  this.$store.dispatch('aUpdateInfo',{
      //    message:"我是信息",
      //    funit: () => {
      //      console.log('函数信息');
           
      //    }
      //  })
      //返回Promise
      this.$store
      .dispatch('aUpdateInfo','我是Promise')
      .then(res => {
        console.log('promise回来了')
        console.log(res);
        
      })

    },
  },
  computed: {

  },
  components:{
    HelloVuex
  }
}
</script>

<style>

</style>
