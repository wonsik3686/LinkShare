<template>
  <v-container>
    <!-- 뒤로가기 -->
    <v-btn icon color="grey" class="mb-5" @click="goBack">
      <v-icon x-large>mdi-arrow-left</v-icon>
    </v-btn>

    <!-- 제목 -->
    <h1>{{ linkbox.title }}
      <v-btn icon color="grey" @click.stop="linkboxSetting=true">
        <v-icon medium>mdi-pencil</v-icon>
      </v-btn>
      <v-btn icon color="grey" @click="deleteLinkbox">
        <v-icon medium>mdi-delete</v-icon>
      </v-btn>
    </h1>
    <linkboxSetting v-model="linkboxSetting" :linkboxdata="this.linkbox"/>

    <!-- 관심사 -->
    <v-row class="mt-3">
      <v-chip
        class="ma-1 px-4"
        v-for="interest in linkbox.interests"
        :key="interest.title"
        color="blue"
        text-color="white"
      >
        <strong>{{ interest.title }}</strong>
      </v-chip>

    </v-row>

    <!-- 본문 -->
    <v-row class="mt-7">
      <p class="text-justify">
        {{ linkbox.desc }}
      </p>
    </v-row>

    <br>
    <br>

    <v-container>
      <v-tabs fixed-tabs>
        <v-tab to="/linkbox/linklist" :linksdata="links" v-if="links">링크목록</v-tab>
        <v-tab to="/linkbox/linktree" :linksdata="links">링크트리</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>

  </v-container>
</template>

<script>
import linkboxSetting from '@/components/linkboxdetail/linkboxSetting'
import { deleteLinkbox } from "@/api/linkbox";

export default {
    components: {
    linkboxSetting,
  },
  data: () => ({
    linkboxSetting: false,
    linkbox: {
      boxid: '000',
      title: '링크박스 제목',
      desc: '링크박스 설명 및 개요',
      interests: [
        { title: "python" },
        { title: "vue.js" },
        { title: "springboot" },
      ]
    },
    links: [
      {id: '1', title: '샘플 링크1', desc: '샘플 링크 개요', url: 'https://github.com/nivaldomartinez/link-prevue?ref=kabanoki.net#custom-card'},
      {id: '2', title: '샘플 링크2', desc: '샘플 링크 개요', url: 'https://github.com/dw3624'},
      {id: '3', title: '샘플 링크3', desc: '샘플 링크 개요', url: 'https://www.youtube.com/'},
    ]
  }),
  methods: {
    deleteLinkbox,
    goBack() {
      this.$router.push('/')
    }
  },
  created() {
    console.log(this.links)
  }
}
</script>

<style>

</style>