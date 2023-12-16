import axios from 'axios';


export default {

    searchFdaApi(name){
        return axios.get('med_search/'+name)
    }
}