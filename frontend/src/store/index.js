import { createStore } from 'vuex'
import axios from 'axios'

const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default createStore({
  state: {
    loginSelection: "",
    token: currentToken || '',
    // user: {}
    user: currentUser || {}
  },
  getters: {
    GET_TOKEN(state){
      return state.token;
    },
    GET_USER(state){
      return state.user;
    }
  },
  mutations: {
    SET_LOGIN_SELECTION(state, payload){
      state.loginSelection = payload;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  },
  actions: {
  },
  modules: {
  }
})
