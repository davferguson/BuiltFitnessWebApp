import axios from 'axios';


export default {

    searchFdaApi(name){
        return axios.get('med_search/'+name)
    }, 
    createSignedUrl(url){
        return axios.get('/signed_url/'+url)
    },
    listObjects(){
        return axios.get('/list_bucket')
    }
}