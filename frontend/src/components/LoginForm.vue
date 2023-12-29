<template>
    <div class="container">
        <div class="popup" :style="{'visibility': registerSuccess ? 'visible' : 'hidden'}">
            <div class="popup-content">
                <span class="material-symbols-outlined">task_alt</span>
                <h2>Success</h2>
                <p>Your account has been successfully created!</p>
                <button @click="openLogin">Login</button>
            </div>
        </div>
        <form v-if="selection == 'login'" @submit.prevent="login">
            <div v-if="selection == 'login'">
                <h3>Login Here</h3>
                <div class="input">
                    <span class="material-symbols-outlined">person</span>
                    <input type="text" placeholder="Username" v-model="loginReq.username" required>
                </div>
                <div class="input">
                    <span class="material-symbols-outlined">key</span>
                    <input type="password" placeholder="Password" v-model="loginReq.password" required> 
                </div>
                <div class="form-alertbox" v-if="invalidCredentials">
                    Invalid username or password
                </div>
                <div class="submit">
                    <button type="submit">Login</button>
                </div>
            </div>
        </form>
        <form v-if="selection == 'register'" @submit.prevent="register">
            <div v-if="selection == 'register'">
                <h3>Register Here</h3>
                <div class="input" :style="{'background-color': isUsernameTaken ? '#fd696946' : formInputColor}">
                    <span class="material-symbols-outlined">person</span>
                    <input type="text" placeholder="Username" v-model="registerReq.username" required>
                    <span class="material-symbols-outlined register-error-icon" v-if="isUsernameTaken">warning</span>
                </div>
                <div class="register-error" v-if="isUsernameTaken">
                    Username already exists
                </div>
                <div class="input" :style="{'background-color': !isValidEmail ? '#fd696946' : formInputColor}">
                    <span class="material-symbols-outlined">mail</span>
                    <input type="text" placeholder="Email" v-model="registerReq.email">
                    <span class="material-symbols-outlined register-error-icon" v-if="!isValidEmail">warning</span>
                </div>
                <div class="register-error" v-if="!isValidEmail">
                    Invalid email
                </div>
                <div class="input" :style="{'background-color': !doPasswordsMatch ? '#fd696946' : formInputColor}">
                    <span class="material-symbols-outlined">key</span>
                    <input type="password" placeholder="Password" v-model="registerReq.password" required> 
                    <span class="material-symbols-outlined register-error-icon" v-if="!doPasswordsMatch">warning</span>
                </div>
                <div class="input" :style="{'background-color': !doPasswordsMatch ? '#fd696946' : formInputColor}">
                    <span class="material-symbols-outlined">key</span>
                    <input type="password" placeholder="Confirm Password" v-model="registerReq.confirmPassword" required> 
                    <span class="material-symbols-outlined register-error-icon" v-if="!doPasswordsMatch">warning</span>
                </div>
                <div class="register-error" id="register-error-password" v-if="!doPasswordsMatch">
                    Passwords don't match
                </div>
                <div class="submit">
                    <button type="submit">Register</button>
                </div>
            </div>
        </form>
        <form v-if="selection == 'forgot'" @submit.prevent>
            <div v-if="selection == 'forgot'">
                <h3>Reset Password</h3>
                <p>
                    Enter your email address below and we'll send you an email with instructions.<br><br>
                    Need Help? Learn more about how to retrieve an existing account.
                </p>
                <div class="input">
                    <span class="material-symbols-outlined">mail</span>
                    <input type="text" placeholder="Email" required>
                </div>
                <div class="submit">
                    <button type="submit">Reset</button>
                </div>
            </div>
            
        </form>
    </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: 'loginForm',
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
                role: "ROLE_USER"
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
.container {
    height: 400px;
    width: 450px;
    background-color: white;
}
.input {
    display: flex;
    align-items: center;
    justify-content: center;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-left: 30px;
    margin-right: 30px;
    margin-bottom: 20px;
    background-color: v-bind('formInputColor');
}
.submit {
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 35px;
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
.register-error {
    margin-top: -16px;
    margin-bottom: 10px;
    margin-left: 30px;
    font-size: 12px;
    padding-top: 0px;
    padding-bottom: 0px;
    color: red;
    text-align: left;
}
.register-error-icon {
    color: red;
    margin-right: 0px;
}
.popup {
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
    visibility: hidden;
}
.popup-content {
    margin: auto;
    width: 350px;
    height: 420px;
    background-color: white;
    color: black;
    text-align: center;
    /* border-radius: 6px; */
    /* padding: 8px 0; */
    position: absolute;
    z-index: 1;
    /* left: 50%; */
    /* margin-left: -80px; */
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
}
#register-error-password {
    margin-bottom: -17px;
}
.popup-content span {
    font-size: 70px;
    color: rgba(47, 224, 47, 0.774);
    margin-top: 50px;
}
.popup-content h2 {
    font-size: 40px;
    color: rgba(47, 224, 47, 0.774);
    margin-top: 20px;
    font-weight: normal;
}
.popup-content p {
    margin-top: 80px;
    margin-left: 50px;
    margin-right: 50px;
    text-align: center;
    font-size: 14px;
    color: #00000094;
}
.popup-content button {
    background-color: rgba(47, 224, 47, 0.774);
    width: 75%;
    /* border-radius: 40px; */
}
p {
    margin-left: 30px;
    margin-right: 30px;
    margin-bottom: 20px;
    text-align: left;
}
input {
    border: none;
    outline: none;
    width: 100%;
    margin-right: 10px;
    background: transparent;
}
span {
    font-size: 18px;
    margin-right: 10px;
}
h3 {
    margin-top: 30px;
    margin-bottom: 30px;
    font-weight: normal;
    font-size: 25px;
}
button {
    background: #5161ce;
    border: none;
    cursor: pointer;
    font-weight: 600;
    color: #fff;
    border-radius: 0;
    font-size: 15px;
    letter-spacing: 2px;
    width: 100%;
    padding: 14px 30px;
}
@media (max-width: 575px) {
    .container {
        width: 250px;
    }
}
</style>