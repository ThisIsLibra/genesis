/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
  {
    path: '/home',
    // Relative to /src/views
    view: 'Home'
  },
  {
    path: '/generate-snippet',
    name: 'Generate Snippet',
    view: 'GenerateSnippet'
  },
  {
    path: '/add-snippet',
    name: 'Add Snippet',
    view: 'AddSnippet'
  },
  {
    path: '/faq',
    name: 'Frequently Asked Questions',
    view: 'Faq'
  },
  {
    path: '/about',
    view: 'About'
  }
]
