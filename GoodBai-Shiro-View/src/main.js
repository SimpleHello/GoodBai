// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Home from './components/Home'
import Menu3 from './components/Menu3'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import iView from 'iview';
import 'iview/dist/styles/iview.css';    // 使用 CSS


Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(iView);

const routes = [{
  path : '/home',
  component : Home
},{
  path : '/menu3',
  component : Menu3
}];

const router = new VueRouter({
  routes
});

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  ...App
})
