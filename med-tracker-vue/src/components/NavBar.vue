<template>
    <div class="container">
        <ul>
            <div id="selection-box"></div>
            <li @click="setActive($event, 1)" :class="{ active : activeElement == 1 }"><router-link :to="{ name: 'home'}">Home</router-link></li>
            <li @click="setActive($event, 2)" :class="{ active : activeElement == 2 }"><router-link :to="{ name: 'about'}">About</router-link></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'NavBar',
    props: {
    },
    data() {
        return {
            activeElement: 0
        }
    },
    methods: {
        setActive(e, element) {
            const tag = e.target;
            const selBox = document.getElementById("selection-box");
            
            selBox.style.left = tag.offsetLeft+'px';
            selBox.style.width = tag.offsetWidth+'px';

            this.activeElement = element;
        }
    },
    computed: {
        
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
</style>