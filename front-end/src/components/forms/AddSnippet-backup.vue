<template>
  <div class="snippetdetails">
    <div class="">
      <div>

        <v-stepper v-model="e1">
          <v-stepper-header>
            <v-stepper-step
              :complete="e1 > 1"
              step="1">Details & Technology</v-stepper-step>

            <v-divider/>

            <v-stepper-step
              :complete="e1 > 2"
              step="2">Class</v-stepper-step>

            <v-divider/>

            <v-stepper-step
              :complete="e1 > 3"
              step="3">Function</v-stepper-step>

            <v-divider/>

          </v-stepper-header>

          <v-stepper-items>
            <v-stepper-content step="1">
              <h5>Details</h5>
              <v-text-field
                v-model="information.title"
                label="Snippet Title"/>

              <v-textarea
                v-model="information.description"
                label="Snippet Description"/>
              <v-text-field
                v-model="information.author"
                label="Author"/>
              <v-text-field
                v-model="information.date"
                label="Date"/>

              <h5>Technology</h5>
              <v-select
                v-model="clazz.language"
                :items="languages"
                label="What's the language?"
                required />

              <v-select
                v-model="clazz.architecture"
                :items="architectures"
                label="What's the architecture?"
                required />

              <div style="position:relative">
                <div style="padding-right:90px;">
                  <v-select
                    v-model="technique"
                    :items="techniques"
                    label="What techniques are used?"
                    required />
                </div>
                <div style="position:absolute;top:-10px;right:0;">
                  <v-btn @click="addTechnique(technique)">+</v-btn>
                </div>
              </div>

              <span v-for="val in added_techniques">
                <v-chip
                  class="ma-2"
                  label
                >
                  {{ val }}
                </v-chip>
              </span><br ><br >

              <v-btn
                class="ma-2"
                color="primary"
                @click="setE2"
              >
                Continue
              </v-btn>

            </v-stepper-content>

            <v-stepper-content step="2">
              <h3>2. Setup Class</h3>
              <v-textarea
                v-model="clazz.script"
                label="Script"/>

              <h3>Add variables</h3>
              <v-btn @click="addVar">+</v-btn>
              <div v-for="kv in added_vars">
                <v-text-field
                  v-model="kv.key"
                  label="Name"/>
                <v-text-field
                  v-model="kv.value"
                  label="Value"/>

              </div>
              <br >
              <br >

              <v-btn
                class="ma-2"
                color="primary"
                @click="setE3"
              >
                Continue
              </v-btn>
              <v-btn
                class="ma-2"
                outlined

                @click="setE1"
              >
                Cancel
              </v-btn>

            </v-stepper-content>

            <v-stepper-content step="3">

              <h3>3. Setup function</h3>

              <div v-if="clazz.language==='POWERSHELL'">
                <v-text-field
                  v-model="added_cmdletbinding"
                  label="cmdletbinding"/>
              </div>
              <div v-if="clazz.language==='VBA'">
                <v-text-field
                  v-model="added_type"
                  label="Type"/>
                <v-text-field
                  v-model="added_returntype"
                  label="Returntype"/>
              </div>

              <v-textarea
                v-model="func.functionBody"
                label="Function Body"/>

              <h3>Add arguments</h3>
              <v-btn @click="addArg">+</v-btn>
              <div v-for="kv in added_args">
                <v-text-field
                  v-model="kv.key"
                  label="Name"/>
                <v-text-field
                  v-model="kv.value"
                  label="Value"/>

              </div>
              <br >
              <br >
              <v-btn
                class="ma-2"
                color="primary"
                @click="saveForm(information,clazz,func)"
              >
                Add Snippet
              </v-btn>
              <v-btn
                class="ma-2"
                outlined

                @click="setE1"
              >
                Cancel
              </v-btn>

            </v-stepper-content>

          </v-stepper-items>
        </v-stepper>

      </div>

      <div/>
    </div>
  </div>
</template>
<style>
.snippetdetails {
    background: white;
    padding: 32px;
    -webkit-box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
    box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
    border-radius: 3px;
    margin: 25px 0 !important;
}
</style>
<script>

/* Escape HTML for the error message box */
import store from '@/store'

String.prototype.htmlEscape = function () { return this.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/\>/g, '&gt;') }

function initialState () {
  return {
    'e1': 1,
    'added_type': '',
    'added_returntype': '',
    'added_cmdletbinding': '',
    'added_techniques': [],
    'added_vars': [],
    'added_args': [],
    'technique': '',
    'information': { 'title': '', 'description': '', 'author': '', 'date': '' },
    'clazz': { 'language': '', 'script': '', 'architecture': '', 'variables': { } },
    'func': { 'dependencies': [], 'name': '', 'arguments': { }, 'functionBody': '', 'language': '', 'techniques': [] }
  }
}

