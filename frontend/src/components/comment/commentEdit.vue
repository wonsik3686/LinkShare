<template>
  <v-list>
    <v-list-item>
      <v-list-item-avatar>
        <!-- <img :src="this.userInfo.imagePath" alt=""> -->
      </v-list-item-avatar>

      <v-list-item-content>
        {{ editedComment }}
        <v-textarea
          outlined
          :value="commentToEdit.content"
          @input="onInput"
        />
        <v-btn @click="onSubmit">post</v-btn>
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script>
export default {
  props: {
    commentToEdit: Object,
  },
  data: () => ({
    editedComment: null,
  }),
  created() {
    console.log(this.commentToEdit.content)
  },
  mounted() {
    this.editedComment = this.commentToEdit.content
  },
  methods: {
    onInput(event) {
      this.editedComment = event.trim()
    },
    // 버튼 클릭시 이벤트 emit
    onSubmit() {
      console.log(this.editedComment)
      this.$emit('done-edit', this.editedComment)
      
    }
  }
}
</script>

<style>

</style>