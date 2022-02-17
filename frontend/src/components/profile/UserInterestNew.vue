<template>
  <v-container>
    <v-row class="pt-7">
      <v-icon class="pr-5">mdi-tag</v-icon>

      <v-combobox
        v-model="select"
        :items="items"
        label="관심사를 추가해주세요"
        clearable
        multiple
        chips
      >
      </v-combobox>
      
      <v-btn
        @click="onSubmit"
        large
        plain
        color="#2C97DE"
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