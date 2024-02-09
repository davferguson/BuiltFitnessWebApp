<template>
    <div class="programs">
      <br>
      <h1>Online Programs Page</h1>
      <h1>From Cloudfront</h1>
      <h2 v-if="!hasAccess">You must be signed in to view this.</h2>
      <video v-if="hasAccess" :key="videoSrc" class="video" ref="video" controls>
        <source :src="videoSrc" type="video/mp4">
      </video>
    </div>
  </template>
  
  <script>
  import ApiService from '@/services/ApiService';
  
  export default {
      name: 'OnlineProgramsView',
      components: {
      },
      data() {
        return {
          videoSrc: '',
          videoObj: 'fish.mp4',
          hasAccess: true,
        }
      },
      methods: {
  
      },
      created() {
          ApiService.createSignedUrl(this.videoObj).then(response => {
              this.videoSrc = response.data;
              this.hasAccess = true;
          })
          .catch(error => {
              if(error.response != null){
                  if (error.response.status === 401) {
                      this.hasAccess = false;
                  }
              }
              
              });
      }
    };
  </script>