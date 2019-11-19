<template>
  <v-app>
    <div v-if="!loaded">
      <div class="preloader">
        <div class="loadwrap">
          <img
            id="loaderlogo1"
            style="height:80px;"
            src="img/vuetifylogo.png">
        </div>
      </div>
    </div>
    <core-toolbar />
    <core-drawer />
    <core-view />
  </v-app>
</template>

<style lang="scss">
@import '@/styles/index.scss';

/* Remove in 1.2 */
.v-datatable thead th.column.sortable i {
  vertical-align: unset;
}

/* make primary buttons green */
.v-btn.primary,
.v-btn.primary:hover { background-color: #55a64f !important;}
 .v-btn.primary .v-btn__content { color: white!important; }

/* loader */
.preloader {
    background:#40494F;
    top:0;
    bottom:0;
    left:0;
    right:0;
    z-index:9999;
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
export default {
  data () {
    return { 'loaded': false }
  },
  mounted () {
    const thiz = this
    setTimeout(function () {
      thiz.$data.loaded = true
    }, 2000)
  }
}
</script>
