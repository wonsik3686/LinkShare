<template>
  <v-card class="mx-auto rounded-xl" max-width="400" height="480">
    <v-container>

      <v-card-title class="text-h4 display-2 font-weight-bold justify-center">
        프로필 편집
      </v-card-title>
      
      <v-card-text>
        <v-row class="align-center">
          <v-file-input
            :value="userInfo.imagePath"
            label="프로필"
            v-model="files"
          />
          <v-btn text color="#2C97DE" @click="upload">upload</v-btn>
        </v-row>

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

        <v-row class="justify-center pt-3 pb-0">
          <v-btn text color="#2C97DE" @click="doneEdit">save</v-btn>
          <v-btn text color="#EF4B4C" @click="cancelEdit">cancel</v-btn>
        </v-row>
      </v-card-text>

    </v-container>
  </v-card>
</template>

<script>
import { putImg, getImgNickname, } from '@/api/image'

export default {
  props: ['userInfo'],
  data: () => ({
    editedProfile: null,
    files: [],
  }),
  mounted() {
    this.editedProfile = {
      nickname: this.userInfo.nickname,
      email: this.userInfo.email,
      introduce: this.userInfo.introduce,
    },
    this.nicknameImg()
  },
  methods: {
    async upload(){
      var fd = new FormData()
      fd.append('uid', this.userInfo.id)
      fd.append('image', this.files)

      await putImg(fd,
      (res) => {
        if (res.data.msg === 'success') {
          console.log(res.data)
          this.editedProfile.imagePath = res.data.object.path
        } else { console.log(res.data.msg) }
      }, (err) => {console.log(err); console.log(fd)})
    },
    nicknameImg() {
      getImgNickname(this.userInfo.nickname,
      (res) => {
        if (res.data.msg === 'success') {
          console.log(res.data)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
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
    cancelEdit() {
      this.$emit('cancel-edit')
      this.editedProfile = null
    },
  }
}
</script>

<style>

</style>