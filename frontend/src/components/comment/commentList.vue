<template>
  <v-container>
    <v-row class="justify-between">
      <v-col align="center" cols="2">
        <h1>
          {{ filteredComments.length }}
        </h1>
        <p>comments</p>
      </v-col>
    </v-row>

    <v-divider></v-divider>

    <!-- create-comment가 emit으로 전달되면 createCommen 함수 실행 -->
    <commentInput
      @create-comment="createComment"
      :boxid="boxid"
    />
    <commentEdit
      v-if="this.editing"
      :commentToEdit="commentToEdit"
      @done-edit="doneEdit"
    />

    <v-list>
      <div v-for="comment in filteredComments" :key="comment.id">
        <commentItem
          :comment="comment"
          @comment-edit="commentEdit"
          @comment-delete="commentDelete"
        />
      </div>
    </v-list>
  </v-container>
</template>

<script>
import commentInput from './commentInput.vue'
import commentEdit from './commentEdit.vue'
import commentItem from './commentItem.vue'
import { createLinkboxComment, searchLinkBoxCommentByBoxId } from '@/api/linkbox'
import { updateLinkboxComment, deleteLinkboxComment } from '@/api/linkbox'

export default {
  components: {
    commentInput,
    commentEdit,
    commentItem,
  },
  data: () => ({
    comments: [],
    commentToEdit: null,
    editedComment: {id:'', content: ''},
    editing: false,
  }),
  created() {
    this.boxid = this.$route.params.boxid
    this.getCommentList(this.boxid)
  },
  methods: {
    getCommentList(boxid) {
      searchLinkBoxCommentByBoxId(boxid,
      (res) => {
        if (res.data.msg === "success") {
          console.log('success searchLinkBoxCommentByBoxId')
          this.comments = res.data.object
        }
      }, (err) => console.log(err))
    },
    createComment(comment) {
      createLinkboxComment(comment,
      (response) => {
        if (response.data.msg === "success") {
          console.log('success createComment')
          this.getCommentList(this.boxid)
        } else { console.log(response.data.msg)}
      }, (err) => console.log(err))
    },
    commentEdit(comment) {
      // 이 페이지의 commentToEdit에 전달된 값 저장
      this.commentToEdit = comment
      this.editing = true
    },
    doneEdit(comment) {
      this.editedComment.id = this.commentToEdit.id
      this.editedComment.content = comment
      // console.log(this.editedComment)
      updateLinkboxComment(this.editedComment,
      (response) => {
        if (response.data.msg === "success") {
          console.log('success doneEdit')
          this.getCommentList(this.boxid)
          this.commentToEdit = null
          this.editing = false
        } else {
          console.log(response.data.msg)
        }
      }, (err) => console.log(err))
    },
    commentDelete(comment) {
      this.deleteIndex = comment.index
      deleteLinkboxComment(comment.id,
      (res) => {
        if (res.data.msg === "success") {
          console.log('success deleteLinkboxComment')
          this.getCommentList(this.boxid)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    }
  },
  computed: {
    // comments에 변화가 있는(comment추가) 경우 comments 갱신
    filteredComments() {
      return this.comments
    },
  }
}
</script>

<style>

</style>