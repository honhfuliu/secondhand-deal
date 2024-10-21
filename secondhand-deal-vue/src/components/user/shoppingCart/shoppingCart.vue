<template>
  <div style="background-color: #f3f6f8">
    <!--头部start-->
    <Common></Common>
    <!--头部end-->

    <div class="div-img">
      <img src="/static/i-shopping-cart.png" alt="">

    </div>

    <!--主体start-->
    <div class="div-d">
      <el-row>
        <el-col :span="16">
          <div style=" background-color: #FFFFFF; border-radius: 8px">
            <div style="line-height: 50px">
              <span class="span-1">全部商品</span>
            </div>


            <!--表格start-->
            <div style="width: 96%; margin: auto auto 20px">
              <el-table :show-header="false" :data="shoppingCartData" @selection-change="handleSelectionChange">

                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>

                <el-table-column width="370">
                  <template  v-slot="scope">
                    <el-link :underline="false">
                      <el-row>
                        <el-col :span="7">
                          <img :src="`${URL}/${scope.row.imgPath}`" class="img-1">
                        </el-col>
                        <el-col :span="15">
                          <span class="span-title">{{scope.row.title}}</span>
                        </el-col>
                      </el-row>
                    </el-link>
                  </template>
                </el-table-column>

                <el-table-column width="200">
                  <template  v-slot="scope">
                    <div v-for="(values ,key) in scope.row.commoditySku" :key="key">
                      <span style="font-weight: 600" >{{ key }}</span>
                      <span style="font-size: 16px; color: black; font-weight: 600">:</span>
                      <span v-for="item in values" :key="item">{{ item }}</span>
                    </div>
                  </template>
                </el-table-column>

                <el-table-column width="120">
                  <template  v-slot="scope">
                    <span class="span-price">￥{{ scope.row.unitPrice }}</span>
                  </template>
                </el-table-column>

                <el-table-column  >
                  <template  v-slot="scope">
                    <el-input-number v-model="scope.row.number" :min="1" :max="9999999"></el-input-number>
                  </template>

                </el-table-column>

                <el-table-column width="120">
                  <template  v-slot="scope">
                    <div style="display: grid">
                      <el-link :underline="false" @click="addCollect(scope.row.status)">移入到收藏夹</el-link>
                      <el-link :underline="false" @click="deleteShoppingCart(scope.row.id)">删除</el-link>
                    </div>
                  </template>
                </el-table-column>

              </el-table>
            </div>
            <!--表格end-->

            <div style="line-height: 50px">
              <span class="span-1">失效商品</span>
            </div>

            <!--表格start-->
            <div style="width: 96%; margin: auto">
              <el-table :show-header="false" :data="NoAvailShoppingCartData">

                <el-table-column width="100">商品已失效</el-table-column>

                <el-table-column width="400">
                  <template  v-slot="scope">
                    <el-link :underline="false">
                      <el-row>
                        <el-col :span="7">
                          <img :src="`${URL}/${scope.row.imgPath}`" class="img-1">
                        </el-col>
                        <el-col :span="15">
                          <span class="span-title">{{scope.row.title}}</span>
                        </el-col>
                      </el-row>
                    </el-link>
                  </template>
                </el-table-column>

                <el-table-column width="200">
                  <template  v-slot="scope">
                    <div v-for="(values ,key) in scope.row.commoditySku" :key="key">
                      <span style="font-weight: 600" >{{ key }}</span>
                      <span style="font-size: 16px; color: black; font-weight: 600">:</span>
                      <span v-for="item in values" :key="item">{{ item }}</span>
                    </div>
                  </template>
                </el-table-column>

                <el-table-column width="80">
                  <template  v-slot="scope">
                    <span class="span-price">￥{{ scope.row.unitPrice }}</span>
                  </template>
                </el-table-column>

                <el-table-column>
                  <template  v-slot="scope">
                    <el-input-number v-model="scope.row.number" :disabled="true"></el-input-number>
                  </template>

                </el-table-column>

                <el-table-column width="80">
                  <template  v-slot="scope">
                    <div style="display: grid">
                      <el-link :underline="false" @click="deleteShoppingCart(scope.row.id)">删除</el-link>
                    </div>
                  </template>
                </el-table-column>

              </el-table>
            </div>
            <!--表格end-->
          </div>
        </el-col>

        <el-col :span="7" class="col-a">
          <div style="background-color: #ffffff; max-height: 666px; border-radius: 15px">
            <div style="line-height: 50px">
              <span class="span-1">结算明细</span>
            </div>

            <div class="divider"></div>

            <div v-if="SettlementList.length === 0" style="height: 200px">
              <div style="position: absolute; left: 31%; top: 39%">
                <span style="color: #7c889c; font-size: 18px">还没有待结算的商品</span>
              </div>
            </div>

            <div style="display: flex; flex-wrap: wrap; width: 96%; margin: auto" v-else>
              <div class="div-j" v-for="item in SettlementList" key="item">
                <img :src="`${URL}/${item.imgPath}`" style="width: 90px; border-radius: 12px">
              </div>

            </div>
            <div class="divider"></div>

            <div>
              <el-row>
                <el-col :span="12">
                  <div style="line-height: 30px; float: left; margin: 20px 15px 0;">
                    <span style="font-size: 18px; font-weight: 600">合计:</span>
                    <span style="color: #ff5000; font-size: 19px; font-weight: 600">￥</span>
                    <span style="color: #ff5000; font-size: 28px; font-weight: 600">{{totalPrice}}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <el-button class="button-1" @click="settleAccounts()">结&nbsp;算</el-button>
                </el-col>
              </el-row>
              <div style="height: 20px"></div>
            </div>




          </div>
        </el-col>
      </el-row>
    </div>
    <!--主体end-->
    <div style="height: 100px"></div>


    <!--尾部start-->
    <CommonEnd></CommonEnd>
    <!--尾部end-->
  </div>
