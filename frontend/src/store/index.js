import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
  state: {
    loggedIn: false,
  },
  mutations: {
    // state.loggedIn 값을 전달된 payload 값으로 변경
    setLoggedIn (state, payload) {
      state.loggedIn = payload
    }
  },
  actions: {
    // login 함수 실행되면 commit
    login ({ commit }) {
      // mutations의 setLoggedIn에 true값 전달하고 commit
      commit('setLoggedIn', true)
    }
  },
  modules: {

  },
  getters: {
    // state의 loggedIn값을 반환하는 loggedIn 함수 생성
    loggedIn: (state) => state.loggedIn
  }
})
