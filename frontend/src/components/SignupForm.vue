<template>
  <v-dialog v-model="show" max-width="400px">
    <v-card justify="center">
      <v-card-title class="justify-center">
        <p class="text-h5 font-weight-bold mt-3">회원가입</p>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row align="center" justify="center">
            <v-col cols="12">
              <v-text-field label="닉네임"></v-text-field>
              <v-text-field label="이메일" placeholder="example@email.com"></v-text-field>
              <v-text-field label="비밀번호" placeholder="8~16자, 대소문자 포함"></v-text-field>
              <v-text-field label="비밀번호 확인" placeholder="8~16자, 대소문자 포함"></v-text-field>
              <br>
              <v-btn block color="blue">회원가입</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-container>
        <p class="text-center mx-5 head-border">SNS 계정으로 로그인</p>
        <br>
        <v-row align="center" justify="center">
          <span class="mx-2"><GoogleLogin /></span>
          <span class="mx-2"><KakaoLogin/></span>
        </v-row>
      </v-container>

      <br>
      <v-card-actions class="justify-center">
        <v-btn text @click.stop="show=false">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import GoogleLogin from "../components/snsLogin/Google.vue";
import KakaoLogin from "../components/snsLogin/Kakao.vue";



export default {
  components: {
    GoogleLogin,
    KakaoLogin,
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
  }
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