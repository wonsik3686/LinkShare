<template>
  <v-card outlined class="px-md py-sm my-1 items-center justify-between">
    <div class="row items-center">
      <div class="col">
        <v-card-title>{{ title }}</v-card-title>
      </div>
      
      <div class="col-auto">
        <flowy-drag-handle>
          <v-btn icon>
            <v-icon>mdi-drag</v-icon>
          </v-btn>
        </flowy-drag-handle>
      </div>

      <v-card-text>{{ description }}</v-card-text>
    </div>
  </v-card>
</template>

<script>
export default {
  props: [ 'title', 'description' ],
}
</script>

<style>

</style>