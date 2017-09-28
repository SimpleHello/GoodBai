import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [{
  path : '/',
  redirect:'/home'
},{
  path:'/home',
  name:'home',
  component:resolve=>require(['@/page/home.vue'],resolve),
  children:[
    {path:'/home/user', component:resolve=>require(['@/page/system/user.vue'],resolve)},
    {path:'*',component:resolve=>require(['@/page/404.vue'],resolve)}
  ]
}];

const router = new VueRouter({
  routes
});

export default router;
