import Vue from 'vue'
import VueRouter from 'vue-router'
import Anime from "../views/Anime";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        meta: {
            requireAuth: true,
            title: "个人管理"
        },
        children: [
            {
                path: '/anime/*',
                name: 'Anime',
                meta: {
                    title: "全部动画"
                },
                component: Anime
            },
            {
                path: '/myAnime',
                name: 'MyAnime',
                meta: {
                    title: "我的动画"
                },
            },
        ],
        component: () => import('../views/Manager.vue')
    },
    {
        path: '/login',
        name: 'Login',
        meta: {
            title: "请登录"
        },
        component: () => import('../views/Login.vue')
    },
    {
        path: '*',
        redirect: "/"
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    if (to.path !== from.path) {
        next()
    }
})

export default router
