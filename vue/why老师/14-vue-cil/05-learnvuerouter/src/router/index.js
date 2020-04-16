import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/componentHOmes/HelloWorld'
const Home =  () => import('../components/Home')
  const HomeNews = () => import('../components/HomeNews')
  const HomeMessage = () => import('../components/HomeMessage')

const About =  () => import('../components/About')
const User =  () => import('../components/User')
const Profile = () => import('../components/Profile')


Vue.use(Router)

const routes =  [
  {
    path: '',
    redirect: '/home',
    meta:{
      title:'首页'
    }, 
  },
  {
    path: '/home',
    component: Home,
    meta:{
      title:'首页'
    },
    children:[
      // {
      //   path:'',
      //   redirect:'/home/news'
      // },
      {
        path:'news',
        component: HomeNews

      },
      {
        path:'message',
        component: HomeMessage
      }
    ],
    beforeEnter: (to, from, next) => {
      console.log('进入主页了');
      next()
    }
  },
  {
    path: '/about',
    component: About,
    meta:{
      title:'关于'
    }
  },
  {
    path:'/user/:userId',
    component: User,
    meta:{
      title:'用户'
    }
  },
  {
    path:'/profile',
    component: Profile,
    meta:{
      title:'档案'
    }
  }
]




const router = new Router({
  routes,
  mode :'history',
  linkActiveClass :'active'
})

//全局守卫
// 前置钩子（hook）/守卫
router.beforeEach((to, from, next) => {
  // to and from are both route objects. must call `next`.
  console.log('++++++');
  //meta描述元素的元素
  document.title = to.matched[0].meta.title;
  //to，即将要进入的目标的路由对象，from当前导航要离开的路由对象，next调用才能进入下一个钩子
  next()
})

//后置钩子
router.afterEach((to, from) => {
  console.log('------');
})

export default router