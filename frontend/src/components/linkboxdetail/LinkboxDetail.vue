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
      <v-card-text>
        <v-chip class="ma-1 px-4"
          v-for="interest in linkbox.interests" :key="interest"
        >
          <strong>{{ interest }}</strong>
        </v-chip>
      </v-card-text>
      <v-card-actions>
        <v-btn icon color="grey" @click="clickDelete">
          <v-icon medium>mdi-delete</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
export default {
  props: ['linkbox'],
  methods: {
    clickDelete() {
      this.$emit('click-delete')
    }
  }
}
</script>

<style>

</style>