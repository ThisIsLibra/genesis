<template>
<div v-if="!loaded">
      <div class="preloader">
        <div class="loadwrap">
            <img id="loaderlogo1" style="height:80px;" src="img/iconblack.png">
        </div>
      </div>
    </div>

</template>
<style>
.preloader {
    background:#24C35E;
    top:0;
    bottom:0;
    left:0;
    right:0;
    position:fixed;
}
.loadwrap {
    position:absolute;
    top:50%;
    text-align:center;
    left:0;
    right:0;
    margin-top:-40px;
}
</style>
<script>
import store from '@/store'
export default {
        
    props: ['obj'],
    data() {
        return {
        };
    },

    methods: {

    },
    computed: {
                loaded: () => { return store.getters['loaded']},
    },
}

</script>
