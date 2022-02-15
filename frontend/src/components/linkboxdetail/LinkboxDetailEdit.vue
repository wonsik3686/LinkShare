<template>
  <v-container>
    <v-card>
      <v-card-title>
        <div class="text-h3 font-weight-bold">
          편집
        </div>
      </v-card-title>
      <v-card-text>
        <v-text-field
          label="제목"
          :value="linkbox.title"
          @input="onInputTitle"
        />
        <v-textarea
          rows="4"
          label="개요"
          :value="linkbox.desc"
          @input="onInputDesc"
        />
      </v-card-text>

      <v-card-actions>
        <v-btn @click="doneEdit">save</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
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
  }
}
</script>

<style>

</style>