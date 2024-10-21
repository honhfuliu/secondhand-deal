<template>
  <div>
    <div class="divtop">
      <Common></Common>

      <div style="width: 100%; height: 80px; background-color: rgb(255, 68, 1); display: flex; margin-bottom: 10px">
        <div style="width: 25%">
          <h2 style="margin: 16px 166px; float: left; color: #FFFFFF; font-size: 34px; font-family:cursive">我的交易</h2>
        </div>

        <div style="width: 60%; display: flex">
          <div>
            <h2 style="margin: 26px 18px; color: #FFFFFF; font-family: cursive; cursor: pointer;">信息首页</h2>
          </div>
          <div>
            <h2 style="margin: 26px 0; color: #ffffff; font-family: cursive; cursor: pointer;" @click="skipRouterPath('Settings')">账号设置</h2>
          </div>
        </div>

      </div>
    </div>


    <div>
      <!--信息start-->
      <div v-show="showInfo === 1" style="width: 80%; margin: 135px auto; ">
        <el-container >
          <el-aside width="135px">
            <el-menu
              background-color="#FFFFFF"
              text-color="#000000FF"
              :router="true"
              style="position: fixed;"
            >
              <el-menu-item>
                <span slot="title" style="font-size: 18px">全部功能</span>
              </el-menu-item>

              <el-menu-item index="/commodity">
                <span slot="title" class="span1">商品发布</span>
              </el-menu-item>

              <el-menu-item index="commodityShow">
                <span slot="title" class="span1">已发布的商品</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1" @click="skipWindow('/shoppingCart')">我的购物车</span>
              </el-menu-item>

              <el-menu-item index="/order">
                <span slot="title" class="span1">已买到的宝贝</span>
              </el-menu-item>

              <el-menu-item  index="awaitingShipment">
                <span slot="title" class="span1">待发货订单</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1" @click="skipWindow('/collect')">我的收藏</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">评价管理</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">退款维权</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">我的足迹</span>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-container>
            <el-main style="margin: -30px 0; ">
              <router-view></router-view>
            </el-main>
          </el-container>
        </el-container>
      </div>
      <!--信息end-->
    </div>

  </div>
</template>

<script>
import Common from "../common/HomeCommon.vue";

export default {
  name: "home",
  components: {Common},
  data(){
    return {
      showInfo: 1,
    }
  },

  created() {
  },

  methods:{
    skipRouterPath(path){
      this.$router.push({name: path})
    },

    // 点击跳转到新的页面
    skipWindow(path) {
     const { href } = this.$router.resolve({
       path: path
     })
      window.open(href)
    }

  }
}
</script>

<style scoped>
.divtop{
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 999;
}

.el-menu-item, .el-submenu__title {
  height:41px;
  line-height: 23px;
}

.span1{
  font-family: monospace;
  font-size: 15px;
}

.el-menu-item:hover {
  background-color: #FFFFFF !important;;

}




</style>
