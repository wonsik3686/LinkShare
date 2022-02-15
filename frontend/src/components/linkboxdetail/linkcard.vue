<template>
  <v-card @dblclick="editCard" class="view">
    <v-card-title class="pb-0">
      {{ link.title }}
    </v-card-title>
    <v-container class="pt-1 pb-2">
      <a  v-bind:href="link.url" class="text-decoration-none">
        <link-prevue :url="link.url">
          <v-row slot-scope="props">
          <v-col cols="3">
            <img
              width="100%"
              height="auto"
              :src="props.img"
              :alt="props.title"
              @error="replaceByDefault"
            />
          </v-col>
          <v-col>
            <h3 v-if="props.title">{{props.title | truncate(30, '...') }}</h3>
            <p v-if="props.description">{{props.description | truncate(80, '...') }}</p>
          </v-col>
        </v-row>
        </link-prevue>
      </a>
    </v-container>

    <v-divider></v-divider>

    <v-card-text>{{ link.desc }}</v-card-text>
  </v-card>
</template>

<script>
import LinkPrevue from 'link-prevue'

export default {
  components: {
    LinkPrevue,
  },
  props: {
    link: Object,
  },
  methods: {
    replaceByDefault(e) {
      e.target.src = ""
      console.log(e.target.src)
    },
    editCard() {
      console.log(this.link)
      this.$emit('edit-card', this.link)
    }
  }
}
</script>

<style>

</style>