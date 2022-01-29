<template>
  <v-container>
    <br>
    <br>

      <h1>📦 LinkShare 인기 박스</h1>
      <!-- https://iancoding.tistory.com/214 -->
      <button class="font-weight-bold" v-on:click="toplinkbox" >더보기 +</button>  
    <Linkbox />
    <Linkbox />
    <Linkbox />
    <Linkbox />

    <br>
    <br>

    <h1>💡 {{username}}님을 위한 추천 박스</h1>
        <p class="mt-3 text-center">관심사를 선택해주세요</p>
        <v-row justify="center">
          <button class="font-weight-bold" @click="Interest=true">관심사 선택하기 ></button>
          <Interest v-model="Interest"/>
        </v-row>

    <br>
    <br>

    <h1>📣 내 팔로워의 최신 박스</h1>
    <p class="mt-3 text-center">다른 계정을 팔로우 해보세요</p>

  </v-container>

</template>


<script>
import Interest from '../components/Interest.vue'
import Linkbox from '../components/Linkbox.vue'

export default {
  name: "Newsfeed",
  components: {
    Interest,
    Linkbox,
  },
  data () {
    return {
      Interest: false,
    }
  },
  methods: {
    toplinkbox() {
      this.$router.replace('toplinkbox')
    }
  }
}
</script>