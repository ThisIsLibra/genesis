<template>
  <div class="snippetdetails">
  <div class="">
    <div>
      <h4>Configuration</h4>



        <v-text-field
      v-model="currentSnippet.id"
      label="Snippet Id"
     readonly
    ></v-text-field>


        <v-select
      v-model="currentSnippet.profile"
      :items="profiles"
      label="Select obufscation profile"
      required
    ></v-select>


      </div>
      <div v-if="currentSnippet.alterators && currentSnippet.alterators.length !== 0">
         <h5>Alterators</h5>


<v-text-field 
            v-bind:key="alterator.key"
            v-for="alterator in currentSnippet.alterators"
            v-model="alterator.value"
            :label="alterator.key"
          ></v-text-field>

        </div>


     <v-btn
      class="ma-2"
      color="primary"
@click="saveForm(currentSnippet)"
    >
      Generate Snippet
    </v-btn> <v-btn
      class="ma-2"
      outlined 

@click="reset()"
    >
      Cancel
    </v-btn>

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

import axios from 'axios';

/* Escape HTML for the error message box */
String.prototype.htmlEscape = function() 
  {return this.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/\>/g,'&gt;')}
 

/* Download file directly via axios */
function download(content,id){
   const obj = window.URL.createObjectURL(new Blob([content]));
   const link = document.createElement('a');
   link.href = obj;
   link.download = id;
   document.body.appendChild(link);
   link.click();
   document.body.removeChild(link);
} 

import store from '@/store';

export default {

    data() {
        return {"name":"",profile:"",extra:[],"snippetObj":{},extra_fields:[]};
    },

    methods: {
    
        reset() {
            store.commit("setCurrentSnippetId","");
            this.$emit('setE');
            this.$forceUpdate();
        },
        async saveForm(obj){
            let snippetObj = JSON.parse(JSON.stringify(obj));
            const payload = {};
            payload['snippetId'] = snippetObj.id;
            payload['profile'] = snippetObj.profile;
            payload['keyValuePairs'] = snippetObj.alterators;

            // check if values are not empty
            for(let k in payload) {
                if(!payload[k]) {
                    toastr.error('Please specify the obfuscator ' + k);
                    return;
                }
            }

            for(let i = 0;i < payload['keyValuePairs'].length;i++) {
                let kv = payload['keyValuePairs'][i];
                console.log("debug: kv",kv);
                if(!kv['value']) {
                    toastr.error('Please provide a value for ' + kv['key'].htmlEscape());
                    return;
                }
            }

            console.log({"payload":payload});
            try {
                await store.dispatch('createSnippet',payload);
                let url = store.getters['downloadData'];
                download(url,snippetObj.id);
                toastr.success('Downloading...');
                this.$emit('setE');
            } catch(err) {
                toastr.error(err);
            }
        }
    },
    computed: {
        downloadData: () => { return 
    store.getters['downloadData']
},

    currentSnippet: () => { return store.getters['currentSnippet']},

        profiles: () => {
            return [
                "NONE",
                "LOW",
                "MEDIUM",
                "HIGH"
            ];
        },
    },
    mounted() {
    
    },
}
</script>
