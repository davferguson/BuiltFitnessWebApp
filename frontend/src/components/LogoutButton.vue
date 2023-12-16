<template>
    <button @click="logout">Logout</button>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$store.commit("LOGOUT");
            this.$router.push("/");
        }
    }
};
</script>