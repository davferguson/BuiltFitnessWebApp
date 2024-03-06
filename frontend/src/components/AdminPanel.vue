<template>
    <div class="container">
        <div class="title">
            <button @click="selectAction('')"><span class="material-symbols-outlined">undo</span>Back</button>
            <span>Control Panel</span>
        </div>
        <div class="content">
            <div class="action-selection" v-if="this.actionSelected === ''">
                <button @click="selectAction('uploadVideo')">Upload Video</button>
                <br>
                <button @click="selectAction('createCourse')">Create Course</button>
            </div>
            <UploadVideo v-if="this.actionSelected === 'uploadVideo'"></UploadVideo>
        </div>
    </div>
</template>
<script>
import UploadVideo from '../components/admin-panel/UploadVideo.vue';

export default {
    name: 'AdminPanel',
    components: {
        UploadVideo
    },
    data() {
      return {
        actionSelected: '',
      }
    },
    methods: {
        selectAction(action){
            this.actionSelected = action;
        }
    },
}
</script>
<style scoped>
.container {
    width: 850px;
    height: 500px;
    background-color: aquamarine;
    margin: 0 auto;
    border-style: solid;
    border-width: 1px;
}
.title {
    background-color: rgb(241, 241, 241);
    border-style: solid;
    border-top: 0;
    border-left: 0;
    border-right: 0;
    border-width: 1px;
    padding-top: 6px;
    padding-bottom: 6px;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}
.title button {
    width: 80px;
    font-size: 14px;
    margin-left: 20px;
    text-align: left;
}
button span {
    padding-right: 12px;
}
span {
    font-size: 16px;
    vertical-align: middle;
}
</style>