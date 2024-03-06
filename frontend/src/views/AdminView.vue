<template>
    <div class="admin">
      <br>
      <h1>Admin Panel</h1>
      <AdminPanel></AdminPanel>
    </div>
  </template>
  
<script>
import AdminPanel from '../components/AdminPanel.vue';

export default {
  name: 'AdminView',
    components: {
      AdminPanel
    },
    data() {
      return {
      }
    },
    methods: {

    },
  };
  </script>