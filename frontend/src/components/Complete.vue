<template>
  <div class="text-center">
    <v-btn
      icon
      @click="snackbar = true"
    >
      📂
    </v-btn>

    <v-snackbar
      v-model="snackbar"
      :timeout="timeout"
      color="primary"
      text
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>


<script>
  export default {
  props: {
    value: Boolean
  },
    data: () => ({
      snackbar: false,
      text: '스크랩이 완료되었습니다',
      timeout: 2000,
    }),
  }
</script>