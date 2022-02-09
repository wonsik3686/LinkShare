<template>
  <v-container>
    <h1>링크박스</h1>
    
    <v-row>
      <v-col cols="md-6">
        <v-card>
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
              v-model="newLinkbox.interest[0]"
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
    


  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import { createLinkbox } from '@/api/linkbox'

export default {
  data: () => ({
    newLinkbox: {
      title: '',
      desc: '',
      interest: [],
    }
  }),
  // 분리한 store에서 state 불러오기
  // mapState의 경우 computed에서 불러오지 않으면 error
  computed: {
    ...mapState('memberStore', ['userInfo']),
  },
  methods: {
    createNewLinkbox() {
      // object 데이터에 uid객체 추가
      this.newLinkbox.uid = this.userInfo.id
      console.log(this.newLinkbox)

      // api 통신
      createLinkbox(this.newLinkbox, 
      (response) => {
        if (response.data.msg === "success") {
          console.log('success createLinkbox')
          alert('새 링크박스 생성')
          this.formReset()
        } else {console.log(response.data.msg)}
      }, (err) => {console.log(err)})
    },
    formReset () {
      this.$refs.form.reset()
      this.newLinkbox = { title: '', desc: '', interest: [] }
    },
  }
}
</script>

<style>

</style>