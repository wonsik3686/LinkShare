<template>
  <div>
    <v-app-bar
      color="white"
      elevate-on-scroll
    >
      <v-toolbar-title
        class="text-h5 blue--text"
        style="cursor: pointer"
        @click="$router.push('/')"
      >
      LinkShare
      </v-toolbar-title>

      <v-spacer class="text-center">
        <!-- Search -->
      </v-spacer>

      <v-toolbar-items>
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="#">Logout</v-btn>
        <v-btn plain class="text-subtitle-1 font-weight-bold" to="/profile">profile</v-btn>
      </v-toolbar-items>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  components: {
    
  },
}
</script>

<style>

</style>