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
        :to="`/linkbox/${boxid}`"
        style=color:black;text-decoration:none;
        class="text-h6 font-weight-light"
      >
        {{ linkbox.title }}
      </router-link>
    </v-card-title>
    
    <v-card-text>
      <router-link
        :to="`/linkbox/${boxid}`"
        style=color:grey;text-decoration:none;
        class="text-h5 font-weight-bold"
      >
        {{ linkbox.desc }}
      </router-link>
    </v-card-text>
    
    <v-card-text v-for="interest in linkbox.interests" :key="interest">
      {{ interest }}
    </v-card-text>
    
    <v-card-actions>
      <v-list-item class="grow">
        <v-list-item-avatar color="grey darken-3">
          <v-img
            class="elevation-6"
            :alt="userInfo.nickname"
            :src="userInfo.imagePath"
          >
          </v-img>
        </v-list-item-avatar>
        
        <v-list-item-content>
          <router-link
            :to="`/profile/${userInfo.email}`"
            style=color:grey;text-decoration:none;
            class="text-h5 font-weight-bold"
          >
            <v-list-item-title>{{ userInfo.nickname }}</v-list-item-title>
          </router-link>
        </v-list-item-content>

        <v-row
          align="center"
          justify="end"
        >
          <v-icon class="mr-1">mdi-heart</v-icon>
          <span class="subheading mr-2">{{ linkbox.likeCount }}</span>

          <v-icon class="mr-1">mdi-comment</v-icon>
          <span class="subheading mr-2">{{ linkbox.commentCount }}</span>

          <v-icon class="mr-1">mdi-paperclip</v-icon>
          <span class="subheading">{{ linkbox.scrapCount }}</span>
        </v-row>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script>
import { getLinkboxInfo, searchLinkBoxCommentByBoxId } from '@/api/linkbox'
import { getUserUid } from '@/api/member'

export default {
  data: () => ({
    linkbox: '',
    comments: '',
    userInfo: '',
  }),
  props: ['boxid'],
  created() {
    getLinkboxInfo(this.boxid,
    (res) => {
      if (res.data.msg === "success") {
        console.log('success getLinkboxInfo')
        this.linkbox = res.data.object
        console.log(this.linkbox)
        this.getUserInfo()
      }
    }),
    searchLinkBoxCommentByBoxId(this.boxid,
    (res) => {
      if (res.data.msg === "success") {
        console.log('success searchLinkBoxCommentByBoxId')
        this.comments = res.data.object
      }
    })
  },
  methods: {
    getUserInfo() {
      getUserUid(12,
      (res) => {
        if (res.data.msg === 'success') {
          this.userInfo = res.data.object.userInfo
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
      }
  }
}
</script>

<style>

</style>