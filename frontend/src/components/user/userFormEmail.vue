<template>
  <v-text-field
    v-model="setEmail"
    :rules="rules"
    label="이메일"
    placeholder="example@email.com"
    outlined
  >
  </v-text-field>
</template>

<script>
export default {
  name: "userFormEmail",
  props: {
    email: {
      // type: string,
      default: ''
    }
  },
  data () {
      return {
        rules: [
          v => !!v || '',
          v => /.+@.+\..+/.test(v) || ''
        ]
      }
    },
  computed: {
    setEmail: {
      get () { return this.name },
      set (newVal) { return this.$emit('update:email', newVal) }
    },
  }
}
</script>

<style>

</style>