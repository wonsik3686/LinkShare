<template>
  <v-container>
    <br>
    <br>

    <h1>링크박스 제목</h1>
    <v-chip
      class="ma-1 px-4"
      v-for="interest in interests"
      :key="interest.title"
      color="blue"
      text-color="white"
    >
      <strong>{{ interest.title }}</strong>
    </v-chip>
    <h5>링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.</h5>

    <br>
    <br>

    <v-container>
      <v-tabs fixed-tabs>
        <v-tab to="/profile/interest">링크목록</v-tab>
        <v-tab to="/profile/linkbox">링크트리</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>

    <v-container>
      <v-row justify="center">
        <router-link to="/linkbox/linklist" style=text-decoration:none;>
          <v-btn plain class="text font-weight-bold">
            링크 목록
          </v-btn>
        </router-link>

        <router-link to="/linkbox/studyflow" style=text-decoration:none;>
          <v-btn plain class="text font-weight-bold">
            스터디플로우
          </v-btn>
        </router-link>
      </v-row>

      <v-container>
        <router-view></router-view>
      </v-container>
      
    </v-container>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    interests: [
      { title: "python" },
      { title: "vue.js" },
      { title: "springboot" },
    ]
  }),
}
</script>

<style>

</style>