import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import actions from './actions'
import getters from './getters'
import modulesA from './modules/a'


Vue.use(Vuex)


const state = {
  counter: 0,
  students: [
    {id:100, name:'ahhtou', age:20},
    {id:101, name:'Kobe', age:25},
    {id:102, name:'jack', age:10},
    {id:103, name:'jerry', age:10}
  ]
}

const store = new Vuex.Store({
  state,
  actions,
  mutations,
  getters,
  modules:{
    a: modulesA

  }
})

export default store