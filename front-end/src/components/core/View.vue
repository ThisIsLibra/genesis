<template>
  <v-content>
    <div id="core-view">
      <v-fade-transition mode="out-in">
        <router-view />
      </v-fade-transition>
    </div>
    <core-footer v-if="$route.name !== 'About'" />
  </v-content>
</template>

<script>
export default {
  metaInfo () {
    return {
      title: 'Genesis'
    }
  }
}
</script>

<style>
#core-view {
  padding-bottom: 100px;
}
</style>
