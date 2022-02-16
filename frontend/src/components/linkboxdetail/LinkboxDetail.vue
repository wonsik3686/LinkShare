<template>
  <v-container>
    <v-card>
      <v-card-title>
        <div class="text-h3 font-weight-bold">
          {{ linkbox.title }}
        </div>
      </v-card-title>
      <v-card-text>
        <div class="text-body-1 font-weight-medium">
          {{ linkbox.desc }}
        </div>
      </v-card-text>

      <linkboxDetail :linkbox="linkbox"/>

      <v-card-actions class="justify-end">
        <v-btn icon color="grey" @click="clickEdit">
          <v-icon medium>mdi-pencil</v-icon>
        </v-btn>
        <v-btn icon color="grey" @click="clickDelete">
          <v-icon medium>mdi-delete</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import linkboxDetail from './LinkboxDetailInterest.vue'

export default {
  components: {
    linkboxDetail,
  },
  props: ['linkbox'],
  methods: {
    clickDelete() {
      this.$emit('click-delete')
    },
    clickEdit() {
      this.$emit('click-edit')
    },
  }
}
</script>

<style>

</style>