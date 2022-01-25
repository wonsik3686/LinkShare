<template>
  <v-dialog v-model="show" max-width="400px">
    <v-card justify="center">
      <v-card-title class="justify-center">
        <p class="text-h5">회원가입</p>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row align="center" justify="center">

            <v-col cols="11">
              <v-text-field label="닉네임" required></v-text-field>
              <v-text-field label="이메일" placeholder="example@mail.com" required></v-text-field>
              <v-text-field label="비밀번호" placeholder="8~16자, 대소문자 포함" required></v-text-field>
              <v-text-field label="비밀번호 확인" placeholder="8~16자, 대소문자 포함" required></v-text-field>
            </v-col>

          </v-row>
          
          <v-row align="center" justify="center">
            <v-btn block color="blue">가입하기</v-btn>
          </v-row>
        </v-container>
      </v-card-text>
      
      <p class="text-center head-border">SNS 계정으로 회원가입</p>

      <v-card-actions class="justify-center">
        <v-btn text @click.stop="show=false">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
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