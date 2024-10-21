<!--收货地址-->
<template>
  <div class="top">

    <div class="div-address">
        <el-row>
          <el-col :span="12">
            <span class="span-title">我的收货地址</span>
          </el-col>
          <el-col :span="12">
            <el-button style="float: right" @click="dialogVisible = true">添加地址</el-button>
          </el-col>
        </el-row>
    </div>

    <div>
      <el-table
        :header-cell-style="{ fontSize: '16px', color: 'black', background: '#f7fafd', }"
        max-height="640"
        :data="MyAddressList"
      >
        <el-table-column label="收货人" prop="name" width="100"></el-table-column>
        <el-table-column label="手机/电话" prop="contact" width="150"></el-table-column>
        <el-table-column label="所在地区" width="270">
          <template v-slot="scope">
            <span>{{scope.row.provinceName}} &nbsp; {{scope.row.cityName}} &nbsp; {{scope.row.areaName}} &nbsp; {{scope.row.streetName}}</span>
          </template>
        </el-table-column>
        <el-table-column label="详细地址" prop="fullAddress"  width="300"></el-table-column>
        <el-table-column label="邮编">
          <template>
            <span>000000</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-link :underline="false" @click="modifyTheDeliveryAddressInformationToObtain(scope.row.id)">修改</el-link>
            <el-link :underline="false" @click="DeleteAddressBasedOnShippingAddressId(scope.row.id)">删除</el-link>
          </template>
        </el-table-column>
        <el-table-column label="移动设置">
          <template v-slot="scope">
            <el-tag v-if="scope.row.status === 1" type="danger">默认地址</el-tag>
            <el-tag v-else @click="setAcquiesceAddress(scope.row.id)">设为默认</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--添加地址start-->
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
          <el-button type="primary" @click="JudgeWhetherModifyOrAdd">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!--添加地址end-->

  </div>

</template>

<script>







export default {
  name: "MyAddress",

  data(){
    return{
      //  对话框控制
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

      // 我的收货地址信息
      MyAddressList: [],


    }
  },

  created() {
    this.getAddress()
  },

  methods:{
    // 默认地址设置
    setAcquiesceAddress(id) {
      this.$axios({
        url: "address/set",
        method:"post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data:{"newAddress": id}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("设置成功")
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 删除收货地址
    DeleteAddressBasedOnShippingAddressId(id){
      this.$axios({
        url: "address/delete",
        method: "post",
        headers: {
          'token': sessionStorage.getItem("token")
        },
        data: {"status": id}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("删除成功")
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 修改数据提交
    updateAddressSubmit(){
      this.$axios({
        url: "address/update",
        method: "post",
        headers: {
          'token': sessionStorage.getItem("token")
        },
        data: this.address
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("修改成功")
          this.dialogVisible = false
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })

    },

    // 判断是添加还是修改
    JudgeWhetherModifyOrAdd(){
      if (this.address.addressId == null) {
        this.addressSubmit()
      } else {
        this.updateAddressSubmit()
      }
    },


    // 修改收货地址信息获取
    modifyTheDeliveryAddressInformationToObtain(id){
      this.dialogVisible = true;

      // 数据获取
      let currentAddress = this.MyAddressList.find(address => address.id === id);

      this.provinceList = [{name: currentAddress.provinceName, code: currentAddress.provinceCode}]
      this.cityList = [{ name: currentAddress.cityName, code: currentAddress.cityCode }]
      this.areaList = [{name: currentAddress.areaName, code: currentAddress.areaCode}]
      this.residentialDistrictList = [{name: currentAddress.streetName, code: currentAddress.streetCode}]
      this.address.province = currentAddress.provinceCode
      this.address.city = currentAddress.cityCode
      this.address.area = currentAddress.areaCode
      this.address.street = currentAddress.streetCode

      this.address.addressId = currentAddress.id;
      this.address.contact  = currentAddress.contact;
      this.address.addressName = currentAddress.name;
      this.address.fullAddress = currentAddress.fullAddress;




    },

    // 获取用户收货地址
    getAddress(){
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
    addressSubmit(){
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

  }
}
</script>

<style scoped>
.top{
  position: fixed;
  width: 73%;
}

.el-form-item{
  margin-bottom: 40px;
}
.input-width{
  width: 576px;
}

.select-width{
  width: 140px;
}
.div-address{
  padding: 30px;
}

.span-title{
  font-size: 20px;
  font-weight: 600;
  margin-left: -21px;
  float: left;
}


</style>
