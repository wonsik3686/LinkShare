import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify'
import * as VeeValidate from 'vee-validate'
import FlowyPlugin from "@hipsjs/flowy-vue";
import "@hipsjs/flowy-vue/dist/lib/flowy-vue.css";

Vue.use(FlowyPlugin)
Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  VeeValidate,
  FlowyPlugin,
  render: h => h(App)
}).$mount('#app')
