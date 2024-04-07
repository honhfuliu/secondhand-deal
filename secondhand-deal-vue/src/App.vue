<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
    <router-view/>
  </div>
</template>

<script>
import DevicePixelRatio from "./utils/devicePixelRatio";
export default {
  name: 'App',
  data() {
    return {
    }
  },
  created() {
    new DevicePixelRatio().init()
  }
}
</script>

<style>

html,body,#app{
  height: 100%;
  width: 100%;



}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  /*margin-top: 60px;*/
}
</style>
