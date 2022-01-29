import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify'
import * as VeeValidate from 'vee-validate'

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  VeeValidate,
  render: h => h(App)
}).$mount('#app')
