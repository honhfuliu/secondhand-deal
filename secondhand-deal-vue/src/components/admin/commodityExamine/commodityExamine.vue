<!--商品审核页面-->
<template>
  <div style="width: 99%; height: 100%; background-color: #FFFFFF">
    <!--头部start-->
    <div>
      <el-row>
        <el-col :span="10">
          <h3 style="float: left; margin-left: 20px">
            <i class="icon iconfont icon-icon-mail"/>
            商品审核
          </h3>
        </el-col>
      </el-row>
    </div>
    <!--头部end-->

    <div class="divider"></div>

    <!--表格start-->
    <div style="width: 95%; margin: auto">
      <el-table
        :header-cell-style="{ fontSize: '15px', background: '#60c1b9', color: 'black', textAlign: 'center'}"
        max-height="640"
        :data="AdminCommodityList"
      >
        <el-table-column label="商品名称"  align="center" width="500">
          <template  v-slot="scope">
            <div style="display: flex">
              <div>
                <img :src="`${img}/${scope.row.imgPath}`" style="width: 90px; height: 90px; float: left">
              </div>

              <div style="margin: 30px 20px">
                <span style="font-size: 19px">{{ scope.row.commodityTitle }}</span>
              </div>

            </div>
          </template>
        </el-table-column>
        <el-table-column label="价格"  align="center" prop="cprice"></el-table-column>
        <el-table-column label="状态"  align="center" >
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.status === 0">待审核</el-tag>
            <el-tag type="danger" v-else-if="scope.row.status === 1">商品违规</el-tag>
            <el-tag type="success" v-else-if="scope.row.status === 2">上架</el-tag>
            <el-tag type="success" v-else-if="scope.row.status === 3">下架</el-tag>
            <el-tag type="danger" v-else>状态错误</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="发布时间"  align="center" prop="createData"></el-table-column>
        <el-table-column label="详情"  align="center" >
          <template v-slot="scope">
            <el-link :underline="false" @click="getByIdDetailsCommodity(scope.row.id)">商品审核</el-link>
          </template>
        </el-table-column>
      </el-table>

      <!--分页start-->
      <el-pagination
        :total="this.total"
        :page-size="this.PageData.PageSize"
        :current-page="this.PageData.PageNo"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5,10,15,20]"
        @current-change="currentChange"
        @size-change="sizeChange"
      />
      <!--分页end-->
    </div>
    <!--表格end-->

  </div>

</template>

<script>
export default {
  name: "commodityExamine",

  data(){
    return{
      // 获取后端地址
      img: this.$axios.defaults.baseURL,

      // 商品数据存储
      AdminCommodityList: [],

      // 分页查询参数
      total: 0,
      PageData: {
        PageNo: 1,
        PageSize: 5,

      },

    }
  },

  created() {
    this.getCommodity()
  },

  methods: {

    // 根据商品id获取到商品详情信息
    getByIdDetailsCommodity(id) {
      this.$axios({
        url: `/adminCommodity/${id}`,
        method: "get",
        headers: {
          "token": sessionStorage.getItem("adminToken")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          // 路由跳转
          this.$router.push({
            name: "CommodityVerify",
            query: { data: encodeURIComponent(JSON.stringify(msg.data.data)) }
          })
        }else {
          alert(msg.data.message)
        }
      })

    },


    // 分页获取为审核的商品
    getCommodity(){
      this.$axios({
        url:"/adminCommodity/all",
        method: "get",
        params: this.PageData,
        headers: {
          "token": sessionStorage.getItem("adminToken")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          this.total = msg.data.data.total
          this.AdminCommodityList = msg.data.data.list
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    currentChange(page) {
      // console.log(page)
      this.PageData.PageNo = page;
      this.getCommodity()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    sizeChange(size){
      this.PageData.PageSize = size;
      this.getCommodity()
    },

  }
}
</script>

<style scoped>
.divider {
  width: 100%;
  height: 1px;
  border-top: solid #797070 1px;
  margin-bottom: 20px;
}

</style>
