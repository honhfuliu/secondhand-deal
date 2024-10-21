<template>
  <div style="background-color: #f3f6f8">
    <Common></Common>

    <!--主体start-->
    <div class="div1">
      <div style="margin-bottom: 50px">
        <el-row>
          <!--商品展示图start-->
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
          <!--商品展示图end-->

          <!--商品购买展示start-->
          <el-col :span="12" style="margin-top: 72px">
            <div style="float: left">
              <div>
                <h1 class="title1" style="margin-right: 24px">{{ commodityInfo.commodityTitle }}</h1>
              </div>
              <div style="float: left;">
                <span class="title_span" style="margin-bottom: 30px" v-if="commodityInfo.buyNumber === null">已售:0</span>
                <span class="title_span" style="margin-bottom: 30px" v-else>已售:&nbsp;{{commodityInfo.buyNumber}}</span>
              </div>

              <div style="height: 90px; background-color: #fcfaf7; margin-top: 58px; border-radius: 10px; margin-bottom: 10px">
                <p style="color: red; float: left; margin: 30px">
                  <span class="span2">¥</span>
                  <span class="span3">{{ commodityInfo.cprice }}</span>
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
                <p style="margin-right: 485px; float: left">
                  <span class="span4 title_span">配送</span>
                  <span>:</span>
                  <span style="font-size: 17px; color: #11192d; margin-left: 5px">{{ commodityInfo.deliveryMethod }}</span>
                </p>
              </div>

              <div style="width: 100%; float: left">
                <div v-for="(values, key) in commodityInfo.commoditySkuHeader" :key="key">
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
                  <el-input-number style="margin-left: 5px" v-model="CommodityNumber" :min="1" :max="9999999"></el-input-number>
                </p>
              </div>

              <div style="float: left">
                <el-button type="primary" style="width: 200px; height: 54px; background-color: #ff6c00" @click="BuyCommodity">立即购买</el-button>
                <el-button type="primary" style="width: 200px; height: 54px; background-color: #ffbf01" @click="CommodityAddShoppingCart()">加入购物</el-button>
                <el-button style="height: 54px" @click="collectCommodity()">
                  <i class="el-icon-star-off"></i>
                  <span>收藏</span>
                </el-button>
                <el-button style="height: 54px"  @click="chat()">
                  <i class="el-icon-star-off"></i>
                  <span>客服</span>
                </el-button>
              </div>

            </div>
          </el-col>
          <!--商品购买展示end-->
        </el-row>
      </div>

      <div style="display: flex; line-height: 50px; margin-bottom: 40px">
        <div>
          <span class="span-name" tabindex="1" @click="click1('yong-hu',$event)">用户评价</span>
        </div>
        <div>
          <span class="span-name" tabindex="2" @click="click1('can-shu',$event)">商品参数</span>
        </div>
        <div>
          <span class="span-name" tabindex="3 " @click="click1('xiang-qing',$event)">商品详情</span>
        </div>
      </div>


      <!--用户评价start-->
      <div>
        <div>
          <span class="span-t" id="yong-hu">用户评价</span>
        </div>
        <div>
          <div style="height: 400px; margin-top: 150px">
              <div class="div-p">
                <img src="/static/0001.png" style="width: 96px; height: 96px">
                <span style="font-size: 18px; color: #7c889c">竟然一条评价都没有</span>
              </div>
          </div>
        </div>
      </div>
      <!--用户评价end-->

      <!--商品参数start-->
      <div style="margin-bottom: 70px">
        <div style="height: 80px">
          <span class="span-t" id="can-shu">商品参数</span>
        </div>

        <div class="div4">
          <div style="width: 50%; display: flex;">
            <div class="div5">产地</div>
            <div class="div6">{{ commodityInfo.locality }}</div>
          </div>

          <div style="width: 50%; display: flex;">
            <div  class="div5">类型</div>
            <div  class="div6">{{ commodityInfo.commodityType }}</div>
          </div>
        </div>

        <div class="div4">
          <div style="width: 50%; display: flex;">
            <div class="div5">适用对象</div>
            <div class="div6">{{ commodityInfo.suitableObject }}</div>
          </div>

          <div style="width: 50%; display: flex;">
            <div  class="div5">品牌</div>
            <div  class="div6">{{ commodityInfo.brand }}</div>
          </div>
        </div>

        <div class="div4">
          <div style="width: 50%; display: flex;">
            <div class="div5">材质</div>
            <div class="div6">{{ commodityInfo.material }}</div>
          </div>

          <div style="width: 50%; display: flex;">
            <div  class="div5">是否整装</div>
            <div  class="div6">{{ commodityInfo.isinteger }}</div>
          </div>
        </div>

        <div class="div4">
          <div style="width: 50%; display: flex;">
            <div class="div5">风格</div>
            <div class="div6">{{ commodityInfo.style }}</div>
          </div>

          <div style="width: 50%; display: flex;">
            <div  class="div5"></div>
            <div  class="div6"></div>
          </div>
        </div>
      </div>
      <!--商品参数end-->

      <!--商品详情start-->
      <div style="margin-bottom: 60px">
        <div style="height: 80px">
          <span class="span-t" id="xiang-qing">商品详情</span>
        </div>
        <div>
          <div v-html="commodityInfo.commodityDetails"></div>
        </div>
      </div>
      <!--商品详情end-->

      <div style="height: 60px"></div>

    </div>
    <!--主体end-->

    <div style="width: 100%; background-color: #FFFFFF; line-height: 80px" v-if="show" class="d">
      <div style="width: 80%; display: flex; margin: auto">
        <span class="span-name" tabindex="1" @click="click1('yong-hu',$event)">用户评价</span>
        <span class="span-name"  tabindex="2" @click="click1('can-shu',$event)">商品参数</span>
        <span class="span-name"  tabindex="3" @click="click1('xiang-qing',$event)">商品详情</span>
      </div>
    </div>

    <div style="height: 150px"></div>

    <CommonEnd></CommonEnd>
  </div>

