<template>
    <h2>{{searchResult}}</h2>
</template>

<script>
import ApiService from '@/services/ApiService';

export default {
    name: "med-search",
    components: {
    },
    data() {
      return {
        searchResult: []
      }
    },
    methods: {
      searchMed() {
        ApiService.searchFdaApi('vyvanse').then(response => {
            this.searchResult = response.data;
        })
      },
    },
    created() {
        ApiService.searchFdaApi('vyvanse').then(response => {
            this.searchResult = response.data;
        })
    }
  };
</script>