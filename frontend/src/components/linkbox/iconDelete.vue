<template>
  <v-btn icon color="grey" @click="clickDelete">
    <v-icon medium>mdi-delete</v-icon>
  </v-btn>
</template>

<script>
export default {
  porps: ['boxid'],
  methods: {
    clickDelete() {
      deleteLinkbox(this.boxid,
      (res) => {
        if (res.data.msg === 'success') {
          this.$router.replace('/')
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
  }
}
</script>

<style>

</style>