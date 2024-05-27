<template>
  <div style="height: 40px; width: 100%; background-color: rgb(227, 228, 229)">
    <el-menu
      mode="horizontal"
      background-color="rgb(227, 228, 229)"

      active-text-color="#ffffff">
      <el-menu-item style=" margin: 0 500px;">
        <div>
          <el-link :underline="false">
            <span>您好，</span>
            <span style="color: red">请登录</span>
          </el-link>
          <el-link style="margin-left: 10px" :underline="false">免费注册</el-link>
        </div>

        <div style="display: none">


        </div>

      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">我的订单</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">我的商品</el-link>
      </el-menu-item>

      <el-menu-item>
        <el-link :underline="false">商家服务</el-link>
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
  name: "Common"
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
