<template>
  <v-container>
    <v-row dense class="justify-center">
      <v-col cols="10">
        <linkboxNew
          v-if="userInfo.email === userItem.email"
          :userInfo="userInfo" @create-linkbox="createNewLinkbox"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-col
        cols='col-xs-1 col-sm-6 col-md-6 col-lg-4 col-xl-4'
        v-for="linkbox in linkboxList" :key="linkbox.id"
      >
        <linkbox :boxid="linkbox.id"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { createLinkbox, listUserLinkbox } from '@/api/linkbox'
import linkbox from '@/components/linkbox/Linkbox'
import linkboxNew from '@/components/linkbox/LinkboxNew'

export default {
  components: {
    linkbox,
    linkboxNew,
  },
  data: () => ({
    linkboxList: null,
  }),
  // 분리한 store에서 state 불러오기
  // mapState의 경우 computed에서 불러오지 않으면 error
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
  },
  created() {
    const userItem = this.$route.params.email;
    this.fetchUserInfo(userItem)
  },
  // mounted는 렌더링 직전에 실행됨(created 다음)
  mounted () {
    this.listLinkbox()
  },
  methods: {
    ...mapActions('memberStore', ['fetchUserInfo']),
    createNewLinkbox(boxdata) {
      createLinkbox(boxdata, 
      (response) => {
        if (response.data.msg === "success") {
          console.log('success createLinkbox')
          alert('새 링크박스 생성')
          this.listLinkbox()
        } else {console.log(response.data.msg)}
      }, (err) => {console.log(err)})
    },
    listLinkbox() {
      listUserLinkbox(this.userItem.id,
      (res) => {
        if (res.data.msg === "success") {
          console.log('success listUserLinkbox')
          this.linkboxList = res.data.object
        } else {console.log(res.data.msg)}
      }, (err) => console.log(err))
    }
  }
}
</script>

<style>

</style>
