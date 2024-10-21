<!--商品审核页面-->
<template>
  <div style="background-color: #f3f6f8">
    <!--占位start-->
    <div style="height: 20px"></div>
    <!--占位end-->

    <div class="div1">
      <div>
        <el-row>
          <!--商品图展示start-->
          <el-col :span="12">
            <div style="display: flex; margin-top: 40px">
              <div style="width: 150px; margin: 45px 0px">
                <el-menu
                  class="el-menu-vertical-demo"
                  v-for="item in imgList"
                  :key="item"
                >
                  <el-menu-item class="li_item">
                    <img class="li_item_img" :src="item" v-on:mouseover="handleOver(item)">
                  </el-menu-item>

                </el-menu>

              </div>

              <div class="div2">
                <img class="img1" :src="`${displayImg}`"  alt="">
              </div>

            </div>
          </el-col>
          <!--商品图展示end-->

          <!--商品购买展示start-->
          <el-col :span="12" style="height: 600px; margin-top: 72px">
            <div style="float: left">
              <div>
                <h1 class="title1" style="margin-right: 24px">{{ Commodity.commodityTitle }}</h1>
              </div>
              <div style="float: left;">
                <span class="title_span" style="margin-bottom: 30px">已售 {{ Commodity.buyNumber }}</span>
              </div>

              <div style="height: 90px; background-color: #fcfaf7; margin-top: 58px; border-radius: 10px; margin-bottom: 30px">
                <p style="color: red; float: left; margin: 30px">
                  <span class="span2">¥</span>
                  <span class="span3">{{ Commodity.cprice }}</span>
                  <span>起</span>
                </p>
              </div>

              <div style="height: 50px">
                <p style="float: left">
                  <span class="span4 title_span">保障</span>
                  <span>:</span>
                  <span style="font-size: 17px; color: #11192d; margin-left: 5px">价保服务 假一赔四 退货运费险</span>
                </p>
              </div>

              <div style="height: 50px">
                <p style="margin-right: 485px">
                  <span class="span4 title_span">配送</span>
                  <span>:</span>
                  <span style="font-size: 17px; color: #11192d; margin-left: 5px">{{ Commodity.deliveryMethod }}</span>
                </p>
              </div>

              <div style="width: 100%; float: left">
                <div v-for="(values, key) in  Commodity.commoditySkuHeader" :key="key">
                  <div>
                    <span class="span4 title_span" style="float: left; margin: 10px 0">{{ key }}</span>
                    <span style="float: left; margin: 7px 4px">:</span>
                  </div>

                  <div style="display: flex; flex-wrap: wrap; width: 70%">
                    <div
                      tabindex="1"
                      class="sku-div-header"
                      :class="{ 'focus': selectedSkus[key] === value }"
                      v-for="(value, index) in values"
                      :key="index"
                      @click="selectSku(key, value)"
                    >{{ value }}</div>
                  </div>
                </div>
              </div>


              <div style="height: 50px; margin-bottom: 70px">
                <p style="float: left">
                  <span class="span4 title_span">数量</span>
                  <span>:</span>
                  <el-input-number style="margin-left: 5px"></el-input-number>
                </p>
              </div>

              <div style="float: left">
                <el-button type="primary" style="width: 200px; height: 54px; background-color: #ff6c00">立即购买</el-button>
                <el-button type="primary" style="width: 200px; height: 54px; background-color: #ffbf01">加入购物</el-button>
                <el-button style="height: 54px" >
                  <i class="el-icon-star-off"></i>
                  <span>收藏</span>
                </el-button>
              </div>

            </div>
          </el-col>
          <!--商品购买展示end-->
        </el-row>

        <!--商品参数start-->
        <div style="margin-bottom: 70px">
          <div style="height: 80px">
            <h2 style="float: left; margin-left: 25px">商品参数</h2>
          </div>

          <div class="div4">
            <div style="width: 50%; display: flex;">
              <div class="div5">产地</div>
              <div class="div6">{{ Commodity.locality }}</div>
            </div>

            <div style="width: 50%; display: flex;">
              <div  class="div5">类型</div>
              <div  class="div6">{{ Commodity.commodityType }}</div>
            </div>
          </div>

          <div class="div4">
            <div style="width: 50%; display: flex;">
              <div class="div5">适用对象</div>
              <div class="div6">{{ Commodity.suitableObject }}</div>
            </div>

            <div style="width: 50%; display: flex;">
              <div  class="div5">品牌</div>
              <div  class="div6">{{ Commodity.brand }}</div>
            </div>
          </div>

          <div class="div4">
            <div style="width: 50%; display: flex;">
              <div class="div5">材质</div>
              <div class="div6">{{ Commodity.material }}</div>
            </div>

            <div style="width: 50%; display: flex;">
              <div  class="div5">是否整装</div>
              <div  class="div6">{{ Commodity.isinteger }}</div>
            </div>
          </div>

          <div class="div4">
            <div style="width: 50%; display: flex;">
              <div class="div5">风格</div>
              <div class="div6">{{ Commodity.style }}</div>
            </div>

            <div style="width: 50%; display: flex;">
              <div  class="div5"></div>
              <div  class="div6"></div>
            </div>

          </div>


        </div>
        <!--商品参数end-->

        <!--商品详情start-->
        <div >
          <div style="height: 80px">
            <h2 style="float: left; margin-left: 25px">商品详情</h2>
          </div>

          <div v-html="Commodity.commodityDetails">

          </div>
        </div>
        <!--商品详情end-->

        <!--商品审核start-->
        <div style="margin-bottom: 40px">
          <div style="height: 80px">
            <h2 style="margin-left: 25px">商品审核</h2>
          </div>
          <div>
            <div style=" margin-left: 40px; margin-bottom: 20px">
              <el-radio v-model="verify.status" label=2>通过</el-radio>
              <el-radio v-model="verify.status" label=1>不通过</el-radio>
            </div>
            <div v-show="verify.status === '1'">
              <el-input placeholder="不通过原因" style="width: 500px; margin-left: 70px" v-model="verify.rejectReason"></el-input>
            </div>
          </div>
        </div>
        <!--商品审核end-->

        <!--提交start-->
        <div style="margin-left: 40px">
          <template>
            <el-button type="primary" @click="commodityVerify()">提交</el-button>
            <el-button type="primary" >取消</el-button>
          </template>
        </div>
        <!--提交end-->


        <!--占位start-->
        <div style="height: 100px"></div>
        <!--占位end-->

      </div>


    </div>


    <!--占位start-->
    <div style="height: 20px"></div>
    <!--占位end-->

    <CommonEnd></CommonEnd>
  </div>

