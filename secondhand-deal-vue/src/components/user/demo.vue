<template>
  <div style="width: 100%; height: 30000px; background-color: red">
    <div style="background-color: #FFFFFF; line-height: 50px" v-if="show" class="d">
      <span>人才1</span>
      <span>人才2</span>
      <span>人才3</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "demo",
  data(){
    return{
      show: false,
    }
  },

  created() {


  },
  methods:{
    handleScroll() {
      // 页面滑动的距离
      let scrollTop = document.documentElement.scrollTop
      // 当页面滑动的距离大于300px时元素显示，否则不显示
      if (scrollTop >= 300) {
        this.show = true
      } else {
        this.show = false
      }
    }
  },

  mounted() {
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
  }

}
</script>

<style scoped>
.d{
  position: fixed;
  top: 0;
  z-index: 999;
}

</style>
