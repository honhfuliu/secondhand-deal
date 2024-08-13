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

  // 个人账号信息设置
  {
    path: '/settings',
    name: 'Settings',
    component: ()=> import("../components/user/accountSettings/AccountSettings.vue"),
    redirect: "/acquiesce1",

    children: [
      // home默认显示
      {
        path: '/acquiesce1',
        name: 'Acquiesce1',
        component: () => import("../components/user/accountSettings/Acquiesce1/Acquiesce1.vue"),
        meta: {
          title: "默认页面"
        }
      },

      // 个人信息修改
      {
        path: "/updateInfo",
        name: "UpdateInfo",
        component: () => import("../components/user/accountSettings/updateInfo/UpdateInfo.vue"),
        meta: {
          title: "个人信息"
        }
      },

      // 个人密码修改页面
      {
        path: "/updatePasswd",
        name: "UpdatePassword",
        component: () => import("../components/user/accountSettings/updatePassword/updatePassword.vue"),
        meta: {
          title: "密码修改"
        }
      },

      // 我的收货地址
      {
        path: "/address",
        name: "Address",
        component: () => import("../components/user/accountSettings/MyAddress/MyAddress.vue"),
        meta: {
          title: "我的收货地址"
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

  // 用户聊天
  {
    path: "/chat",
    name: "UserChat",
    component: () => import("../components/user/UserChat/userChat.vue"),
    meta: {
      title: "用户聊天"
    }
  },


  {
    path: "/demo",
    component: () => import("../components/user/demo.vue")
  },

  // 商品详情路由
  {
    path: '/commodityDetails',
    name: 'CommodityDetails',
    component: () => import("../components/user/commodityDisplay/commodityDisplay.vue"),
    meta: {
      title: "商品详情"
    }
  },

  // 商品搜索路由
  {
    path: "/search",
    name: "Search",
    component: () => import("../components/user/commoditySearch/commoditySearch.vue"),
    meta: {
      title: "商品搜索",
    }

  },

  // 我的收藏页面跳转
  {
    path: "/collect",
    name: "Collect",
    component: () => import("../components/user/CollectCommodity/collectCommodity.vue"),
    meta: {
      title: "我的收藏"
    }
  },

  // 购物车
  {
    path: "/shoppingCart",
    name: "ShoppingCart",
    component: () => import("../components/user/shoppingCart/shoppingCart.vue"),
    meta: {
      title: "我的购物车"
    }
  },

  // 创建购物订单
  {
    path: "/createOrder",
    name: "CreateShoppingOrder",
    component: ()=> import("../components/user/createShoppingOrder/createShoppingOrder.vue"),
    meta: {
      title: "创建订单"
    }
  },

  // 订单支付页面
  {
    path: "/orderPay",
    name: "OrderPay",
    component: ()=> import("../components/user/orderPay/orderPay.vue"),
    meta: {
      title: "订单支付"
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
            component: () => import("../components/admin/systemLog/systemLog.vue"),
            name: 'SystemLog',
            meta: {
              title: "日志管理"
            }
          },
          {
            path: "commodityClassification",
            component: () => import("../components/admin/commodityClassification/commodityClassification.vue"),
            name: 'CommodityClassification',
            meta: {
              title: "分类添加"
            }
          },
          {
            path: "commodityExamine",
            component: () => import("../components/admin/commodityExamine/commodityExamine.vue"),
            name: 'CommodityExamine',
            meta: {
              title: "商品审核"
            }

          },

        ]

      }
    ]
  },
  // 商品审核页面
  {
    path: '/commodityVerify',
    name: 'CommodityVerify',
    component: () => import("../components/admin/commodityExamine/commodityVerifyDisplay/commodityVerifyDisplay.vue"),
    meta: {
      title: "商品审核页面"
    }
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


