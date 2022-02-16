<template>
  <v-container>
    <h1>팔로워</h1>
    <v-list-item
      v-for="follower in followers"
      :key="follower.nickname"
    >
      <v-list-item-avatar>
        <v-img
          :alt="`${follower.nickname} avatar`"
          :src="follower.avatar"
        ></v-img>
      </v-list-item-avatar>

      <v-list-item-content class="text-left">
        <v-list-item-title v-text="follower.nickname"></v-list-item-title>
      </v-list-item-content>

      <v-list-item-action>
        <!-- <v-btn
        v-if="!follower.isfollow"
        @click="follower.isfollow = true"
        text
        icon> -->
        <v-btn
          v-if="!follower.isfollow"
          @click="follower.isfollow = true"
          rounded
          outlined
          class="font-weight-bold"
          color="blue"
        >
        팔로우
          <!-- <v-icon
          color="grey lighten-1">
          mdi-heart-outline
          </v-icon> -->
        </v-btn>
        <!-- <v-btn
          v-else
          @click="follower.isfollow = false"
          text
          icon
        > -->
        <v-btn
          v-else
          @click="follower.isfollow = false"
          rounded
          depressed
          class="font-weight-bold white--text"
          color="blue"
        >
        팔로잉
          <!-- <v-icon color="pink">
            mdi-heart
          </v-icon> -->
        </v-btn>
      </v-list-item-action>
    </v-list-item>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    followers: [
      {
        isfollow: true,
        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
        nickname: 'Jason Oner',
      },      
      {
        isfollow: true,
        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
        nickname: 'Mike Carlson',
      },
      {
        isfollow: false,
        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
        nickname: 'Cindy Baker',
      },
      {
        isfollow: false,
        avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
        nickname: 'Ali Connors',
      },
    ],
  }),
}
</script>

<style>

</style>