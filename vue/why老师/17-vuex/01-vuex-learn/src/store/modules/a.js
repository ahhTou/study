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