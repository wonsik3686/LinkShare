<template>
  <v-dialog v-model="show" max-width="400px">
    <v-card justify="center">
      <v-card-title class="justify-center">
        <h1 class="text-h5 font-weight-bold mt-3">로그인</h1>
      </v-card-title>

      <v-card-text>
        <ValidationObserver v-slot="{ invalid }" ref="form">
          <ValidationProvider
            v-slot="{ errors }"
            name="이메일"
            rules="required|email"
          >
            <v-text-field
              v-model="params.user.email"
              label="이메일"
              placeholder="example@email.com"
              :error-count="1"
              :error-messages="errors"
              outlined
            />
          </ValidationProvider>

          <ValidationProvider
            vid="password"
            v-slot="{ errors }"
            name="비밀번호"
            rules="required|min:8"
          >
            <v-text-field
              v-model="params.user.password"
              label="비밀번호"
              placeholder=""
              :counter="100"
              :type="showPassword ? 'text' : 'password'"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :error-count="1"
              :error-messages="errors"
              outlined
              autocomplete="on"
              @click:append="showPassword = !showPassword"
            />
          </ValidationProvider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <router-link to="#" class="body-2 text-decoration-none">
              비밀번호 찾기
            </router-link>
          </v-card-actions>

          <v-btn
            :disabled="invalid || loading"
            :loading="loading"
            block
            color="blue"
            class="white--text"
            @click="onSubmit"
            @keyup.enter="onSubmit"
          >
            로그인
          </v-btn>
        </ValidationObserver>   
      </v-card-text>

      <v-container>
        <snsLogin/>
      </v-container>

      <br>
      <v-card-actions class="justify-center">
        <v-btn text @click.stop="show=false">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { ValidationObserver, ValidationProvider, extend } from 'vee-validate'
import { required, email, min } from 'vee-validate/dist/rules'
extend('required', required)
extend('email', email)
extend('min', min)
import snsLogin from "@/components/snsLogin/snsLogin"
import { mapActions, mapState } from 'vuex'

const memberStore = "memberStore"

export default {
  components: {
    ValidationProvider,
    ValidationObserver,
    snsLogin
  },
  props: {
    value: Boolean
  },
  data () {
    return {
      showPassword: false,
      loading: false,
      params: {user: { email: '', password: ''} }
    }
  },
  computed: {
    ...mapState(memberStore, ['loggedIn']),
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  },
  methods: {
    ...mapActions(memberStore, ['userSignin', 'getUserProfile']),
    // vuex에서 action 실행, action에서 api 통신
    async onSubmit () {
      this.loading = true
      await this.userSignin(this.params.user)
      // let token = localStorage.getItem('token')
      // console.log(token)
      console.log(this.loggedIn)
      if (this.loggedIn) {
        await this.getUserProfile()
        this.$router.replace('/').catch(()=>{})
        // replace : URL 방문기록을 리셋
        // push : URL 방문기록에 추가
        alert('로그인 성공')
        this.show = false
        this.loading = false
        this.formReset()
      } else {
        console.log('로그인 실패')
        alert('로그인에 실패했습니다.')
        this.loading = false
      }

      setTimeout(() => {
        this.loading = false
      }, 1500)
    },
    formReset () {
      this.$refs.form.reset()
      this.params = { user: { email: '', password: ''}}
      this.v = ''
    },
  },
}
</script>

<style>

</style>