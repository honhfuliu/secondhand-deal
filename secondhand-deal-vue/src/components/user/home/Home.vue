<template>
  <div style="">
    <div class="divtop">
      <Common></Common>

      <div style="width: 100%; height: 80px; background-color: rgb(255, 68, 1); display: flex; margin-bottom: 10px">
        <div style="width: 25%">
          <h2 style="margin: 16px 166px; float: left; color: #FFFFFF; font-size: 34px; font-family:cursive">我的交易</h2>
        </div>

        <div style="width: 60%; display: flex">
          <div  @click="showInfo= 1">
            <h2 style="margin: 26px 18px; color: #FFFFFF; font-family: cursive">信息首页</h2>
          </div>
          <div @click="showInfo= 2">
            <h2 style="margin: 26px 0; color: #ffffff; font-family: cursive">账号设置</h2>
          </div>
        </div>

      </div>
    </div>


    <div style="height: 100%; width: 100%">
      <!--信息start-->
      <div v-show="showInfo === 1" style="width: 80%; height: 100%;margin: 135px auto">
        <el-container style="height: 100%">
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

              <el-menu-item>
                <span slot="title" class="span1">已发布的商品</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">我的购物车</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">已买到的宝贝</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">购买过的商家</span>
              </el-menu-item>

              <el-menu-item>
                <span slot="title" class="span1">我的收藏</span>
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
            <el-main style="margin: -30px 0">
              <router-view></router-view>
            </el-main>
          </el-container>
        </el-container>
      </div>
      <!--信息end-->

      <!--账号设置start-->
      <div v-show="showInfo === 2">
        <h1>人才2</h1>
      </div>
      <!--账号设置end-->

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

  }
}
</script>

<style scoped>
.divtop{
  position: fixed;
  top: 0;
  width: 100%;
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
