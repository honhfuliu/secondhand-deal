import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import Home from "../components/home/Home.vue"
import Common from "../components/common/Common.vue";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/Common',
      name: 'Common',
      component: Common
    }
  ]
})
