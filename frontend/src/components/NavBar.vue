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
      LinkShare
      </v-toolbar-title>

      <v-spacer class="text-center">
        <!-- Search -->
      </v-spacer>

      <v-toolbar-items>
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="/about">About</v-btn>
        <v-btn plain class="text-subtitle-1 font-weight-bold" @click.stop="SignupForm=true">회원가입</v-btn>
        <SignupForm v-model="SignupForm"/>
        <v-btn plain class="text-subtitle-1 font-weight-bold" @click.stop="LoginForm=true">로그인</v-btn>
        <LoginForm v-model="LoginForm"/>
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="/profile">profile</v-btn>
      </v-toolbar-items>
    </v-app-bar>
  </div>
</template>

<script>
import SignupForm from '@/components/SignupForm'
import LoginForm from '@/components/LoginForm'

export default {
  name: "NavBar",
  components: {
    SignupForm,
    LoginForm,
  },
  data () {
    return {
      SignupForm: false,
      LoginForm: false,
    }
  }
}
</script>

<style>

</style>