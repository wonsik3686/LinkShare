<template>
  <v-container>

    <v-row dense class="justify-start">
      <v-col cols="5" class="mx-3">
        <!-- 새 링크 생성 -->
        <!-- 해당 컴포넌트에서 done-create가 emit되면 doneCreate 함수 실행 -->
        <linkCardNew @done-create="doneCreate"/>
      </v-col>
      <v-col cols="5" class="mx-3"
        v-for="link in filteredData" :key="link.id" 
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
    filteredData() {
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
      // emit된 데이터 할당 후 boxid와 임시 id 부여
      // (id가 v-for의 key로 들어가있기 때문)
      const newLink = cardData
      newLink.boxid = this.boxid
      newLink.id = 'temp' + this.tempId

      if (cardData) {
        createLink(newLink,
        (response) => {
          if (response.data.msg === "success") {
            console.log('link data created')
            // data의 links array에 추가 후 임시 id 갱신
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