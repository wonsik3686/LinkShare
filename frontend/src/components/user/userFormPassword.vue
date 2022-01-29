<template>
  <v-text-field
    v-model="setPassword"
    :rules="form.rules"
    :counter="!noValidation"
    :hint="form.hint"
    label="비밀번호"
    :placeholder="form.placeholder"
    :hide-details="noValidation"
    :append-icon="toggle.icon"
    :type="toggle.type"
    outlined
    autocomplete="on"
    @click:append="show = !show"
  >
  </v-text-field>
</template>

<script>
export default {
  name: "userFormPassword",
  props: {
    password: {
      type: String,
      default: '',
    },
    noValidation: {
      type: Boolean,
      default: false,
    }
  },
  data () {
    return {
      show: false
    }
  },
  computed: {
    setPassword: {
      get () { return this.password },
      set (newVal) { return this.$emit('update:password', newVal) }
    },
    form () {
      const min = '8자 이상'
      const msg = `${min}으로 작성해주세요`
      const required = v => !!v || ''
      const format = v => /^[\w-]{8,72}$/.test(v) || msg

      const rules = this.noValidation ? [required] : [format]
      const hint = this.noValidation ? undefined : msg
      const placeholder = this.noValidation ? undefined : min
      return { rules, hint, placeholder }
    },
    toggle () {
      const icon = this.show ? 'mdi-eye' : 'mdi-eye-off'
      const type = this.show ? 'text' : 'password'
      return {icon, type}
    }
  }
}
</script>

<style>

</style>