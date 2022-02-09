<template>
  <v-card outlined>
    <div class="row items-center">
      <div class="col">
        <v-card-title class="justify-center pb-1" v-if="isroot">{{ title }}</v-card-title>
        <v-card-title v-else>{{ title }}</v-card-title>
      </div>
      
      <div class="col-auto" v-if="!isroot">
        <flowy-drag-handle>
          <v-btn icon>
            <v-icon>mdi-drag</v-icon>
          </v-btn>
        </flowy-drag-handle>
      </div>

    </div>
    <v-card-text v-html="description" class="pt-0"></v-card-text>
    <v-card-actions v-if="!isroot" class="justify-end">
      <v-btn text color="red" @click="remove()">
        delete
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: ['isroot', 'remove', 'node', 'title', 'description'],
}
</script>

<style>

</style>