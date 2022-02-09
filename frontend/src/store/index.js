import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore.js";
import linkboxStore from "./modules/linkboxStore.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  strict: true,
  modules: {
    memberStore,
    linkboxStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료 시 제거하기 위해 localStrage가 아닌 sessionStorage로 변경.
      storage: sessionStorage,
    }),
  ],
});

export default store;