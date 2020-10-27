import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


import TipsView from '@/components/_overall/TipsView/index'
import JustLoad from '@/components/_overall/JustLoad/index'
import JustTips from '@/components/_overall/JustTips/index'

Vue.use(JustTips)   // 全局提醒
Vue.use(TipsView)   // 全局弹窗
Vue.use(JustLoad)   // 全局加载

/* md5 */
/* axios */
import md5 from 'js-md5'
import {HelloAxios} from '@/utils/MyAjax'
import {result} from '@/utils/ResultHandler'
import {objCopy} from '@/utils/Utils'

Vue.prototype.$md5 = md5
Vue.prototype.$axios = new HelloAxios()
Vue.prototype.$result = result
Vue.prototype.$objCopy = objCopy


/* 图标 */
import './assets/css/icon/iconfont.css'


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
