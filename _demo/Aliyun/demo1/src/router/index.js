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
                },
                component: () => import('../components/PrimeMenu.vue')
            },
            {
                path: '/MsgBoard',
                name: 'MsgBoard',
                meta: {
                    title: '留言板',
                },
                component: () => import('../components/MsgBoard.vue')
            },
            {
                path: '/BgChanger',
                name: 'BgChanger',
                meta: {
                    title: '切换背景',
                },
                component: () => import('../components/BgChanger.vue')
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
