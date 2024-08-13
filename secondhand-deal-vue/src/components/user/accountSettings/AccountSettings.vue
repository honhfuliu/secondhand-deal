<template>
  <div>
    <div class="divtop">
      <Common></Common>

      <div style="width: 100%; height: 80px; background-color: rgb(255, 68, 1); display: flex; margin-bottom: 10px">
        <div style="width: 25%">
          <h2 style="margin: 16px 166px; float: left; color: #FFFFFF; font-size: 34px; font-family:cursive">我的交易</h2>
        </div>

        <div style="width: 60%; display: flex">
          <div  @click="showInfo= 1">
            <h2 style="margin: 26px 18px; color: #FFFFFF; font-family: cursive; cursor: pointer;" @click="skipRouterPath('Home')">信息首页</h2>
          </div>
          <div @click="showInfo= 2">
            <h2 style="margin: 26px 0; color: #ffffff; font-family: cursive; cursor: pointer;">账号设置</h2>
          </div>
        </div>

      </div>
    </div>


    <!--账号设置start-->
    <div style="width: 80%;margin: 135px auto;">
      <el-container >
        <el-aside width="135px">
          <el-menu
            background-color="#FFFFFF"
            text-color="#000000FF"
            :router="true"
            style="position: fixed;"
          >
            <el-menu-item>
              <span slot="title" style="font-size: 18px">账号管理</span>
            </el-menu-item>

            <el-menu-item index="updateInfo">
              <span slot="title" class="span1" >个人资料</span>
            </el-menu-item>

            <el-menu-item index="updatePasswd">
              <span slot="title" class="span1">密码修改</span>
            </el-menu-item>

            <el-menu-item>
              <span slot="title" class="span1" >手机号绑定</span>
            </el-menu-item>

            <el-menu-item index="address">
              <span slot="title" class="span1">收货地址</span>
            </el-menu-item>

            <el-menu-item>
              <span slot="title" class="span1">购买过的商家</span>
            </el-menu-item>

            <el-menu-item>
              <span slot="title" class="span1" >我的收藏</span>
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
    <!--账号设置end-->
  </div>
</template>

<script>
import Common from "../common/HomeCommon.vue";
export default {
  name: "AccountSettings",
  components: {Common},

  data(){
    return{

    }
  },

  methods:{
    skipRouterPath(path){
      this.$router.push({name: path})
    },
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
