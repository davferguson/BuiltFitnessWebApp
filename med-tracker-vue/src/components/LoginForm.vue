<template>
    <form class="container" @submit.prevent>
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
            <div class="submit">
                <button type="submit" @click="login">Login</button>
            </div>
        </div>
        <div v-if="selection == 'register'">
            <h3>Register Here</h3>
            <div class="input">
                <span class="material-symbols-outlined">person</span>
                <input type="text" placeholder="Username" required>
            </div>
            <div class="input">
                <span class="material-symbols-outlined">mail</span>
                <input type="text" placeholder="Email" required>
            </div>
            <div class="input">
                <span class="material-symbols-outlined">key</span>
                <input type="password" placeholder="Password" required> 
            </div>
            <div class="input">
                <span class="material-symbols-outlined">key</span>
                <input type="password" placeholder="Confirm Password" required> 
            </div>
            <div class="submit">
                <button type="submit">Register</button>
            </div>
        </div>
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
            invalidCredentials: false
        }
    },
    methods: {
        login() {
            authService
            .login(this.loginReq)
            .then(response => {
                if(response.status == 200) {
                    console.log("YES");
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    this.$store.commit("SET_USER", response.data.user);
                    this.$router.push("/home");
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
    background-color: #eee;
}
.submit {
    margin-left: 30px;
    margin-right: 30px;
    margin-top: 35px;
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
    background: #00ad45;
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