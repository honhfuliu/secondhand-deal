<template>
<div class="div-body">
  <common></common>
  <div style="height: 50px"></div>
  <div class="centrality">

    <el-row>
      <!--订单详情start-->
      <el-col :span="16" class="col-a">
        <!--收货地址start-->
        <div class="div-t">
          <div style="width: 95%; margin: auto;">
            <div class="div-address">
              <div>
                <span style="color: #11192d; font-weight: 700; font-size: 20px">确认收货地址</span>
              </div>
              <div >
                <el-link :underline="false" class="a-font"  @click="dialogVisible = true">使用新地址</el-link>
                <el-link :underline="false" class="a-font" @click="skipPath('Address')">管理地址</el-link>
              </div>
            </div>

            <div style="display: flex; flex-wrap: wrap">

              <div class="individual" tabindex="1" v-for="item in MyAddressList" :key="item" @click="getAddressId(item.id)">
                <div style="display: flex">
                  <div style="margin-right: 10px">
                    <i class="el-icon-location icon-t" ></i>
                  </div>
                  <div>
                    <div style="display: flex">
                      <div>
                        <el-tag type="warning" v-if="item.status === 1">默认</el-tag>
                      </div>
                      <div>
                        <span class="span-address">{{ item.provinceName }}&nbsp;{{ item.cityName }}&nbsp;{{ item.areaName }}&nbsp; {{ item.streetName }}</span>
                      </div>
                    </div>
                    <div style="margin-bottom: 5px">
                      <span class="span-details-address">{{ item.fullAddress }}</span>
                    </div>
                    <div style="float: left">
                      <span style="font-size: 15px">{{ item.name }}&nbsp;{{ item.contact }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div style="height: 50px"></div>
          </div>
        </div>
        <!--收货地址end-->

        <!--确认订单start-->
        <div class="div-t">
          <div style="width: 95%; margin: auto;">
            <div style="display: flex; height: 70px">
              <span style="color: #11192d; font-weight: 700; font-size: 20px; line-height: 67px">确认订单信息</span>
            </div>
            <!--商品信息表格start-->
            <div>
              <el-table
                :header-cell-style="{ fontSize: '15px', background: '#f3f6f8',color: 'black', textAlign: 'center' }"
                :data="MyOrder"
              >
                <el-table-column label="商品名称" align="center" width="370">
                  <template v-slot="scope">
                    <div style="display: flex; justify-content: space-between">
                      <div>
                        <img :src="`${URL}/${scope.row.path}`" alt="" style="width: 70px">
                      </div>
                      <div>
                        <span class="title-span">{{scope.row.title}}</span>
                      </div>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="配送服务" align="center">
                  <template v-slot="scope">
                    <span>{{scope.row.deliveryMethod}}</span>
                    <span>:</span>
                    <span>{{scope.row.deliveryFee}}</span>
                  </template>
                </el-table-column>

                <el-table-column label="商品规格" width="200" align="center">
                  <template  v-slot="scope">
                    <div v-for="(values ,key) in scope.row.commoditySku" :key="key">
                      <span style="font-weight: 600" >{{ key }}</span>
                      <span style="font-size: 16px; color: black; font-weight: 600">:</span>
                      <span v-for="item in values" :key="item">{{ item }}</span>
                    </div>
                  </template>
                </el-table-column>

                <el-table-column label="原价" align="center" prop="unitPrice"></el-table-column>
                <el-table-column label="数量" align="center" width="200px">
                  <template v-slot="scope">
                    <el-input-number v-model="scope.row.buyQuantity" :min="1" :max="9999999" @change="priceCount"></el-input-number>
                  </template>
                </el-table-column>
                <el-table-column label="小计" align="center">
                  <template v-slot="scope">
                    <span> {{ (scope.row.unitPrice * scope.row.buyQuantity + scope.row.deliveryFee).toFixed(2) }}</span>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <!--商品信息表格end-->
          </div>

          <!--商品备注 是否使用积分start-->
          <div style="width: 95%; margin: auto; background-color: #f3f6f8; border-radius: 10px">
            <div style="width: 95%; margin: auto;">
              <div style="line-height: 60px; display: flex;width: 100%">
                <span style="font-size: 19px; font-weight: 600">积分使用</span>
              </div>
              <div style="line-height: 30px; display: flex; justify-content: space-between; margin-bottom: 15px">
                <div>
                  <span style="color: #beb8b8; margin-left: 8px">积分少于1000无法使用</span>
                </div>
                <div>
                  <span style="font-weight: 600; color: #524444">当前剩余积分：{{ acquisition }}</span>
                </div>
              </div>

              <div style="float: left">
                <el-checkbox v-model="checked1" v-if="acquisition > 1000" @change="CountAcquisition">使用积分</el-checkbox>
                <el-checkbox disabled v-if="acquisition < 1000">使用积分</el-checkbox>
              </div>
              <div style="float: left; margin: -10px 17px"  v-show="checked1">
                <el-select  placeholder="选择使用积分减免的商品" v-model="integral">
                  <el-option
                    v-for="item in MyOrder"
                    :key="item.value"
                    :label="item.title"
                    :value="item.commodityId">
                  </el-option>
                </el-select>
              </div>
              <div style="float: left; margin: -10px 26px; width: 200px" v-show="checked1">
                <el-input-number v-model="score" :min="1000" :max="acquisition"  @change="CountAcquisition"></el-input-number>
              </div>

            </div>

            <div style="width: 95%; margin: auto;">
              <div style="line-height: 60px; display: flex;width: 100%">
                <span style="font-size: 19px; font-weight: 600">商品备注</span>
              </div>

              <div style="width: 500px">
                <el-input placeholder="请输入，付款后商家可见，建议和商家协商一致" v-model="remark"></el-input>
              </div>
            </div>

            <div style="height: 30px"></div>

          </div>
          <!--商品备注 是否使用积分end-->
          <div style="height: 30px"></div>
        </div>
        <!--确认订单end-->



        <!--新增收货地址start-->
        <div>
          <el-dialog
            title="地址添加"
            :visible.sync="dialogVisible"
            width="37%"
            :before-close="dialogClose"
          >
            <div>
              <el-form>
                <el-form-item label="地址信息:">
                  <el-select
                    :clearable="true"
                    placeholder="省"
                    v-model="address.province"
                    @click.native="getAllProvince"
                    class="select-width">
                    <el-option
                      v-for="item in provinceList"
                      :key="item"
                      :value="item.code"
                      :label="item.name"
                    >
                    </el-option>
                  </el-select>

                  <el-select
                    :clearable="true"
                    placeholder="市"
                    v-model="address.city"
                    @click.native="getCityInfo"
                    class="select-width">
                    <el-option
                      v-for="item in cityList"
                      :key="item"
                      :value="item.code"
                      :label="item.name"
                    >
                    </el-option>
                  </el-select>

                  <el-select
                    :clearable="true"
                    placeholder="区"
                    v-model="address.area"
                    @click.native="getAreaInfo"
                    class="select-width">
                    <el-option
                      v-for="item in areaList"
                      :key="item"
                      :value="item.code"
                      :label="item.name"
                    >
                    </el-option>
                  </el-select>

                  <el-select
                    :clearable="true"
                    placeholder="街道"
                    v-model="address.street"
                    @click.native="getResidentialDistrictInfo"
                    class="select-width">
                    <el-option
                      v-for="item in residentialDistrictList"
                      :key="item"
                      :value="item.code"
                      :label="item.name"
                    >
                    </el-option>
                  </el-select>

                </el-form-item>

                <el-form-item label="详细地址:">
                  <el-input
                    v-model="address.fullAddress"
                    class="input-width"
                    placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息"
                  ></el-input>
                </el-form-item>

                <el-form-item label="收货人姓名:">
                  <el-input
                    v-model="address.addressName"
                    style="margin-left: -18px"
                    class="input-width"
                    placeholder="长度不超过25个字符"
                  ></el-input>
                </el-form-item>

                <el-form-item label="手机号码:">
                  <el-input
                    v-model="address.contact"
                    class="input-width"
                    placeholder="电话号码、手机号码必须填一项"
                  ></el-input>
                </el-form-item>

              </el-form>
            </div>
            <div>
              <el-button @click="dialogClose">取 消</el-button>
              <el-button type="primary" @click="addAddress">确 定</el-button>
            </div>
          </el-dialog>
        </div>
        <!--新增收货地址end-->

      </el-col>
      <!--订单详情end-->

      <!--付款详情start-->
      <el-col :span="8" class="col-b">
        <div style="width: 92%; margin: auto auto 20px;">

          <div style="line-height: 60px; width: 100%; display: flex; margin-bottom: 10px">
            <span style="font-size: 22px; font-weight: 600">付款详情&nbsp;</span>
            <span style="font-size: 18px">共{{MyOrder.length}}件商品</span>
          </div>

          <div>
            <div class="div-shopping">
              <div>
                <span class="shopping-price-font">商品总价</span>
              </div>
              <div>
                <span class="shopping-price">￥</span>
                <span class="shopping-price">{{jiage}}</span>
              </div>
            </div>

            <div class="div-shopping">
              <div>
                <span class="shopping-price-font">积分减免</span>
              </div>
              <div>
                <span class="shopping-price">￥</span>
                <span class="shopping-price">{{ PointsReduction }}</span>
              </div>
            </div>

            <div class="div-shopping">
              <div>
                <span class="shopping-price-font">配送费用</span>
              </div>
              <div>
                <span class="shopping-price">￥</span>
                <span class="shopping-price">{{ yunfei }}</span>
              </div>
            </div>
          </div>
        </div>

        <div class="link-top"></div>
        <div style="width: 90%; margin: auto">
          <div style="display: flex; justify-content: space-between">
            <div>
              <div style="margin-bottom: 5px; font-size: 17px">实付款</div>
              <div style="color: #ff5000; font-size: 24px; font-weight: 600">￥{{ totalPrice }}</div>
            </div>
            <div>
              <el-button class="order-submit" @click="submitCommodityOrder">提交订单</el-button>
            </div>
          </div>

          <div style="height: 30px"></div>
        </div>
      </el-col>
      <!--付款详情end-->
    </el-row>

  </div>
  <div style="height: 50px"></div>

  <CommonEnd></CommonEnd>
</div>
</template>

<script>
import CommonEnd from "../common/CommonEnd.vue";
import Common from "../common/Common.vue";

export default {
  name: "createShoppingOrder",
  components: {CommonEnd, Common},

  data(){
    return{
      totalPrice: 0,
      addressId: null,

      integral: null,

      // 备注
      remark: null,
      // 用户积分
      acquisition: null,
      checked1: false,

      score: 1000,

      // 商品总价
      jiage: 0,
      // 运费总价
      yunfei: 0,

      // 减免金额
      PointsReduction: 0.00,
      // 商品订单提交数据存储




      // 订单数据接收
      MyOrder: null,

      // 地址信息存储
      MyAddressList: [],

      // 添加收货地址弹框控制
      dialogVisible: false,

      // 地址添加数据
      address: {
        addressId: null, // 地址id
        province: null, // 省级信息
        city: null, // 市级信息
        area: null, // 区级信息
        street: null, // 街道信息
        fullAddress: null, //详细地址
        addressName: null, // 收货人
        contact: null, // 联系方式
      },

      // 所有省级信息存储
      provinceList: [],

      // 所有市级信息存储
      cityList: [],

      // 所有区级信息存储
      areaList: [],

      // 所有街道信息存储
      residentialDistrictList: [],

      URL: null,
    }
  },
  created() {
    this.getUserAddress()
    this.CommodityOrderInfo()
    this.getUserAcquisition()
    this.URL = this.$axios.defaults.baseURL
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
    // 商品订单提交
    submitCommodityOrder(){
      // 提交数据处理
      const commodityInfos = []
      // 商品数据
      for (let i = 0; i < this.MyOrder.length; i++) {
        let info = {
          commodityId: this.MyOrder[i].commodityId,
          unitPrice: this.MyOrder[i].unitPrice,
          buyQuantity: this.MyOrder[i].buyQuantity,
          totalPrice: this.totalPrice[i].unitPrice * this.MyOrder[i].buyQuantity,
          commoditySku: this.MyOrder[i].commoditySku
        }
        commodityInfos.push(info)
      }

      if (!this.checked1) {
        this.score = 0
      }

      let data = {
        commodityInfos: commodityInfos,
        integral: this.score,
        whetherIntegral: this.checked1,
        useIntegralId: this.integral,
        addressId: this.addressId,
        remark: this.remark,
      }

      // 发送请求
      this.$axios({
        url: "order/submit",
        method: "post",
        headers: {"token": sessionStorage.getItem("token")},
        data: data
      }).then(msg => {
        if (msg.data.code === 200) {
          this.$router.push({
            name: 'OrderPay',
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

    // 收货地址id获取
    getAddressId(id){
      console.log("触发")
      this.addressId = id
    },

    // 积分减免计算
    CountAcquisition(){
      this.jiage = 0 //总价格
      this.yunfei = 0 // 运费
      this.totalPrice = 0 //实付款
      this.PointsReduction = 0 // 积分减免

      let price = 0
      if (this.checked1){
        price = this.score / 1000;
      }
      this.PointsReduction = price.toFixed(2)

      for (let i = 0; i < this.MyOrder.length; i++) {
        this.jiage += this.MyOrder[i].unitPrice * this.MyOrder[i].buyQuantity
        console.log(this.jiage)
        this.yunfei += this.MyOrder[i].deliveryFee
        console.log(this.yunfei)
      }

      this.totalPrice = this.jiage + this.yunfei - price
      this.totalPrice = this.totalPrice.toFixed(2)
      this.jiage = this.jiage.toFixed(2)
      this.yunfei = this.yunfei.toFixed(2)

    },

    // 用户积分获取
    getUserAcquisition(){
      this.$axios({
        url: "user/acquisition",
        method: "get",
        headers: {"token": sessionStorage.getItem("token")}
      }).then(msg => {
        if (msg.data.code === 200) {
          this.acquisition = msg.data.data.acquisition
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },

    // 订单价格计算
    priceCount(){
      this.jiage = 0
      this.yunfei = 0
      this.totalPrice = 0
      for (let i = 0; i < this.MyOrder.length; i++) {
        this.jiage += this.MyOrder[i].unitPrice * this.MyOrder[i].buyQuantity
        this.yunfei += this.MyOrder[i].deliveryFee
      }

      let count = 0
      if(this.checked1){
        count =  this.score / 1000
      }

      this.totalPrice = this.jiage + this.yunfei - count
      this.totalPrice = this.totalPrice.toFixed(2)
      this.jiage = this.jiage.toFixed(2)
      this.yunfei = this.yunfei.toFixed(2)
    },

    // 订单信息接收
    CommodityOrderInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));
      this.MyOrder = decodedData;

      for (let i = 0; i < this.MyOrder.length; i++) {
        this.jiage += this.MyOrder[i].unitPrice * this.MyOrder[i].buyQuantity
        this.yunfei += this.MyOrder[i].deliveryFee
      }

      this.totalPrice = this.jiage + this.yunfei
      this.totalPrice = this.totalPrice.toFixed(2)
      this.jiage = this.jiage.toFixed(2)
      this.yunfei = this.yunfei.toFixed(2)
    },




    // 获取用户收货地址
    getUserAddress(){
      this.$axios({
        url: "address",
        method: "get",
        headers: {
          'token': sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          // 获取数据
          let addresses = msg.data.data;

          // 找到status为1的地址
          const status1Address = addresses.find(address => address.status === 1);

          if (status1Address) {
            // 移除status为1的地址
            addresses = addresses.filter(address => address.status !== 1);
            // 将status为1的地址放在第一个位置
            addresses.unshift(status1Address);
          }

          // 将排序后的地址列表赋值给MyAddressList
          this.MyAddressList = addresses;
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 收货地址提交
    addAddress(){
      this.$axios({
        url: "address",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: this.address
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("添加成功")
          this.dialogVisible = false
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 获取所有街道信息
    getResidentialDistrictInfo(){
      if (this.address.area === null) {
        return false
      }
      this.$axios({
        url: `province/residential/${this.address.area}`,
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.residentialDistrictList = msg.data.data
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 获取区级信息
    getAreaInfo(){
      if (this.address.city === null) {
        return false
      }

      this.$axios({
        url: `province/area/${this.address.city}`,
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.areaList = msg.data.data
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 市级信息获取
    getCityInfo(){
      if (this.address.province === null) {
        return false;
      }

      this.$axios({
        url: `province/city/${this.address.province}`,
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.cityList =  msg.data.data
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 省级信息获取
    getAllProvince(){
      this.$axios({
        url: "province",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.provinceList = msg.data.data
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 关闭对话框
    dialogClose(){
      this.dialogVisible = false
      this.address = {
        province: null,
        city: null,
        area: null,
        residentialDistrict: null,
        fullAddress: null,
        consignee: null,
        contact: null,
      };

    },

    // 跳转到用户收货地址页面
    skipPath(path){
      let routeData = this.$router.resolve({
        name: path,
      });
      window.open(routeData.href, '_blank');
    },

  }
}
</script>

<style scoped>
.title-span{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 18px;
  color: black;
  margin-left: 9px;
  margin-top: 9px;
}

.input-width{
  width: 576px;
}

.select-width{
  width: 140px;
}

.order-submit{
  height: 56px;
  width: 140px;
  border-radius: 15px;
  background-color: #ff5000;
  color: #FFFFFF;
  font-weight: 600;
  font-size: 18px;
}

.link-top{
  height: 1px;
  border-top: solid #ACC0D8 1px;
  margin-bottom: 15px;
}

.div-shopping{
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 15px;
}
.shopping-price{
  font-size: 20px;
  font-weight: 600;
  color: #11192d;
}

.shopping-price-font{
  font-size: 19px;
  font-weight: 600;
  color: #11192d;
}

.div-t{
  background-color: #FFFFFF;
  border-radius: 15px;
  margin-bottom: 30px;
}

.span-details-address{
  font-size: 17px;
  font-weight: 600;
  display: block;
  width: 254px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.icon-t{
  font-size: 26px;
  line-height: 83px
}

.individual{
  width: 282px;
  border: 1px solid #e8caca;
  padding: 8px;
  border-radius: 15px;
  margin: 20px 22px 0;
  cursor: pointer;
}

/*.individual:active{*/
/*  border: 1px solid #ff0202;*/
/*}*/
.individual:focus{
  border: 1px solid #ff0202;
}


.span-address{
  line-height: 30px;
  font-size: 15px;
  display: block;
  width: 210px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.a-font{
  font-size: 18px;
  color: #11192d;

}

.div-address{

  display: flex;
  height: 70px;
  align-items: center;
  justify-content: space-between
}

.col-b{
  background-color: #FFFFFF;
  float: right;
  border-radius: 15px;
  background-image: linear-gradient(180deg, #c3eeff, #fff 100px);

}

.col-a{
  margin: 0 -17px;
}

.centrality{
  width: 85%;
  margin: auto;
}

.div-body{
  background-color: #f3f6f8;

}

</style>
