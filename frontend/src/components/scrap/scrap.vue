<template>
  <v-container>
    <v-btn
      @click="scrapLinkbox"
      x-large
      outlined
      color="blue"
    >
      <v-icon>mdi-paperclip</v-icon>
      &nbsp;
      <span>scrap</span>
      &nbsp;&nbsp;
    </v-btn>
  </v-container>
</template>

<script>
import { createScrap, deleteScrap, getScrapBoxid, getUserScrap, } from '@/api/linkbox'

export default {
  props: {
    userInfo: Object,
    boxid: String,
  },
  data: () => ({
    isScrap: false,
  }),
  created() {
    getScrapBoxid(this.boxid,
    (res) => {
      if (res.data.msg === 'success') {
        console.log(res.data)
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err)),

    getUserScrap(this.userInfo.id,
    (res) => {
      if (res.data.msg === 'success') {
        console.log(res.data)
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  methods: {
    scrapLinkbox() {
      const userboxdata = { uid: this.userInfo.id, boxid: Number(this.boxid) }
      createScrap(userboxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success createScrap')
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    unscrapLinkbox() {
      deleteScrap()
    }
  }
}
</script>

<style>

</style>