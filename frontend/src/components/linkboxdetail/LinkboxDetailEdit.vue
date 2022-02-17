<template>
  <v-card>
    <v-container>
      <v-card-title>
        <div class="text-h3 font-weight-bold pt-5">
          편집
        </div>
      </v-card-title>

      <v-card-text class="pt-2">
        <v-text-field
          label="제목"
          :value="linkbox.title"
          @input="onInputTitle"
        />
        <v-textarea
          label="개요"
          :value="linkbox.desc"
          @input="onInputDesc"
          rows="4"
          auto-grow
        />
      </v-card-text>

      <v-card-actions class="justify-center">
        <v-btn plain x-large color="#2C97DE" @click="doneEdit">save</v-btn>
        <v-btn plain x-large color="#353C45" @click="cancelEdit">cancel</v-btn>
      </v-card-actions>

    </v-container>
  </v-card>
</template>

<script>
export default {
  props: ['linkbox'],
  data: () => ({
    editedLinkbox: { boxid:'', title: '', desc: '' },
  }),
  mounted() {
    this.editedLinkbox = {
      boxid: this.linkbox.id,
      title: this.linkbox.title,
      desc: this.linkbox.desc
    }
  },
  methods: {
    onInputTitle(event) {
      if (event) {
        this.editedLinkbox.title = event.trim()
      } else { this.editedLinkbox.title = this.linkbox.title }
    },
    onInputDesc(event) {
      if (event) {
        this.editedLinkbox.desc = event.trim()
      } else { this.editedLinkbox.desc = this.linkbox.desc }
    },
    doneEdit() {
      this.$emit('done-edit', this.editedLinkbox)
    },
    cancelEdit() {
      this.editedLinkbox = { boxid:'', title: '', desc: '' },
      this.$emit('cancel-edit')
    },  
  }
}
</script>

<style>

</style>