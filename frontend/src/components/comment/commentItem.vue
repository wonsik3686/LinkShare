<template>
  <div>
    <v-list-item>
      <v-list-item-icon>
        <v-icon size="50">mdi-account-circle</v-icon>
      </v-list-item-icon>

      <v-list-item-content>
        <v-list-item-title>
          <span class="text-subtitle-1 font-weight-bold">{{ comment.nickName }}</span>
          &nbsp;
          <span class="text-caption font-weight-light">{{ comment.regtime }}</span>
        </v-list-item-title>

        <v-list-item-subtitle>
          {{ comment.content }}
        </v-list-item-subtitle>
        
        <v-list-item-subtitle>
          <h4 class="text-end">
            <v-btn plain @click="commentEdit">EDIT</v-btn>
            <v-btn plain color="red" @click="commentDelete">DELETE</v-btn>
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