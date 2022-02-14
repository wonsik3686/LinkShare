<template>
  <v-container>
    <v-row>
      <v-col v-for="scrap in scraplist" :key="scrap.id">
        <linkboxCard :boxid="scrap.boxid"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { deleteScrap, getUserScrap, } from '@/api/linkbox'
import { mapActions, mapState } from 'vuex';
import linkboxCard from '@/components/LinkboxCard'

export default {
  components: {
    linkboxCard
  },
  data: () => ({
    scraplist: [],
  }),
  created() {
    const userItem = this.$route.params.email;
    this.fetchUserInfo(userItem)
    console.log(this.userItem)

    getUserScrap(this.userItem.id,
    (res) => {
      if (res.data.msg === 'success') {
        this.scraplist = res.data.object
      } else { console.log(res.data.msg) }
    }, (err) => console.log(err))
  },
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
  },
  methods: {
    ...mapActions('memberStore', ['fetchUserInfo']),
    unscrapLinkbox() {
      deleteScrap()
    }
  }
}
</script>

<style>

</style>