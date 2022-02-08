<template>
  <v-container>
    <v-row class="text-center" align="center" justify="center">
      <v-col cols="12">
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
          :src="require('../assets/logo.svg')"
          class="my-3"
          contain
          height="150"
        />
      </v-col>

      <v-col>
        <h2 class="display-2 font-weight-bold mb-3">
          {{ userItem.nickname }}
        </h2>
        
        <p class="subheading font-weight-regular">
          {{ userItem.email }}
        </p>

        <p>
          팔로우: {{ userItem.following }} 명 |
          팔로워: {{ userItem.follower }} 명
        </p>
        
        <div v-if="userInfo.email === userItem.email">
          <v-btn icon class="mb-3">
            <v-icon medium>mdi-cog-outline</v-icon>
          </v-btn>
        <!-- </div>
        <div v-else> -->
          <v-btn
            rounded
            outlined
            class="font-weight-bold"
            color="blue"

          >
            팔로우
          </v-btn>
          <v-btn
            rounded
            depressed
            class="font-weight-bold white--text"
            color="blue"
          >
            팔로잉
          </v-btn>
        </div>
      </v-col>
    </v-row>

    <br>

    <v-container>
      <v-tabs fixed-tabs>
        <v-tab :to="`/profile/${userItem.email}/interest`">관심사</v-tab>
        <v-tab :to="`/profile/${userItem.email}/linkbox`">링크박스</v-tab>
        <v-tab :to="`/profile/${userItem.email}/scrap`">스크랩</v-tab>
        <v-tab :to="`/profile/${userItem.email}/following`">팔로잉</v-tab>
        <v-tab :to="`/profile/${userItem.email}/follower`">팔로워</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>

  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  methods: {
    ...mapActions('memberStore', ['fetchUserInfo']),
    replaceByDefault(e) {
      e.target.src = '../assets/logo.svg'
      console.log(e.target.src)
    }
  },
  created() {
    const userItem = this.$route.params.email;
    this.fetchUserInfo(userItem)
  },
  computed: {
    ...mapState('memberStore', ['userInfo', 'userItem']),
  },
}
</script>

<style>

</style>