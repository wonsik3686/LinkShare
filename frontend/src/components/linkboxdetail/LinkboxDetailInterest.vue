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
    
    <LinkboxDetailInterestNew @add-interest="addInterest"/>
  </v-container>
</template>

<script>
import LinkboxDetailInterestNew from './LinkboxDetailInterestNew.vue'
import { addLinkboxInterest, getInterestList, deleteLinkboxInterest } from '@/api/linkbox'

export default {
  components: {
    LinkboxDetailInterestNew,
  },
  props: ['linkbox'],
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
    filteredInterests () {
      return this.interests
    },
  },
  methods: {
    remove (interest) {
      deleteLinkboxInterest(this.boxid, interest,
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