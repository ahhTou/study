import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import UtilsFactory from 'assets/factory/UtilsFactory'


/* 全局弹窗 */
import TipsView from './components/TipsView/index'

Vue.use(TipsView)

/* md5 */
import md5 from 'js-md5'

Vue.prototype.$md5 = md5

/* 图标 */
import './assets/css/icon/iconfont.css'

/* UtilsFactory */
UtilsFactory.create()

/* fastClick */
import FastClick from 'fastclick'

FastClick.prototype.focus = function (targetElement) {
    let length
    if (targetElement.setSelectionRange && targetElement.type.indexOf('date') !== 0 && targetElement.type !== 'time' && targetElement.type !== 'month') {
        length = targetElement.value.length
        targetElement.focus()
        targetElement.setSelectionRange(length, length)
    } else {
        targetElement.focus()
    }
}
FastClick.attach(document.body)


Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
