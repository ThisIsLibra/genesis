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
            <div class="title font-weight-light mb-2">What is Genesis?</div>
            <div class="category">
              <strong>This questions provides insight in the goal of Genesis</strong>
            </div>
          </div>
          <h5>
            Reverse engineering malware to ensure it can be executed in a test environment is one of the tasks that is often performed during purple team exercises. Finding out what the malware is capable of, is the first step within this process. Knowing exactly what the complete binary does, is a time consuming task, if possible at all. The capabilities that need to be tested can also be copied into a newly made project. This mimics the malware's capabilities whilst the complete source code is known. Aside from the time consuming process this is, the sample is (roughly) the same, since it isn't obfuscated.<br><br>Genesis automates this process: a small source file is required, which is then altered based upon the given settings. Strings can be obfuscated, dead code can be inserted and/or the control flow of the output can be changed. All the source files that are included in the public project and/or files that are added privately, are mapped to the MITRE ATT&CK framework. Searching for <em>tactics</em> or <em>techniques</em> returns a list of possible source files that can be used. Additionally, one can mimic an Advanced Persistent Threat (APT), based on their description within the MITRE APT groups list.</h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">Is Genesis an alternative to known frameworks, such as the Metasploit Framework or Cobalt Strike?</div>
            <div class="category">
              <strong>This question provides insight in the purpose of Genesis, especially in relation to existing frameworks</strong>
            </div>
          </div>
          <h5>Genesis is <u>not</u> an alternative to other frameworks, but rather an extension. Most frameworks provide a static output, which can then be obfuscated with Genesis. Merging multiple samples will also be possible in the future, allowing the user to easily combine two or more samples. With the community behind the project, the default 'library' of snippets can be expanded to contain a lot of files from the get-go.</h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">How can I trust the output of Genesis?</div>
            <div class="category">
              <strong>This question provides information regarding the source code of Genesis</strong>
            </div>
          </div>
          <h5>Genesis is open-source! As such, the source code can be inspected at any given moment. Be sure to update regularly, otherwise you might miss out on newly published (community) content.</h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">How can I generate a sample?</div>
            <div class="category">
              <strong>This question provides additional information regarding the generation of a sample and known pitfalls</strong>
            </div>
          </div>
          <h5>Samples can be generated via <a href="generate-snippet">this</a> page.</h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">Can I use Genesis to automate certain tasks?</div>
            <div class="category">
              <strong>This question provides information regarding the API of Genesis.</strong>
            </div>
          </div>
          <h5>Genesis has an API! As a matter of fact, this VueJS front-end also uses the API. One can find the documentation <a href="https://github.com/thisislibra/genesis">here</a>.</h5>
        </material-card>
        <material-card
          color="green">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">How can I contribute to Genesis?</div>
            <div class="category">
              <strong>This question provides information the architecture of Genesis</strong>
            </div>
          </div>
          <h5>To participate in the development of Genesis, please see this (add link to github page with information) page.</h5>
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
