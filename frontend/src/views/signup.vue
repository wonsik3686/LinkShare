<template>
  <v-container>
    <v-row align="center" justify="center">

      <v-col cols="12" class="text-center">
        <h1 class="text-h5 font-weight-bold">
          회원가입
        </h1>
      </v-col>

      <v-card flat width="80%" max-width="350">
        <!-- v-form 태그의 validation이 유효한 경우 ture 반환 -->
        <ValidationObserver v-slot="{ invalid }" ref="form">
          <ValidationProvider
            v-slot="{ errors }"
            name="닉네임"
            rules="required"
          >
            <v-text-field
              v-model="params.user.nickname"
              label="닉네임"
              placeholder="닉네임을 입력해주세요"
              :counter="30"
              :error-count="1"
              :error-messages="errors"
              outlined
            />
          </ValidationProvider>

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
              :type="showPassword1 ? 'text' : 'password'"
              :append-icon="showPassword1 ? 'mdi-eye' : 'mdi-eye-off'"
              :error-count="1"
              :error-messages="errors"
              outlined
              @click:append="showPassword1 = !showPassword1"
            />
          </ValidationProvider>
          
          <ValidationProvider
            v-slot="{ errors }"
            name="비밀번호 확인"
            rules="required|confirmed:password"
          >
            <v-text-field
              v-model="value"
              label="비밀번호 확인"
              placeholder=""
              :counter="100"
              :type="showPassword2 ? 'text' : 'password'"
              :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
              :error-count="1"
              :error-messages="errors"
              outlined
              @click:append="showPassword2 = !showPassword2"
            />
          </ValidationProvider>

          <v-btn
            :disabled="invalid || loading"
            :loading="loading"
            block
            color="blue"
            class="white--text"
            @click="signup"
          >
            회원가입
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
import { required, email, confirmed, min } from 'vee-validate/dist/rules'
extend('required', required)
extend('email', email)
extend('confirmed', confirmed)
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
      showPassword1: false,
      showPassword2: false,
      loading: false,
      params: {user: { nickname: '', email: '', password: ''} }
    }
  },
  methods: {
    signup () {
      this.loading = true
      setTimeout(() => {
        this.formReset()
        this.loading = false
      }, 1500)
    },
    formReset () {
      this.$refs.form.reset()
      this.params = { user: { name: '', email: '', password: ''}}
      this.value = ''
    }
  },
}
</script>

<style>

</style>