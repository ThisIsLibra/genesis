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
            <div class="title font-weight-light mb-2">Why was Genesis made?</div>
            <div class="category">
              <strong>This questions explains why Genesis was created and who the target audience is</strong>
            </div>
          </div>
          <h5>
            During purple teaming excersises, one of the impediments that is often encountered, is the lack of time to create functioning samples. Creating rules without fully testing them, will inevitable result in mistakes that would have been noticed during testing. Additionally, the Security Operations Centre (SOC) can use Genesis to periodically test old cases with newly generated samples The Red Team can also use Genesis to create samples during an adverserial campaign.
          </h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">Who made Genesis?</div>
            <div class="category">
              <strong>This questions contains information about the author of Genesis</strong>
            </div>
          </div>
          <h5>
            Genesis has been developed by
            <a
              target="_blank"
              href="https://maxkersten.nl/about-me/">
              Max 'Libra' Kersten
            </a>
            , who is employed by ABN AMRO CISO Operations & Intelligence. If there are any questions or suggestions about the framework, feel free to send a (direct) message on
            <a
              target="_blank"
              href="https://twitter.com/LibraAnalysis">Twitter @LibraAnalysis
            </a>.
          </h5>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    color: null,
    colors: [
      'purple',
      'info',
      'success',
      'warning',
      'error'
    ],
    top: true,
    bottom: false,
    left: false,
    right: false,
    snackbar: false
  }),

  methods: {
    snack (...args) {
      this.top = false
      this.bottom = false
      this.left = false
      this.right = false

      for (const loc of args) {
        this[loc] = true
      }

      this.color = this.colors[Math.floor(Math.random() * this.colors.length)]

      this.snackbar = true
    }
  }
}
</script>
