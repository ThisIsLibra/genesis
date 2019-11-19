// https://vuex.vuejs.org/en/mutations.html

export default {
  //
  setLanguages (state, data) {
    state.languages = data
  },
  setTechniques (state, data) {
    state.techniques = data
  },
  setArchitectures (state, data) {
    state.architectures = data
  },
  setDownloadData (state, data) {
    state.downloadData = data
  },
  setSnippets (state, data) {
    const objects = {}
    for (let i = 0; i < data.length; i++) {
      const obj = data[i]
      objects[obj.id] = obj
    }
    state.snippets = objects
  },
  setCurrentSnippetId (state, data) {
    state.currentSnippetId = data
  },
  setStatustechniqueCount (state, data) {
    state.statustechniqueCount = data
  },
  setStatustoplanguage (state, data) {
    state.statusTopLanguage = data
  },
  setStatusversion (state, data) {
    state.statusVersion = data
  },
  setStatussnippets (state, data) {
    state.statusSnippets = data
  }
}
