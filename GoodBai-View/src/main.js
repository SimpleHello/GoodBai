import Vue from 'vue';
import iView from 'iview';
import Router from './config/routes';
import App from './app.vue';
import 'iview/dist/styles/iview.css';

Vue.use(iView);

// 开启debug模式
Vue.config.debug = true;



new Vue({
    el: '#app',
    router: Router,
    render: h => h(App)
});
