<template>
    <div @click="selectSnippet(obj)" class="snippet">
        <div class="select_snippet">
            <button @click="selectSnippet(obj)" type="button" class="v-btn v-btn--contained theme--light v-size--default"><span class="v-btn__content">Select Snippet</span></button>
        </div>
        <h4>{{obj.title}} by {{obj.author}}</h4>
        <p>{{obj.description}}</p>
        <p>{{obj.date}}</p>
        <div style="clear:both;height:1px;"></div>
    </div>
</template>
<style>
.select_snippet {
    float:right;
    margin:16px;
}
.snippet {
    cursor:pointer;
    background:white;
    padding:32px;
        -webkit-box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
    box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
    border-radius: 3px;
        margin: 25px 0 !important;
}
.v-btn__content { color: black!important; }
</style>
<script>
import store from '@/store'

export default {
        
    props: ['obj'],
    components: {
    },
    data() {
        return {
        };
    },
    methods: {
        selectSnippet(obj) {
            store.commit("setCurrentSnippetId",obj.id);
            this.$emit('setE');
        },
    },
    computed: {
    },
    mounted() {
        if(!this.$props.obj) {
            let obj = {id:"1",title:"Snippet V1","description":"this is snippet" ,"author":"me" ,"date":"date"};
            this.$props.obj = obj;
        }
    },
}

</script>

