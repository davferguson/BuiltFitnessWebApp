<template>
    <div>
        <form>
                <div class="input-group">
                    <input type="text" name="title">
                    <label for="title">Title</label>
                </div>
                <div class="input-group">
                    <input type="text" name="description"> 
                    <label for="description">Description</label>
                </div>
                <div class="submit-btn">
                    <button type="submit">Sign in</button>
                </div>
            </form>
    </div>
</template>
<script>

export default {
  name: 'AdminView',
  components: {
  },
  data() {
    return {
    }
  },
  methods: {

  },
};
</script>