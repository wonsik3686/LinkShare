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
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="#">Logout</v-btn>

        <v-btn plain class="text-subtitle-1 font-weight-bold" :to="`/profile/${userInfo.email}`">{{userInfo.nickname}}</v-btn>
      </v-toolbar-items>
      
      <!-- 프로필 이미지 -->
      <v-btn icon class="mr-2">
        <v-avatar>
          <img
            v-if="userInfo.imagePath"
            :src="userInfo.imagePath"
            @error="replaceByDefault"
          />
          <!-- imagePath가 비어있는 경우 표시 -->
          <img
            v-else
            :src="require('../../assets/logo.svg')"
          />
        </v-avatar>
      </v-btn>

    </v-app-bar>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "NavBar",
  computed: {
    ...mapState('memberStore', ['userInfo']),
  },
  methods: {
    replaceByDefault(e) {
      e.target.src = '../assets/logo.svg'
      console.log(e.target.src)
    }
  }
}
</script>

<style>

</style>