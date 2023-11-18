import { createStore } from 'vuex'

export default createStore({
  state: {
    loginSelection: ""
  },
  getters: {
  },
  mutations: {
    SET_LOGIN_SELECTION(state, payload){
      state.loginSelection = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
