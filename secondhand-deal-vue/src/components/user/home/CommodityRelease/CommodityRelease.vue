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
            <div style="display: flex; margin-top: 40px; margin-left: 20px">
              <el-cascader  clearable></el-cascader>
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
                <el-select v-model="addCommodity.isInteger" placeholder="请选择" class="input1">
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
            <el-input placeholder="请输入商品的价格" style="margin: -3px 21px; width: 300px; float: left" v-model="addCommodity.commodityPrice"></el-input>
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
            <el-input placeholder="请输入商品的数量" style="margin: -3px 21px; width: 300px; float: left" v-model="addCommodity.commodityNumber"></el-input>
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
            <el-button type="primary" @click="abc">提交</el-button>
            <el-button type="primary">取消</el-button>
          </el-col>

        </el-row>
        <!--提交end-->


      </div>
    </div>
  </div>
</template>

<script>
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

import '@wangeditor/editor/dist/css/style.css'

import Update from '../../../package/update/index.vue'



export default {
  name: "CommodityRelease",
  components: { Update, Editor, Toolbar },

  data() {
    return{

      update: {
        result: null,
        fileList: [],
        bac: "123",
      },


      //新增商品数据存储
      addCommodity:{
        commodityClassification: null, //商品分类
        commodityTitle: null, //商品标题
        brand: null, // 品牌
        locality: null, //产地
        isInteger: null, //是否整装
        style: null, //风格
        design: null, //图案
        material: null, //材质
        suitableObject: null, //适用对象
        commodityType: null, //商品类型（成色）
        commodityPrice: null, //价格
        commodityNumber: null, //数量
        deliveryMethod: null, //配送方式
        shippingFees: null, // 配送费用
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
            server: this.url + "/img/app"
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
        value: '是',
        label: '是'
      },{
        value: '其它',
        label: '其它'
      }],

      // 图案
      design: [{
        value: '是',
        label: '是'
      },{
        value: '其它',
        label: '其它'
      }],

      // 图案
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

  },


  methods:{
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

    abc(){
      // console.log(this.html)
      console.log(this.update.fileList)

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