</template>

<script>


import CommonEnd from "../../../user/common/CommonEnd.vue";

export default {
  name: "commodityVerifyDisplay",
  components: {CommonEnd},

  data() {
    return {

      selectedSkus: {},

      // 商品展示图
      displayImg: null,

      // 商品主图存储
      imgList: [

      ],
      // 'static/display/1.招财纳福手链.jpg',
      // 'static/display/2.水蜜桃抽拉珍珠手链.jpg',
      // 'static/display/3.珍珠山茶花手链女.jpg',
      // 'static/display/4.开口双层蝴蝶轻奢手镯.jpg',
      // 'static/display/5.蓝粉水晶串珠手链女.jpg'

      // 数据接收存储
      Commodity:{
        cid: null, //商品id
        commodityTitle: null, //商品标题
        classifyId: null, //商品分类
        brand: null, // 品牌
        locality: null, //产地
        isinteger: null, //是否整装
        style: null, //风格
        design: null, //图案
        material: null, //材质
        suitableObject: null, //适用对象
        commodityType: null, //商品类型（成色）
        cprice: null, //价格
        cnumber: null, //数量
        deliveryMethod: null, //配送方式
        shippingFees: null, // 配送费用
        commodityDetails: null, // 富文本信息
        buyNumber: 0, // 购买数量
        commoditySkuHeader: null// 商品sku
      },

      // 审核数据提交
      verify:{
        cid: null, //商品id
        status: null, // 审核状态
        rejectReason: null, //不通过上架原因
      }
    }
  },

  created() {
    this.verifyCommodityInfo()

  },

  methods:{

    selectSku(specKey, specValue) {
      console.log(specKey, specValue)
      // 更新选中状态，只会改变当前规格的选中项
      this.$set(this.selectedSkus, specKey, specValue);
    },

    // 商品审核数据提交
    commodityVerify(){
      this.verify.cid = this.Commodity.cid;
      this.$axios({
        url: "adminCommodity/v",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("adminToken")
        },
        data: this.verify
      }).then(msg =>{
        if (msg.data.code === 200) {
          alert("提交成功");
          // 跳转页面
          this.$router.push(
            {
              name: 'CommodityExamine'
            }
          )
        }else {
          alert(msg.data.message)
        }
      })

    },


    // 审核商品信息接收
    verifyCommodityInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));

      console.log(decodedData);
      // 商品id
      this.Commodity.cid = decodedData.cid;

      this.Commodity.commodityTitle = decodedData.commodityTitle; // 商品名称

      // 主图
      for (let i = 0; i < decodedData.commodityPicturePaths.length; i++) {
        this.imgList.push(this.$axios.defaults.baseURL  + "/" + decodedData.commodityPicturePaths[i]);
      }
      this.displayImg = this.imgList[0]


      this.Commodity.cprice = decodedData.cprice; //价格

      this.Commodity.brand = decodedData.brand; // 品牌
      this.Commodity.locality = decodedData.locality; //产地
      this.Commodity.isinteger = decodedData.isinteger; //是否整装
      this.Commodity.style = decodedData.style; //风格
      this.Commodity.design = decodedData.design; //图案
      this.Commodity.material = decodedData.material; //材质
      this.Commodity.suitableObject = decodedData.suitableObject; //适用对象
      this.Commodity.commodityType = decodedData.commodityType;  //商品类型

      //配送方式
      this.Commodity.deliveryMethod = decodedData.deliveryMethod;
      if (this.Commodity.deliveryMethod === "配送上门") {
        this.Commodity.shippingFees = decodedData.shippingFees;
      }


      this.Commodity.commodityDetails = decodedData.commodityDetails; // 副本文本信息

      this.Commodity.commoditySkuHeader = decodedData.commoditySkuHeader;


    },



    // 鼠标移入触发事件
    handleOver(item) {
      this.displayImg = item
      // console.log(this.displayImg)
    }

  }
}
</script>

