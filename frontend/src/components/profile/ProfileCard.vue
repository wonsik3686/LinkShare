<template>
  <v-card class="mx-auto" max-width="400" height="500">
    <v-container>
    <!-- 프로필 이미지 표시, 에러날 경우 디폴트 이미지 표시 -->
    <v-img
      v-if="userItem.imagePath"
      :src="userItem.imagePath"
      @error="replaceByDefault"
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
      {{ userItem.nickname }}
    </v-card-title>
    
    <v-card-text>
      <v-row class="subheading font-weight-regular justify-center">
        {{ userItem.email }}
      </v-row>
      <v-row class="font-weight-regular justify-center" v-if="userItem.introduce">
        {{ userItem.introduce }}
      </v-row>
      <v-row v-else class="font-weight-regular justify-center">
        자기소개가 아직 없습니다.
      </v-row>
    </v-card-text>
    
    <div v-if="userInfo.email === userItem.email">
      <v-btn text @click="editProfile">
        edit
      </v-btn>
    </div>
    </v-container>
  </v-card>
</template>

<script>
export default {
  props: ['userItem', 'userInfo'],
  methods: {
    editProfile() {
      this.$emit('edit-profile', this.userItem)
    },
  }
}
</script>

<style>

</style>