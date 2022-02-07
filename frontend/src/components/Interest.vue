<template>
  <v-dialog
    v-model="show"
    max-width="700px"
  >
    <v-card>
      <v-card-title class="justify-center">
        <p class="text-h5 font-weight-bold mt-3">관심사 선택하기</p>
      </v-card-title>
      <!-- <v-card-text>
        <v-select
          :items="select"
          label="관심있는 분야를 선택해주세요"
          item-value="text"
        ></v-select>
      </v-card-text> -->

      <v-card-actions class="justify-center">
        <v-combobox
          v-model="chips"
          :items="items"
          chips
          clearable
          label="관심있는 분야를 선택해주세요"
          multiple
          prepend-icon="mdi-filter-variant"
          solo
        >
          <template v-slot:selection="{ attrs, item, select, selected }">
            <v-chip
              v-bind="attrs"
              :input-value="selected"
              close
              @click="select"
              @click:close="remove(item)"
            >
              <strong>{{ item }}</strong>&nbsp;
            </v-chip>
          </template>
        </v-combobox>
      </v-card-actions>

        <v-card-actions class="justify-center">
          <v-btn text @click.stop="show=false">
            완료
          </v-btn>
          <v-btn text @click.stop="show=false">
            Close
          </v-btn>
        </v-card-actions>

    </v-card>
  </v-dialog>
</template>


<script>
export default {
  data () {
    return {
      // 제공되는 관심사 카테고리 리스트
      // select: [
      //   { text: 'Python' },
      //   { text: 'JavaScript' },
      //   { text: 'Django' },
      //   { text: 'C++' },
      //   { text: 'Vue.js' },
      // ],
      chips: [],
      items: ['C', 'C++', 'C#', 'Django', 'Java', 'JavaScript', 'React.js', 'Vue.js', 'Python', 'R', 'Ruby', 'TypeScript'],
    }
  },
    methods: {
      remove (item) {
        this.chips.splice(this.chips.indexOf(item), 1)
        this.chips = [...this.chips]
      },
    },

  props: {
    value: Boolean
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  }
}
</script>