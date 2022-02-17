<template>
  <v-card outlined class="cardColor">
    <v-card-text>
      <v-row class="align-center">

        <v-col class="align-center">
          <v-row class="justify-center">
            <v-btn fab dark @click="cancelCreate" x-large color="#EF4B4C" class="white--text">
              <v-icon>mdi-restart</v-icon>
            </v-btn>
          </v-row>
        </v-col>

        <v-col cols="7">
          <v-text-field
            rounded
            outlined
            label="Title"
            v-model="newCardData.title"
          />
          <v-text-field
            rounded
            outlined
            v-model="newCardData.url"
            label="URL"
          />
          <v-textarea
            rounded
            outlined
            label="Memo (optional)"
            v-model="newCardData.desc"
            auto-grow
          />
        </v-col>
        
        <v-col class="align-center">
          <v-row class="justify-center">
            <v-btn fab dark @click="doneCreate" x-large color="#2C97DE" class="white--text">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </v-row>
        </v-col>

      </v-row>
    </v-card-text>
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
    cancelCreate() {
      this.newCardData = { boxid: '', id: '', title: '', desc: '', url: '' }
    },
  }

}
</script>

<style>
.cardColor {
  border-color: white !important;
}
</style>