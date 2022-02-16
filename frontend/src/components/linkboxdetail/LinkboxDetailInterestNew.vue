<template>
  <v-container>
    <v-row>
      <v-combobox
        v-model="select"
        :items="items"
        label="관심있는 분야를 선택해주세요"
        clearable
        multiple
        chips
      >
      </v-combobox>

      <v-btn
        @click="onSubmit"
        text
        color="blue"
        height="auto"
      >
        add
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    interests: Array
  },
  data: () => ({
    select: [],
    items: ['C', 'Python', 'Java', 'HTML', 'CSS'],
  }),
  methods: {
    onSubmit() {
      this.$emit('add-interest', this.select)
      this.select = []
    }
  }
}
</script>

<style>

</style>