import Vue from 'vue'
import VueRouter from 'vue-router'
import {ymzs,getUrl} from '../module/leftNav.js';

Vue.use(VueRouter)

const routes = [{
  path : '/',
  redirect:'/home'
},{
  path:'/home',
  name:'home',
  component:resolve=>require(['@/page/home.vue'],resolve),
  children:[
    {path:'/sys/userinfo', component:resolve=>require(['@/page/system/user.vue'],resolve),meta: {requiresAuth: true}},
    {path:'/sys/addUser', component:resolve=>require(['@/page/system/addUser.vue'],resolve),meta: {requiresAuth: false}},
    {path:'*',component:resolve=>require(['@/page/404.vue'],resolve)}
  ]
}
];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  // 可以在路由元信息指定哪些页面需要登录权限
  let sx = getUrl(ymzs.nav);
  let path = to.path;
  const islogin = sx.includes(path); // 假设没有登录（这里应从接口获取）
  if (to.meta.requiresAuth && !islogin) { // 需要登录授权，这里还需要判断是否登录
    next('/') // 跳转到登录
    return
  }
  next()
})

export default router;
