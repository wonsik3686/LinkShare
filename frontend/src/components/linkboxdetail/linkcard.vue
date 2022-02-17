<template>
<div>
  <v-card @dblclick="editCard" class="view">
    <v-card-title class="pb-0">
      <span>{{ link.title }}</span>
      <!-- <v-spacer></v-spacer>
      <v-btn icon @click="doneDelete">
        <v-icon>mdi-delete</v-icon>
      </v-btn> -->
    </v-card-title>

    <v-card-text class="justify-center pt-2 pb-0">
      <a  v-bind:href="link.url" class="text-decoration-none">
        <link-prevue :url="link.url">
          <v-row slot-scope="props" class="align-center">
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
              <div v-if="props.title" class="text-subtitle-1 font-weight-bold">
                {{props.title | truncate(30, '...') }}
              </div>
              <div v-if="props.description" class="text-caption">
                {{props.description | truncate(50, '...') }}
              </div>
            </v-col>
          </v-row>
        </link-prevue>
      </a>
    </v-card-text>
    
    <v-card-text>
      {{ link.url | truncate(40, '...') }}
    </v-card-text>
  </v-card>

  <v-card @dblclick="editCard" class="view" color="#EEEEEE">
    <v-card-text>{{ link.desc }}</v-card-text>
  </v-card>
</div>
</template>

<script>
import LinkPrevue from 'link-prevue'

export default {
  components: {
    LinkPrevue,
  },
  props: ['link', 'userInfo', 'boxUser'],
  methods: {
    replaceByDefault(e) {
      e.target.src = ""
      console.log(e.target.src)
    },
    editCard() {
      if (this.userInfo.id!==this.boxUser.id) {
        return;
      }
      console.log(this.link)
      this.$emit('edit-card', this.link)
    },
    // doneDelete() {
    //   console.log(this.editedCardData.id)
    //   this.$emit('done-delete', this.editedCardData.id)
    // },
  }
}
</script>

<style>

</style>