<template>
  <v-container>
    <v-chip
      class="ma-1 px-4"
      v-for="interest in filteredInterests"
      :key="interest"
      @click:close="remove(interest)"
      close
    >
      <strong>{{ interest }}</strong>
    </v-chip>
    
    <LinkboxDetailInterestNew @add-interest="addInterest" v-if="this.userInfo.id===this.boxUser.id"/>
  </v-container>
</template>

<script>
import LinkboxDetailInterestNew from './LinkboxDetailInterestNew.vue'
import { addLinkboxInterest, getInterestList, deleteLinkboxInterest } from '@/api/linkbox'
import { mapState } from 'vuex'

export default {
  components: {
    LinkboxDetailInterestNew,
  },
  props: ['linkbox', 'boxUser'],
  data: () => ({
    boxid: null,
    interests: [],
    toRemove: [],
  }),
  created() {
    this.boxid = this.$route.params.boxid
  },
  mounted() {
    this.interestList()
  },
  computed: {
    ...mapState('memberStore', ['userInfo']),
    filteredInterests () {
      return this.interests
    },
  },
  methods: {
    async remove (interest) {
      if (this.userInfo.nickname !== this.boxUser.nickname){
        alert('관심사는 작성자만 삭제할 수 있습니다.')
        return;
      }
      await deleteLinkboxInterest(this.boxid, interest,
      (res) => {
        if (res.data.msg === 'success') {
          this.interestList()
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    addInterest(selected) {
      const interestdata = { boxid: this.linkbox.id, interests: selected}
      console.log(interestdata)
      addLinkboxInterest(interestdata,
      (res) => {
        if (res.data.msg === 'success') {
          this.interestList()
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    interestList() {
      getInterestList(this.boxid,
      (res) => {
        if (res.data.msg === 'success') {
          this.interests = res.data.object
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    }
  },
}
</script>

<style>

</style>