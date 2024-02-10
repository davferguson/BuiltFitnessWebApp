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

    export default {
        name: 'ClassesView',
        components: {
        },
        data() {
        return {
          file: [],
          uploadURL: ''
        }
      },
      methods: {
        storeFile(currentFile) {
            console.log(currentFile[0]);
            this.file = currentFile[0];
            // console.log(this.file);
        },
        uploadFile(){
            ApiService.createSignedUrl(this.file.name).then(response => {
              this.uploadURL = response.data;
              console.log(response.data);
          })
          .catch(error => {
              if(error.response != null){
                  if (error.response.status === 401) {
                    console.log('No  Entry');
                  }
              }
              
              });
        }
    },
    }
</script>