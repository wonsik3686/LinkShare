<template>
  <v-card
    class="mx-5"
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
          <router-link to="/linkbox" style=text-decoration:none;>
          링크박스 제목
          </router-link>
        </v-list-item-title>
        <v-list-item-subtitle>링크박스 내용</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-card-actions class="justify-end pt-0">

      <v-btn icon @click="Commentmodal=true">💬</v-btn>
        <Commentmodal v-model="Commentmodal"/>

        <Complete v-model="Complete"/>

    </v-card-actions>
  </v-card>
</template>

<script>
import Commentmodal from '../components/Commentmodal.vue'
import Complete from '../components/Complete.vue'

  export default {
    name: "Linkbox",
    components: {
      Commentmodal,
      Complete,
    },
    data () {
      return {
        Commentmodal: false,
        Complete: false,
      }
    }
  }
</script>