export default {
  data () {
    return initialState()
  },
  computed: {
    languages: () => { return store.getters['languages'] },
    techniques: () => { return store.getters['techniques'] },
    architectures: () => { return store.getters['architectures'] }
  },
  mounted () {
  },
  methods: {
    setE1 () {
      this.$data.e1 = 1
      Object.assign(this.$data, initialState())
    },
    setE2 () {
      this.$data.e1 = 2
    },
    setE3 () {
      this.$data.e1 = 3
    },
    addTechnique (technique) {
      console.log('debug addTechnique', technique)
      console.log('debug added_techniques', this.$data.added_techniques)
      this.$data.added_techniques.push(technique)
    },
    addArg () {
      this.$data.added_args.push({ 'key': '', 'value': '' })
    },
    addVar () {
      this.$data.added_vars.push({ 'key': '', 'value': '' })
    },

    reset () {
      store.commit('setCurrentSnippetId', '')
      this.$forceUpdate()
    },
    async saveForm (information, clazz, func) {
      let informationObj = JSON.parse(JSON.stringify(information))
      let clazzObj = JSON.parse(JSON.stringify(clazz))
      let funcObj = JSON.parse(JSON.stringify(func))
      let techniques = JSON.parse(JSON.stringify(this.$data.added_techniques))
      // "information": { "title": "api-addition2", "description": "this is a description for the JS test snippet", "author": "Max 'Libra' Kersten", "date": "21/05/2019" },
      // "class": { "language": "JAVASCRIPT", "script": "theScriptTextHere", "architecture": "vm", "variables": { "body": "9" },

      // "functions": { "jsFunction": { "dependencies": [], "name": "jsFunction", "arguments": { "varName": "valueOfThisVariable", "varName2": "AdifferentValue" }, "functionBody": "functionBodyTextHere", "language": "JAVASCRIPT", "techniques": [ "SupplyChainCompromise", "SpearphishingViaService" ] } } }

      const payload = {}

      /* Form Validation */
      let funcKey = 'jsFunction' // hardcoded key
      payload['information'] = informationObj
      payload['class'] = clazzObj
      payload['class']['functions'] = {}
      payload['class']['functions'][funcKey] = funcObj
      console.log('debug: payload functions funckey', payload['class']['functions'])
      payload['class']['functions'][funcKey]['name'] = funcKey
      payload['class']['functions'][funcKey]['language'] = clazz['language']
      payload['class']['functions'][funcKey]['techniques'] = techniques

      /* load variables if VBA/POWERSHELL */
      if (payload.class.language === 'POWERSHELL') {
        payload['class']['functions'][funcKey]['cmdletBinding'] = JSON.parse(JSON.stringify(this.$data.added_cmdletbinding))
      }
      if (payload.class.language === 'VBA') {
        payload['class']['functions'][funcKey]['type'] = JSON.parse(JSON.stringify(this.$data.added_type))
        payload['class']['functions'][funcKey]['returnType'] = JSON.parse(JSON.stringify(this.$data.added_returntype))
      }

      /* load variables from dynamic form */

      let variables = {}
      for (let i = 0; i < this.$data.added_vars.length; i++) {
        let kv = this.$data.added_vars[i]
        console.log('debug: kv', kv)
        if (!kv['key'] && !kv['value']) {
          continue // ignore field if is has no key and value
        } else if (!kv['value']) {
          toastr.error('Please specify ' + kv['key'].htmlEscape())
          return
        } else {
          variables[kv.key] = kv.value
        }
      }

      payload['class']['variables'] = variables

      /* load arguments from dynamic form */
      let args = {}
      for (let i = 0; i < this.$data.added_args.length; i++) {
        let kv = this.$data.added_args[i]
        console.log('debug: kv', kv)
        if (!kv['key'] && !kv['value']) {
          continue // ignore field if is has no key and value
        } else if (!kv['value']) {
          toastr.error('Please specify ' + kv['key'].htmlEscape())
          return
        } else {
          args[kv.key] = kv.value
        }
      }

      payload['class']['functions'][funcKey]['arguments'] = args

      console.log({ 'payload': payload })
      for (let k in payload) {
        if (!payload[k]) {
          toastr.error('Please specify ' + k)
          return
        }
      }

      for (let i = 0; i < payload['class']['variables'].length; i++) {
        let kv = payload['class']['variables'][i]
        console.log('debug: kv', kv)
        if (!kv['value']) {
          toastr.error('Please specify ' + kv['key'].htmlEscape())
          return
        }
      }

      // alert(JSON.stringify(payload));
      try {
        await store.dispatch('addNewSnippet', payload)
        toastr.success('Snippet added!')
        this.setE1()
      } catch (err) {
        toastr.error(err)
      }
    }

  }
}
</script>
