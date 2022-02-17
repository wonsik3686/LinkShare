<template>
  <v-card outlined color="#EEEEEE">
    <v-row class="items-center">
      <v-col>
        <v-card-title class="justify-center pb-1" v-if="isroot">{{ title }}</v-card-title>
        <v-card-title v-else>{{ title|truncate(18, '...') }}</v-card-title>
      </v-col>
      
      <v-col cols="auto" v-if="!isroot">
        <flowy-drag-handle>
          <v-btn icon>
            <v-icon>mdi-drag</v-icon>
          </v-btn>
        </flowy-drag-handle>
      </v-col>

    </v-row>
    <v-card-text v-html="description" class="pt-0 pb-1"></v-card-text>

    <v-card-actions v-if="!isroot" class="justify-center">
      <a :href="url" class="text-decoration-none pl-2">바로가기</a>
      <v-spacer></v-spacer>
      <v-btn icon color="#EF4B4C" @click="remove()">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: ['isroot', 'remove', 'node', 'title', 'description', 'url'],
}
</script>

<style>

</style>