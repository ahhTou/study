import Vue from 'vue'
import Vuex from 'vuex'
import * as a  from './mutations-types'

//安装插件
Vue.use(Vuex)

const modulesA = {
  state:{
    message:'我是modulesA中的message'
  },
  mutations:{
    updateName(state, payload){
      state.message= payload
    }
  },
  actions:{

  },
  getters:{
    fullName(state, getters, rootState){
      return rootState.counter
    }
  }
}

const store = new Vuex.Store({
  state:{
    //保存状态
    counter: 0,
    students: [
      {id:100, name:'ahhtou', age:20},
      {id:101, name:'Kobe', age:25},
      {id:102, name:'jack', age:10},
      {id:103, name:'jerry', age:10}
    ]
  },
  actions:{
    //异步操作
    //content 上下文
    aUpdateInfo(content, payload){
      // setTimeout(() => {
        //携带参数
        // console.log(payload)

        //携带函数
        // payload()

        //携带对象
        // console.log(payload.message)
        // payload.funit()

        
        // }, 1000)

        //返回个Promise
      return new Promise((resolve, reject) => {
        setTimeout(()=>{
          content.commit('updatedInfo')
          console.log(payload)
          resolve('Promise返回的信息')
        }, 1000)

      })
    }
  },
  mutations:{
    //方法
    //时间类型（回调函数）{}
    [a.INCREMENT](state) {
      state.counter++
    },
    decrement(state) {
      state.counter--
    },
    incrementCount(state, payload) {
      console.log(payload);
      
      state.counter+= payload.count
    },
    updatedInfo(state) {
      //响应式更新
      Vue.set(state.students, 3, {gender:'男'})
      // Vue.delete
    },
  },
  getters:{
    //计算属性
    powerCounter(state) {
      return state.counter * state.counter
    },
    more20stu(state) {
      return state.students.filter(s => s.age >=20)
    },
    more20stuLength(state, getters) {
      return getters.more20stu.length
    },
    moreAgeStu(state) {
      // return function(age){
      //   return state.students.filter(s => s.age >= age)
      // }
      return age => {
        return state.students.filter(s => s.age >= age) 
      }
    },
  },
  modules:{
    //划分模块
    a: modulesA

  }
})

export default store