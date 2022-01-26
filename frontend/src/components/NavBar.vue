<template>
  <div>
    <v-app-bar
      color="white"
      elevate-on-scroll
    >
      <v-toolbar-title
        class="text-h5 blue--text"
        style="cursor: pointer"
        @click="$router.push('/')"
      >
      📁 LinkShare
      </v-toolbar-title>

      <v-spacer class="text-center">
        <!-- Search -->
      </v-spacer>

      <v-toolbar-items>
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="/about">About</v-btn>
        <v-btn plain class="text-subtitle-1 font-weight-bold" @click.stop="SignupForm=true">회원가입</v-btn>
        <SignupForm v-model="SignupForm"/>
        <v-btn plain class="text-subtitle-1 font-weight-bold" @click.stop="Login=true">로그인</v-btn>
        <Login v-model="Login"/>
      </v-toolbar-items>
    </v-app-bar>
  </div>
</template>

<script>
import SignupForm from '@/components/SignupForm'
import Login from '@/components/Login'

export default {
  name: "NavBar",
  components: {
    SignupForm,
    Login,
  },
  data () {
    return {
      SignupForm: false,
      Login: false,
    }
  }
}
</script>

<style>

</style>