</template>

<script>
import Common from "../common/HomeCommon.vue";
import CommonEnd from "../common/CommonEnd.vue";

export default {
  name: "shoppingCart",
  components: {Common, CommonEnd},

  data(){
    return{
      number: null,

      // 总价格结算
      totalPrice: 0,

      // 购物车有效商品数据存在
      shoppingCartData: [],

      //购物车无效商品数据存在
      NoAvailShoppingCartData: [],

      // 结算商品信息存储
      SettlementList: [],

      URL: null,
    }
  },
  created() {
    this.getShoppingCartInfo()
    this.getShoppingCartInfoNoAvail()
    this.URL = this.$axios.defaults.baseURL
  },

  methods:{
    // 提交结算商品
    settleAccounts(){
      // 提交信息存储
      const SubmitData = [];
      for (let i = 0; i < this.SettlementList.length; i++) {
        let itemInfo = {
          shoppingCartId: this.SettlementList[i].id,
          commodityId: this.SettlementList[i].status,
          unitPrice: this.SettlementList[i].unitPrice,
          totalPrice: this.SettlementList[i].totalPrice,
          buyQuantity: this.SettlementList[i].number,
          commoditySku: this.SettlementList[i].commoditySku
        }
        SubmitData.push(itemInfo)
      }

      // 发送请求获取数据
      this.$axios({
        url: "order",
        method: "post",
        headers:{"token" : sessionStorage.getItem("token")},
        data: SubmitData,
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

    // 从购物车中删除商品
    deleteShoppingCart(id){
      this.$axios({
        url: "shopping/d",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {'status': id}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("删除成功")
          location.reload() // 刷新页面
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 加入到收藏夹
    addCollect(id){
      this.$axios({
        url: "collect",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {'commodityId': id}

      }).then(msg => {
        if (msg.data.code === 200){
          alert("收藏成功")
        }else{
          alert(msg.data.message)
        }
      })
    },


    handleSelectionChange(val){
      console.log(val)
      this.totalPrice = 0
      this.SettlementList = val;

      // 购物车价格计算
      if (this.SettlementList.length === 0) {
        this.totalPrice = 0
      } else {
        for (let i = 0; i < this.SettlementList.length; i++) {
          // console.log(this.SettlementList[i])
          const commodityPrice = this.SettlementList[i].number * this.SettlementList[i].unitPrice
          // console.log(commodityPrice)
          this.totalPrice = this.totalPrice + commodityPrice
        }


        // 格式化为两位小数
        this.totalPrice = this.totalPrice.toFixed(2);
      }

    },

    getShoppingCartInfo(){
      this.$axios({
        url: "/shopping",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.shoppingCartData = msg.data.data
        }else {
          alert(msg.data.message)
        }
      })
    },

    getShoppingCartInfoNoAvail(){
      this.$axios({
        url: "/shopping/n",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.NoAvailShoppingCartData = msg.data.data
        }else {
          alert(msg.data.message)
        }
      })
    }
  }
}
</script>

<style scoped>

.col-a{
  margin-left: 60px;
  position: sticky;
  left: 63%;
  z-index: 99999;
  top: 12%;
}

.div-j{
  margin: 5px 12px;
}

.button-1{
  height: 55px;
  width: 130px;
  background-color: #ff5000;
  color: #FFFFFF;
  border-radius: 15px;
  font-size: 20px;
  font-weight: 600;
  font-family: cursive;
}

.divider {
  width: 100%;
  height: 1px;
  border-top: solid #d9d6d6 1px;
  margin-bottom: 15px;
}

.span-title{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 17px;
  color: black;
  margin-top: 5px;
}

.span-price{
  color: #FF5000;
  font-weight: 600;
  font-size: 16px;
}

.img-1{
  height: 96px;
  width: 96px;
  border-radius: 15px;
}

.span-1{
  font-weight: 600;
  font-size: 19px;
  display: flex;
  width: 96%;
  margin: auto
}

.div-img{
  display: flex;
  margin: 7px 134px 20px;

}

.div-d{
  width: 85%;
  margin: auto;
}

</style>
