<template>
  <v-footer
    id="core-footer"
    absolute
    height="82"
  >
    <div class="footer-items">
      <span
        v-for="link in links"
        :key="link.name"
      >
        <a
          :href="link.Link"
          class="tertiary--text footer-links">{{ link.name }}</a>
      </span>
    </div>
    <v-spacer/>
    <span class="font-weight-light copyright">
      Front-end template design by &copy;
      {{ (new Date()).getFullYear() }}
      <a
        href="https://www.creative-tim.com/"
        target="_blank">Creative Tim</a> wih modificaions by <a href="https://yourdeveloper.tech/maartenschermer">Maarten Schermer</a>
    </span>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    links: [
      { name: 'Home', Link: '/dashboard' },
      { name: 'Github', Link: 'https://www.github.com/thisislibra/genesis' }
    ]
  })
}
</script>

<style>
#core-footer {
  z-index: 0;
}
</style>
