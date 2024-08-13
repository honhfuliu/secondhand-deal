<template>
  <div style="background-color: #f3f6f8;">
    <Common></Common>
    <div style="height: 60px"></div>
    <div style="width: 80%; margin: auto; background-color: #FFFFFF; border-radius: 15px">

      <!--订单信息start-->
      <div>
        <div style="height: 60px"></div>
        <el-row style="margin-bottom: 60px">
          <el-col :span="12">
            <div style="display: flex; width: 80%; margin: auto">
              <div>
                <img src="static/chenggong.png" style="height: 60px">
              </div>
              <div style="margin-left: 30px">
                <div style="font-size: 22px; font-weight: 600; margin-bottom: 8px" >您的订单{{ orderInfo1.orderNumber }}提交成功，正在等待处理！</div>
                <span style="float: left; font-family: cursive; color: #727070; font-size: 20px; font-weight: 600">应付金额:&nbsp;</span>
                <span style="float: left;  font-size: 19px; font-weight: 600; color: #be4242; margin: -2px">&nbsp;￥{{ orderInfo1.Amount }}元</span>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div style="line-height: 52px">
              <span class="span-font">请您在&nbsp;</span>
              <span style="font-size: 17px; color: #f89061; font-weight: 600">{{ orderInfo1.overdueTime }}</span>
              <span class="span-font">&nbsp;之前支付订单款项，否则该订单将失效</span>
            </div>
          </el-col>
        </el-row>



      </div>
      <!--订单信息end-->

      <!--付款方式start-->
      <div>
        <div style="width: 95%; margin: auto auto 30px;">
          <div style="display: flex; margin-bottom: 20px">
            <span style="font-weight: 600; font-size: 17px">支付方式</span>
          </div>

          <div style="display: flex" >
            <div class="pay" tabindex="1">
              <div>
                <img src="/static/zfb.png" style="height: 50px">
              </div>
              <div style="line-height: 44px; margin-left: 20px">
                <span>支付宝</span>
              </div>
            </div>

            <div class="pay" tabindex="1">
              <div>
                <img src="/static/weix.png" style="height: 50px">
              </div>
              <div style="line-height: 44px; margin-left: 20px">
                <span>微信</span>
              </div>
            </div>

            <div class="pay" tabindex="1">
              <div>
                <img src="/static/yinlian.png" style="height: 50px">
              </div>
              <div style="line-height: 44px; margin-left: 20px">
                <span>银联支付</span>
              </div>
            </div>
          </div>
        </div>

        <div style="width: 62%">
          <el-button class="button-submit" @click="pay">点击支付</el-button>
        </div>

        <div style="height: 60px"></div>
      </div>
      <!--付款方式end-->

    </div>
    <div style="height: 60px"></div>
    <CommonEnd></CommonEnd>

  </div>

</template>

<script>
import CommonEnd from "../common/CommonEnd.vue";
import Common from "../common/Common.vue";

export default {
  name: "orderPay",
  components: {CommonEnd, Common},
  data(){
    return{
      // 订单数据接收
      orderInfo1: null,
    }
  },
  created() {
    this.orderInfo()
  },

  mounted() {
    window.addEventListener('popstate', () => {
      // 监听浏览器后退事件
      this.$router.push({
        path: '/' // 跳转到首页
      })
    })
  },

  methods:{
    pay(){
      this.$axios({
        url: "order/pay",
        method: "post",
        headers: {"token": sessionStorage.getItem("token")},
        data:{"OrderNumber": this.orderInfo1.orderNumber}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("支付成功")
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }

      })
    },

    orderInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));
      this.orderInfo1 = decodedData
    }

  }
}
</script>

<style scoped>
.button-submit{
  border-radius: 10px;
  height: 50px;
  width: 150px;
  font-size: 18px;
  color: #6b6969;
  background-image: linear-gradient(180deg, #c3eeff, #fff 50px);
}

.pay{
  display: flex;
  width: 190px;
  border: solid #c7bdbd 1px;
  border-radius: 10px;
  padding: 9px 14px;
  margin: 0 42px;
  cursor: pointer;
}
.pay:focus{
  border: 2px solid #ff0202;
}

.span-font{
  font-size: 17px;
  color: #696969;
}

</style>
