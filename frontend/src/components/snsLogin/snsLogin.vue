<template>
  <v-form>
    <p class="text-center mx-5 head-border">SNS 계정으로 로그인</p>
    <br>
    <v-row align="center" justify="center">
      <span class="mx-2"><GoogleLogin/></span>
      <span class="mx-2"><KakaoLogin/></span>
    </v-row>
  </v-form>
</template>

<script>
import GoogleLogin from "@/components/snsLogin/Google.vue";
import KakaoLogin from "@/components/snsLogin/Kakao.vue";

export default {
  name: "snsLogin",
  components: {
    GoogleLogin,
    KakaoLogin,
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
    flex-grow: 1;
    background-color: rgb(167, 167, 167);
}
 
.head-border:before {
    margin-right: 1rem;
}
 
.head-border:after {
    margin-left: 1rem;
}
</style>