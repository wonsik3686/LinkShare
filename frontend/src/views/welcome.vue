<template>
  <v-container fill-height>
    <!-- <v-row align="center" justify="center">
      <v-col cols="4">
        <p class="text-h1">Linkshare</p>
        <p class="text-h2">
          Save content<br>
          from<br>
          anywhere
        </p>
      </v-col>
      
      <v-col cols="6">
        <h1 class="text-center">Image Placeholder</h1>
      </v-col>
    </v-row> -->
    <v-row class="justify-center">
      <Flowy/>
    </v-row>
  </v-container>
</template>

<script>
import Flowy from '@/components/flowy/flowy'

export default {
  components: {
    Flowy,
  }
}
</script>

<style>

</style>