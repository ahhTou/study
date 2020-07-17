import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        bgUrl: null,
    },
    mutations: {
        changeBg(state, value) {
            state.bgUrl = value
        },
    },
    actions: {},
    modules: {}
})
