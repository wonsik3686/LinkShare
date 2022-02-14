<template>
  <div>
    <v-list-item>
      <v-list-item-avatar>
        <img :src="this.comment.avatar" alt="">
      </v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title>
          {{ comment.nickName }}
          <span>{{ comment.regtime }}</span>
        </v-list-item-title>

        <v-list-item-subtitle>
          {{ comment.content }}
        </v-list-item-subtitle>
        
        <v-list-item-subtitle>
          <h4>
            <v-btn text @click="commentEdit">EDIT</v-btn>
            <v-btn text @click="commentDelete">DELETE</v-btn>
          </h4>
        </v-list-item-subtitle>
      </v-list-item-content>

    </v-list-item>

  </div>
</template>

<script>
// import { userInfo } from '@/api/member'

export default {
  props: {
    comment: Object,
  },
  data: () => ({
    user: null,
  }),
  created() {
    // 회원정보 가져오기
    console.log(this.comment)
    // userInfo(this.comment.email,
    // (response) => {
    //   if (response.data.msg === "success") {
    //     console.log(response.data)
    //     // this.user = response.data.object.userInfo
    //   } else { console.log(response.data.msg) }
    // }, (err) => console.log(err))
  },
  methods: {
    // edit 버튼 누르면 발동, commentList로
    commentEdit() {
      this.$emit('comment-edit', this.comment)
    },
    commentDelete() {
      this.$emit('comment-delete', this.comment)
    }
  }
}
</script>

<style>

</style>