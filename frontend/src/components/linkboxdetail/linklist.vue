<template>
  <v-container>

    <v-row dense class="justify-start">
      <v-col cols="5" class="mx-3">
        <linkCardNew @done-create="doneCreate"/>
      </v-col>
      <v-col cols="5" class="mx-3"
        v-for="link in fileteredData" :key="link.id" 
        :class="{completed: link.completed, editing: link == editedLink}"
      >
        <linkCard :link="link" @edit-card="editCard"/>
        <linkCardEdit :link="link" @done-edit="doneEdit" @done-delete="doneDelete"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// import LinkPrevue from 'link-prevue'
import { listLink, createLink, updateLink, deleteLink } from "@/api/linkbox"
import linkCard from './linkcard.vue'
import linkCardNew from './linkcardNew.vue'
import linkCardEdit from './linkcardEdit.vue'

export default {
  components: {
    linkCard,
    linkCardNew,
    linkCardEdit,
  },
  data: () => ({
    links: null,
    editedLink: null,
    newLink: null,
    tempId: 0,
  }),
  computed: {
    fileteredData() {
      return this.links
    }
  },
  created() {
    const boxid = this.$route.params.boxid
    this.boxid = boxid

    listLink(boxid,
      (response) => {
        if (response.data.msg === "success") {
          this.links = response.data.object
        } else { console.log(response.data.msg) }
      }, (err) => console.log(err))
    },
  methods: {
    doneCreate(cardData){
      const newLink = cardData
      newLink.boxid = this.boxid
      newLink.id = 'temp' + this.tempId

      if (cardData) {
        createLink(newLink,
        (response) => {
          if (response.data.msg === "success") {
            console.log('link data created')
            this.links.push(newLink)
            this.tempId++
          } else {console.log(response.data.msg) }
        }, (err) => console.log(err))
      }
    },
    editCard(linkdata) {
      this.editedLink = linkdata
      console.log('card data updating...')
    },
    doneEdit(cardData) {
      if (!this.editedLink) {
        return;
      }
      if (cardData) {
        this.editedLink = cardData
        console.log(this.editedLink)
        updateLink(this.editedLink,
        (response) => {
          if (response.data.msg === "success") {
            console.log(response.data)
            console.log('link data updated')
          } else {console.log(response.data.msg) }
        }, (err) => console.log(err))
      } else {
        this.editedLink = null
      }
      this.editedLink = null
    },
    doneDelete(cardId) {
      deleteLink(cardId,
      (response) => {
        if (response.data.msg === "success") {
          console.log(response.data)
          console.log('link data deleted')
          const idx = this.links.findIndex(function(item) {return item.id === cardId})
          this.links.splice(idx, 1)
        } else {console.log(response.data.msg) }
      }, (err) => console.log(err))
    }
  }
}
</script>

<style>
.editing .view {
  display: none;
}
.editing .edit {
  display: block;
}
.edit {
  display: none;
}
</style>