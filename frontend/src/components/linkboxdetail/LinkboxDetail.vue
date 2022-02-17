<template>
  <v-card>
    <v-container>
      <v-card-title>
        <span class="text-h3 font-weight-bold pt-5">
          {{ linkbox.title }}
        </span>
      </v-card-title>

      <v-card-text>
        <div class="text-body-1 font-weight-medium pt-2">
          {{ linkbox.desc }}
        </div>
      </v-card-text>

      <linkboxDetail :linkbox="linkbox" :boxUser="boxUser"/>

      <v-card-actions class="justify-end">
        <v-spacer></v-spacer>
        <v-btn icon color="grey" @click="clickEdit" v-if="this.userInfo.id===this.boxUser.id">
          <v-icon medium>mdi-pencil</v-icon>
        </v-btn>
        <v-btn icon color="grey" @click="clickDelete" v-if="this.userInfo.id===this.boxUser.id">
          <v-icon medium>mdi-delete</v-icon>
        </v-btn>
        <div>
          <span class="text-subtitle-1 font-weight-bold">by {{ boxUser.nickname }}</span>
          &nbsp;
          <span class="text-caption font-weight-light">{{ linkbox.regtime }}</span>
        </div>
      </v-card-actions>

    </v-container>
  </v-card>
</template>

<script>
import { mapState } from 'vuex'
import linkboxDetail from './LinkboxDetailInterest.vue'

export default {
  components: {
    linkboxDetail,
  },
  props: ['linkbox', 'boxUser'],
  computed: {
    ...mapState('memberStore', ['userInfo'])
  },
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