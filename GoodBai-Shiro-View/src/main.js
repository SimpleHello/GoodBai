// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Menu11 from './components/Menu11'
import Menu12 from './components/Menu12'
import Home from './components/Home'
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
  path : '/menu11',
  component : Menu11
},{
  path : '/menu12',
  component : Menu12
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
