<template>
  <v-dialog v-model="show" max-width="600px" persistent>
    <v-card>
      <v-card-title class="justify-center">
        <p class="headline mt-3">링크박스 설정</p>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-text-field v-model.lazy="linkboxdata.title" outlined label="링크박스 이름"/>
          </v-row>
          <v-row>
            <v-textarea
              
              outlined
              auto-grow
              label="링크박스 개요"
            />
          </v-row>
          
        </v-container>
      </v-card-text>

      <v-card-actions class="justify-center">
        <v-btn text @click.stop="show=false" class="red--text">Cancel</v-btn>
        <v-btn text @click.stop="show=false" @click="updateLinkbox" class="blue--text">Save</v-btn>
      </v-card-actions>
      {{linkboxdata}}
    </v-card>
  </v-dialog>
</template>

<script>
import { updateLinkbox } from "@/api/linkbox";

export default {
  data: () => ({
    linkbox: {},
  }),
  props: {
    value: Boolean,
    linkboxdata: Object,
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    },
  },
  methods: {
    updateLinkbox,
  }
}
</script>

<style>

</style>