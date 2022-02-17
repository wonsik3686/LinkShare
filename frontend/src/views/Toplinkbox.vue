<template>
  <v-container>
    <br>
    <br>

    <h1 style="color:#2C97DE;">전체 박스</h1>
    <v-row class="pt-3">
      <v-col v-for="box in linkboxList" v-bind:key="box.id"
        cols='col-xs-1 col-sm-6 col-md-4 col-lg-4 col-xl-3'
      >
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { listLinkbox } from '@/api/linkbox'
import Linkbox from '@/components/linkbox/Linkbox'

export default {
  components: {
    Linkbox,
  },
  data: () => ({
    linkboxList: [],
  }),
  created() {
    this.getLinkboxList()
  },
  methods: {
    getLinkboxList() {
      listLinkbox(
        (res) => {
          if (res.data.msg === 'success') {
            // console.log(res.data)
            this.linkboxList = res.data.object
          } else {console.log(res.data.msg)}
        }, (err) => console.log(err))
    }
  }
}
</script>

<style>

</style>