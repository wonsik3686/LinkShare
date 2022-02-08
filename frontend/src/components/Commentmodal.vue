<template>
  <v-dialog v-model="show" max-width="700px">
    <v-card justify="center">

    <v-container>
      <v-card-title>
        <h4>개의 댓글이 있습니다</h4>
      </v-card-title>
      <v-divider class="mx-5 mb-2"></v-divider>


      <v-list-item v-for="comment in comments" :key="comment.title">
        
        <!-- 댓글 작성자 프로필 사진 -->
        <v-list-item-avatar class="ms-5">
          <v-img
            :alt="`${comment.nickname} avatar`"
            :src="comment.avatar"
          ></v-img>
        </v-list-item-avatar>

        <!-- 댓글 작성자 닉네임 -->
        <v-list-item-content class="text-left">
          <v-list-item-title v-text='comment.nickname'></v-list-item-title>
        </v-list-item-content>

        <!-- 댓글 내용 -->
        <v-list-item-content class="text-left">
          <v-list-item-title v-text='comment.content'></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <br>
      <v-divider class="mx-5"></v-divider>

      <v-card-actions class="justify-center py-0">
      <v-btn text v-on:click="detail">댓글 전체보기</v-btn>
      </v-card-actions>

      <v-card-actions class="justify-center pt-5 pb-0">
        <v-btn text @click.stop="show=false">Close</v-btn>
      </v-card-actions>
    </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data: () => ({
    comments: [
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
        nickname: 'Jason Oner',
        content: '댓글을 달았습니다'
      }, 
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
        nickname: 'Mike Carlson',
        content: '댓글을 달았습니다222'
      },     
    ],
  }),
  props: {
    value: Boolean
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  },
  methods: {
    detail() {
      this.$router.replace('linkbox')
    }
  }
}
</script>
