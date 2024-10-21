<template>

  <div style="background-color: #fbf2f2">
    <!--公共头部start-->
    <Common></Common>
    <!--公共头部end-->

    <!--搜索框start-->
    <div style="height: 160px">
      <el-row>
        <el-col :span="7" >
          <div>
            <img src="/static/校园购2.png" alt="" style="height: 140px; margin-top: 10px">
          </div>
        </el-col>
        <el-col :span="16">

          <div class="div1">
            <el-input v-model="searchInfo">
              <template #suffix>
                <el-button style="height: 50px; width: 100px;border-radius: 50px; background-color: #fe0137; color: #FFFFFF" @click="search()">
                  <span style="font-size: 20px" >搜索</span>
                </el-button>
              </template>
            </el-input>
          </div>

        </el-col>
      </el-row>

    </div>
    <!--搜索框end-->


    <div  style="width: 85%; background-color: #FFFFFF; margin: auto; border-radius: 15px">
      <!--个人信息 走马灯 商品分类start-->
      <div style="margin-bottom: 60px">
        <el-row>
          <!--分类start-->
          <el-col :span="5" style="height: 600px ">
            <div style="width: 100%; margin: 22px 31px; display: flex">
              <span style="font-weight: 600; color: #fe0137; font-size: 19px; ">商品分类</span>
            </div>

            <div style="width: 92%; float: right">
              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma "></i>
                </div>
                <el-link :underline="false" @click="searchClassify('女装')">女装</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('内衣')">内衣</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('男装')">男装</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('户外运动')">户外运动</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('女鞋')">女鞋</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('男装')">男鞋</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('箱包')">箱包</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('美妆')">美妆</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('个人护肤')">个人护肤</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('腕表')">腕表</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('个人眼镜')">个人眼镜</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('珠宝饰品')">珠宝饰品</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('手机')">手机</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('电脑')">电脑</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('个人办公')">个人办公</el-link>
              </div>


              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('母婴玩具')">母婴玩具</el-link>
              </div>


              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('零食')">零食</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('茶酒')">茶酒</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('进口零食')">进口零食</el-link>
              </div>


              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('水果生鲜')">水果生鲜</el-link>
              </div>


              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('大家电')">大家电</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('生活电器')">生活电器</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('家具')">家具</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('灯具')">灯具</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('卫浴')">卫浴</el-link>
              </div>


              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('汽车')">汽车</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('配件')">配件</el-link>
                <div style="margin: 8px 8px; font-size: 20px">/</div>
                <el-link :underline="false" @click="searchClassify('用品')">用品</el-link>
              </div>

              <div style=" height: 40px; display: flex">
                <div style="margin: 11px 15px">
                  <i class="icon iconfont icon-yanzhengma i-1"></i>
                </div>
                <el-link :underline="false" @click="searchClassify('图书音像')">图书音像</el-link>
              </div>

            </div>
          </el-col>
          <!--分类end-->

          <!--走马灯start-->
          <el-col :span="12" style="height: 600px">
            <div style="height: 560px; padding: 25px">
              <template>
                <el-carousel indicator-position="outside" height="550px">
                  <el-carousel-item v-for="item in imgList" :key="item">
                    <!--                  <h3>{{ item }}</h3>-->
                    <el-link :underline="false" :href="item.a">
                      <img :src="item.img" style="height: 540px; width: 773px; border-radius: 15px; ">
                    </el-link>
                  </el-carousel-item>
                </el-carousel>
              </template>
            </div>
          </el-col>
          <!--走马灯end-->

          <!--个人信息start-->
          <el-col :span="7" style="height: 600px ">
            <div style="width: 90%; background-color: #f3f3f3; margin: 25px 11px; border-radius: 15px">

              <div style="height: 94px">
                <img src="/static/16196625695897672.jpg" style="width: 70px; height: 70px; border-radius: 50%; margin: 20px">
              </div>
              <div style="margin-bottom: 20px">
                <span>你好欢迎来到二手交易</span>
              </div>

              <div style="margin-bottom: 20px">
                <el-button type="warning" round>我要买</el-button>
                <el-button type="warning" round>我要卖</el-button>
              </div>

              <div style="margin-bottom: 20px">
                <el-link :underline="false" style="margin: 10px 35px" @mouseover.native="handleMouseHelp(1)">公告</el-link>
                <el-link :underline="false" style="margin: 10px 35px" @mouseover.native="handleMouseHelp(2)">指南</el-link>
                <el-link :underline="false" style="margin: 10px 35px" @mouseover.native="handleMouseHelp(3)">帮助</el-link>
              </div>

              <div style="margin-bottom: 20px">
                <div style="width: 80%;display: grid;" v-if="this.helpElement === 1">
                  <el-link style="margin-bottom: 15px; margin-left: 44px" :underline="false">关于“终身找回包赔”服务的有关声明</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书5645</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书6666</el-link>
                </div>

                <div style="width: 80%;display: grid;" v-else-if="this.helpElement === 2">
                  <el-link style="margin-bottom: 15px" :underline="false">【指南】如何注册二手交易平台？</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【指南】如何登录二手交易平台？</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【指南】忘记密码如何找回？</el-link>
                </div>

                <div style="width: 80%;display: grid;" v-else-if="this.helpElement === 3">
                  <el-link style="margin-bottom: 15px" :underline="false">66“终身找回包赔”服务的有关声明</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书5645</el-link>
                  <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书6666</el-link>
                </div>
              </div>

              <div style="width: 90%; height: 160px; margin: auto; display: flex; flex-wrap: wrap">
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>
                <div style="display: grid; height: 90px; padding: 0 20px">
                  <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                  <span style="margin-top: -20px">安心购</span>
                </div>

              </div>



            </div>
          </el-col>
        </el-row>
      </div>
      <!--个人信息 走马灯 商品分类end-->

      <!--推荐商品start-->
      <div>
        <div style="margin-bottom: 20px">
          <div style="font-size: 28px;  display: flex; margin-left: 35px">猜你喜欢</div>
        </div>
        <div>

          <div style="display: flex; flex-wrap: wrap">

            <div class="div-3" v-for="item in commodityShow" :key="item">
              <el-link :underline="false" class="el-link-h" @click="commodityDetails(item.id)">
                <div>
                  <div>
                    <img :src="`${URL}/${item.imgPath}`" class="div-img">
                  </div>
                  <div style="width: 280px; margin-bottom: 20px; height: 49px">
                    <span class="title-span">{{ item.commodityTitle }}</span>
                  </div>
                  <div style="display: flex; margin-bottom: 10px; margin-left: 8px">
                    <span class="span-ziti">¥</span>
                    <div>
                      <span class="span-ziti" style="width: 80px; display: flex">{{ item.cprice }}</span>
                    </div>
                    <span style="font-size: 16px; margin: 3px 13px; color: #50607a" v-if="item.buyNumber === null">0人累计付款</span>
                    <span style="font-size: 16px; margin: 3px 13px; color: #50607a" v-else>{{ item.buyNumber }}人累计付款</span>
                  </div>

                </div>
              </el-link>
            </div>

          </div>


        </div>
      </div>
      <!--推荐商品end-->


    </div>
    <div style="height: 150px"></div>




    <!--公共start-->
    <CommonEnd></CommonEnd>
    <!--公共end-->


  </div>
