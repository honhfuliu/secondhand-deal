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

        <!--商品规格sku start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              *商品规格
            </span>
          </el-col>
        </el-row>
        <div>
          <div style="height: 40px; margin-bottom: 20px">
            <div style="float: right; margin: 0 60px">
              <el-button @click="addSku" round style="background-color: #b3e19d; color: #FFFFFF; font-weight: 600; font-size: 15px">添加规格</el-button>
            </div>
          </div>

          <div v-for="index in privateGoodsItem" :key="index">
            <div style="width: 70%; margin: auto; background-color: #fff0f0; margin-bottom: 20px; border-radius: 10px">
              <div>
                <el-row>
                  <el-col :span="18">
                    <el-form :inline="true" style="margin-top: 25px" label-width="80px">
                      <el-form-item label="规格名" style="display: flex">
                        <el-input placeholder="请输入规格名" v-model="index.privateSpecName"></el-input>
                      </el-form-item>
                      <el-form-item label="规格值" style="display: flex">
                        <el-tag size="medium" v-for="tag in index.dynamicTags" :key="tag" closable :disable-transitions="false" @close="delTag(index,tag)" style="margin: 0 7px; font-size: 17px; height: 32px; line-height: 30px;">
                          {{ tag }}
                        </el-tag>

                        <el-dialog
                          title="规格值添加"
                          :visible.sync="index.inputVisible"
                          width="30%">
                          <div>
                            <el-form :inline="true">
                              <el-form-item label="规格值" >
                                <el-input style="width: 270px" v-model="index.inputValue"></el-input>
                              </el-form-item>
                            </el-form>
                          </div>
                          <span slot="footer" class="dialog-footer">
                            <el-button @click="dialogClose(index)">取 消</el-button>
                            <el-button type="primary" @click="dialogConfirm(index)">确 定</el-button>
                          </span>
                        </el-dialog>
                        <el-button size="mini" @click="showDialog(index)">添加</el-button>
                      </el-form-item>
                    </el-form>
                  </el-col>
                  <el-col :span="6" style="line-height: 140px">
                    <el-link :underline="false" @click="delSku(index)">删除规格</el-link>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>



        </div>
        <!--商品规格sku end-->

        <!--商品规格价格库存start-->
        <el-row style="padding: 40px 0">
          <el-col :span="4">
            <span style="float: right; font-size: 20px">
              价格/库存
            </span>
          </el-col>
        </el-row>
        <div style="background-color: #77c5e3; width: 90%; margin: auto">
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            :data="tableColumnList.tableBodyList">
            <el-table-column align="center" :label="item.propName" :property="item.prop" v-for="item in tableColumnList.tableHeaderList" :key="item.prop">
              <template v-slot="scope">
                <span>{{ scope.row[item.propName] }}</span>
              </template>
            </el-table-column>

            <el-table-column label="价格" align="center">
              <template v-slot="scope">
                <el-input-number :min="1" :max="9999999" v-model="scope.row.price">

                </el-input-number>
              </template>
            </el-table-column>

            <el-table-column label="库存" align="center">
              <template v-slot="scope">
                <el-input-number :min="1" :max="9999999" v-model="scope.row.stock">

                </el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="预警库存" align="center">
              <template v-slot="scope">
                <el-input-number :min="1" :max="9999999" v-model="scope.row.low_stock">

                </el-input-number>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!--商品规格展示end-->

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
            <el-button type="primary" @click="estimateUpdateOrAdd">提交</el-button>
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
import index from "../../Index/Index.vue";


