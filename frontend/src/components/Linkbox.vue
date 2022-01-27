<template>
  <v-card
    class="mx-auto"
    max-width="344"
    outlined
    color="white"
  >

    <v-list-item three-line>
      <v-list-item-avatar
        tile
        size="80"
        color="grey"
        class="pt-5"
      ></v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title class="text-h5 mb-1">
          링크박스 제목
        </v-list-item-title>
        <v-list-item-subtitle>링크박스 내용</v-list-item-subtitle>
      </v-list-item-content>

    </v-list-item>

    <v-card-actions class="justify-end pt-0">
      <v-btn icon @click="Commentmodal=true">💬</v-btn>
        <Commentmodal v-model="Commentmodal"/>
      <v-btn icon>📂</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import Commentmodal from '../components/Commentmodal.vue'

  export default {
    name: "Linkbox",
    components: {
      Commentmodal,
    },
    data () {
      return {
        Commentmodal: false,
      }
    }
  }
</script>