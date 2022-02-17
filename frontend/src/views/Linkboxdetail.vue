<template>
  <v-container>
    <!-- 뒤로가기 -->
    <v-btn icon color="grey" class="mb-5" @click="goBack">
      <v-icon x-large>mdi-arrow-left</v-icon>
    </v-btn>
    
    <v-row class="justify-center">
      <iconLike v-if="linkbox !== '' && user !== ''"
        :user="user" :linkbox="this.linkbox"/>
      <iconScrap v-if="linkbox !== '' && user !== ''"
        :user="user" :linkbox="this.linkbox"/>
    </v-row>
    
    <br>

    <v-row class="justify-center">
      <v-col cols="9">
        <linkboxDetail
          v-if="!editing"
          :linkbox="linkbox"
          :boxUser="user"
          @click-delete="clickDelete" 
          @click-edit="clickEdit"
        />
        <linkboxDetailEdit
          v-else :linkbox="linkbox"
          @done-edit="doneEdit"
          @cancel-edit="cancelEdit"
        />
      </v-col>
    </v-row>

    <v-container>

      <v-tabs fixed-tabs>
        <v-tab :to="`/${user.email}/linkbox/${boxid}`">LinkBox</v-tab>
        <v-tab :to="`/${user.email}/linkbox/${boxid}/linklist`">LinkList</v-tab>
        <v-tab :to="`/${user.email}/linkbox/${boxid}/linktree`">LinkTree</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
      
      <br><br>

      <commentList/>
      
    </v-container>

  </v-container>
</template>

<script>
import iconLike from '@/components/linkbox/iconLike'
import iconScrap from '@/components/linkbox/iconScrap'
import commentList from '@/components/comment/commentList'
import { getLinkboxInfo, updateLinkbox, deleteLinkbox } from "@/api/linkbox";
import { userInfo } from '@/api/member'
import { mapState } from 'vuex'
import linkboxDetail from '@/components/linkboxdetail/LinkboxDetail'
import linkboxDetailEdit from '@/components/linkboxdetail/LinkboxDetailEdit'

export default {
  components: {
    iconLike,
    iconScrap,
    commentList,
    linkboxDetail,
    linkboxDetailEdit,
  },
  data: () => ({
    tab: null,
    linkbox: '',
    links: null,
    boxid: null,
    myInfo: '',
    user: '',
    editedLinkbox: null,
    editing: false,
  }),
  created() {
    this.boxid = this.$route.params.boxid
    this.linkboxInfo()
    this.myInfo = this.userInfo
    const userEmail = this.$route.params.email
    this.getUserEmail(userEmail)
  },
  methods: {
    linkboxInfo() {
      getLinkboxInfo(this.boxid,
      (res) => {
        if (res.data.msg === "success") {
          console.log(res.data.object)
          this.linkbox = res.data.object
        } else {console.log(res.data.msg)}
      },
      (err) => console.log(err))
    },
    getUserEmail(email) {
      userInfo(email,
      (res) => {
        if (res.data.msg === 'success') {
          this.user = res.data.object.userInfo
          console.log(this.user)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    clickDelete() {
      deleteLinkbox(this.boxid,
      (res) => {
        if (res.data.msg === 'success') {
          this.$router.replace('/')
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    clickEdit() {
      this.editing = true
    },
    doneEdit(boxdata) {
      boxdata.boxid = this.boxid
      console.log(boxdata)
      updateLinkbox(boxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success updateLinkbox')
          this.linkboxInfo()
          this.editing = false
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    cancelEdit() {
      this.editing = false
    },
    goBack() {
      this.$router.push('/')
    }
  },
  computed: {
    ...mapState('memberStore', ['userInfo']),
  }
}
</script>

<style>

</style>