<!--商品发布-->
<template>
  <div style="  background-color: #E3E3E3; ">
    <div style="width: 70%; margin: auto;">
      <div style="display: flex; padding: 75px 0px; height: 44px">
        <p class="p1">二手交易</p>
        <p class="p2">商品发布</p>
      </div>

      <div style="width: 100%; height: 40px;background-color: #007DDB; ">
        <span style="float: left; font-size: 18px; margin: 7px 5px">商品发布</span>
      </div>

      <div style="width: 100%; height: 100%;background-color: #FFFFFF; ">
        <!--商品主图start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *上传商品主图
            </span>
          </el-col>

          <el-col :span="18">
            <div>
              <span style="float: left; margin: 4px 10px; color: #949393">上传高清商品主图</span>
            </div>
            <div style="display: flex; margin-top: 40px">

              <div class="ava">
                <el-upload
                  class="avatar-uploader"
                  action="#"
                  list-type="picture-card"
                  :limit="5"
                  :on-exceed="masterFileMax"
                  :file-list="update.fileList"
                  :auto-upload="false"
                  :show-file-list="true"
                  :on-change="handleChange"
                  :before-upload="beforeUpload"
                  accept="image/jpeg,image/png">
                  <i  class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>

              </div>

            </div >
          </el-col>
        </el-row>
        <!--商品主图end-->


        <!--商品分类start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *确认商品分类
            </span>
          </el-col>

          <el-col :span="18" style="">
            <div>
              <span style="float: left; margin: 4px 10px; color: #949393">请确认或修改商品类目</span>
            </div>
            <div style="display: flex; margin-top: 40px; margin-left: 20px" @click="ClassificationAll">
              <el-cascader
                style="width: 30%"
                placeholder="分类"
                ref="elCascader"
                v-model="classifyId1"
                :options="ClassificationList"
                :props="cascaderProps"
                :clearable="true"
                :collapse-tags="true"
                @change="handleChangeCascader"
              >

              </el-cascader>
            </div>


          </el-col>
        </el-row>
        <!--商品分类end-->

        <!--商品标题start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *宝贝标题
            </span>
          </el-col>

          <el-col :span="18" >
            <el-input placeholder="请输入商品的标题" style="margin: 0 20px" v-model="addCommodity.commodityTitle"></el-input>
            <p style="float: left; color: #afafaf; margin: 7px 20px">建议将以下推荐词添加至标题中以提升您的商品表达，请只添加符合您商品描述的内容</p>
          </el-col>
        </el-row>
        <!--商品标题end-->

        <!--商品属性start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *商品属性
            </span>
          </el-col>

          <el-col :span="18" >
            <el-form :inline="true" style="margin-top: 33px">
              <el-form-item label="品牌："  >
                <el-input type="text" class="input1" v-model="addCommodity.brand"></el-input>
              </el-form-item>
              <el-form-item label="产地：" >
                <el-select v-model="addCommodity.locality" placeholder="请选择" class="input1">
                  <el-option
                    v-for="item in locality"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>


              <el-form-item label="是否整装："  style="margin-left: -24px">
                <el-select v-model="addCommodity.isinteger" placeholder="请选择" class="input1">
                  <el-option
                    v-for="item in isInteger"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="风格："  >
                <el-select v-model="addCommodity.style" placeholder="请选择" class="input1">
                  <el-option
                    v-for="item in style"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="图案：" >
                <el-select v-model="addCommodity.design" placeholder="请选择" class="input1">
                  <el-option
                    v-for="item in design"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="材质：" >
                <el-input type="text" class="input1" v-model="addCommodity.material"></el-input>
              </el-form-item>

              <el-form-item label="适用对象：" style="margin-right: 406px">
                <el-select v-model="addCommodity.suitableObject" placeholder="请选择" class="input1">
                  <el-option
                    v-for="item in suitableObject"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <!--商品属性end-->

        <!--商品类型start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *商品类型
            </span>
          </el-col>

          <el-col :span="18" style="float: inherit; margin: 39px 37px">
            <template>
              <el-radio v-model="addCommodity.commodityType" label="全新">全新</el-radio>
              <el-radio v-model="addCommodity.commodityType" label="99新">99新</el-radio>
              <el-radio v-model="addCommodity.commodityType" label="95新">95新</el-radio>
              <el-radio v-model="addCommodity.commodityType" label="90新">90新</el-radio>
              <el-radio v-model="addCommodity.commodityType" label="85新">85新</el-radio>
              <el-radio v-model="addCommodity.commodityType" label="85以下">85以下</el-radio>
            </template>
          </el-col>
        </el-row>
        <!--商品类型end-->

        <!--商品价格start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *商品价格
            </span>
          </el-col>

          <el-col :span="18" >
            <el-input-number
              :precision="2"
              placeholder="请输入商品的价格"
              style="margin: -3px 21px; width: 300px; float: left"
              v-model="addCommodity.cprice"
            ></el-input-number>
          </el-col>
        </el-row>
        <!--商品价格end-->

        <!--商品数量start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *商品数量
            </span>
          </el-col>

          <el-col :span="18" >
            <el-input-number
              placeholder="请输入商品的数量"
              style="margin: -3px 21px; width: 300px; float: left"
              v-model="addCommodity.cnumber"
            ></el-input-number>
          </el-col>
        </el-row>
        <!--商品数量end-->


        <!--配送方式start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *配送方式
            </span>
          </el-col>

          <el-col :span="18" >

            <div style="display: flex; margin: 8px 22px">
              <el-radio v-model="addCommodity.deliveryMethod" label="上门自取">上门自取</el-radio>
              <el-radio v-model="addCommodity.deliveryMethod" label="配送上门">配送上门</el-radio>
            </div>

            <div v-show="addCommodity.deliveryMethod === '配送上门'" style="width: 300px ;margin: 18px">
              <el-input placeholder="请输入配送费" v-model="addCommodity.shippingFees"></el-input>
            </div>

          </el-col>
        </el-row>
        <!--配送方式end-->



        <!--详情描述start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              详情描述
            </span>
          </el-col>

          <el-col :span="18" >
            <div style="border: 1px solid #ccc; margin-top: 30px; margin-left: 20px">
              <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editor"
                :defaultConfig="toolbarConfig"
                :mode="mode"
              />
              <Editor
                style="height: 500px; overflow-y: hidden;"
                v-model="html"
                :defaultConfig="editorConfig"
                :mode="mode"
                @onCreated="onCreated"
              />
            </div>
          </el-col>

        </el-row>
        <!--详情描述end-->

        <!--提交start-->
        <el-row style="padding: 150px 0">
          <el-col>
            <el-button type="primary" @click="commoditySubmit">提交</el-button>
            <el-button type="primary">取消</el-button>
          </el-col>

        </el-row>
        <!--提交end-->


      </div>
    </div>
  </div>
