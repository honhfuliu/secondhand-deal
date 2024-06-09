import Vue from 'vue'
import Router from 'vue-router'
import Index from "../components/user/Index/Index.vue";
import Home from "../components/user/home/Home.vue"
import Common from "../components/user/common/Common.vue"
import Register from "../components/user/login/register.vue";
import Login from "../components/user/login/login.vue";
import RecoverPassword from "../components/user/login/recoverPassword.vue";
import SystemLogin from "../components/admin/systemLogin/systemLogin.vue";
import SystemHome from "../components/admin/systemHome/systemHome.vue";

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    meta: {
      roles: "user"
    }
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: "/acquiesce",

    children: [
      // home默认显示
      {
        path: '/acquiesce',
        name: 'Acquiesce',
        component: () => import("../components/user/home/Acquiesce/Acquiesce.vue"),
        meta: {
          title: "默认页面"
        }
      },

      // 已发布商品查询路由
      {
        path: "/commodityShow",
        name: "CommodityShow",
        component: ()=> import("../components/user/home/commodityShow/commodityShow.vue"),
        meta: {
          title: "商品查询"
        }
      },
    ]

  },

  //商品发布路由
  {
    path: '/commodity',
    name: 'CommodityRelease',
    component: () => import("../components/user/home/CommodityRelease/CommodityRelease.vue"),
    props: true,
    meta: {
      title: "商品发布"
    }
  },


  {
    path: '/Common',
    name: 'Common',
    component: Common,
  },
  {
    path: '/reg',
    name: 'Register',
    component: Register,
    meta: {
      title: "注册"
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      title: "登录"
    }
  },
  {
    path: '/recover',
    name: 'RecoverPassword',
    component: RecoverPassword,
    meta: {
      title: "密码找回"
    }
  },



  // 后端管理路由
  {
    path: "/systemManage",
    component: {render: (e) => e("router-view")},
    children: [
      {
        path: "adminLogin",
        component: SystemLogin,
        meta: {
          SystemLogin: true,
        },
      },
      {
        path: "systemHome",
        component: SystemHome,
        meta: {
          SystemHome: true,
        },
        children: [
          {
            path: "systemLog",
            component: () => import("../components/admin/systemLog/systemLog.vue")
          },
          {
            path: "commodityClassification",
            component: () => import("../components/admin/commodityClassification/commodityClassification.vue")
          }

        ]

      }
    ]
  }

]

const router =  new Router({
  mode: 'history',
  routes,

})

export default router

/*
router.beforeEach((to, from, next) => {
  console.log(to.path)
  if (to.matched.some(route => route.path.startsWith('/systemManage'))) {
    if (to.path === "/systemManage/adminLogin") {
      next()
    } else {
      let adminToken = sessionStorage.getItem("adminToken");
      if (adminToken) {
        next()
      } else {
        next("/systemManage/adminLogin")
      }
    }
  } else if (to.path === "/" || to.path === "/reg" || to.path === "/recover") {
    next()
  } else {
    let Token = sessionStorage.getItem("Token");
    if (Token) {
      next()
    }else {
      next("/login")
    }
  }
})*/


