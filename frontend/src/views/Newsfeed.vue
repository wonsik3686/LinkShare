<template>
  <v-container>
    <br>
    <br>

    <h1>인기 박스</h1>
    <!-- https://iancoding.tistory.com/214 -->
    
    <br>
    <br>

    <v-row>
      <v-col v-for="box in boxlist" v-bind:key="box.id" cols='6'>
        <Linkbox :boxid="box.id"/>
      </v-col>
    </v-row>

    <v-row>
      <v-btn block plain class="font-weight-bold" v-on:click="toplinkbox">
        더보기 +
      </v-btn>
    </v-row>

    <br>
    <br>

    <h1>💡 님을 위한 추천 박스</h1>
        <p class="mt-3 text-center">관심사를 선택해주세요</p>
        <v-row justify="center">
          <button class="font-weight-bold" @click="Interest=true">관심사 선택하기 ></button>
        </v-row>

  </v-container>

</template>


<script>
import Linkbox from '@/components/linkbox/Linkbox'
import { listPopLinkbox } from '../api/linkbox.js'

export default {
  name: "Newsfeed",
  components: {
    Linkbox,
  },
  data () {
    return {
      Interest: false,
      boxlist: null,
    }
  },
  methods: {
    toplinkbox() {
      this.$router.replace('toplinkbox')
    }
  },
  created() {
    listPopLinkbox(
      (response) => {
        if (response.data.msg === "success") {
          console.log(response.data)
          this.boxlist = response.data.object
        } else {
          console.log(response.data.msg)
        }
      }, (err) => console.log(err)
    )
  }
}

</script>