<template>
  <v-card id="edit" class="edit">
    
    <v-card-text>
      <v-text-field
        label="제목"
        :value="link.title"
        @input="onInputTitle"
      />
      <v-text-field
        label="URL"
        :value="link.url"
        @input="onInputURL"
      />
    </v-card-text>
    
    <v-divider></v-divider>

    <v-card-text>
      <v-textarea
        label="메모"
        :value="link.desc"
        @input="onInputDesc"
        rows="3"
      />
    </v-card-text>
    <v-btn @click="doneEdit">완료</v-btn>
    <v-btn @click="doneDelete">삭제</v-btn>
  </v-card>
</template>

<script>
export default {
  props: {
    link: Object
  },
  data: () => ({
    editedCardData: null
  }),
  mounted() {
    this.editedCardData = this.link
  },
  methods: {
    onInputTitle(event) {
      this.editedCardData.title = event.trim()
    },
    onInputURL(event) {
      this.editedCardData.url = event.trim()
    },
    onInputDesc(event) {
      this.editedCardData.desc = event.trim()
    },
    doneEdit() {
      this.$emit('done-edit', this.editedCardData)
    },
    doneDelete() {
      console.log(this.editedCardData.id)
      this.$emit('done-delete', this.editedCardData.id)
    },
  }

}
</script>

<style>

</style>