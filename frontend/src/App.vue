<template>
  <v-app>
    <Navbar v-if="loggedIn"/>
    <welNavbar v-else/>

    <v-main>
      <router-view/>
    </v-main>
    
  </v-app>
</template>

<script>
import Navbar from '@/components/navigation/Navbar'
import welNavbar from '@/components/navigation/welNavbar'
import { mapGetters } from 'vuex'

export default {
  name: 'App',
  components: {
    Navbar,
    welNavbar,
  },

  data: () => ({
    //
  }),
  computed: {
    ...mapGetters(['loggedIn'])
  },
  watch: {
    loggedIn(state) {
      console.log('state', state)
    }
  },
};
</script>
