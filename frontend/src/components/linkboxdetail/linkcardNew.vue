<template>
  <v-card>
    <v-card-text>
      <v-text-field
        label="제목"
        v-model="newCardData.title"
      />
      <v-text-field
        v-model="newCardData.url"
        label="URL"
      />
    </v-card-text>
    
    <v-divider></v-divider>

    <v-card-text>
      <v-textarea
        label="메모"
        v-model="newCardData.desc"
        rows="3"
      />
    </v-card-text>

    <v-btn @click="doneCreate">완료</v-btn>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    newCardData: { boxid: '', id: '', title: '', desc: '', url: '' }
  }),
  methods: {
    doneCreate() {
      console.log('done Create')
      this.$emit('done-create', this.newCardData)
      this.newCardData = { boxid: '', id: '', title: '', desc: '', url: '' }
    },
  }

}
</script>

<style>

</style>