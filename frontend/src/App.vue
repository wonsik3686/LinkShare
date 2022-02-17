<template>
  <v-app>
    <Navbar v-if="loggedIn"/>
    <welNavbar v-else/>

    <v-main>
      <router-view/>
    </v-main>
    
    <Footer/>
  </v-app>
</template>

<script>
import Navbar from '@/components/navigation/Navbar'
import welNavbar from '@/components/navigation/welNavbar'
import Footer from '@/components/navigation/Footer'
import { mapState, mapGetters } from 'vuex'

export default {
  name: 'App',
  components: {
    Navbar,
    welNavbar,
    Footer
  },

  data: () => ({
    //
  }),
  computed: {
    ...mapState(['loggedIn']),
    // vuex 분리시 namespaced: true 옵션으로 할 경우 앞에 모듈명을 추가해야 함
    ...mapGetters('memberStore', ['loggedIn'])
  },
  watch: {
    loggedIn(state) {
      console.log('state', state)
    }
  },
};
</script>