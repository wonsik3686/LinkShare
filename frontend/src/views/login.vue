<template>
  <v-container>
    <v-row align="center" justify="center">

      <v-col cols="12" class="text-center">
        <h1 class="text-h5 font-weight-bold">
          로그인
        </h1>
      </v-col>

      <v-card flat width="80%" max-width="350">
        <!-- v-form 태그의 validation이 유효한 경우 ture 반환 -->
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
            @click="login"
          >
            로그인
          </v-btn>
        </ValidationObserver>
        

          <!-- true 반환된 경우 버튼 무효화 -->
        

        <v-card-text>
          {{ params }}
        </v-card-text>
        
        <snsLogin/>

      </v-card>

    </v-row>
  </v-container>
</template>

<script>
import { ValidationObserver, ValidationProvider, extend } from 'vee-validate'
import { required, email, min } from 'vee-validate/dist/rules'
extend('required', required)
extend('email', email)
extend('min', min)
import snsLogin from "@/components/snsLogin/snsLogin"

export default {
  components: {
    ValidationProvider,
    ValidationObserver,
    snsLogin
  },
  data () {
    return {
      value: '',
      showPassword: false,
      loading: false,
      params: {user: { nickname: '', email: '', password: ''} }
    }
  },
  methods: {
    login () {
      this.loading = true
      setTimeout(() => {
        // index.js의 action 매소드 불러오기
        this.$store.dispatch('login')
        // 메인화면으로 이동
        this.$router.replace('/')
        this.loading = false
      }, 1500)
    },
  },
}
</script>

<style>

</style>