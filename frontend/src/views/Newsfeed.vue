<template>
  <v-container>
    <br><br>

    <h1 style="color:#2C97DE;">인기 박스</h1>
    <v-row class="pt-3">
      <v-col v-for="box in boxlistPop" v-bind:key="box.id"
        cols='col-xs-1 col-sm-6 col-md-4 col-lg-4 col-xl-3'
      >
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>

    <br><br>

    <h1 style="color:#2C97DE;">추천 박스</h1>
    <v-row class="pt-3">
      <v-col v-for="box in boxlistInt" v-bind:key="box.id"
        cols='col-xs-1 col-sm-6 col-md-4 col-lg-4 col-xl-3'
      >
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>
    
    <br><br>

    <v-row class="justify-center">
      <router-link :to="'/toplinkbox'" class="text-decoration-none">
        <v-btn block text x-large class="font-weight-bold">
          더보기 +
        </v-btn>
      </router-link>
    </v-row>
    
    <br><br>

  </v-container>

</template>


<script>
import Linkbox from '@/components/linkbox/Linkbox'
import { listPopLinkbox, listInterestLinkbox } from '@/api/linkbox'
import { mapState } from 'vuex'

export default {
  name: "Newsfeed",
  components: {
    Linkbox,
  },
  data () {
    return {
      Interest: false,
      boxlistPop: null,
      boxlistInt: null,
    }
  },
  computed: {
    ...mapState('memberStore', ['userInfo'])
  },
  created() {
    const userid = this.userInfo.id

    listPopLinkbox(
      (res) => {
        if (res.data.msg === "success") {
          console.log(res.data)
          this.boxlistPop = res.data.object
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err)),

    listInterestLinkbox(userid,
      (res) => {
        if (res.data.msg === "success") {
          console.log(res.data)
          this.boxlistInt = res.data.object
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
  },
}

</script>