import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        meta: {
            requireAuth: true,
        },
        component: () => import('../views/Manager.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        console.log("需要登录")
        next()
    } else {
        console.log("不需要登录")
        next()
    }
})

export default router
