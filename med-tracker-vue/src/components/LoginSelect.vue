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
  background: #00ad45;
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
</style>