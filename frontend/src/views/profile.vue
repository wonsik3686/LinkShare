<template>
  <v-container>
    <profileCard
      v-if="!editing"
      :userEmail="userEmail"
      :userInfo="this.userInfo"
      @click-edit="clickEdit"
    />
    <profileCardEdit v-else :userInfo="userInfo" @done-edit="doneEdit" @cancel-edit="cancelEdit"/>

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
    userEmail: null,
    editing: false,
    editedProfile: null,
  }),
  created() {
    const userEmail = this.$route.params.email;
    this.fetchUserInfo(userEmail)
    this.userEmail = userEmail
  },
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
    ...mapGetters('memberStore', ['userInfo']),
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
    clickEdit() {
      this.editing = true
    },
    doneEdit(profileData) {
      // if (!this.editedProfile) {
      //   return;
      // }
      if (profileData) {
        this.editedProfile = profileData
        this.updateUserProfile(this.editedProfile)
        
        this.editing = false
      } else {
        this.editedProfile = null
      }
      this.editedProfile = null
    },
    cancelEdit() {
      this.editing = false
    }
  },
}
</script>

