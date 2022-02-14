<template>
  <v-container>
    <!-- &nbsp;&nbsp; -->
    <v-btn
      v-if="!isliked"
      @click="isliked=true"
      x-large
      outlined
      color="red"
    >
      <v-icon>mdi-heart-outline</v-icon>
      &nbsp;
      <span>like</span>
      &nbsp;&nbsp;
    </v-btn>

    <v-btn
      v-else
      x-large
      fill
      color="blue"
    >
      <v-icon>mdi-heart-outline</v-icon>
      &nbsp;
      <span>like</span>
      &nbsp;&nbsp;
    </v-btn>
  </v-container>
</template>

<script>
import { likeLinkbox, unlikeLinkbox, getLikeList, getUserLike } from '@/api/linkbox'

export default {
  props: ['userInfo', 'boxid'],
  data: () => ({
    isliked: false,
  }),
  created() {
    console.log(this.userInfo.id)
    getUserLike(this.boxid, this.userInfo.id,
    (res) => {
      if (res.data.msg === 'success') {
        console.log('success getUserLike')
        this.isliked = res.data.object
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err)),

    getLikeList(this.boxid,
    (res) => {
      if (res.data.msg === 'success') {
        console.log('success getLikeList')
        console.log(res.data)
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  methods: {
    clickLike() {
      const userboxdata = { uid: this.userInfo.id, boxid: this.boxid }
      likeLinkbox(userboxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success likeLinkbox')
          this.isliked = res.data.object
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    clickUnlike() {
      unlikeLinkbox(this.boxid,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success unlikeLinkbox')
          console.log(res.data)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    }
  }
}
</script>

<style>

</style>