</template>

<script>
import CommonEnd from "../common/CommonEnd.vue";
import Common from "../common/Common.vue";


export default {
  name: "commodityDisplay",

  components: {CommonEnd, Common},

  data(){
    return{
      // 记录每个规格的选中状态
      selectedSkus: {}, // 每个规格选中的值
      // 商品数据接收
      commodityInfo: null,

      // 商品数量
      CommodityNumber: null,



      css: true,
      show: false,

      displayImg: null,

      // 图片数据接收
      imgList: [
        // 'static/display/1.招财纳福手链.jpg',
        // 'static/display/2.水蜜桃抽拉珍珠手链.jpg',
        // 'static/display/3.珍珠山茶花手链女.jpg',
        // 'static/display/4.开口双层蝴蝶轻奢手镯.jpg',
        // 'static/display/5.蓝粉水晶串珠手链女.jpg'
      ],

      number: 1
    }
  },

  created() {
    this.CommodityInfo()
  },

  computed: {

  },

  methods:{
    selectSku(specKey, specValue) {
      console.log(specKey, specValue)
      // 更新选中状态，只会改变当前规格的选中项
      this.$set(this.selectedSkus, specKey, specValue);
    },

    // 购买商品
    BuyCommodity(){
      // 数据处理
      let SubmitDat = {
        commodityId: this.commodityInfo.cid,
        unitPrice: this.commodityInfo.cprice,
        totalPrice: this.commodityInfo.cprice * this.CommodityNumber,
        buyQuantity: this.CommodityNumber,
        commoditySku: this.selectedSkus
      }

      // 发送请求
      this.$axios({
        url: "order/page",
        method: "post",
        headers: { "token": sessionStorage.getItem("token") },
        data: SubmitDat
      }).then(msg => {
        if (msg.data.code === 200) {
          // 跳转到订单结算页面
          this.$router.push({
            name: "CreateShoppingOrder",
            query: { data: encodeURIComponent(JSON.stringify(msg.data.data)) }
          })
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },

    // 跳转到聊天页面
    chat(){
      this.$axios({
        url:"chat",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {"userId": this.commodityInfo.cid}
      }).then(msg => {
        if (msg.data.code === 200) {
          let routeData = this.$router.resolve({
            name: "UserChat",
          });
          window.open(routeData.href, '_blank');

        } else {
          alert(msg.data.message)
        }
      })
    },

    // 商品购物车添加
    CommodityAddShoppingCart(){
      let yuan = 0
      let xian = 0
      for (let commoditySkuHeaderKey in this.commodityInfo.commoditySkuHeader) {
        yuan += 1
      }

      for(let sku in this.selectedSkus){
        xian += 1
      }
      if (yuan !== xian) {
        this.$notify.warning({
          title: "警告",
          message: "未选中商品规格"
        })
        return false;
      }
      this.$axios({
        url: "shopping",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {
          "commodityId":this.commodityInfo.cid,
          "shoppingNumber": this.CommodityNumber,
          "commoditySku": this.selectedSkus
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("加入购物车成功")
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 商品收藏
    collectCommodity(){
      this.$axios({
        url: "collect",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {'commodityId': this.commodityInfo.cid}

      }).then(msg => {
        if (msg.data.code === 200){
          alert("收藏成功")
        }else{
          alert(msg.data.message)
        }
      })
    },

    // 商品信息接收
    CommodityInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));
      // console.log(decodedData)
      this.commodityInfo = decodedData
      console.log(this.commodityInfo)

      // 主图
      for (let i = 0; i < decodedData.commodityPicturePaths.length; i++) {
        this.imgList.push(this.$axios.defaults.baseURL  + "/" + decodedData.commodityPicturePaths[i]);
      }
      this.displayImg = this.imgList[0]

      console.log(this.commodityInfo)
    },


    //位置跳转
    click1(id,event){

      // 获取所有span元素并移除focus类
      document.querySelectorAll('.span-name').forEach(span => {
        span.classList.remove('focus');
      });

      // 给当前点击的span添加focus类
      event.target.classList.add("focus");

      // 更新窗口的hash值
      window.location.hash=`${id}`

    },



    // 鼠标移入触发事件
    handleOver(item) {
      this.displayImg = item
      // console.log(this.displayImg)
    },

    handleScroll() {
      // 页面滑动的距离
      let scrollTop = document.documentElement.scrollTop
      // 当页面滑动的距离大于300px时元素显示，否则不显示
      if (scrollTop >= 550) {
        this.show = true
      } else {
        this.show = false
      }
    }

  },

  mounted() {
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
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
  background-color: #FFFFFF;
  border-color: #ff5000;
  color: #ff5000;
}

.d{
  position: fixed;
  top: 0;
  z-index: 999;
}


.span-t{
  font-size: 28px;
  font-weight: 600;
  display: flex;
  margin-left: 20px
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

.div-p{
  display: flex;
  flex-direction: column;
  align-items: center;
}

.span-name{
  font-size: 20px;
  padding: 0px 23px;
  cursor: pointer;
}

.span-name.focus{
  background-color: #FFFFFF;
  color: red;
  font-weight: 600;
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

.li_item_img {
  width: 80px;
  height: 80px;
  border-radius: 15px
}

.li_item {
  width: 80px;
  height: 80px;
  margin: 10px 10px;
  background-color: #fafafa;
}

.li_item:hover{
  width:130px;
  height:88px;
  border:2px solid #D94600;
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

</style>
