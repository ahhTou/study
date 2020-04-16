var a = require('./js/toSay.js')
import Vue from 'vue'

require('./css/normal.css')
require('./css/special.less')
a.say();

// import App from './vue/app'
import App from './vue/App.vue'

new Vue({
        el: '#app',
        //template会替换id为add 的层中
        template: '<App/>',
        components: {
                App
        }

});

//依赖css文件
