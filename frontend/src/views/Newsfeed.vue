<template>
  <v-container>
    <br>
    <br>

    <h1>인기 박스</h1>
    <v-row>
      <v-col v-for="box in boxlistPop" v-bind:key="box.id" cols='6'>
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>

    <!-- <v-row>
      <v-btn block plain class="font-weight-bold" v-on:click="toplinkbox">
        더보기 +
      </v-btn>
    </v-row> -->

    <br>
    <br>

    <h1>추천 박스</h1>
    <v-row>
      <v-col v-for="box in boxlistInt" v-bind:key="box.id" cols='6'>
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>

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
  methods: {
    toplinkbox() {
      this.$router.replace('toplinkbox')
    }
  },
}

</script>