export default {
  name: "CommodityRelease",
  components: { Update, Editor, Toolbar },

  data() {
    return{
      tableColumnList: {
        tableHeaderList: [],
        tableBodyList: []
      },

      privateGoodsItem: [
        {
          privateSpecName: '', //规格名
          dynamicTags: [], //规格值数组
          inputVisible: false,
          inputValue: ''
        }
      ],

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
        cid: null, //商品id
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
        deliveryMethod: null, //配送方式
        shippingFees: null, // 配送费用
        commodityPicturePaths: [], // 商品主图地址存储
        commodityDetails: null, // 富文本信息
        commoditySku: null, // 商品sku
        commodityHeader: null // 商品规格
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
            maxFileSize: 1 * 1024 * 1024, // 1M

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

  watch: {
    privateGoodsItem: {
      handler(newVal) {
        let cloneNewVal = JSON.parse(JSON.stringify(newVal))
        // console.log('规格已更改:', cloneNewVal);
        let attrName = [] //规格名数组
        let attrValue = [] //规格值数组
        for (let key in cloneNewVal) {
          console.log("key666", key)
          console.log("key6661", cloneNewVal[key].privateSpecName)
          attrName.push(cloneNewVal[key].privateSpecName)
          attrValue.push(cloneNewVal[key].dynamicTags)
        }
        // console.log("输出1" ,  attrName)
        // console.log("输出2" ,  attrValue)
        // 笛卡尔积
        let finalArr =  this.cartesianProductOf(...attrValue)
        // console.log("笛卡尔积" , finalArr)

        let tableObj = {
          tableBodyList: [],
          tableHeaderList: []
        }
        /*
        // 表格内容
        tableObj.tableBodyList = finalArr.map((item) => {
          console.log("item", item)
          let obj = {
            price: 0,
            stock: 0,
            low_stock: 0
          }
          for (let i = 0; i < item.length; i++) {
            obj[attrName[i]] = item[i]
          }
          return obj
        })
        this.tableColumnList.tableBodyList = tableObj.tableBodyList //表格内容数据*/

        // 表格内容
        tableObj.tableBodyList = finalArr.map((item) => {
          console.log("item", item);

          // 使用默认值初始化对象
          let obj = {
            price: 0,
            stock: 0,
            low_stock: 0
          };

          // 在当前数据中找到对应的现有 SKU
          let existingSku = this.tableColumnList.tableBodyList.find(skuItem => {
            return attrName.every((name, index) => skuItem[name] === item[index]);
          });

          // 如果找到匹配的 SKU，保留其值
          if (existingSku) {
            obj.price = existingSku.price || obj.price;
            obj.stock = existingSku.stock || obj.stock;
            obj.low_stock = existingSku.low_stock || obj.low_stock;
          }

          // 将新的 SKU 属性分配给对象
          for (let i = 0; i < item.length; i++) {
            obj[attrName[i]] = item[i];
          }

          return obj;
        });

        this.tableColumnList.tableBodyList = tableObj.tableBodyList; // 表格内容数据

        // 表头
        let skuTableArr = Object.keys(newVal)
        tableObj.tableHeaderList = skuTableArr.map((item) => {
          console.log("item1", item)
          return {
            prop: item,
            propName: attrName[item]
          }
        })
        this.tableColumnList.tableHeaderList = tableObj.tableHeaderList // 表头

        console.log("最终：", this.tableColumnList)
      },
      deep: true, // 深度监听，确保监听对象内部属性的变化
    },
  },



  methods:{
    // 笛卡尔积计算sku规格组合
    cartesianProductOf(...args) {
      return args.reduce(
        (total, current) => {
          let ret = []
          total.forEach((a) => {
            current.forEach((b) => {
              ret.push(a.concat([b]))
            })
          })
          return ret
        },
        [[]]
      )
    },




    // 商品sku
    // 添加规格
    addSku(index){
      this.privateGoodsItem.push({
        privateSpecName: '',
        dynamicTags: [],
        inputVisible: false,
        inputValue: ''
      })
    },
    // 删除规格
    delSku(index){
      this.privateGoodsItem.splice(index, 1)
    },
    // 规格值添加
    showDialog(index){
      index.inputVisible = true
    },

    // 取消添加规格
    dialogClose(index){
      index.inputVisible = false
      index.inputValue = ''
    },

    // 规格添加
    dialogConfirm(index){
      index.inputVisible = false
      index.dynamicTags.push(index.inputValue)
      index.inputValue = ''
    },

    // 删除规格
    delTag(index, tag){
      index.dynamicTags.splice(index.dynamicTags.indexOf(tag), 1)
    },



    // 修改商品详情信息接收，并赋值
    updateCommodityInfo(){
      const decodedData = JSON.parse(decodeURIComponent(this.$route.query.data));
      // console.log(decodedData)

      // 商品id
      this.addCommodity.cid = decodedData.cid;

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

      // sku规格名
      console.log("rencai:", decodedData.commoditySkuHeader)
      let data = []
      for (let key in decodedData.commoditySkuHeader) {
        if (decodedData.commoditySkuHeader.hasOwnProperty(key)) { // 过滤掉原型链上的属性
          let obj = {
            privateSpecName: key,
            dynamicTags: decodedData.commoditySkuHeader[key],
            inputVisible: false,
            inputValue: ''
          }
          data.push(obj)
        }
      }
      this.privateGoodsItem = data

      // 规格值
      console.log(decodedData.commoditySkus)
      this.tableColumnList.tableBodyList = decodedData.commoditySkus

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
      // console.log(this.update.fileList)
    },

    beforeUpload(file){
    },


    // 判断是修改还是新增
    estimateUpdateOrAdd() {
      if (this.addCommodity.cid === null) {
        this.commoditySubmit()
      } else {
        this.UpdateCommodity()
      }
    },

    // 商品修改提交
    async UpdateCommodity() {
      try
      {
        // 提交商品图片
        if (this.update.fileList.length === 0) {
          alert("商品主图不能为空")
          return false;
        }

        let formData = new FormData();
        let hasFiles = false; // 假设默认没有文件

        for (let i = 0; i <this.update.fileList.length; i++) {
          if (this.update.fileList[i].raw) {
            formData.append("files", this.update.fileList[i].raw);
            hasFiles = true
          } else {
            this.addCommodity.commodityPicturePaths[i] = this.update.fileList[i].name;
          }
        }

        //提交图片
        if (hasFiles !== false) {
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
            for (let i = 0; i < pictureResponse.data.data.length; i++) {
              this.addCommodity.commodityPicturePaths.push(pictureResponse.data.data[i])
            }

            this.addCommodity.commodityDetails = this.html;

            //分类处理
            if(this.classifyId1 === -1 || this.classifyId1 === null){
              this.addCommodity.classifyId = null
            } else {
              this.addCommodity.classifyId = this.classifyId1[this.classifyId1.length - 1];
            }

            // 商品sku
            this.addCommodity.commoditySku = this.tableColumnList.tableBodyList
            // 商品sku规格
            const header = []
            this.privateGoodsItem.forEach(item =>{
              const data = {[item.privateSpecName]: item.dynamicTags}
              header.push(data)
            })
            this.addCommodity.commodityHeader = header

          } else {
            alert(pictureResponse.data.message);
            return false;
          }
        } else {
          this.addCommodity.commodityPicturePaths = null;

          this.addCommodity.commodityDetails = this.html;

          //分类处理
          if(this.classifyId1 === -1){
            this.addCommodity.classifyId = null
          } else {
            this.addCommodity.classifyId = this.classifyId1[this.classifyId1.length - 1];
          }

          // 商品sku
          this.addCommodity.commoditySku = this.tableColumnList.tableBodyList
          // 商品sku规格
          const header = []
          this.privateGoodsItem.forEach(item =>{
            const data = {[item.privateSpecName]: item.dynamicTags}
            header.push(data)
          })
          this.addCommodity.commodityHeader = header
        }

        // 提交商品信息
        const infoResponse = await this.$axios({
          url: "commodity/UpdateInfo",
          method: "post",
          data: this.addCommodity,
          headers: {
            token: sessionStorage.getItem("token")
          }
        });

        if (infoResponse.data.code === 200) {
          alert("商品修改成功")
          this.$router.push('/commodityShow');
        } else {
          alert(infoResponse.data.message);
        }


      }catch (error)
      {
        console.error('请求失败', error);
      }

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
          // 商品sku
          this.addCommodity.commoditySku = this.tableColumnList.tableBodyList
          // 商品sku规格
          const header = []
          this.privateGoodsItem.forEach(item =>{
            const data = {[item.privateSpecName]: item.dynamicTags}
            header.push(data)
          })
          this.addCommodity.commodityHeader = header


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
          this.$router.push('/commodityShow');
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
