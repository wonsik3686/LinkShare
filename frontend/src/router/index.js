import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Newsfeed from '../views/Newsfeed.vue'
import Linkboxdetail from '../views/Linkboxdetail.vue'
import Toplinkbox from '../views/Toplinkbox'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/newsfeed',
    name: 'Newsfeed',
    component: Newsfeed
  },
  {
    path: '/linkboxdetail',
    name: 'Linkboxdetail',
    component: Linkboxdetail
  },
  {
    path: '/toplinkbox',
    name: 'Toplinkbox',
    component: Toplinkbox
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
