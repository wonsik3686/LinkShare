<template>
  <v-dialog v-model="show" max-width="700px">
    <v-card justify="center">
      <v-card-title>
        <h4>개의 댓글이 있습니다</h4>
      </v-card-title>

      <br>
      <br>
      <v-divider class="mx-5"></v-divider>

      <v-card-actions class="justify-center">
      <button v-on:click="detail">댓글 전체보기</button>
      </v-card-actions>

      <v-card-actions class="justify-center">
        <v-btn text @click.stop="show=false">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
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
