<template>
  <div>
    <h1>用户界面</h1>
    <h1>{{ message }}</h1>
    <h2>{{ userId }}</h2>
  </div>
</template>


<script>
export default {
  name: "User",
  data() {
    return {
      message: "ahhTOu"
    };
  },
  created() {
    document.title = "用户";
  },
  computed: {
    userId() {
      return this.$route.params.userId;
    },
    msg() {
      return this.message;
    }
  },
  created() {
    console.log("用户被创建了");
  },
  destroyed() {
    console.log("用户被销毁了");
  }
};
</script>


<style scoped>
</style>