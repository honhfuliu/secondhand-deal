// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios";
import '../src/assets/font_icon/iconfont.css'
import TreeTable from 'vue-table-with-tree-grid'




Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.component('tree-table', TreeTable)

axios.defaults.baseURL = "http://localhost:8989"
// 将axios挂载到vue对象中
Vue.prototype.$axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
