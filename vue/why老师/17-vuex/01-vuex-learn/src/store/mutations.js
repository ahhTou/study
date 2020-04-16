import * as a  from './mutations-types'

export default {
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
    Vue.set(state.students, 3, {gender:'男'})
  },
}