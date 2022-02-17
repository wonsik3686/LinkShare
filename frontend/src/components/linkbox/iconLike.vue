<template>
  <div>
    <v-icon class="mr-1" v-if="!isliked"
      @click="clickLike"
    >
      mdi-heart
    </v-icon>
    <v-icon class="mr-1" color="red" v-else
      @click="clickUnlike"
    >
    mdi-heart
    </v-icon>
    <span class="subheading mr-2">{{ linkbox.likeCount }}</span>
  </div>
</template>

<script>
import { likeLinkbox, unlikeLinkbox, getUserLike } from '@/api/linkbox'
import { mapState } from 'vuex'

export default {
  props: ['linkbox', 'user'],
  data: () => ({
    myId: null,
    isliked: false,
    userboxdata: null,
  }),
  async created() {
    this.myId = this.userInfo.id
    this.userboxdata = { uid: this.myId, boxid: this.linkbox.id }

    await getUserLike(this.linkbox.id, this.myId,
    (res) => {
      if (res.data.msg === 'success') {
        this.isliked = res.data.object
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  computed: {
    ...mapState('memberStore', ['userInfo'])
  },
  methods: {
    clickLike() {
      likeLinkbox(this.userboxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success likeLinkbox')
          this.linkbox.likeCount += 1
          this.isliked = true
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    async clickUnlike() {
      console.log(this.myId)
      console.log(this.linkbox.id)
      await unlikeLinkbox(this.myId, this.linkbox.id,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success unlikeLinkbox')
          this.linkbox.likeCount -= 1
          this.isliked = false
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err.response))
    }
  }

}
</script>

<style>

</style>