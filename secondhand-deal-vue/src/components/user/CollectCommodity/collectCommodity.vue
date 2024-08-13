<template>
  <div>
    <!--头部start-->
    <Common></Common>
    <!--头部end-->

    <!--收藏夹start-->
    <div style="margin-bottom: 20px">
      <div class="div-collect">
        <div style="width: 80%; margin: auto">
          <img src="/static/20240701143715.png" class="img1">
        </div>
      </div>
    </div>
    <!--收藏夹end-->

    <!--收藏商品展示start-->
    <div style="margin-bottom: 120px">
      <div class="div-body">
        <div>
          <el-tabs v-model="activeName" @tab-click="collectClick()">
            <!--全部商品start-->
            <el-tab-pane label="全部商品" name="1" >
              <div class="div-c">

                <div class="div-in" v-for="item in collectList" :key="item">
                  <el-link :underline="false" >
                    <div style="margin-bottom: 8px" @mouseenter="enter(item.id)" @mouseleave="leave(item.id)">
                      <img :src="`${URL}/${item.imgPath}`" class="img-dis">
                      <i class="el-icon-delete i-i" v-if="item.id === controlDelete" @click="collectCommodityDelete(item.id)"></i>
                    </div>
                    <div @click="commodityShow(item.id)">
                      <div style="margin-bottom: 8px" >
                        <span class="title-s">{{ item.title }}</span>
                      </div>
                      <div>
                        <div v-if="item.status === 2" style="margin-right: 102px">
                          <span class="span-price">¥</span>
                          <span class="span-price">{{ item.cprice }}</span>
                        </div>

                        <div v-else-if="item.status === 3">
                        <span style="font-size: 15px">
                          <i class="icon-zhanghao" style="font-size: 18px"></i>
                          商品已下架
                        </span>
                        </div>
                      </div>
                    </div>

                  </el-link>
                </div>
              </div>
            </el-tab-pane>
            <!--全部商品end-->

            <!--失效商品start-->
            <el-tab-pane label="失效" name="2">
              <div class="div-c">

                <div class="div-in"
                     v-for="item in collectList"
                     :key="item"
                     v-if="item.status === 3">
                  <el-link :underline="false">
                    <div style="margin-bottom: 8px"  @mouseenter="enter(item.id)" @mouseleave="leave(item.id)">
                      <img :src="`${URL}/${item.imgPath}`" class="img-dis">
                      <i class="el-icon-delete i-i" v-if="item.id === controlDelete" @click="collectCommodityDelete(item.id)"></i>
                    </div>
                    <div style="margin-bottom: 8px">
                      <span class="title-s">{{ item.title }}</span>
                    </div>
                    <div>
                      <div>
                        <span style="font-size: 15px">
                          <i class="icon-zhanghao" style="font-size: 18px"></i>
                          商品已下架
                        </span>
                      </div>
                    </div>
                  </el-link>
                </div>
              </div>
            </el-tab-pane>
            <!--失效商品end-->

          </el-tabs>
        </div>

      </div>
    </div>
    <!--收藏商品展示end-->


    <!--尾部start-->
    <CommonEnd></CommonEnd>
    <!--尾部end-->
  </div>
</template>

<script>
import Common from "../common/HomeCommon.vue";
import CommonEnd from "../common/CommonEnd.vue";
export default {
  name: "collectCommodity",
  components: {Common,CommonEnd},

  data(){
    return{
      // 删除图标控制
      controlDelete: null,

      // 卡片控制
      activeName: '1',

      // 商品状态显示
      status: 0,

      // 收藏商品存储
      collectList: [],

      URL: null,

    }
  },
  created() {
    this.getCollectCommodity()
    this.URL = this.$axios.defaults.baseURL
  },

  methods:{
    //收藏商品删除
    collectCommodityDelete(id){
      this.$axios({
        url: "collect/d",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {"collectId": id}
      }).then(msg => {
        if(msg.data.code === 200) {
          alert("删除成功")
          this.getCollectCommodity()
        } else {
          alert(msg.data.message)
        }
      })
    },

    commodityShow(id){
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


    enter(id){
      this.controlDelete = id
    },
    leave(){
      this.controlDelete = null
    },


    collectClick(){
      this.getCollectCommodity()
    },

    getCollectCommodity(){
      this.$axios({
        url: "/collect",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          this.collectList = msg.data.data
        } else {
          alert(msg.data.message)
        }
      })
    }

  }
}
</script>

<style scoped>
.i-i{
  position: absolute;
  margin: 11px -28px;
  font-size: 20px;
  color: #ffffff;
  background: rgba(0,0,0,0.5);
  width: 22px;
  height: 23px;
}

.span-price{
  color: #f40;
  font-size: 15px;
  font-weight: 600;
}

.title-s{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  font-size: 16px;
  color: black;
}

.img-dis{
  width: 210px;
  height: 210px;

}

.div-in{
  /*background-color: red;*/
  height: 280px;
  width: 200px;
  margin: 0 10px;
}

.div-c{
  display: flex;
  flex-wrap: wrap;
}

/deep/ .el-tabs__item{
  font-size: 18px;
  margin: 4px 1px;
}

.div-body{
  width: 80%;
  margin: auto;

}

.img1{
  float: left;
  margin: 18px;
}

.div-collect{
  height: 80px;
  background-color: #ff4400
}

</style>
