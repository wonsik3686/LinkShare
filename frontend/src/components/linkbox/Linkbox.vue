<template>
  <v-hover v-slot="{ hover }">
    <v-card
      class="mx-auto"
      max-width="400"
      :elevation="hover ? 12 : 2"
      :class="{ 'on-hover': hover }"
    >
      <v-card-title>
        <router-link
          :to="`/linkbox/${boxid}`"
          style=color:black;text-decoration:none;
          class="text-h4 font-weight-bold"
        >
          {{ linkbox.title | truncate(9, '...')  }}
        </router-link>
      </v-card-title>
      
      <v-card-text>
        <router-link
          :to="`/linkbox/${boxid}`"
          style=color:grey;text-decoration:none;
          class="text-body-1 font-weight-medium"
        >
          {{ linkbox.desc | truncate(50, '...')  }}
        </router-link>
      </v-card-text>
      
      <v-card-text>
        <v-chip class="ma-1 px-4"
          v-for="interest in linkbox.interests" :key="interest"
        >
          <strong>{{ interest }}</strong>
        </v-chip>
      </v-card-text>
      
      <v-card-actions>
        <v-list-item class="grow">
          <v-list-item-avatar color="grey darken-3">
            <v-img
              class="elevation-6"
              :alt="user.nickname"
              :src="user.imagePath"
            >
            </v-img>
          </v-list-item-avatar>
          
          <v-list-item-content>
            <router-link
              :to="`/profile/${user.email}`"
              style=color:grey;text-decoration:none;
              class="text-h5 font-weight-bold"
            >
              <v-list-item-title>{{ user.nickname }}</v-list-item-title>
            </router-link>
          </v-list-item-content>

          <v-row
            align="center"
            justify="end"
          >
            <iconLike
              v-if="linkbox !== '' && user !== ''"
            :linkbox="linkbox" :user="user"
            />

            <iconScrap
              v-if="linkbox !== '' && user !== ''"
            :linkbox="linkbox" :user="user"
            />

            <iconComment
              v-if="linkbox !== ''"
            :linkbox="linkbox"
            />
          </v-row>
        </v-list-item>
      </v-card-actions>
    </v-card>
  </v-hover>
</template>

<script>
import { getLinkboxInfo, searchLinkBoxCommentByBoxId, viewLinkbox } from '@/api/linkbox'
import { getUserUid } from '@/api/member'
import iconLike from './iconLike.vue'
import iconScrap from './iconScrap.vue'
import iconComment from './iconComment.vue'

export default {
  components: {
    iconLike,
    iconScrap,
    iconComment,
  },
  data: () => ({
    linkbox: '',
    comments: '',
    user: '',
  }),
  props: ['boxid'],
  created() {
    getLinkboxInfo(this.boxid,
    (res) => {
      if (res.data.msg === "success") {
        console.log('success getLinkboxInfo')
        this.linkbox = res.data.object
        console.log(this.linkbox)
        this.getUserInfo(this.linkbox.uid)
      }
    }),
    searchLinkBoxCommentByBoxId(this.boxid,
    (res) => {
      if (res.data.msg === "success") {
        console.log('success searchLinkBoxCommentByBoxId')
        this.comments = res.data.object
      }
    }),
    viewLinkbox(this.boxid,
    (res) => {
      if (res.data.msg === 'success') {
        console.log('조회수 1 증가')
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  methods: {
    getUserInfo(userid) {
      getUserUid(userid,
      (res) => {
        if (res.data.msg === 'success') {
          this.user = res.data.object.userInfo
          console.log(this.user)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
      }
  },
}
</script>

<style>

</style>