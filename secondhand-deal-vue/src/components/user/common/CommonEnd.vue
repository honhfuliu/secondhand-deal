<template>
  <div style="width: 100%; height: 600px;">
    <div style="width: 100%; height: 120px; background-color: #3399ff; display: flex; justify-content: center; margin-bottom: 20px">
      <dl style="display: flex; margin-right: 40px">
        <dt style="margin-top: 13px">
          <i class="el-icon-platform-eleme" style="font-size: 64px"></i>
        </dt>
        <dd style="margin: 15px 15px">
          <h2>游戏交易，应有尽有</h2>
          <p>关于商品你想要的，这里都有</p>
        </dd>
      </dl>

      <dl style="display: flex; margin-right: 40px">
        <dt  style="margin-top: 13px">
          <i class="el-icon-s-goods" style="font-size: 64px"></i>
        </dt>
        <dd style="margin: 15px 15px">
          <h2>交易安全 资金保障</h2>
          <p>保障交易安全的事，我们都做</p>
        </dd>
      </dl>

      <dl style="display: flex; margin-right: 40px">
        <dt  style="margin-top: 13px">
          <i class="el-icon-s-custom" style="font-size: 64px"></i>
        </dt>
        <dd style="margin: 15px 15px">
          <h2>7*24小时 专属客服</h2>
          <p>时时刻刻想联系我，客服都在</p>
        </dd>
      </dl>

      <dl style="display: flex;  margin-right: 40px">
        <dt  style="margin-top: 13px">
          <i class="el-icon-goblet-square-full" style="font-size: 64px"></i>
        </dt>
        <dd style="margin: 15px 15px">
          <h2>发货快 安全可靠</h2>
          <p>我是值得信赖平台，大家都造</p>
        </dd>
      </dl>

    </div>
    <div class="divider"></div>

    <div style="display: flex; justify-content: center">
      <dl class="dl1">
        <dt class="dt1">账号操作</dt>
        <dd class="dd1">
          <p><el-link>如何注册</el-link></p>
          <p><el-link>忘记密码</el-link></p>
          <p><el-link>实名认证</el-link></p>
          <p><el-link>快捷登录</el-link></p>

        </dd>
      </dl>

      <dl class="dl1">
        <dt class="dt1">买家指南</dt>
        <dd class="dd1">
          <p><el-link>挑选商品</el-link></p>
          <p><el-link>支付订单</el-link></p>
          <p><el-link>发货进度</el-link></p>
          <p><el-link>确认收货</el-link></p>
        </dd>
      </dl>

      <dl class="dl1">
        <dt class="dt1">卖家指南</dt>
        <dd class="dd1">
          <p><el-link>服务准入</el-link></p>
          <p><el-link>发布商品</el-link></p>
          <p><el-link>如何发货</el-link></p>
          <p><el-link>保障图标</el-link></p>
        </dd>
      </dl>

      <dl class="dl1">
        <dt class="dt1">交易安全</dt>
        <dd class="dd1">
          <p><el-link>谨防钓鱼</el-link></p>
          <p><el-link>谨防刷单</el-link></p>
          <p><el-link>网络退款</el-link></p>
          <p><el-link>私下交易</el-link></p>
        </dd>
      </dl>

      <dl class="dl1">
        <dt class="dt1">服务中心</dt>
        <dd class="dd1">
          <p><el-link>我要咨询</el-link></p>
          <p><el-link>我要建议</el-link></p>
          <p><el-link>我要投诉</el-link></p>
          <p><el-link>真假客服</el-link></p>
        </dd>
      </dl>




    </div>
    <div class="divider"></div>

    <div style="margin-top: 20px; display: flex;justify-content: center">
      <el-menu
        mode="horizontal"
        background-color="#f3f6f8"

        active-text-color="#ffffff">
        <el-menu-item>
          <el-link :underline="false">关于二手交易 <span>|</span></el-link>
        </el-menu-item>
        <el-menu-item>
          <el-link :underline="false">联系我们 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">免责声明 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">招聘精英 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">家长监护 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">商户合作 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">网站地址 <span>|</span></el-link>
        </el-menu-item>

        <el-menu-item>
          <el-link :underline="false">平台协议 <span>|</span></el-link>
        </el-menu-item>

      </el-menu>
    </div>
  </div>

</template>

<script>
export default {
  name: "CommonEnd.vue"
}
</script>

<style scoped>

.el-menu-item *{
  font-size: 15px;
}

span {
  margin: 0 18px;
  font-size: 18px;
}


.dl1 {
  margin: 25px 130px;
}
.dd1{
  margin: auto;
}
.dt1{
  font-size: 19px;
  margin-bottom: 20px;
}
.dd1 p{
  margin-bottom: 20px;
}

.divider {
  width: 100%;
  height: 1px;
  border-top: solid #e0dddd 1px;
}

li {
  margin: 0;
  padding: 0;
}
h2 {
  margin: 0;
}
p{
  margin: 0;
  padding: 0;
}

.el-menu-item:hover {
  background-color: rgb(244, 244, 244) !important;;
}

</style>
