import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router';
import VueRouter from 'vue-router';
Vue.use(VueRouter);
new Vue({
  router,
  vuetify,
  el:'#app',
  render: h => h(App)
}).$mount('#app')
