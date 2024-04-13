<template>
    <div class="course-container">
        <div class="name">{{ course.course_name }}</div>
        <div class="thumbnail"><img :src="imgSrc"></div>
        <div class="desc"><li v-for="item in course.description" :key="item">{{ item }}</li></div>
        <h4 class="price">${{ course.price }}</h4>
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
            imgSrc: '/images/course/' + this.course.image_key + '.jpg'
        }
    }
}
</script>

<style scoped>
.course-container {
    margin: auto;
    margin-bottom: 10px;
    height: 300px;
    width: 600px;
    border-style: solid;
    border-width: 2px;

    display: grid;
    grid-template-columns: 1.5fr 1fr;
    grid-template-rows: 1fr 4fr 1fr;
    grid-template-areas: 
        'name empty'
        'thumbnail desc'
        'thumbnail price';
    align-items: center;
}

.name {
    grid-area: name;
}

.thumbnail {
    grid-area: thumbnail;
}

.desc {
    grid-area: desc;
}

.price {
    grid-area: price;
}

img {
    max-width:90%;
    max-height:90%;
}
</style>