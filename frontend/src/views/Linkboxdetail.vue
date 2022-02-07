<template>
  <v-container>
    <br>
    <br>

    <h1>링크박스 제목</h1>
    <v-btn class="primary mt-3 mb-5">태그</v-btn>
    <v-btn class="warning mt-3 mb-5 mx-2">태그</v-btn>
    <h5>링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.링크박스 내용입니다.</h5>

    <br>
    <br>

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
}
</script>

<style>

</style>