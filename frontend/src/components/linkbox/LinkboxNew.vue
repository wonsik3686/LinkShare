<template>
  <v-container>
    <v-card class="mx-auto rounded-xl">
      <v-card-title class="blue--text">Create a Linkbox</v-card-title>
      <v-card-text>
        <v-text-field
          label="제목"
          placeholder="새 링크박스 제목"
          outlined
          v-model="boxdata.title"
        />
        <v-textarea
          label="메모"
          placeholder="새 링크박스 메모"
          outlined
          v-model="boxdata.desc"
        />
        <v-combobox
            v-model="boxdata.interests"
            :items="items"
            label="관심있는 분야를 선택해주세요"
            clearable
            multiple
            chips
          >
        </v-combobox>
      </v-card-text>

      <v-card-actions>
        <v-btn rounded block color="blue" class="white--text" @click="onSubmit">
          <span class="mx-3">create</span>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
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
  },
  created() {
    console.log(this.userInfo)
  }
}
</script>

<style>

</style>