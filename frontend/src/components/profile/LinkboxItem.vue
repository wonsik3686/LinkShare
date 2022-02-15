<template>
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-card-title>
      <v-icon large left>
        mdi-folder
      </v-icon>
      <router-link
        :to="`/linkbox/${linkbox.id}`"
        style=color:black;text-decoration:none;
        class="text-h6 font-weight-light"
      >
        {{ linkbox.title }}
      </router-link>
    </v-card-title>

    <v-card-text>
      <router-link
        :to="`/linkbox/${linkbox.id}`"
        style=color:grey;text-decoration:none;
        class="text-h5 font-weight-bold"
      >
        {{ linkbox.desc }}
      </router-link>
    </v-card-text>
    
    <v-card-text v-for="interest in linkbox.interests" :key="interest">
      {{ interest }}
    </v-card-text>

    {{ this.comments }}

    <v-card-actions>
      <v-list-item class="grow">
        <v-list-item-avatar color="grey darken-3">
          <v-img
            class="elevation-6"
            :alt="userItem.nickname"
            :src="userItem.imagePath"
          ></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>Evan You</v-list-item-title>
        </v-list-item-content>

        <v-row
          align="center"
          justify="end"
        >
          <v-icon class="mr-1">
            mdi-heart
          </v-icon>
          <span class="subheading mr-2">256</span>
          <span class="mr-1">·</span>
          <v-icon class="mr-1">
            mdi-share-variant
          </v-icon>
          <span class="subheading">45</span>
        </v-row>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script>
import { searchLinkBoxCommentByBoxId } from '@/api/linkbox'

export default {
  data: () => ({
    comments: null,
  }),
  props: {
    linkbox: Object,
    userItem: Object,
  },
  created() {
    searchLinkBoxCommentByBoxId(this.linkbox.id,
    (res) => {
      if (res.data.msg === "success") {
        console.log('success searchLinkBoxCommentByBoxId')
        this.comments = res.data.object
      }
    })
  }
}
</script>

<style>

</style>