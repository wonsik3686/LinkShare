import { signIn, userProfile, updateProfile, userInfo } from '@/api/member'
import jwt_decode from 'jwt-decode'

const memberStore = {
  namespaced: true,
  state: {
    loggedIn: false,
    userInfo: null,
    userItem: null,
  },
  mutations: {
    // state.loggedIn 값을 전달된 payload 값으로 변경
    setLoggedIn (state, payload) {
      state.loggedIn = payload
    },
    signout (state) {
      state.loggedIn = false
      state.userInfo = null
      localStorage.removeItem('token')
      location.reload()
    },
    setUserInfo (state, payload) {
      state.userInfo = payload
    },
    editUserInfo (state, payload) {
      console.log(payload)
      if (payload.imagePath) {
        state.userInfo.imagePath = payload.imagePath
      }
      state.userInfo.nickname = payload.nickname
      state.userInfo.email = payload.email
      state.userInfo.introduce = payload.introduce
    },
    fetchUserInfo (state, payload) {
      state.userItem = payload
    }
  },
  actions: {
    // userSignin 함수 실행되면 commit
    async userSignin ({ commit }, user) {
      // api 통신 후 response 수령
      // 로그인 성공
      await signIn(user,
        (response) => {
          if (response.data.msg === "success") {
            console.log('success userSignin')
            // mutations의 setLoggedIn에 true값 전달하고 commit
            commit('setLoggedIn',true)
            // 토큰값 저장
            let token = response.data.object.token
            localStorage.setItem('token', token)
          } else {
            //로그인 실패(api 통신 성공)
            console.log(response.data.msg)
          }
        }, (err) => {
          // 로그인 실패(api 통신 실패)
          console.log(err)
        }
      )
    },
    userSignout ({ commit }) {
      commit('signout')
    },
    async getUserProfile ({ commit }) {
      // 토큰 불러오기
      let token = localStorage.getItem('token')
      // console.log(token)
      let decode = jwt_decode(token)
      // console.log(decode)
      await userProfile(decode,
        (response) => {
          if (response.data.msg == 'success') {
            console.log('success getUserInfo')
            console.log(response.data.object.userInfo)
            commit('setUserInfo', response.data.object.userInfo)
            
          } else {
            console.log('error getUserInfo apiFailed')
          }
        },
        (err) => console.log(err))
    },
    updateUserProfile({ commit }, editedProfile) {
      console.log(editedProfile)
      updateProfile(editedProfile,
        (response) => {
          if (response.data.msg === "success") {
            console.log('profile updated')
            commit('editUserInfo', editedProfile)
          } else {console.log(response.data.msg) }
        }, (err) => console.log(err))
    },
    async fetchUserInfo({ commit }, user) {
      await userInfo(user,
        (response) => {
          if (response.data.msg == 'success') {
            console.log('success fetchUserInfo')
            console.log(response.data.object.userInfo)
            commit('fetchUserInfo', response.data.object.userInfo)
            
        } else {
          console.log('error fetchUserInfo apiFailed')
        }
      },
      (err) => console.log(err))
    }
  },
  modules: {

  },
  getters: {
    // state의 loggedIn값을 반환하는 loggedIn 함수 생성
    loggedIn: (state) => state.loggedIn,
    userInfo: (state) => state.userInfo,
  }
}

export default memberStore