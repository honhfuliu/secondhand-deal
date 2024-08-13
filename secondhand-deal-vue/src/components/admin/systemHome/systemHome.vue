<template>
  <div style="height: 100%; width: 100%">
    <el-container style="height: 100%">
      <el-header style="background-color: dodgerblue">
        <div>
          <el-row>
            <el-col span="1" style="height: 45px; width:45px; margin-top: 10px">
              <i class="icon iconfont icon-lanling-ershoujiaoyi" style="font-size: 45px"></i>
            </el-col>

            <el-col span="4" style="padding-right: 140px; font-size: 20px; font-weight: 600; font-family: cursive;color: #ffffff;">
              <p>二手交易管理系统</p>
            </el-col>

            <el-col span="10">
              <el-menu
                class="el-menu-demo"
                mode="horizontal"
                background-color="#1E90FFFF"
                :router="true">

                <el-menu-item index="/login">
                  <i class="icon iconfont icon-diannao c"></i>
                  <span class="c">首页</span>
                </el-menu-item>

                <el-menu-item index="/login">
                  <i class="icon iconfont icon-shezhi c" ></i>
                  <span class="c">设置</span>
                </el-menu-item>

                <el-menu-item index="/login">
                  <i class="icon iconfont icon-shuaxin c" ></i>
                  <span class="c">刷新</span>
                </el-menu-item>
              </el-menu>
            </el-col>

            <el-col span="9">
              <el-menu
                class="el-menu-demo"
                mode="horizontal"
                background-color="#1E90FFFF"
                style="display: flex; justify-content: end"
                :router="true">

                <el-menu-item index="/login">
                  <i class="icon iconfont icon-tuichu c" ></i>
                </el-menu-item>
                <el-menu-item>
                  <img src="/static/6-666.png" style="border-radius: 50%; width: 58px; height: 58px">
                </el-menu-item>
              </el-menu>
            </el-col>

          </el-row>
        </div>
      </el-header>
      <el-container>
        <el-aside width="250px" style="overflow-y: hidden">
          <el-menu
            :default-openeds="defaultOpenedsArray"
            default-active="2"
            class="el-menu-vertical-demo"
            background-color="#2F2222"
            text-color="#fff"
            active-text-color="#ffd04b"
            :unique-opened="true"
            :router="true">

            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>系统管理</span>
              </template>

              <el-menu-item index="/systemManage/systemHome/systemLog">
                <i class="el-icon-menu"></i>
                <span slot="title">日志管理</span>
              </el-menu-item>


            </el-submenu>

            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>商品管理</span>
              </template>
              <el-menu-item index="/systemManage/systemHome/commodityClassification" style="margin-left: 20px">
                <i class="el-icon-menu"></i>
                <span slot="title">商品分类</span>
              </el-menu-item>

              <el-menu-item index="" style="margin-left: 20px">
                <i class="el-icon-menu"></i>
                <span slot="title">所有商品</span>
              </el-menu-item>
              <el-menu-item index="/systemManage/systemHome/commodityExamine" style="margin-left: 33px">
                <i class="el-icon-menu"></i>
                <span slot="title">待审核商品</span>
              </el-menu-item>
            </el-submenu>

          </el-menu>


        </el-aside>
        <el-main class="main-el" style="background-color: #eeeded; overflow-y: hidden; overflow-x: hidden">
          <div class="mianbao">

          </div>

          <div class="main-el-div">
            <router-view></router-view>
          </div>

        </el-main>

      </el-container>
    </el-container>
  </div>

</template>

<script>
export default {
  name: "systemHome",
  methods: {
  }
}
</script>

<style scoped>
.main-el-div{
  width: 100%;
  height: 100%;
  background-color: #ffffff;
  margin-left: 20px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;
}


.mianbao {
  height: 50px;
  background-color: #ffffff;
  margin-bottom: 20px;
  /*box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;*/
  box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;
}

.el-aside {
  display: block;
  position: relative;
  overflow-y: scroll;
  background-color: #324157 ;
}

.main-el{
  margin: 0;
  padding: 0;
  /*margin-left: 20px;*/
  background-color: #ffffff;
}

.c{
  color: #ffffff;
  font-size: 18px;
}

</style>
