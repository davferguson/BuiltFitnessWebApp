<template>
    <div>
        <h3>{{ course.course_name }}</h3>
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