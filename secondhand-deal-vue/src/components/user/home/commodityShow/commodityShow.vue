<template>
  <div style="position: fixed; top: 145px; z-index: 999; width: 70%">
    <!--头部start-->
    <div >
      <el-row>
        <el-col>
          <el-form :inline="true" style="float: right">

            <el-form-item label="商品名称">
              <el-input v-model="PageData.commodityTitle"></el-input>
            </el-form-item>

            <el-form-item label="状态">
              <el-select v-model="PageData.status" placeholder="选择商品状态">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item >
              <el-button @click="getCommodityInfo">查询</el-button>
              <el-button @click="refresh">重置</el-button>
            </el-form-item>

          </el-form>
        </el-col>
      </el-row>
    </div>
    <!--头部end-->


    <!--表格start-->
    <div >
      <!--表格start-->
      <el-table
        :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
        max-height="640"
        :data="commodityList"
      >
        <el-table-column label="商品名称"  width="500">
          <template  v-slot="scope">
            <div style="display: flex">
              <div>
                <img :src="`${img}/${scope.row.imgPath}`" style="width: 90px; height: 90px; float: left">
              </div>

              <div style="margin: 30px 20px">
                <span style="font-size: 19px" class="title_s">{{ scope.row.commodityTitle }}</span>
              </div>

            </div>
          </template>
        </el-table-column>
        <el-table-column label="价格"  sortable align="center"  prop="price"></el-table-column>
        <el-table-column label="库存" sortable align="center" prop="cnumber"></el-table-column>
        <el-table-column label="总销量" sortable align="center" prop="buyNumber">
          <template v-slot="scope">
            <span v-if="scope.row.buyNumber === null">0</span>
            <span v-else>{{ scope.row.buyNumber }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发布时间" sortable align="center" prop="createData"></el-table-column>
        <el-table-column label="状态" align="center" >
          <template v-slot="scope">
            <el-tag v-if="scope.row.status === 0">待审核</el-tag>
            <el-tag type="danger" v-else-if="scope.row.status === 1">商品违规</el-tag>
            <el-tag type="success" v-else-if="scope.row.status === 2">上架</el-tag>
            <el-tag type="warning" v-else-if="scope.row.status === 3">下架</el-tag>
            <el-tag type="danger" v-else>状态错误</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" >
          <template v-slot="scope">
            <div style="display: grid">
              <el-link :underline="false" @click="getByIdCommodityInfo(scope.row.id)">修改商品</el-link>
              <el-link :underline="false" @click="getByIdCommodityDelete(scope.row.id)">删除商品</el-link>
              <el-link v-if="scope.row.status === 1" :underline="false" @click="rejectReason(scope.row.rejectReason)">违规原因</el-link>
              <el-link :underline="false" v-if="scope.row.status === 2" @click="removedFromShelvesCommodity(scope.row.id)">下架商品</el-link>
              <el-link :underline="false" v-if="scope.row.status === 3" @click="onShelvesCommodity(scope.row.id)">上架商品</el-link>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--表格end-->

    <!--分页start-->
    <el-pagination
      style="float: right"
      :total="total"
      :page-size="this.PageData.PageSize"
      :current-page="this.PageData.PageNo"
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[5,10,15,20]"
      @current-change="currentChange"
      @size-change="sizeChange"
    />
    <!--分页end-->


  </div>

</template>

<script>
import axios from "axios";
export default {
  name: "commodityShow",

  data(){
    return{

      // 状态选择数据
      options: [{
        value: 0,
        label: '审核中'
      }, {
        value: 1,
        label: '违规商品'
      }, {
        value: 2,
        label: '上架'
      }, {
        value: 3,
        label: '下架'
      }],

      // 获取后端地址
      img: this.$axios.defaults.baseURL,

      // 分页数据
      // 总条数
      total: null,

      // 分页查询数据
      PageData:{
        PageSize: 5, // 每页数据
        PageNo:1, // 页数
        commodityTitle: null, // 名称
        status: null, //状态
      },

      //商品查询数据存储
      commodityList: [],

    }
  },

  created() {
    this.getCommodityInfo()
  },

  methods: {
    // 重置
    refresh(){
      location.reload();
    },

    // 违规原因显示
    rejectReason(value){
      alert(value)
    },

    // 下架商品
    removedFromShelvesCommodity(id){
      this.$axios({
        url: `/commodity/removedFromShelves/${id}`,
        method: "post",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 上架商品
    onShelvesCommodity(id){
      this.$axios({
        url: `/commodity/onShelvesCommodity/${id}`,
        method: "post",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 根据id删除商品
    getByIdCommodityDelete(id){
      this.$axios({
        url: `/commodity/d/${id}`,
        method: "post",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("删除成功")
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })

    },

    // 根据id获取到商品信息
    getByIdCommodityInfo(id) {
      this.$axios({
        url: `/commodity/${id}`,
        method: "get",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200){
          // 路由跳转并传递商品数据
          this.$router.push({
            name: 'CommodityRelease',
            query: { data: encodeURIComponent(JSON.stringify(msg.data.data)) }
          });
        }else {
          alert(msg.data.message)
        }
      })
    },

    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    currentChange(page) {
      // console.log(page)
      this.PageData.PageNo = page;
      this.getCommodityInfo()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    sizeChange(size){
      this.PageData.PageSize = size;
      this.getCommodityInfo()
    },

    // 获取用户发布商品列表
    getCommodityInfo()
    {
      this.$axios({
        url: "/commodity/select",
        method: "get",
        params: this.PageData,
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200)
        {
          this.commodityList = msg.data.data.list
          this.total = msg.data.data.total
          // console.log(msg.data.data)
        } else
        {
          alert(msg.data.message)
        }
      })
    }

  },
}
</script>

<style scoped>
.title_s{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 18px;
  color: black;
}

::v-deep .header1{
  position: fixed;
  top: 190px;
}

</style>
