import axios from 'axios';


export default {

    getAllCourses() {
        return axios.get('/courses')
    },
}