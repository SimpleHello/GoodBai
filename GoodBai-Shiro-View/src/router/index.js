import Vue from 'vue'
import VueRouter from 'vue-router'
import Menu11 from "../components/Menu11"
import Menu12 from '../components/Menu12'
import Home from '../components/Home'

Vue.use(VueRouter)

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

export default router;
