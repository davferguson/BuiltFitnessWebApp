<template>
    <div class="container">
            <input type="radio" name="action" id="option1" value="login" v-model="selection" @change="onSelect()" checked>
            <label for="option1">
                <span class="material-symbols-outlined">account_circle</span>
                Login
            </label>

            <input type="radio" name="action" id="option2" value="register" v-model="selection" @change="onSelect()">
            <label for="option2">
                <span class="material-symbols-outlined">app_registration</span>
                Register
            </label>

            <input type="radio" name="action" id="option3" value="forgot" v-model="selection" @change="onSelect()">
            <label for="option3">
                <span class="material-symbols-outlined">lock</span>
                Forgot Password?
            </label>
    </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
    name: "loginSelect",
    data() {
        return {
            selection: "login"
        }
    },
    methods: {
        onSelect() {
            this.$store.commit("SET_LOGIN_SELECTION", this.selection)
        }
    },
    computed: {
        ...mapState(['loginSelection']),
    },
    watch: {
        loginSelection: {
            handler(newVal){
                this.selection = newVal;
            }
        }
    },
    created() {
        this.$store.commit("SET_LOGIN_SELECTION", this.selection)
    }
}
</script>

<style scoped>
.container {
    width: 450px;
    background-color: white;
    font-size: 17px;
}
input {
    display: none;
}
input:checked + label {
  background: #5161ce;
  color: #fff;
}
span {
    font-size: 40px;
}
label {
  display: flex;
  flex-direction: column;
  gap: 6px;
  cursor: pointer;
  width: 100%;
  padding-top: 34px;
  padding-bottom: 34px;
}

@media (max-width: 950px) {
    .container {
        width: 300px;
    }
}
@media (max-width: 800px) {
    .container {
        width: 200px;
    }
}
@media (max-width: 700px) {
    .container {
        width: 100px;
        font-size: 13px;
    }
}
</style>