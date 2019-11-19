// https://vuex.vuejs.org/en/actions.html
import Api from '@/Api.ts'

export default {
  /* POST snippet data to save new snippets (OR {'error':'message'}) */
  addNewSnippet: async ({ commit, dispatch }, payload) => {
    try {
      const obj = JSON.parse(JSON.stringify(payload))
      const res = await Api().post('/snippets/create', obj)
      if (res.data.error) {
        commit('error', res.data.error)
        return Promise.reject(res.data.error)
      }
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* POST snippet data to receive download URL from API (OR {'error':'message'}) */
  createSnippet: async ({ commit, dispatch }, payload) => {
    try {
      const obj = JSON.parse(JSON.stringify(payload))
      const res = await Api().post('/build', obj)
      if (res.data.error) {
        commit('error', res.data.error)
        return Promise.reject(res.data.error)
      } else {
        commit('setDownloadData', res.data)
      }
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET StatustechniqueCount from API */
  retrieveStatustechniqueCount: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/techniqueCount')
      commit('setStatustechniqueCount', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Statustoplanguage from API */
  retrieveStatustoplanguage: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/topLanguage')
      commit('setStatustoplanguage', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET techniqueCount from API */
  retrievetechniqueCount: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/techniqueCount')
      commit('settechniqueCount', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Toplanguage from API */
  retrieveToplanguage: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/topLanguage')
      commit('setToplanguage', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Statusversion from API */
  retrieveStatusversion: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/version')
      commit('setStatusversion', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Statussnippets from API */
  retrieveStatussnippets: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/status/snippets')
      commit('setStatussnippets', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },

  /* Load all resources needed */
  retrieveAll: ({ commit, dispatch }, payload) => {
    /* promises are used to make actions execute faster (non blocking) */
    let promises = []
    promises.push(dispatch('retrieveLanguages'))
    promises.push(dispatch('retrieveTechniques'))
    promises.push(dispatch('retrieveArchitectures'))
    promises.push(dispatch('retrieveSnippets'))
    promises.push(dispatch('retrieveStatustechniqueCount'))
    promises.push(dispatch('retrieveStatustoplanguage'))
    promises.push(dispatch('retrieveStatusversion'))
    promises.push(dispatch('retrieveStatussnippets'))
    return Promise.all(promises)
  },

  /* GET Languages from API */
  retrieveLanguages: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/snippets/languages')
      commit('setLanguages', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Techniques from API */
  retrieveTechniques: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/techniques')
      commit('setTechniques', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Architectures from API */
  retrieveArchitectures: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/snippets/architectures')
      commit('setArchitectures', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  },
  /* GET Snippets from API */
  retrieveSnippets: async ({ commit, dispatch }, payload) => {
    try {
      const res = await Api().get('/snippets')
      commit('setSnippets', res.data)
    } catch (err) {
      commit('error', err)
      return Promise.reject(err)
    }
  }
}
