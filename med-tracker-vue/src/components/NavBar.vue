<template>
    <div class="container">
        <!-- <div class="nav-logo">
            <router-link :to="{ name: 'home'}">MedTracker</router-link>
        </div> -->
        <ul>
            <div class="nav-logo" @click="setActive(listItemOne, 1)">
                <router-link :to="{ name: 'home'}">MedTracker</router-link>
            </div>
            <div id="selection-box"></div>
            <li id="list-item-one" @click="setActive($event.target, 1)" :class="{ active : activeElement == 1 }"><router-link :to="{ name: 'home'}">Home</router-link></li>
            <li id="list-item-two" @click="setActive($event.target, 2)" :class="{ active : activeElement == 2 }"><router-link :to="{ name: 'about'}">About</router-link></li>
        </ul>
    </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'

export default {
    name: 'NavBar',
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
    async mounted() {
        const route = useRoute()
        const router = useRouter()
        await router.isReady()
        let listItem = null;

        switch(route.path) {
            case '/home':
                listItem = document.getElementById("list-item-one");
                this.setActive(listItem, 1);
                break;
            case '/about':
                listItem = document.getElementById("list-item-two");
                this.setActive(listItem, 2);
                break;
        }

        this.listItemOne = document.getElementById("list-item-one");
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto');
    .container {
        background-color: #5161ce;
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
    }
</style>