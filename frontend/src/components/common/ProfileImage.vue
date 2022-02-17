<template>
  <!-- 프로필 이미지 -->
  <v-avatar :size="size" v-if="imagePath">
    <img
      :src="imagePath"
      @error="replaceByDefault"
    />
  </v-avatar>
  <!-- imagePath가 비어있는 경우 표시 -->
  <v-avatar v-else :size="size" color="blue">
    <span class="white--text">
      {{ nickname | truncate(1, '') }}
    </span>
  </v-avatar>
</template>

<script>
export default {
  props: ['imagePath', 'nickname', 'size'],
  methods: {
    replaceByDefault(e) {
      e.target.src = ''
    },
  }
}
</script>
