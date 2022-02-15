<template>
  <v-container>
    <!-- 뒤로가기 -->
    <v-btn icon color="grey" class="mb-5" @click="goBack">
      <v-icon x-large>mdi-arrow-left</v-icon>
    </v-btn>

    <linkboxDetail :linkbox="linkbox" @click-delete="clickDelete"/>
    <linkboxDetailEdit :linkbox="linkbox" @done-edit="doneEdit"/>

    <v-container>

      <v-tabs fixed-tabs>
        <v-tab :to="`/linkbox/${boxid}/linklist`">링크목록</v-tab>
        <v-tab :to="`/linkbox/${boxid}/linktree`">링크트리</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>

      <iconLike v-if="linkbox !== '' && user !== ''"
        :user="user" :linkbox="this.linkbox"/>
      <iconScrap v-if="linkbox !== '' && user !== ''"
        :user="user" :linkbox="this.linkbox"/>

      <commentList/>
      
    </v-container>

  </v-container>
</template>

<script>
import iconLike from '@/components/linkbox/iconLike'
import iconScrap from '@/components/linkbox/iconScrap'
import commentList from '@/components/comment/commentList'
import { getLinkboxInfo, updateLinkbox, deleteLinkbox } from "@/api/linkbox";
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
    user: '',
    editedLinkbox: null,
  }),
  created() {
    this.boxid = this.$route.params.boxid
    this.user = this.userInfo
    this.linkboxInfo()
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
    clickDelete() {
      deleteLinkbox(this.boxid,
      (res) => {
        if (res.data.msg === 'success') {
          this.$router.replace('/')
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    doneEdit(boxdata) {
      boxdata.boxid = this.boxid
      console.log(boxdata)
      updateLinkbox(boxdata,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success updateLinkbox')
          this.linkboxInfo()
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
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