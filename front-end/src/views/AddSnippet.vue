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
            <div class="title font-weight-light mb-2">Add New Snippet</div>
            <div class="category">
              <strong>To add a snippet, please provide the requested information below</strong>
            </div>
          </div>
          <FormAddSnippet />
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<style>

</style>

<script>
import FormAddSnippet from '@/components/forms/AddSnippet.vue';

import AddSnippet from '@/components/forms/AddSnippet.vue';


import store from '@/store';

export default {
  components: {
    FormAddSnippet,
        AddSnippet,
      },
  data () {
    return {
      loaded: false,
    };
  },
  computed: {
    languages: () => { return store.getters['languages']},
        techniques: () => { return store.getters['techniques']},
        architectures: () => { return store.getters['architectures']},
      },
  methods: {
  },
  async mounted() {
        try {
            await store.dispatch("retrieveAll");
            this.$data.loaded = true;
        } catch(err) {
            console.error(err);
            toastr.error(err);
        }
    },
}
</script>

