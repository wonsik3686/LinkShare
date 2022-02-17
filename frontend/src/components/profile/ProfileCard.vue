<template>
  <v-card class="mx-auto rounded-xl" max-width="400" height="480">
    <v-container>
    <!-- 프로필 이미지 표시, 에러날 경우 디폴트 이미지 표시 -->
    <v-img
      v-if="profileUser.imagePath"
      :src="profileUser.imagePath"
      class="my-3"
      contain
      height="150"
    />
    <!-- imagePath가 비어있는 경우 표시 -->
    <v-img
      v-else
      :src="require('@/assets/logo.svg')"
      class="my-3"
      contain
      height="150"
    />

    <v-card-title class="text-h2 display-2 font-weight-bold justify-center">
      {{ profileUser.nickname }}
    </v-card-title>
    
    <v-card-text class="pb-5">
      <v-row class="subheading font-weight-regular justify-center">
        {{ profileUser.email }}
      </v-row>
    </v-card-text>

    <v-card-text class="text-body-1">
      <v-row class="justify-center" v-if="profileUser.introduce">
        {{ profileUser.introduce }}
      </v-row>
      <v-row v-else class="justify-center">
        자기소개가 아직 없습니다.
      </v-row>
    </v-card-text>

    <v-card-text>
      <v-row class="justify-center" v-if="userInfo.email === profileUser.email">
        <v-btn text x-large color="#2C97DE" @click="clickEdit">
          edit
        </v-btn>
      </v-row>
    </v-card-text>

    </v-container>
  </v-card>
</template>

<script>
import { userInfo } from '@/api/member'
import { getImgEmail } from '@/api/image'

export default {
  props: ['userEmail', 'userInfo'],
  data: () => ({
    profileUser: '',
    userImagePath: '',
  }),
  async created() {
    await this.getProfileUser()
    await this.getImg()
  },
  methods: {
    async getProfileUser() {
      await userInfo(this.userEmail,
      (res) => {
        if (res.data.msg === 'success') {
          // console.log('success userInfo')
          this.profileUser = res.data.object.userInfo
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    async getImg() {
      await getImgEmail(this.userEmail, 
      (res) => {
        if (res.data.msg === 'success') {
          // console.log('success getImgEmail')
          // console.log(res.data)
          this.userImagePath = res.data.object.path
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    clickEdit() {
      this.$emit('click-edit')
    }
  }
}
</script>

<style>

</style>
