<template>
  <v-list>
    <v-list-item>
      <v-list-item-icon>
        <v-icon size="50">mdi-account-circle</v-icon>
      </v-list-item-icon>

      <v-list-item-content>
        <v-textarea outlined v-model="myComment.content" rows="3" auto-grow/>
        <v-btn color="#2C97DE" class="white--text" @click="onSubmit">comment</v-btn>
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script>
import { mapState } from 'vuex'

export default {
  data: () => ({
    myComment: { uid: '', boxid: '', content: '', email: '' },
  }),
  props: ['boxid'],
  computed: {
    // state에서 현재 유저정보 가져오기
    ...mapState('memberStore', ['userInfo'])
  },
  created() {
    console.log(this.userInfo)
  },
  methods: {
    // 버튼 클릭시 이벤트 emit
    onSubmit() {
      this.myComment.uid = this.userInfo.id
      this.myComment.boxid = this.boxid
      this.myComment.email = this.userInfo.email
      this.$emit('create-comment', this.myComment)
      this.myComment = { uid: '', boxid: '', content: '', email: '' }
    }
  }
}
</script>

<style>

</style>