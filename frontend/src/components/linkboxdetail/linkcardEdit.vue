<template>
  <v-card id="edit" class="edit">
    
    <v-card-text class="pb-0">
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

    <v-card-text class="pb-0">
      <v-textarea
        label="메모"
        :value="link.desc"
        @input="onInputDesc"
        filled
        rows="3"
        auto-grow
      />
    </v-card-text>

    <v-card-actions class="justify-center">
      <v-btn plain @click="doneEdit" color="#2C97DE">save</v-btn>
      <v-btn plain @click="doneDelete" color="#EF4B4C">delete</v-btn>
    </v-card-actions>
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
    cancelEdit() {
      this.$emit('cancel-edit')
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