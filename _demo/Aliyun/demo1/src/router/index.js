import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {
            title: '欢迎',
        },
        children: [
            {
                path: '/PrimeMenu',
                name: 'PrimeMenu',
                meta: {
                    title: '老demo',
                    EnterType: 'center'
                },
                component: () => import('../components/PrimeMenu.vue')
            },
            {
                path: '/MsgBoard',
                name: 'MsgBoard',
                meta: {
                    title: '留言板',
                    EnterType: 'centerWithScroll',
                },
                component: () => import('../components/MsgBoard.vue')
            },
            {
                path: '/BgChanger',
                name: 'BgChanger',
                meta: {
                    title: '切换背景',
                    EnterType: 'center',
                },
                component: () => import('../components/BgChanger.vue')
            },
            {
                path: '/Register',
                name: 'Register',
                meta: {
                    title: '注册',
                    EnterType: 'card',
                },
                component: () => import('../components/Register.vue')
            },
            {
                path: '/Login',
                name: 'Login',
                meta: {
                    title: '登录',
                    EnterType: 'card',
                },
                component: () => import('../components/Login.vue')
            },

        ],
    },


]

const router = new VueRouter({
    mode: 'history',
    base: 'hello',
    // base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title
    next()

})


export default router
