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

axios.interceptors.response.use(
  function(response) {
    // console.log(response.data)
    // 如果响应是成功的状态码，直接返回响应数据
    if (response.data.code === 2002 || response.data.code === 2001 || response.data.code === 2004 || response.data.code === 5008){
      const role = sessionStorage.getItem("Role")
      console.log(role)
      if (role === "admin"){
        alert(response.data.message)
        router.push({ name: 'AdminLogin' });
        return false;
      }else {
        alert(response.data.message)
        router.push({ path: '/login' });
        return false;
      }

    }
    return response;
  },
);


axios.defaults.baseURL = "http://ip:8986"
// 将axios挂载到vue对象中
Vue.prototype.$axios = axios

// axios 拦截器配置


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
