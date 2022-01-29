<template>
  <v-text-field
    v-model="setName"
    :rules="rules"
    :counter="max"
    label="닉네임"
    placeholder="닉네임을 입력하세요"
    outlined
  >
  </v-text-field>
</template>

<script>
export default {
  name: "userFormName",
  props: {
    name: {
      // type: string,
      default: ''
    }
  },
  data () {
    const max = 30
    return {
      max,
      rules: [
        v => !!v || '',
        v => (!!v && max >= v.length) || `${max}자 이내로 작성해주세요`
      ]
    }
  },
  computed: {
    setName: {
      get () { return this.name },
      set (newVal) { return this.$emit('update:name', newVal) }
    }
  },
}
</script>

<style>

</style>