import Vue from 'vue'
import App from './App.vue'
// list的增强
import './assets/js/MyList'

Vue.config.productionTip = false

new Vue({
    render: h => h(App),
}).$mount('#app')
