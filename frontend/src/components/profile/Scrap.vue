<template>
  <v-container>
    <v-row>
      <v-col
        cols='col-xs-1 col-sm-6 col-md-6 col-lg-4 col-xl-4'
        v-for="scrap in scraplist" :key="scrap.id"
      >
        <linkbox :boxid="scrap.boxid"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { deleteScrap, getScrapUid, } from '@/api/linkbox'
import { userInfo } from '@/api/member'
import { mapState } from 'vuex';
import linkbox from '@/components/linkbox/Linkbox'

export default {
  components: {
    linkbox
  },
  data: () => ({
    profileUser: null,
    scraplist: [],
  }),
  async created() {
    const profileUserEmail = this.$route.params.email;
    await this.getProfileUser(profileUserEmail)
  },
  computed: {
    ...mapState('memberStore', ['userInfo']),
  },
  methods: {
    unscrapLinkbox() {
      deleteScrap()
    },
    getProfileUser(email) {
      userInfo(email,
      (res) => {
        if (res.data.msg === 'success') {
          this.profileUser = res.data.object.userInfo
          this.getUserScrap(res.data.object.userInfo.id)
          console.log(this.profileUser)
        } else {console.log(res.data.msg)}
      }, (err) => console.log(err)) 
    },
    getUserScrap(userid) {
      getScrapUid(userid,
      (res) => {
        if (res.data.msg === 'success') {
          this.scraplist = res.data.object
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    }
  }
}
</script>

<style>

</style>