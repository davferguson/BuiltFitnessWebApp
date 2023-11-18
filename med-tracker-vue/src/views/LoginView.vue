<template>
    <div class="login-bg">
        <h1>Medicine Tracking App</h1>
        <div class="mainwindow">
            <LoginSelect id="login-select"/>
            <LoginForm id="login-form"/>
        </div>
    </div>
</template>

<script>
import LoginSelect from '../components/LoginSelect.vue';
import LoginForm from '../components/LoginForm.vue';

export default {
    name: 'login-view',
    components: {
        LoginSelect,
        LoginForm
    }
}
</script>

<style scoped>
.login-bg {
    background: url("../../public/images/login-background1.png") no-repeat center;
    background-size: cover;
    min-height: 100vh;
}
.mainwindow {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-auto-flow: column;
    place-items: center;
    gap: 40px;
    /* width: 940px; */
    margin-left: auto;
    margin-right: auto;
}
#login-select {
    justify-self: end;
}
#login-form {
    justify-self: start;
}
h1 {
    color: white;
    padding-top: 30px;
    padding-bottom: 40px;
    font-size: 40px;
}
</style>