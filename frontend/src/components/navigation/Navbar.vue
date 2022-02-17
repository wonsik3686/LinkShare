<template>
  <div>
    <v-app-bar
      color="white"
      elevate-on-scroll
    >
      <v-toolbar-title
        class="text-h5 font-weight-medium blue--text"
        style="cursor: pointer"
        @click="$router.push('/').catch(()=>{})"
      >
        <v-icon color="#2C97DE">mdi-folder</v-icon>
        LinkShare
      </v-toolbar-title>

      <v-spacer class="text-center">
        <!-- Search -->
      </v-spacer>

      <v-toolbar-items>
        <v-btn icon>
          <profileImage :size="35" :imagePath="userInfo.imagePath" :nickname="userInfo.nickname"/>
        </v-btn>
        <v-btn plain class="text-subtitle-1 font-weight-bold px-1" :to="`/${userInfo.email}`">{{userInfo.nickname}}</v-btn>
        
        <v-btn plain class="text-subtitle-1 font-weight-bold" @click="makelogout">로그아웃</v-btn>
      </v-toolbar-items>
      

    </v-app-bar>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import profileImage from '@/components/common/ProfileImage'

export default {
  name: "NavBar",
  components: {
    profileImage,
  },
  computed: {
    ...mapState('memberStore', ['userInfo']),
  },
  methods: {
    ...mapActions('memberStore', ['userSignout']),
    replaceByDefault(e) {
      e.target.src = '../assets/logo.svg'
      console.log(e.target.src)
    },
    async makelogout() {
      this.userSignout()
    }
  }
}
</script>

<style>

</style>