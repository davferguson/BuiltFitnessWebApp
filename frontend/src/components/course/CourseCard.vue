<template>
    <div>
        <h2>{{ course.course_name }}</h2>
        <li v-for="item in course.description" :key="item">{{ item }}</li>
        <h4>${{ course.price }}</h4>
    </div>
</template>

<script>

export default {
    name: 'course-card',
    props: {
        course: Object,
    },
    data() {
        return {
            
        }
    }
}
</script>