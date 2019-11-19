<template>
  <v-container
    fluid
    grid-list-xl
    fill-height>
    <v-layout
      justify-center
      align-center
    >
      <v-flex xs12>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">Snippet generation</div>
            <div class="category">
              <strong>To generate a snippet, follow the steps that are presented below</strong>
            </div>
          </div>
 <v-stepper v-model="e1">
    <v-stepper-header>
      <v-stepper-step :complete="e1 > 1" step="1">Select a snippet</v-stepper-step>
      <v-divider></v-divider>
      <v-stepper-step :complete="e1 > 2" step="2">Configure & generate</v-stepper-step>
      <v-divider></v-divider>
    </v-stepper-header>
    <v-stepper-items>
      <v-stepper-content step="1">
                  <div v-for="obj in snippets">
                      <Snippet @setE='setE2' :obj=obj />
                  </div>
              </v-stepper-content>
              <v-stepper-content step="2">
                <GenerateSnippet
                  v-if="currentSnippet"
                  :obj="currentSnippet"
                  @setE="setE1" />
              </v-stepper-content>
            </v-stepper-items>
          </v-stepper>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import AddSnippet from '@/components/forms/AddSnippet.vue'
import Snippet from '@/components/Snippet.vue'
import GenerateSnippet from '@/components/SnippetDetails.vue'
import store from '@/store'

export default {
  components: {
    AddSnippet,
    Snippet,
    GenerateSnippet
  },
  data () {
    return {
      e1: 1,
      loaded: false
    }
  },
  computed: {
    snippets: () => { return store.getters['snippets'] },
    downloadData: () => { return store.getters['downloadData'] },
    currentSnippet: () => { return store.getters['currentSnippet'] }
  },
  async mounted () {
    try {
      // reset selected snippet & download url
      store.commit('setDownloadData', '')
      store.commit('setCurrentSnippetId', '')
      await store.dispatch('retrieveAll')
      this.$data.loaded = true
    } catch (err) {
      console.error(err)
      toastr.error(err)
    }
  },
  methods: {
    // callback to set slider
    setE2 () {
      this.$data.e1 = 2
    },
    setE1 () {
      this.$data.e1 = 1
    },
    reset () {
      // reset selected snippet & download url
      store.commit('setDownloadData', '')
      store.commit('setCurrentSnippetId', '')
    }
  }
}
</script>
