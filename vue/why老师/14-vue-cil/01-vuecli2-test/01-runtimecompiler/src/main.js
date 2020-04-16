// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'

})

// compiler:   template -> ast(虚拟语法树) —> render -> vdom -> UI

//1.性能更强 2.代码量更少
//  noly:   render -> vdom -> UI