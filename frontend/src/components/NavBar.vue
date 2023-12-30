<template>
    <div class="background">
        <div class="container">
            <div class="nav-logo" @click="setActive(listItemOne, 1)">
                <router-link :to="{ name: 'home'}"><img src="../../public/images/built-logo.png"></router-link>
            </div>
            <ul>
                <!-- <div id="selection-box"></div> -->
                <li id="list-item-one" @click="setActive($event.target, 1)" :class="{ active : activeElement == 1 }"><router-link :to="{ name: 'home'}">Home</router-link></li>
                <li id="list-item-two" @click="setActive($event.target, 2)" :class="{ active : activeElement == 2 }"><router-link :to="{ name: 'about'}">About</router-link></li>
                <li><router-link :to="{ name: 'about'}">Classes</router-link></li>
                <li><router-link :to="{ name: 'about'}">Online Programs</router-link></li>
                <li><router-link :to="{ name: 'about'}">Contact</router-link></li>
                <!-- <li><LogoutButton /></li> -->
            </ul>
            <div class="dropdown-btn">
                <p>Account</p>
                <span class="material-symbols-outlined">expand_more</span>
                <div class="dropdown-content">
                    <router-link :to="{ name: 'login'}">Login</router-link>
                    <router-link :to="{ name: 'login'}">Register</router-link>
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>
// import { useRoute, useRouter } from 'vue-router'
// import LogoutButton from '@/components/LogoutButton.vue';

export default {
    name: 'NavBar',
    components: {
        // LogoutButton,
    },
    props: {
    },
    data() {
        return {
            activeElement: 0,
            listItemOne: null,
        }
    },
    methods: {
        setActive(e, element) {
            const selBox = document.getElementById("selection-box");
            
            selBox.style.left = e.offsetLeft+'px';
            selBox.style.width = e.offsetWidth+'px';

            this.activeElement = element;
        }
    },
    // async mounted() {
    //     const route = useRoute()
    //     const router = useRouter()
    //     await router.isReady()
    //     let listItem = null;
    //     switch(route.name) {
    //         case 'home':
    //             listItem = document.getElementById("list-item-one");
    //             this.setActive(listItem, 1);
    //             break;
    //         case 'about':
    //             listItem = document.getElementById("list-item-two");
    //             this.setActive(listItem, 2);
    //             break;
    //     }

    //     this.listItemOne = document.getElementById("list-item-one");
    // }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto');
@import url('https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap');
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
    /* .container {
        background-color: #000000;
        float: left;
        overflow: hidden;
        position: relative;
        padding: 10px 0px;
        width: 100%;
    }
    .active {
        color: #5161ce;
        transition: all 0.7s;
    }
    .nav-logo {
        display: inline-block;
        position: absolute;
        left: 0px;
        padding: 0px 20px;
        font-size: 20px;
        line-height: 45px;
        color: white;
        cursor: pointer;
    }
    #selection-box {
        display: inline-block;
        position: absolute;
        height: 100%;
        transition-duration: 0.6s;
        transition-timing-function: cubic-bezier(0.68, -0.55, 0.265, 1.55);
        background-color: white;
        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
        width: 150px;
        left: -500px;
        pointer-events: none;
    }
    li {
        display: inline-block;
        color: rgba(255,255,255,0.5);
    }
    li a{
        display: inline-block;
        padding: 0px 20px;
        font-size: 15px;
        line-height: 45px;
        font-family: 'Roboto', sans-serif;
        cursor: pointer;
        position: relative;
        z-index: 1000 !important;
    }
    a {
        all: unset;
    }
    ul {
    text-align: right;
    } */
</style>