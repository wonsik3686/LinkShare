<template>
  <v-container>
    <h1>링크 목록</h1>
    <!-- <p>{{ links }}</p> -->
    <p>{{ linksdata }}</p>
    <v-row dense class="justify-start">
      <v-col v-for="link in links" v-bind:key="link.id" cols="5" class="mx-3">

        <v-card>
          <v-card-title>{{ link.title }}</v-card-title>
          <v-container>
            <a  v-bind:href="link.url" class="text-decoration-none">
              <link-prevue :url="link.url">
                <v-row slot-scope="props">
                <v-col cols="3">
                  <img
                    width="100%"
                    height="auto"
                    :src="props.img"
                    :alt="props.title"
                    @error="replaceByDefault"
                  />
                </v-col>
                <v-col>
                  <h3 v-if="props.title">{{props.title | truncate(30, '...') }}</h3>
                  <p v-if="props.description">{{props.description | truncate(80, '...') }}</p>
                </v-col>
              </v-row>
              </link-prevue>
            </a>
          </v-container>

          <v-divider></v-divider>

          <v-card-text>{{ link.desc }}</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import LinkPrevue from 'link-prevue'

export default {
  components: {
    LinkPrevue,
  },
  props: {
    linksdata: Array,
  },
  created() {
    this.datalist = this.linksdata
    console.log(this.linksdata)
  },
  data: () => ({
    links: [
      {id: '1', title: '샘플 링크1', desc: '샘플 링크 개요1', url: 'https://vuejs.org/'},
      {id: '2', title: '샘플 링크2', desc: '샘플 링크 개요2', url: 'https://github.com/dw3624'},
      {id: '3', title: '샘플 링크3', desc: '샘플 링크 개요3', url: 'https://www.youtube.com'},
    ],
    datalist: null,
  }),
  methods: {
    replaceByDefault(e) {
      // e.target.src = "imgurl"
      console.log(e.target.src)
    }
  }
}
</script>

<style>

</style>