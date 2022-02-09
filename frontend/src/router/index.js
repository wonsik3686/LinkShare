import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store/index.js'
import Newsfeed from '../views/Newsfeed.vue'
// import Linkboxdetail from '../views/Linkboxdetail.vue'
import Toplinkbox from '../views/Toplinkbox'


Vue.use(VueRouter)

const routes = [
  {
    path: '/welcome',
    name: 'welcome',
    component: () => import('../views/welcome.vue')
  },
  {
    path: '/newsfeed',
    name: 'Newsfeed',
    component: Newsfeed
  },
  {
    path: '/',
    redirect: () => {
      if (store.getters['memberStore/loggedIn']) {
        return '/newsfeed';
      } else {
        return '/welcome';
      }
    }
  },
  {
    path: '/profile/:email',
    component: () => import('../views/profile.vue'),
    children: [
      {
        path: "interest",
        name: "interest",
        component: () => import('@/components/profile/Interest.vue'),
      },
      {
        path: "linkbox",
        component: () => import('@/components/profile/Linkbox.vue'),
      },
      {
        path: "scrap",
        component: () => import('@/components/profile/Scrap.vue'),
      },
      {
        path: "following",
        component: () => import('@/components/profile/Following.vue'),
      },
      {
        path: "follower",
        component: () => import('@/components/profile/Follower.vue'),
      },
    ]
  },
  {
    path: '/linkbox',
    component: () => import('../views/Linkboxdetail.vue'),
    children: [
      {
        path: "linklist",
        component: () => import('@/components/linkboxdetail/linklist.vue'),
      },
      {
        path: "linktree",
        component: () => import('@/components/linkboxdetail/flowy/flowy.vue'),
      },
    ]
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
