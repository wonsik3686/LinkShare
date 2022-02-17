<template>
  <v-card outlined class="px-md py-sm my-1 items-center justify-between" color="#EEEEEE">
    <v-row class="items-center">
      <v-col>
        <v-card-title>{{ title|truncate(18, '...') }}</v-card-title>
      </v-col>
      
      <v-col cols="auto">
        <flowy-drag-handle>
          <v-btn icon>
            <v-icon>mdi-drag</v-icon>
          </v-btn>
        </flowy-drag-handle>
      </v-col>
    </v-row>

    <v-card-text class="py-0">{{ description|truncate(30, '...') }}</v-card-text>
    <v-card-text>
      <a :href="url" class="text-decoration-none">바로가기</a>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: [ 'title', 'description', 'url' ],
}
</script>

<style>

</style>