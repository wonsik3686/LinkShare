<template>
  <v-container>
    <v-row class="text-center" align="center" justify="center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/logo.svg')" 
          class="my-3"
          contain
          height="150"
        />
      </v-col>

      <v-col>
        <h2 class="display-2 font-weight-bold mb-3">
          닉네임
        </h2>
        
        <p class="subheading font-weight-regular">
          example@email.com
        </p>
        <p>
          팔로우: 0 명 |
          팔로워: 0 명
        </p>
        <v-img
          :src="require('../assets/logo.svg')" 
          contain
          height="50"
        />
      </v-col>
    </v-row>

    <br>

    <v-container>
      <v-row justify="center">
        <router-link to="/profile/interest" exact>
          관심사
          <!-- <v-btn plain class="text font-weight-bold" :ripple="false">
            관심사
          </v-btn> -->
        </router-link>
        <router-link to="/profile/linkbox" style=text-decoration:none; active-class="font-weight-bold" exact>
          링크박스
          <!-- <v-btn plain class="text font-weight-bold">
            링크박스
          </v-btn> -->
        </router-link>
        <router-link to="/profile/scrap" style=text-decoration:none;>
          <v-btn plain class="text font-weight-bold" active-class="font-italic">
            스크랩
          </v-btn>
        </router-link>
        <router-link to="/profile/following" style=text-decoration:none;>
          <v-btn plain class="text font-weight-bold">
            팔로잉
          </v-btn>
        </router-link>
        <router-link to="/profile/follower" style=text-decoration:none;>
          <v-btn plain class="text font-weight-bold">
            팔로워
          </v-btn>
        </router-link>
      </v-row>

      <br>

      <v-container>
        <router-view></router-view>
      </v-container>

    </v-container>
  </v-container>
</template>

<script>
export default {
}
</script>

<style>

</style>