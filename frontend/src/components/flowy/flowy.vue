<template>
  <v-container>
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
import Block from '@/components/flowy/block'
import Node from '@/components/flowy/node'
import _ from 'lodash'

Vue.component('Node', Node)

export default {
  components: {
    Block,
    Node,
  },
  data: () => ({
    holder: [],
    dragging: false,
    blocks: [
      {
        preview: {
          title: 'New visitor',
        },
        node: {
          title: 'New visitor',
          description: '<span>When a <b>new visitor</b> goes to <b>Site 1</span></span>',
        },
      },
      {
        preview: {
          title: 'Update database',
          icon: 'error',
        },
        node: {
          title: 'Update database',
          description: '<span>Triggers when somebody performs a <b>specified action</b></span>',
        },
      },
      {
        preview: {
          title: 'Time has passed, very very very fast',
        },
        node: {
          title: 'Time has passed',
          description: 'Triggers after a specified <b>amount</b> of time',
        },
      },
      {
        preview: {
          title: 'Cannot be added',
        },
        node: {
          title: 'Time has passed',
          description: 'Triggers after a specified <b>amount</b> of time',
          canBeAdded: false,
        },
      },
    ],
    nodes: [
      {
        id: '1',
        parentId: -1,
        nodeComponent: Node,
        data: {
          text: 'Parent block',
          title: 'Root Node',
          description: '<span>Make your own <b>LinkTree</b></span>',
          isroot: true,
        },
      },
      {
        id: '2',
        parentId: '1',
        nodeComponent: Node,
        data: {
          text: 'Parent block',
          title: 'New Visitor',
          description: '<span>When a <b>new visitor</b> goes to <i>Site 1</i></span>',
        },
      },
      {
        id: '3',
        parentId: '1',
        nodeComponent: Node,
        data: {
          text: 'Parent block',
          title: 'New Visitor',
          description: '<span>When a <b>new visitor</b> goes to <i>Site 1</i></span>',
        },
      },
    ],
  }),
  methods: {
    onDragStartNewBlock (event) {
      console.log('onDragStartNewBlock', event);
      // contains all the props and attributes passed to demo-node
      const { props } = event
      this.newDraggingBlock = props;
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
  watch: {

  },
}
</script>

<style>

</style>