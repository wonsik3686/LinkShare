<template>
  <v-container>
    <userInterestNew @add-interest="addInterest"/>
    <v-chip
      class="ma-1 px-4"
      v-for="interest in filteredInterests"
      :key="interest"
      @click:close="remove(interest)"
      close
    >
      <strong>{{ interest }}</strong>
    </v-chip>
  </v-container>
</template>

<script>
import userInterestNew from './UserInterestNew.vue'
import { createUserInterest, getUserInterest, deleteUserInterest } from '@/api/member'
import { mapState } from 'vuex'

export default {
  components: {
    userInterestNew,
  },
  data: () => ({
    userid: null,
    interests: [],
    toRemove: [],
  }),
  created() {
    this.userid = this.userInfo.id
    this.interestList()
  },
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
    filteredInterests () {
      return this.interests
    }
  },
  methods: {
    remove (interest) {
      deleteUserInterest(this.userInfo.id, interest,
      (res) => {
        if (res.data.msg === 'success') {
          this.interestList()
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    addInterest(selected) {
      const interestdata = { uid: this.userInfo.id, interests: selected}
      console.log(interestdata)
      createUserInterest(interestdata,
      (res) => {
        if (res.data.msg === 'success') {
          this.interestList()
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    interestList() {
      getUserInterest(this.userItem.id, { uid: this.userItem.id },
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