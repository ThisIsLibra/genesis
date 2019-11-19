<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout
      justify-center
      wrap
    >
      <v-flex
        md12
      >
        <material-card
          color="green"
          title="Mitre ATT&CK Tactics"
          text="All embedded tactics and their respective techniques"
        >
          <v-data-table
            :headers="headers"
            :items="items"
            hide-actions
          >
            <template
              slot="headerCell"
              slot-scope="{ header }"
            >
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template
              slot="items"
              slot-scope="{ item }"
            >
              <td>{{ item.name }}</td>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      headers: [
        {
          sortable: false,
          text: 'Tactics',
          value: 'tactics'
        },
        {
          sortable: false,
          text: 'Number of techniques',
          value: 'techniqueCount'
        }
      ],
      loading: true,
      items: []
    }
  },

  created () {
    axios.get('http://localhost:8080/genesis/api/v1/tactics')
      .then(response => {
        console.log(response.data)
        this.items = response.data // contains ".name" as a string for the tactic name, and ".techniques" as an array for the techniques
      })
      .catch(error => console.log(error))
      .finally(() => {
        this.loading = false
      })
  }
}
</script>
