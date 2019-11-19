// https://vuex.vuejs.org/en/getters.html

export default {
  //
  languages: (state) => {
    return state.languages
  },
  techniques: (state) => {
    return state.techniques
  },
  architectures: (state) => {
    return state.architectures
  },
  downloadData: (state) => {
    return state.downloadData
  },
  snippets: (state) => {
    return state.snippets
  },
  currentSnippet: (state) => {
    return state.snippets[state.currentSnippetId]
  },
  statustechniqueCount: (state) => {
    return state.statustechniqueCount
  },
  statusTopLanguage: (state) => {
    return state.statusTopLanguage
  },
  statusVersion: (state) => {
    return state.statusVersion
  },
  statusSnippets: (state) => {
    return state.statusSnippets
  }
}
