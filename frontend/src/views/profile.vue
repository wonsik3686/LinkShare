<template>
  <v-container>
    <profileCard
      :userItem="userItem"
      :userInfo="this.userInfo"
      @edit-profile="editProfile"
    />
    <profileCardEdit :userInfo="userInfo" @done-edit="doneEdit"/>

    <br>

    <v-container>
      <v-tabs fixed-tabs>
        <v-tab :to="`/profile/${userItem.email}/interest`">관심사</v-tab>
        <v-tab :to="`/profile/${userItem.email}/linkbox`">링크박스</v-tab>
        <v-tab :to="`/profile/${userItem.email}/scrap`">스크랩</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>

  </v-container>
</template>

<script>
import { mapActions, mapGetters, mapState } from 'vuex'
import profileCard from '@/components/profile/ProfileCard'
import profileCardEdit from '@/components/profile/ProfileCardEdit'

export default {
  components: {
    profileCard,
    profileCardEdit,
  },
  data: () => ({
    editedProfile: null,
  }),
  created() {
    const userItem = this.$route.params.email;
    this.fetchUserInfo(userItem)
  },
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
    ...mapGetters('memberStore', ['userInfo'])
  },
  watch: {
    watch() {
      this.userInfo
    }
  },
  methods: {
    ...mapActions('memberStore', ['fetchUserInfo', 'updateUserProfile']),
    replaceByDefault(e) {
      e.target.src = '../assets/logo.svg'
      console.log(e.target.src)
    },
    editProfile(profileData) {
      this.editedProfile = profileData
    },
    doneEdit(profileData) {
      // if (!this.editedProfile) {
      //   return;
      // }
      if (profileData) {
        this.editedProfile = profileData
        this.updateUserProfile(this.editedProfile)
      } else {
        this.editedProfile = null
      }
      this.editedProfile = null
    },
  },
}
</script>

<style>

</style>