<style scoped>

.sku-div-header{
  margin: 6px 14px;
  border: #c9bfbf 1px solid;
  border-radius: 5px;
  padding: 3px 11px;
  cursor: pointer;
}

.sku-div-header.focus{
  border-color: #ff5000;
  color: #ff5000;
}


.div1 {
  width: 82%;
  margin: auto;
  background-color: #FFFFFF;
  border-radius: 35px;

}

.div2 {
  width: 600px;
  height: 600px;
  background-color: #fafafa;
  border-radius: 25px;
}

.img1 {
  width: 500px;
  height: 500px;
  border: 47px solid #fafafa;
  border-radius: 63px;
}


.li_item {
  width: 80px;
  height: 80px;
  margin: 10px 10px;
  background-color: #fafafa;
}

.li_item_img {
  width: 80px;
  height: 80px;
  border-radius: 15px
}


.li_item:hover{

  width:130px;
  height:88px;
  border:3px solid #D94600;
  padding: 0px 17px;
  border-radius: 15px;

}

.title1{

  font-weight: 600;
  line-height: 30px;
  font-size: 20px;
  color: #11192d;
}

.title_span{
  font-size: 18px;
  line-height: 14px;
  color: #7c889c;
}

.span2{
  font-size: 20px;
}

.span3{
  font-size: 28px;
}

.span4 {
  width: 58px;
  display: inline-block;
  text-align-last: justify;
}

.div4{
  width: 1100px;
  height: 100%;
  margin-left: 25px;
  display: flex;
  border:1px solid #efeeee
}

.div5{
  width: 50%;
  height: 50px;
  background-color: #f3f6f8;
  text-align: center;
  padding-top: 30px;
  font-size: 19px;


}

.div6{
  width: 50%;
  text-align: center;
  padding-top: 30px;
  font-size: 19px;


}





</style>
