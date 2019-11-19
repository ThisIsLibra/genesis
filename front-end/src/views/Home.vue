<template>
  <div v-if="loaded">
    <v-container
      fill-height
      fluid
      grid-list-xl
    >
      <v-layout wrap>
        <v-flex
          sm6
          xs12
          md6
          lg3
        >
          <material-stats-card
            :value="statusSnippets.message"
            color="green"
            icon="mdi-counter"
            title="Loaded Snippets"
            sub-icon="mdi-information-outline"
            sub-text="The amount of  loaded snippets within Genesis"
          />
        </v-flex>
        <v-flex
          sm6
          xs12
          md6
          lg3
        >
          <material-stats-card
            :value="statusTopLanguage.message"
            color="green"
            icon="mdi-format-italic"
            title="Most used language"
            sub-icon="mdi-information-outline"
            sub-text="The most used language in all loaded snippets"
          />
        </v-flex>
        <v-flex
          sm6
          xs12
          md6
          lg3
        >
          <material-stats-card
            :value="statustechniqueCount.message"
            color="green"
            icon="mdi-cube"
            title="Technique count"
            sub-icon="mdi-information-outline"
            sub-text="The amount of unique techniques in all snippets"
          />
        </v-flex>
        <v-flex
          sm6
          xs12
          md6
          lg3
        >
          <material-stats-card
            :value="statusVersion.message"
            color="green"
            icon="mdi-git"
            title="Version"
            sub-icon="mdi-information-outline"
            sub-text="The version of this instance of Genesis"
          />
        </v-flex>
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
                  <div class="title font-weight-light mb-2">Genesis</div>
                  <div class="category">
                    <strong>Welcome to the home page!</strong>
                  </div>
                </div>
                <h5>
                  This website serves as a graphical user interface for the Java back-end. All communications go through the back-end API, where the request is processed and the requested value is returned. To generate a snippet based upon the provided selection, please head over to the <a href="/generate-snippet">Generate Snippet</a> page. To add a new snippet to this instance of Genesis, please head over to the <a href="add-snippet">Add Snippet</a> page.<br><br>
                  If any questions are raised, please refer to the <a href="/faq">Frequently Asked Questions</a>. If your problem is not resolved there, please do not hesitate to open an issue on the <a href="GITHUB URL">public Github repository</a>.
                </h5>
              </material-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import store from '@/store'

export default {
  data () {
    return {
      loaded: false
    }
  },
  computed: {
    statustechniqueCount: () => { return store.getters['statustechniqueCount'] },
    statusTopLanguage: () => { return store.getters['statusTopLanguage'] },
    statusVersion: () => { return store.getters['statusVersion'] },
    statusSnippets: () => { return store.getters['statusSnippets'] }
  },
  async mounted () {
    try {
      await store.dispatch('retrieveAll')
      this.$data.loaded = true
    } catch (err) {
      console.error(err)
      toastr.error(err)
    }
  },
  methods: {
    complete (index) {
      this.list[index] = !this.list[index]
    }
  }
}
</script>
