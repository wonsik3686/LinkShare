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
        :key="interest"
        color="blue"
        text-color="white"
      >
        <strong>{{ interest }}</strong>
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
        <v-tab :to="`/linkbox/${boxid}/linklist`">링크목록</v-tab>
        <v-tab :to="`/linkbox/${boxid}/linktree`">링크트리</v-tab>
      </v-tabs>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>

  </v-container>
</template>

<script>
import linkboxSetting from '@/components/linkboxdetail/linkboxSetting'
import { getLinkboxInfo, deleteLinkbox } from "@/api/linkbox";

export default {
  components: {
    linkboxSetting,
  },
  data: () => ({
    tab: null,
    linkboxSetting: false,
    linkbox: {},
    links: null,
  }),
  methods: {
    deleteLinkbox,
    goBack() {
      this.$router.push('/')
    }
  },
  created() {
    const boxid = this.$route.params.boxid
    this.boxid = boxid
    console.log(boxid)

    getLinkboxInfo(boxid,
      (response) => {
        if (response.data.msg === "success") {
          console.log(response.data.object)
          this.linkbox = response.data.object
        } else {console.log(response.data.msg)}
      },
      (err) => console.log(err))
  }
}
</script>

<style>

</style>