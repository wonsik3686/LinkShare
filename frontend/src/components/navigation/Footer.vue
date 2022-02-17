<template>
  <v-footer padless>
    <v-card flat tile width="100%"
      color="#EEEEEE"
    >
      <br>

      <!-- <v-card-text>
        <div><strong>LinkShare</strong> by ssafy A603</div>
        <div>강동원 | 염형덕 | 전슬민 | 정원식</div>
      </v-card-text> -->

      <v-card-text class="text-center">
        <div><strong>LinkShare</strong> by A603</div>
        {{ new Date().getFullYear() }} — <strong>SSAFY</strong>
      </v-card-text>
      
      <br>
    </v-card>
  </v-footer>
</template>

<script>
  export default {

  }
</script>

<style>

</style>