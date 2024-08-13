<template>
  <div style="height: 40px; width: 100%; background-color: #f5f5f5">
    <el-menu
      mode="horizontal"
      background-color="rgb(227, 228, 229)"

      active-text-color="#ffffff">
      <el-menu-item style=" margin: 0 500px;">
        <div>
          <el-link :underline="false">
            用户名称00001
          </el-link>
        </div>

        <div style="display: none">


        </div>

      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false" href="/">首页</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">购物车</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">收藏夹</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">网站导航</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false" type="red">网站无障碍</el-link>
      </el-menu-item>

    </el-menu>

  </div>

</template>

<script>
export default {
  name: "HomeCommon"
}
</script>

<style scoped>
.el-menu--horizontal>.el-menu-item {
  height: 40px;
  line-height: 40px;
}




.el-menu-item:hover {
  background-color: rgb(227, 228, 229) !important;;
  color: red !important;;
}


</style>
