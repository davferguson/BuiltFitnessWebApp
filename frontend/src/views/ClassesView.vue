<template>
    <div class="classes">
        <br>
        <h1>Classes Page</h1>
        <input type="file" @change="storeFile($event.target.files)" />
        <h1>{{ file }}</h1>
        <button @click="uploadFile"> Upload </button>
    </div>
</template>
  
<script>
    import ApiService from '@/services/ApiService';
    import axios from 'axios';

    export default {
        name: 'ClassesView',
        components: {
        },
        data() {
        return {
          file: [],
        }
      },
      methods: {
        storeFile(currentFile) {
            console.log(currentFile[0]);
            this.file = currentFile[0];
        },
        uploadFile(){
            ApiService.createPresignedPutUrl(this.file.name).then(response => {
                let url = response.data;
                //TODO fix this
                delete axios.defaults.headers.common["Authorization"];
                const ax = axios.create({
                });
                ax.put(url, this.file);
            })
        }
    },
    }
</script>