<template>
  <v-container>
    
    <v-row>
      <v-col>
        <v-card class="mx-auto" v-if="userInfo.email === userItem.email">
          <v-card-title>[test]새 링크박스</v-card-title>
          <v-card-text>
            <v-text-field
              label="제목"
              placeholder="새 링크박스 제목"
              outlined
              v-model="newLinkbox.title"
            />
            <v-text-field
              label="관심사"
              placeholder="새 링크박스 관심사"
              outlined
              v-model="newLinkbox.interests[0]"
            />
            <v-textarea
              label="메모"
              placeholder="새 링크박스 메모"
              outlined
              v-model="newLinkbox.desc"
            />
          </v-card-text>
          {{ this.newLinkbox }}
          <v-card-actions>
            <v-btn icon @click="createNewLinkbox">
              <v-icon large>mdi-plus</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col v-for="linkbox in linkboxList" :key="linkbox.id">
        <linkboxItem :linkbox="linkbox" :userItem="userItem"/>
      </v-col>
    </v-row>
    


  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { createLinkbox, listUserLinkbox } from '@/api/linkbox'
import linkboxItem from './LinkboxItem'

export default {
  components: {
    linkboxItem
  },
  data: () => ({
    newLinkbox: { title: '', desc: '', interests: [] },
    linkboxList: null,
  }),
  created() {
    const userItem = this.$route.params.email;
    this.fetchUserInfo(userItem)
  },
  // mounted는 렌더링 직전에 실행됨(created 다음)
  mounted () {
    listUserLinkbox(this.userItem.id,
    (response) => {
      if (response.data.msg === "success") {
        console.log('success listUserLinkbox')
        this.linkboxList = response.data.object
      }
    })
  },
  // 분리한 store에서 state 불러오기
  // mapState의 경우 computed에서 불러오지 않으면 error
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
  },
  methods: {
    ...mapActions('memberStore', ['fetchUserInfo']),
    createNewLinkbox() {
      // object 데이터에 uid객체 추가
      this.newLinkbox.uid = this.userItem.id
      console.log(this.newLinkbox)

      // api 통신
      createLinkbox(this.newLinkbox, 
      (response) => {
        if (response.data.msg === "success") {
          console.log('success createLinkbox')
          alert('새 링크박스 생성')
          this.newLinkbox = { title: '', desc: '', interests: [] }
        } else {console.log(response.data.msg)}
      }, (err) => {console.log(err)})
    },
  }
}
</script>

<style>

</style>