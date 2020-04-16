import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App)
})


// compiler: template -> ast(虚拟语法树) —> render -> vdom -> UI
//  noly: render -> vdom -> UI