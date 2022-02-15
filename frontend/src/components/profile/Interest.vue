<template>
  <v-container>
    <h1>관심사</h1>
    <v-chip
      class="ma-1 px-4"
      v-for="interest in interests"
      :key="interest.title"
      @click:close="remove(interest)"
      close
      color="blue"
      text-color="white"
    >
      <strong>{{ interest.title }}</strong>
    </v-chip>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    interests: [
      { title: "python" },
      { title: "vue.js" },
      { title: "springboot" },
    ]
  }),
  methods: {
    remove (item) {
      this.interests.splice(this.interests.indexOf(item), 1)
      this.interests = [...this.interests]
    }
  }
}
</script>

<style>

</style>