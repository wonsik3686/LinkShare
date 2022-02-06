<template>
  <v-dialog v-model="show" max-width="400px">
    <v-card justify="center">
      <v-card-title class="justify-center">
        <h1 class="text-h5 font-weight-bold mt-3">회원가입</h1>
      </v-card-title>

      <v-card-text>
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
              v-model="v"
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
import { required, email, confirmed, min } from 'vee-validate/dist/rules'
extend('required', required)
extend('email', email)
extend('confirmed', confirmed)
extend('min', min)
import snsLogin from "@/components/snsLogin/snsLogin"
import axios from 'axios'

export default {
  components: {
    ValidationProvider,
    ValidationObserver,
    snsLogin,
  },
  props: {
    value: Boolean
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  },
  data () {
    return {
      v: '',
      showPassword1: false,
      showPassword2: false,
      loading: false,
      params: {user: { nickname: '', email: '', password: ''} }
    }
  },
  methods: {
    signup () {
      // 전송할 데이터 확인
      console.log(this.params.user)
      this.loading = true

      axios({
        method: 'post',
        // baseURL: process.env.VUE_APP_SERVER_URL,
        baseURL: 'https://d3b93656-afa7-4d7c-b27f-95e7069e044a.mock.pstmn.io',
        url: 'user/signup',
        headers: {'Content-Type': 'application/json' },
        data: JSON.stringify(this.params.user),
      })
        .then((res) => {
          console.log(res.data)
          this.show = false
        })
        .catch((err) => {
          console.log(err)
        })
      setTimeout(() => {
        this.formReset()
        this.loading = false
      }, 1500)
    },
    formReset () {
      this.$refs.form.reset()
      this.params = { user: { nickname: '', email: '', password: ''}}
      this.v = ''
    }
  },
}
</script>

<style>
.head-border {
    display: flex;
    align-items: center;
    color: rgb(167, 167, 167);
}
 
.head-border:before,
.head-border:after {
    content: "";
    height: 1px;
    flex-grow: 0.8;
    background-color: rgb(167, 167, 167);
}
 
.head-border:before {
    margin-right: 1rem;
}
 
.head-border:after {
    margin-left: 1rem;
}

</style>