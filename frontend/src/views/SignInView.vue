<template>
    <div class="background">
        <div class="container">
            <img src="../../public/images/built-logo.png">
            <p>Sign in</p>
            <form @submit.prevent="login">
                <div class="input-group">
                    <input type="text" name="username" v-model="loginReq.username" required>
                    <label for="username">Username</label>
                </div>
                <div class="input-group">
                    <input type="password" name="password" v-model="loginReq.password" required> 
                    <label for="password">Password</label>
                </div>
                <div class="form-alertbox" v-if="invalidCredentials">
                    Invalid username or password
                </div>
                <div class="submit-btn">
                    <button type="submit">Sign in</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: 'SignIn',
    data() {
        return {
            loginReq: {
                username: "",
                password: ""
            },
            registerReq: {
                username: "",
                email: "",
                password: "",
                confirmPassword: "",
                role: "ROLE_DEFAULT"
            },
            invalidCredentials: false,
            isUsernameTaken: false,
            doPasswordsMatch: true,
            isValidEmail: true,
            formInputColor: "#eee",
            registerSuccess: false,
        }
    },
    methods: {
        login() {
            authService
            .login(this.loginReq)
            .then(response => {
                if(response.status == 200) {
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    this.$store.commit("SET_USER", response.data.user);
                    this.$router.push({ name: 'home' });
                }
            })
            .catch(error => {
            if(error.response != null){
                const response = error.response;

                if (response.status === 401) {
                    this.invalidCredentials = true;
                }
            }
            
            });
        },
        register() {
            this.isUsernameTaken = false;
            //eslint-disable-next-line
            if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.registerReq.email)){
                    this.isValidEmail = true;
            } else {
                this.isValidEmail = false;
            }
            if (this.registerReq.password != this.registerReq.confirmPassword) {
                this.doPasswordsMatch = false;
            } else {    
                this.doPasswordsMatch = true;       
                //eslint-disable-next-line
                if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.registerReq.email)){
                    authService
                    .register(this.registerReq)
                    .then((response) => {
                        if (response.status == 201) {
                            this.registerSuccess = true;
                            this.registerReq.username = '';
                            this.registerReq.email = '';
                            this.registerReq.password = '';
                            this.registerReq.confirmPassword = '';
                            this.$router.push({
                                path: '/',
                                // query: { registration: 'success' },
                            });
                        }
                    })
                    .catch((error) => {
                        const response = error.response;
                        if(response != null){
                            if (response.status === 400 && response.data.message === "User Already Exists.") {
                                this.isUsernameTaken = true;
                            }
                        }
                    });
                } 
            }
        },
        openLogin() {
            this.$store.commit("SET_LOGIN_SELECTION", "login");
            this.registerSuccess = false;
        }
    },
    computed: {
        selection: function() {
            return this.$store.state.loginSelection;
        }
    }
}
</script>

<style scoped>
.background {
    width: 100vw;
    height: 100vh;
    background-color: #231f20;
}
.container {
    width: 400px;
    height: 100vh;
    margin: 0 auto;
    background-color: #231f20;
    font-family: 'Rowdies', sans-serif;
    color: white;
    font-size: 2.2rem;
}
.container img {
    width: 80%;
    margin-top: 50px;
    margin-bottom: 30px;
}
form {
    margin-top: 50px;
    text-align: left;
    font-size: 1.0rem;
}
form label {
    display: block;
    color:#a3a3a3
}
input {
    width: 100%;
    background-color: #ffffff00;
    border-width: 1px;
    border-right: 0;
    font-size: 20px;
    margin-top: 10px;
    color: white;
}
input:focus {
    outline: none;
    border-color: rgb(255, 255, 255);
    border-top: 0;
    border-left: 0;
}
input:focus + label {
    color: rgb(255, 255, 255);
}
.input-group {
    display: flex;
    flex-direction: column-reverse;
    margin-bottom: 30px;
}
button {
    background: #ffffff;
    border: none;
    cursor: pointer;
    font-weight: 600;
    color: #000000;
    border-radius: 0;
    font-size: 1.1rem;
    width: 40%;
    padding-top: 14px;
    padding-bottom: 14px;
    margin-top: 30px;
}
button:hover {
    background: #a3a3a3;
}
.submit-btn {
    text-align: center;
}
.form-alertbox {
    border-style: solid;
    border-width: 1px;
    border-color: red;
    background-color: #fd696946;
    color: red;
    font-size: 14px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-left: 30px;
    margin-right: 30px;
    margin-bottom: 20px;
}
</style>