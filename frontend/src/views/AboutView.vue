<template>
  <div class="about">
    <h1>From Cloudfront</h1>
    <video :key="videoSrc" class="video" ref="video" controls>
      <source :src="videoSrc" type="video/mp4">
    </video>
  </div>
</template>

<script>
import ApiService from '@/services/ApiService';

export default {
    components: {
    },
    data() {
      return {
        videoSrc: '',
        videoObj: 'fish.mp4'
      }
    },
    methods: {

    },
    created() {
        ApiService.createSignedUrl(this.videoObj).then(response => {
            this.videoSrc = response.data;
        })
    }
  };
</script>