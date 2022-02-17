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
            label="Title"
            rounded
            outlined
            v-model="boxdata.title"
          />
          <v-textarea
            label="Description"
            rounded
            outlined
            v-model="boxdata.desc"
            rows="4"
            auto-grow
          />
          <v-combobox
              v-model="boxdata.interests"
              :items="items"
              label="관련 있는 태그를 추가해주세요"
              clearable
              multiple
              chips
            >
          </v-combobox>
        </v-col>

        <v-col class="align-center">
          <v-row class="justify-center">
            <v-btn fab dark x-large color="#2C97DE" class="white--text" @click="onSubmit">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </v-row>
        </v-col>

      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapState } from 'vuex'

export default {
  components: {
  },
  data: () => ({
    boxdata: { uid: '', title: '', desc: '', interests: [] },
    items: ['C', 'Python', 'Java', 'HTML', 'CSS'],
  }),
  computed: {
    ...mapState('memberStore', ['userInfo'])
  },
  methods: {
    onSubmit() {
      this.boxdata.uid = this.userInfo.id
      this.$emit('create-linkbox', this.boxdata)
      this.boxdata = { uid: '', title: '', desc: '', interests: [] }
    },
    cancelCreate() {
      this.boxdata = { uid: '', title: '', desc: '', interests: [] }
    }
  },
  created() {
    console.log(this.userInfo)
  }
}
</script>

<style>
.cardColor {
  border-color: white !important;
}
</style>