</template>

<script>
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'

import '@wangeditor/editor/dist/css/style.css'

import Update from '../../../package/update/index.vue'

import axios from "axios";


export default {
  name: "CommodityRelease",
  components: { Update, Editor, Toolbar },

  data() {
    return{
      // 接收修改的数据参数
      commodityUpdateInfo: null,

      update: {
        fileList: [],
      },

      // 分类数据存储
      ClassificationList: [],

      // 级联属性绑定
      cascaderProps: {
        expandTrigger: 'hover',
        value: 'classificationId',
        label: 'classificationName',
        children: 'children',
        checkStrictly: true,
        // multiple: true
      },

      //商品分类id存储
      classifyId1 : null,




      //新增商品数据存储
      addCommodity:{
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
        commodityPicturePaths: [], // 商品主图地址存储
        commodityDetails: null, // 富文本信息
      },

      //富文本编辑框配置
      editor: null,
      html: '<p></p>',
      toolbarConfig: { },
      // editorConfig: { placeholder: '请输入内容...' },
      mode: 'default', // or 'simple',

      editorConfig: {
        placeholder: '请输入内容...',

        // 所有的菜单配置，都要在 MENU_CONF 属性下
        MENU_CONF:{
          // 图片上传配置
          uploadImage: {
            // form-data fieldName ，默认值 'wangeditor-uploaded-image'
            fieldName: 'file',

            // 后端接口请求地址
            server:  axios.defaults.baseURL + "/commodity/picture",

            // 单个文件的最大体积限制，默认为 2M
            maxFileSize: 2 * 1024 * 1024, // 1M

            // 请求头配置
            // 自定义增加 http  header
            headers: {
              token: sessionStorage.getItem("token")
            },


            // 自定义插入图片
            customInsert(res, insertFn) {
              // res 即服务端的返回结果
              // 从 res 中找到 url alt href ，然后插入图片
              const url = axios.defaults.baseURL + "/" + res.data.url;

              const alt = res.data.alt;
              const href = res.data.href;
              insertFn(url, alt, href)
            },


          },

        },
      },



      // 产地select数据
      locality: [{
        value: '中国大陆',
        label: '中国大陆'
      },{
        value: '其它',
        label: '其它'
      }],

      // 是否整装
      isInteger: [{
        value: '是',
        label: '是'
      },{
        value: '否',
        label: '否'
      }],

      // 风格
      style: [{
        value: '其它',
        label: '其它'
      },{
        value: '其它',
        label: '其它'
      }],

      // 图案
      design: [{
        value: '其它',
        label: '其它'
      },{
        value: '其它',
        label: '其它'
      }],

      // 适用人群
      suitableObject: [{
        value: '儿童',
        label: '儿童'
      },{
        value: '青少年',
        label: '青少年'
      },{
        value: '中年',
        label: '中年'
      },{
        value: '孕妇',
        label: '孕妇'
      },{
        value: '老年',
        label: '老年'
      },{
        value: '所有',
        label: '所有'
      }],

    }
  },

  created() {
    // 接收页面传递的参数并赋值
    this.updateCommodityInfo();


  },


  methods:{
    // 修改商品详情信息接收，并赋值
    updateCommodityInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));
      console.log(decodedData)

      // 主图
      // this.update.fileList = decodedData.commodityPicturePaths;
      for (let i = 0; i < decodedData.commodityPicturePaths.length; i++) {
        let path = {
          url: this.$axios.defaults.baseURL  + "/" + decodedData.commodityPicturePaths[i],
          name: decodedData.commodityPicturePaths[i]
        }
        this.update.fileList.push(path);
      }

      // 商品分类
      this.ClassificationList = [
        {
          classificationId: -1,
          classificationName: decodedData.classificationName
        }
      ]

      this.classifyId1 = -1;



      this.addCommodity.commodityTitle = decodedData.commodityTitle; // 商品名称
      this.addCommodity.brand = decodedData.brand; // 品牌
      this.addCommodity.locality = decodedData.locality; //产地
      this.addCommodity.isinteger = decodedData.isinteger; //是否整装
      this.addCommodity.style = decodedData.style; //风格
      this.addCommodity.design = decodedData.design; //图案
      this.addCommodity.material = decodedData.material; //材质
      this.addCommodity.suitableObject = decodedData.suitableObject; //适用对象
      this.addCommodity.commodityType = decodedData.commodityType;  //商品类型
      this.addCommodity.cprice = decodedData.cprice; //价格
      this.addCommodity.cnumber = decodedData.cnumber; //数量
      //配送方式
      this.addCommodity.deliveryMethod = decodedData.deliveryMethod;
      if (this.addCommodity.deliveryMethod === "配送上门") {
        this.addCommodity.shippingFees = decodedData.shippingFees;
      }


      this.html = decodedData.commodityDetails; // 副本文本信息
    },



    // 请求所有商品分类
    ClassificationAll() {
      this.$axios({
        url: "ification",
        method: "get",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          console.log(msg.data.data)
          this.ClassificationList = msg.data.data

        }
      })

    },

    // 关闭级联选择器
    handleChangeCascader(){
      this.$refs.elCascader.dropDownVisible = false
    },


    onCreated(editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
    },

    // 主图相关函数
    handleChange(file, fileList) {
      this.update.fileList = fileList;

    },

    masterFileMax(files, fileList){
      this.$message.warning(`请最多上传5 张图片。`);
      console.log(this.update.fileList)
    },

    beforeUpload(file){
    },

    // 商品发布提交

    /*将 commoditySubmit 方法声明为 async，以便使用 await 等待异步请求完成。*/
    async commoditySubmit() {
      try {
        // 提交商品图片
        if (this.update.fileList.length === 0) {
          alert("商品主图不能为空")
          return false;
        }

        let formData = new FormData();
        this.update.fileList.forEach((file) => {
          formData.append("files", file.raw);
        });

        const pictureResponse = await this.$axios({
          url: "commodity/pictures",
          method: "post",
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data',
            token: sessionStorage.getItem("token")
          }
        });

        if (pictureResponse.data.code === 200) {
          this.addCommodity.commodityPicturePaths = pictureResponse.data.data;
          this.addCommodity.commodityDetails = this.html;
          console.log(this.classifyId1)
          this.addCommodity.classifyId = this.classifyId1[this.classifyId1.length - 1];

        } else {
          alert(pictureResponse.data.message);
          return false;
        }

        // 提交商品信息
        const infoResponse = await this.$axios({
          url: "commodity/info",
          method: "post",
          data: this.addCommodity,
          headers: {
            token: sessionStorage.getItem("token")
          }
        });

        if (infoResponse.data.code === 200) {
          alert("商品添加成功")
        } else {
          alert(infoResponse.data.message);
        }
      } catch (error) {
        console.error('请求失败', error);
      }
    }

  },

  mounted() {
    // 模拟 ajax 请求，异步渲染编辑器
    /*setTimeout(() => {
      this.html = '<p></p>'
    }, 1500)*/
  },
  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  }


}
</script>

<style scoped>


.input1{
  width: 300px;
}
.ava {
  margin: 0 14px;
}

.p1{
  margin: 0;
  padding: 0;
  font-size: 35px;
  font-family: cursive;
  font-weight: 900;
}
.p2{
  margin: 0;
  padding: 0;
  margin-top: 13px;
  font-size: 18px;
}
</style>
