<template>
  <div style=";background-color: #f0eff2">
    <Common></Common>
    <!--搜素start-->
    <div style="height: 110px; margin-bottom: 30px">
      <el-row>
        <el-col :span="7" >
          <div>
            <img src="/static/校园购2.png" alt="" style="height: 130px">
          </div>
        </el-col>
        <el-col :span="16">

          <div class="div1">
            <el-input v-model="PageData.searchName">
              <template #suffix>
                <el-button style="height: 50px; width: 100px;border-radius: 50px; background-color: #fe0137; color: #FFFFFF" @click="searchCommodity">
                  <span style="font-size: 20px" >搜索</span>
                </el-button>
              </template>
            </el-input>
          </div>

        </el-col>
      </el-row>
    </div>
    <!--搜素end-->

    <!--商品展示start-->
    <div class="display">
      <div style="height: 80px"></div>


      <!--商品条件start-->
      <div>
        <div class="div2">
          <span class="next-span" @click="click2(1,$event)">
            综合
          </span>
          <span class="next-span" @click="click2(2, $event)">
            销量
          </span>

        </div>
      </div>

      <div class="divider"></div>

      <!--商品条件end-->

      <div style="margin-bottom: 60px">
        <div style=" width: 100%; display: flex; flex-wrap:wrap ">
          <div class="div-3" v-for="item in searchCommodityList" :key="item">
            <el-link :underline="false" class="el-link-h" @click="commodityX(item.id)">
              <div>
                <div>
                  <img :src="`${URL}/${item.imgPath}`" class="div-img">
                </div>
                <div style="width: 250px">
                  <span class="title-span">{{ item.commodityTitle }}</span>
                </div>
                <div style="height: 48px"></div>
                <div style="display: flex; margin-bottom: 10px; margin-left: 8px">
                  <span class="span-ziti">¥</span>
                  <div>
                    <span class="span-ziti" style="width: 80px; display: flex">{{ item.cprice }}</span>
                  </div>
                  <span style="font-size: 16px; margin: 3px 13px; color: #50607a" v-if="item.buyNumber === null">0人累计付款</span>
                  <span style="font-size: 16px; margin: 3px 13px; color: #50607a" v-else>{{ item.buyNumber }}人累计付款</span>
                </div>

                <div style="margin-left: 8px">
                  <span style="color: #50607a; font-size: 17px; float: left">校园购</span>
                </div>
              </div>
            </el-link>
          </div>

        </div>

      </div>

      <div style="height: 100px">
        <!--分页start-->
        <el-pagination

          :total="total"
          :page-size="this.PageData.PageSize"
          :current-page="this.PageData.PageNo"
          layout="prev, pager, next, jumper"
          @current-change="currentChange"
          @size-change="sizeChange"
        />
        <!--分页end-->
      </div>


    </div>
    <!--商品展示end-->
    <CommonEnd></CommonEnd>
  </div>
</template>

<script>
import Common from "../common/HomeCommon.vue";
import CommonEnd from "../common/CommonEnd.vue";
export default {
  name: "commoditySearch",
  components: {Common, CommonEnd},

  data(){
    return{
      // 搜素数据存储
      searchCommodityList: [],



      // 分页数据
      // 总条数
      total: null,

      // 分页查询数据
      PageData:{
        PageSize: 40, // 每页数据
        PageNo:1, // 页数
        searchName: null, // 搜素名称存储
        sortBy: null //排序字段
      },

      URL: null,
    }
  },

  created() {
    this.SearchClassify()
    this.URL = this.$axios.defaults.baseURL
  },

  mounted() {
    // 默认给第一个 span 元素添加 focus 类
    document.querySelector('.next-span').classList.add('focus');
  },

  methods:{
    // 点击颜色变化
    click2(count, event) {

      // 获取所有span元素并移除focus类
      document.querySelectorAll('.next-span').forEach(span => {
        span.classList.remove('focus');
      });

      // 给当前点击的span添加focus类
      event.target.classList.add("focus");

      if (count === 2) {
        // 根据销量进行排序
        this.PageData.sortBy = 6
        this.SearchClassify()
        this.PageData.sortBy = null
      } else if (count === 1) {
        this.SearchClassify()
      }
    },


    // 根据商品id查看商品详情
    commodityX(id) {
      this.$axios({
        url: `commodity/show/${id}`,
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.$router.push({
            name: "CommodityDetails",
            query: { data: encodeURIComponent(JSON.stringify(msg.data.data)) }
          })
        }else {
          alert(msg.data.message)
        }
      })
    },

    // 搜素商品
    searchCommodity(){
      sessionStorage.setItem("searchName", this.PageData.searchName)
      this.$axios({
        url: "commodity/search",
        method: "get",
        params: this.PageData,
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.total = msg.data.data.total
          this.searchCommodityList = msg.data.data.list
        } else {
          alert(msg.data.message)
        }
      })

    },


    // 页面跳转搜素名称获取,并进行搜索
    SearchClassify() {
      this.PageData.searchName =  sessionStorage.getItem("searchName")
      this.$axios({
        url: "commodity/search",
        method: "get",
        params: this.PageData,
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.total = msg.data.data.total
          this.searchCommodityList = msg.data.data.list
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    currentChange(page) {
      // console.log(page)
      this.PageData.PageNo = page;
      this.SearchClassify()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    sizeChange(size){
      this.PageData.PageSize = size;
      this.SearchClassify()
    },

  },


}
</script>

<style scoped>
.display{
  width: 86%;
  /*height: 100%;*/
  margin: auto;
  background-color: #FFFFFF;
  border-radius: 15px;
  margin-bottom: 100px;
}

/deep/ .el-input__inner{
  height: 50px;
  border-radius: 50px;

}
/deep/ .el-input__suffix-inner{
  margin: 0 -6px;
}

.div1{
  width: 800px;
  border: 1px solid #fe0137;
  border-radius: 50px;
  margin-top: 40px;
}

.divider {
  width: 100%;
  height: 1px;
  border-top: solid #b0acac 1px;
  margin-bottom: 30px;
}

.div2{
  display: flex;
  margin-bottom: 20px;
}

.next-span{
  font-size: 24px;
  padding: 9px 18px;
  cursor: pointer;
}

.next-span.focus{
  color: red;
  font-weight: 600;
}
.span-ziti{
  color: #ff6200;
  font-size: 23px;
  font-weight: 700;
}

.el-link-h:hover{
  border: 1px solid #ff6200;
  border-radius: 12px;
}

.title-span{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 18px;
  color: black;
}

.div-3{
  width: 305px;
  margin-bottom: 20px;
  padding: 0 14px;
}

.div-img{
  width: 290px;
  border-radius: 10px
}

</style>
