<template>
  <div>
    <v-app-bar
      color="white"
      absolute
      elevate-on-scroll
    >
      <v-toolbar-title class="text-h5">📁 LinkShare</v-toolbar-title>

      <v-spacer class="text-center">
        <!-- Search -->
      </v-spacer>

      <v-toolbar-items>
        <v-btn plain class="text-subtitle-1 font-weight-bold">회원가입</v-btn>
        <v-btn plain class="text-subtitle-1 font-weight-bold">로그인</v-btn>
      </v-toolbar-items>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "NavBar",
}
</script>

<style>

</style>