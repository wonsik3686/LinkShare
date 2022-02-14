<template>
  <v-container>
    <!-- <v-btn>{{ changeButtonText }}</v-btn> -->
    <v-btn @click="createUserLinktree">생성</v-btn>
    <v-btn @click="updateUserLinktree">편집</v-btn>
    <v-btn @click="deleteUserLinktree">삭제</v-btn>

    <div>
      <div class="row my-3">
        <flowy-new-block
          v-for="(block, index) in blocks"
          :key="index"
          @drag-start="onDragStartNewBlock"
          @drag-stop="onDragStopNewBlock"
        >
          <template v-slot:preview="{}">
            <Block
              :title="block.preview.title"
              :description="block.preview.description"
            />
          </template>
          <template v-slot:node="{}">
            <Node
              :title="block.node.title"
              :description="block.node.description"
              :custom-attribute="block.node.canBeAdded"
            />
          </template>
        </flowy-new-block>
      </div>

      <v-divider></v-divider>

      <div class="row my-5">
        <div class="flex-grow overflow-auto" style="width:100%">
          <flowy
            class="mx-auto"
            :nodes="nodes"
            :beforeMove="beforeMove"
            :beforeAdd="beforeAdd"
            @add="add"
            @move="move"
            @remove="remove"
            @drag-start="onDragStart"
          >
          </flowy>
        </div>

      </div>
    </div>
  </v-container>
</template>

<script>
import Vue from 'vue'
import Block from './block'
import Node from './node'
import _ from 'lodash'
import { listLink } from '@/api/linkbox'
import { createLinktree, listLinktree, updateLinktree, deleteLinktree } from '@/api/linktree'

Vue.component('Node', Node)

export default {
  components: {
    Block,
    Node,
  },
  data: () => ({
    editingTree: false,
    links: null,
    test: [],
    holder: [],
    dragging: false,
    blocks: [],
    nodes: [
      {
        id: '1',
        parentId: -1,
        nodeComponent: 'Node',
        data: {
          text: 'Parent block',
          title: 'Root Node',
          description: '<span>Make your own <b>LinkTree</b></span>',
          isroot: true,
        },
      },
    ],
  }),
  created() {
    console.log(this.nodes)
    const boxid = this.$route.params.boxid
    this.boxid = boxid
    // tree의 blocks 정보 갖고오기
    // 각 링크의 데이터 가져와 block 형태로 변형 
    listLink(boxid,
      (response) => {
        if (response.data.msg === "success") {
          this.links = response.data.object
        
          this.links.forEach((value) => {
            // block, node array에 값 추가
            var linkBlock = {preview: { title: '', description: '' },
                             node: {title:'', description:''}}
            linkBlock.preview.title = linkBlock.node.title = value.title
            linkBlock.preview.description = linkBlock.node.description = value.desc
            this.blocks.push(linkBlock)
          })
        } else { console.log(response.data.msg) }
      }, (err) => console.log(err))
    // tree의 node 정보 갖고오기
    console.log(this.nodes)
    this.listUserLinktree(boxid)
    },
  methods: {
    createUserLinktree() {
      const linktreeData = { boxid: this.boxid, treeContents: JSON.stringify({ nodes: this.nodes })}
      console.log(linktreeData.treeContents)
      createLinktree(linktreeData,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success createLinktree')
          console.log(res.data)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    listUserLinktree(boxid) {
      listLinktree(boxid,
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success listLinktree')
          console.log(res.data)
          console.log(JSON.parse(res.data.object['0'].treeContents))
          this.nodes = JSON.parse(res.data.object['0'].treeContents).nodes
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    updateUserLinktree() {
      const linktreeData = { boxid: this.boxid, treeContents: this.nodes }
      updateLinktree(linktreeData, 
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success updateLinktree')
          console.log(res.data)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    deleteUserLinktree() {
      deleteLinktree(this.boxid, 
      (res) => {
        if (res.data.msg === 'success') {
          console.log('success deleteLinktree')
          console.log(res.data)
        } else { console.log(res.data.msg) }
      }, (err) => console.log(err))
    },
    // Flowy 관련 methods
    onDragStartNewBlock (event) {
      console.log('onDragStartNewBlock', event);
      // contains all the props and attributes passed to demo-node
      const { props } = event
      this.newDraggingBlock = props;
      console.log(this.nodes)
    },
    onDragStopNewBlock (event) {
      console.log('onDragStopNewBlock', event);
      this.newDraggingBlock = null;
    },
    // REQUIRED
    beforeMove ({ to, from }) {
      // called before moving node (during drag and after drag)
      // indicator will turn red when we return false
      // from is null when we're not dragging from the current node tree
      console.log('beforeMove', to, from);

      // we cannot drag upper parent nodes in this demo
      if (from && from.parentId === -1) {
        return false;
      }
      // we're adding a new node (not moving an existing one)
      if (from === null) {
        // we've passed this attribute to the demo-node
        if (this.newDraggingBlock['custom-attribute'] === false) {
          return false
        }
      }

      return true;
    },
    // REQUIRED
    beforeAdd ({ to, from }) {
      // called before moving node (during drag and after drag)
      // indicator will turn red when we return false
      // from is null when we're not dragging from the current node tree
      console.log('beforeAdd', to, from);

      // we've passed this attribute to the demo-node
      if (this.newDraggingBlock['custom-attribute'] === false) {
        return false
      }

      return true;
    },
    randomInteger () {
      return Math.floor(Math.random() * 10000) + 1;
    },
    generateId (nodes) {
      let id = this.randomInteger();
      // _.find is a lodash function
      while (_.find(nodes, { id }) !== undefined) {
        id = this.randomInteger();
      }
      return id;
    },
    addNode (_event) {
      const id = this.generateId();
      this.nodes.push({
        ..._event.node,
        id,
      });
    },
    remove (event) {
      console.log('remove', event)

      // node we're dragging to
      const { node } = event

      // we use lodash in this demo to remove node from the array
      const nodeIndex = _.findIndex(this.nodes, { id: node.id });
      this.nodes.splice(nodeIndex, 1);
    },
    move (event) {
      console.log('move', event);

      // node we're dragging to and node we've just dragged
      const { dragged, to } = event;

      // change panentId to id of node we're dragging to
      dragged.parentId = to.id;
    },
    add (event) {
      // every node needs an ID
      const id = this.generateId();

      // add to array of nodes
      this.nodes.push({
        id,
        ...event.node,
      });
    },
    onDragStart (event) {
      console.log('onDragStart', event);
      this.dragging = true;
    },
  },
  computed: {
    changeButtonText() {
      return this.editingTree ? 'edit' : 'save';
    }
  },
}
</script>

<style>

</style>