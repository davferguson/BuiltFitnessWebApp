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
    // import { S3Client, PutObjectCommand } from "@aws-sdk/client-s3"; // ES Modules import

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
        awsUpload(){
            console.log("aws start:");
            const { S3Client,PutObjectCommand } = require("@aws-sdk/client-s3");
            console.log("in clinet:");
            let client = new S3Client({
                region:'us-east-1',
                credentials:{
                    accessKeyId:'',
                    secretAccessKey:''
                }
            });
            console.log("aws miasdasd:");
            (async () => {
                const input = {
                    "Body": this.file,
                    "Bucket": "built-video-secured",
                    "Key": "uptest7" + ".json", //json is file type
                    // "ServerSideEncryption": "AES256",
                    // "Tagging": "key1=value1&key2=value2"
                };
                console.log("aws mid:");
                const command = new PutObjectCommand(input);
                const response = await client.send(command);
                console.log(response);
            })();
            console.log("aws finished:");
        },
        storeFile(currentFile) {
            console.log(currentFile[0]);
            this.file = currentFile[0];
            // console.log(this.file);
        },
        uploadFile(){
            ApiService.createSignedUrl(this.file.name).then(response => {
              this.uploadURL = response.data;
              console.log(response.data);

              this.awsUpload();
              
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