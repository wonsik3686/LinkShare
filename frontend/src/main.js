import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify'
import * as VeeValidate from 'vee-validate'
import FlowyPlugin from "@hipsjs/flowy-vue";
import "@hipsjs/flowy-vue/dist/lib/flowy-vue.css";
import LinkPrevue from 'link-prevue'

Vue.use(FlowyPlugin)
Vue.config.productionTip = false
Vue.filter('truncate', function (text, length, suffix) {
  if (text.length > length) {
      return text.substring(0, length) + suffix;
  } else {
      return text;
  }
});

new Vue({
  store,
  router,
  vuetify,
  VeeValidate,
  FlowyPlugin,
  LinkPrevue,
  render: h => h(App)
}).$mount('#app')
