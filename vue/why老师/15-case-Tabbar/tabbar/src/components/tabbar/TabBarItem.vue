<template>
  <div class="tab-bar-item" @click="itemClick">
    <div v-if="!isActive">
      <slot name="item-icon"></slot>
    </div>
    <div v-else>
      <slot name="item-icon-active"></slot>
    </div>
    <div :class="{active:isActive}">
      <slot name="item-text"></slot>
    </div>
  </div>
</template>


<script>
export default {
  name: "TabBarItem",
  props:{
    path:String
  },
  data() {
    return {
      // isActive: false
    };
  },
  computed: {
    isActive() {
      //indexof不为-1时没有找到
      return this.$route.path.indexOf(this.path) !== -1
    }
  },
  methods: {
    itemClick() {
     if(this.$route.path != this.path){
       this.$router.replace(this.path)
     }
    }
  },
};
</script>


<style scoped>
.tab-bar-item {
  flex: 1;
  text-align: center;
  font-size: 10px;

  /* background: #f6f6f6; */
}
.tab-bar-item img {
  width: 24px;
  margin-top: 3px;
  vertical-align: -webkit-baseline-middle;
}
</style>