</template>

<script>
import Common from "../common/Common.vue";
import CommonEnd from "../common/CommonEnd.vue";

export default {
  name: "Index",
  components: {CommonEnd, Common},

  data(){
    return{
      // 搜素数据存储
      searchInfo: null,

      // 走马灯图片存储
      imgList: [
        {a:"/login", img:"/static/1-1.png"},
        {a:"/login", img:"/static/1-2.png"},
        {a:"/login", img:"static/1-3.png"},
        {a:"/login", img:"static/1-4.png"},
      ],


      // 公共 指南 帮助 影藏元素控制
      helpElement: 1,

      // 商品展示数据存储
      commodityShow: [],

      URL: null,
    }
  },

  created() {
    this.getCommodity()
    this.URL = this.$axios.defaults.baseURL
  },

  methods:{
    // 商品搜素
    search(){
      if (this.searchInfo != null){
        this.searchClassify(this.searchInfo)
      }

    },

    // 分类跳转
    searchClassify(classify){
      sessionStorage.setItem("searchName", classify)
      this.$router.push({
        name: "Search",
      })
    },


    //推荐商品获取
    getCommodity(){
      this.$axios({
        url: "commodity/show",
        method: "get",
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.commodityShow = msg.data.data
          console.log(this.commodityShow)
        } else {
          alert(msg.data.message)
        }
      })
    },


    // 获取商品详情信息并跳转到商品展示页面
    commodityDetails(id){
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

    // 商品分类影藏元素控制
    handleMouseMove(number) {
      this.hovers = number;

    },
    handleMouseLeave(){
      this.hovers = null
    },

    // 公告影藏元素控制
    handleMouseHelp(number){
      this.helpElement = null
      this.helpElement = number
      console.log(this.helpElement)
    },

  },
}
</script>

<style scoped>
.div1 {
  width: 800px;
  border: 1px solid #fe0137;
  border-radius: 50px;
  margin-top: 80px;

}

/deep/ .el-input__inner{
  height: 50px;
  border-radius: 50px;

}

/deep/ .el-input__suffix-inner{
  margin: 0 -6px;
}

.div-3{
  width: 301px;
  margin-bottom: 20px;
  padding: 0 14px;
}

.div-img{
  width: 290px;
  height: 290px;
  border-radius: 10px
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

.span-ziti{
  color: #ff6200;
  font-size: 23px;
  font-weight: 700;
}




</style>
