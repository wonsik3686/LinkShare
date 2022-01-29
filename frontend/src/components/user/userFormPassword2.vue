<template>
<div>
  <v-text-field
    v-model="setPassword"
    :rules="form.rules1"
    :counter="!noValidation"
    :hint="form.hint"
    label="비밀번호"
    :placeholder="form.placeholder"
    :hide-details="noValidation"
    :append-icon="toggle1.icon"
    :type="toggle1.type"
    @click:append="show1 = !show1"
    outlined
    autocomplete="on"
  >
  </v-text-field>
  <v-text-field
    :rules="form.rules2"
    :counter="!noValidation"
    label="비밀번호 확인"
    :hide-details="noValidation"
    :append-icon="toggle2.icon"
    :type="toggle2.type"
    @click:append="show2 = !show2"
    outlined
    autocomplete="on"
  >
  </v-text-field>
</div>
</template>

<script>
export default {
  name: "userFormPassword2",
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
      show1: false,
      show2: false,
    }
  },
  computed: {
    setPassword: {
      get () { return this.password },
      set (newVal) { return this.$emit('update:password', newVal) }
    },
    form () {
      const min = '8자 이상'
      const msg1 = `${min}으로 작성해주세요`
      const msg2 = '패스워드가 일치하지 않습니다'
      const required = v => !!v || ''
      const format = v => /^[\w-]{8,72}$/.test(v) || msg1
      const iscorr = v => ('' || v === this.password) || msg2

      const rules1 = this.noValidation ? [required] : [format]
      const rules2 = this.noValidation ? [required] : [iscorr]
      const hint = this.noValidation ? undefined : msg1
      const placeholder = this.noValidation ? undefined : min

      return { rules1, rules2, hint, placeholder }
    },
    toggle1 () {
      const icon = this.show1 ? 'mdi-eye' : 'mdi-eye-off'
      const type = this.show1 ? 'text' : 'password'
      return {icon, type}
    },
    toggle2 () {
      const icon = this.show2 ? 'mdi-eye' : 'mdi-eye-off'
      const type = this.show2 ? 'text' : 'password'
      return {icon, type}
    }
  },
  watch: {
    'password': function() {
      console.log(this.password)
    },
  }
}
</script>

<style>

</style>