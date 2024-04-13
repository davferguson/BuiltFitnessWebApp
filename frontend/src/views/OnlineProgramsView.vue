<template>
  <div class="programs">
    <br>
    <h1>Online Programs Page</h1>
    <!-- <h1>From Cloudfront</h1>
    <h2 v-if="!hasAccess">You must be signed in to view this.</h2>
    <video v-if="hasAccess" :key="videoSrc" class="video" ref="video" controls>
      <source :src="videoSrc" type="video/mp4">
    </video> -->
    <CourseCard v-bind:course="course" v-for="course in allCourses" v-bind:key="course.course_id" />
  </div>
</template>
  
<script>
  // import ApiService from '@/services/ApiService';
  import CourseService from '@/services/CourseService';
  import CourseCard from '../components/course/CourseCard.vue';

  export default {
      name: 'OnlineProgramsView',
      components: {
        CourseCard
      },
      data() {
        return {
          allCourses: []
          
          // videoSrc: '',
          // videoObj: 'fish.mp4',
          // hasAccess: true,
        }
      },
      methods: {

      },
      created() {
        CourseService.getAllCourses().then(response => {
          this.allCourses = response.data;
        })
          // ApiService.createSignedUrl(this.videoObj).then(response => {
          //     this.videoSrc = response.data;
          //     console.log(response.data);
          //     this.hasAccess = true;
          // })
          // .catch(error => {
          //     if(error.response != null){
          //         if (error.response.status === 401) {
          //             this.hasAccess = false;
          //         }
          //     }
              
          // });

          // ApiService.listObjects().then(response => {
          //   console.log(response.data);
          // }).catch(error => {
          //   if(error.response != null){
          //         console.log('failed');
          //     }
          // });
      }
    };
</script>