<template>
    <div class="background">
        <div class="container">
            <div class="nav-logo">
                <router-link :to="{ name: 'home'}"><img src="../../public/images/built-logo.png"></router-link>
            </div>
            <ul>
                <li><router-link :to="{ name: 'home'}">Home</router-link></li>
                <li><router-link :to="{ name: 'about'}">About</router-link></li>
                <li><router-link :to="{ name: 'about'}">Classes</router-link></li>
                <li><router-link :to="{ name: 'about'}">Online Programs</router-link></li>
                <li><router-link :to="{ name: 'about'}">Contact</router-link></li>
            </ul>
            <div class="dropdown-btn">
                <p>{{ accountText }}</p>
                <span class="material-symbols-outlined">expand_more</span>
                <div class="dropdown-content">
                    <router-link :to="{ name: 'login'}" v-if="this.$store.getters.GET_TOKEN === ''">Login</router-link>
                    <router-link :to="{ name: 'login'}" v-if="this.$store.getters.GET_TOKEN === ''">Register</router-link>
                    <router-link :to="{ name: 'login'}" v-if="this.$store.getters.GET_TOKEN !== ''" @click="logout">Logout</router-link>
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>

export default {
    name: 'NavBar',
    components: {
    },
    props: {
    },
    data() {
        return {
            accountText: 'Account'
        }
    },
    methods: {
        logout() {
            this.$store.commit("LOGOUT");
        }
    },
    created() {
        if(JSON.stringify(this.$store.getters.GET_USER) !== '{}'){
            this.accountText = this.$store.getters.GET_USER.username;
        }
    },
}
</script>

<style scoped>
.background {
    background-color: #231f20;
}
.container {
    display: flex;
    flex-direction: row;
    position: relative;
    align-items: center;
    block-size: 80px;
    background-color: #231f20;
    padding-right: 20px;
    max-width: 1400px;
    margin-left: auto;
    margin-right: auto;
    font-family: 'Rowdies', sans-serif;
}
.nav-logo {
    inline-size: 260px;
    flex-shrink: 0;
}
.nav-logo img {
    width: 100%;
}
.dropdown-btn {
    color: rgb(255, 255, 255);
    font-size: 1.1rem;
    display: flex;
    align-items: center;
    height: 100%;
    cursor: pointer;
}
.dropdown-content {
    display: none;
    position: absolute;
    top: 100%;
    background-color: #ffffff;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    min-width: 120px;
    z-index: 1;
}
.dropdown-content a {
    display: block;
    color: #231f20;
    padding-top: 15px;
    height: 30px;
    font-size: .9rem;
    padding-left: 10px;
    text-align: left;
}
.dropdown-btn:hover .dropdown-content {
    display: block;
}
.dropdown-content a:hover {
    background-color: #f0f0f0;
}
.dropdown-btn:hover {
    color: #ffffffcc;
}
span {
    font-size: 19px;
    font-weight: bolder;
    vertical-align: middle;
}
ul {
    display: flex;
    margin-left: auto;
    margin-right: auto;
    list-style: none;
    font-size: 1.1rem;
}
li {
    margin-left: 30px;
    margin-right: 30px;
}
a {
    text-decoration: none;
    color: #ffffff;
}
li a:hover {
    color: #ffffffcc;
}
</style>