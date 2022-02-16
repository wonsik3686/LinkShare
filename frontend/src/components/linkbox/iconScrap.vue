<template>
  <div>
    <v-icon class="mr-1" v-if="!isScrap"
      @click="clickScrap"
    >
      mdi-paperclip
    </v-icon>
    <v-icon class="mr-1" color="blue" v-else
      @click="clickUnscrap"
    >
    mdi-paperclip
    </v-icon>
    <span class="subheading mr-2">{{ linkbox.scrapCount }}</span>
  </div>
</template>

<script>
import { createScrap, getUserScrap, deleteScrap } from '@/api/linkbox'
import { mapState } from 'vuex'

export default {
  props: ['linkbox', 'user'],
  data: () => ({
    isScrap: false,
    userboxdata: null,
  }),
  created() {
    this.userboxdata = { uid: this.userInfo.id, boxid: this.linkbox.id }
    
    getUserScrap(this.userInfo.id, this.linkbox.id,
    (res) => {
      if (res.data.msg === 'success') {
        console.log('success getUserScrap')
        this.isScrap = res.data.object
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  computed: {
    ...mapState('memberStore', ['userInfo'])
  },
  methods: {
    clickScrap() {
      createScrap(this.userboxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success createScrap')
          this.linkbox.scrapCount += 1
          this.isScrap = true
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    clickUnscrap() {
      deleteScrap(this.user.id, this.linkbox.id,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success deleteScrap')
          this.linkbox.scrapCount -= 1
          this.isScrap = false
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    }
  }

}
</script>

<style>

</style>