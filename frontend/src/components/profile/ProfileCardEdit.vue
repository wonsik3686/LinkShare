<template>
  <v-card class="mx-auto" max-width="400" height="500">
    <v-container>

    <v-card-title class="text-h4 display-2 font-weight-bold justify-center">
      프로필 편집
    </v-card-title>
    
    <v-card-text>
      <v-file-input
        label="프로필"
        :value="userInfo.imagePath" 
      />
      
      <v-text-field
        label="닉네임"
        :value="userInfo.nickname"
        @input="onInputNickname"
      />

      <v-text-field
        label="이메일"
        :value="userInfo.email" 
        @input="onInputEmail"
      />

      <v-textarea
        label="소개글"
        :value="userInfo.introduce"
        @input="onInputIntroduce"
        rows="4"
      />
      <v-btn text color="green" class="text-center" @click="doneEdit">save</v-btn>
    </v-card-text>
    

    </v-container>
  </v-card>
</template>

<script>
export default {
  props: ['userInfo'],
  data: () => ({
    editedProfile: null,
  }),
  mounted() {
    this.editedProfile = {
      imagePath: this.userInfo.imagePath,
      nickname: this.userInfo.nickname,
      email: this.userInfo.email,
      introduce: this.userInfo.introduce,
    }
  },
  methods: {
    onInputNickname(event) {
      this.editedProfile.nickname = event.trim()
    },
    onInputEmail(event) {
      this.editedProfile.email = event.trim()
    },
    onInputIntroduce(event) {
      this.editedProfile.introduce = event.trim()
    },
    doneEdit() {
      this.$emit('done-edit', this.editedProfile)
    },
  }
}
</script>

<style>

</style>