<template>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
  </head>
  <!-- <LogoutButton v-if="$store.state.token != ''" /> -->
  <NavBar v-if="$route.name !='login'" />
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav> -->
  <router-view/>
</template>

<script>
  // import LogoutButton from '@/components/LogoutButton.vue';
  import NavBar from '@/components/NavBar.vue';

  export default {
      components: {
          // LogoutButton,
          NavBar
      }
  }
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap');
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0;
}
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
* { 
 margin: 0; 
 padding: 0; 
} 
</style>
