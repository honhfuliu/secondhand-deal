<template>
  <div style="width: 99%; height: 100%">
    <div style="width: 100%; height: 50px; margin-bottom: 20px; display: flex; margin-left: 20px">
      <h3 style="margin-right: 15px">
        <i class="icon iconfont icon-icon-mail"/>
        商品分类
      </h3>
    </div>

    <div class="divider"></div>


    <div style="display: flex; margin-left: 45px; margin-bottom: 20px; width: 100%">
      <el-button type="primary" @click="addClassification">添加分类</el-button>
    </div>

    <tree-table
      :data="ClassificationList"
      :columns="columns"
      :selection-type="false"
      :expand-type="false"
      :show-index="true"
      :border="true"
      style="width: 95%; margin: auto"

    >
      <!--是否有效-->
      <template slot="isok">
        <i class="el-icon-success" style="color: lightgreen; font-size: 20px"></i>
      </template>

      <!--排序-->
      <template slot="order" slot-scope="scope">
        <el-tag v-if="scope.row.level === 1">一级</el-tag>
        <el-tag type="success" v-else-if="scope.row.level === 2">二级</el-tag>
        <el-tag type="warning" v-else-if="scope.row.level === 3">三级</el-tag>
      </template>

      <!--操作-->
      <template slot="operate"  slot-scope="scope">
        <el-button type="primary" icon="el-icon-edit"  size="small" @click="updateClassification(scope.row.classificationId, scope.row.level)">修改</el-button>
        <el-button type="danger" icon="el-icon-delete"  size="small" @click="getByIdDelete(scope.row.classificationId)">删除</el-button>


      </template>
    </tree-table>

    <!--新增分类 start-->
    <el-dialog
      title="分类添加"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :model="addClassificationInfo">
        <el-form-item >
          <el-input type="text" placeholder="分类名称" v-model="addClassificationInfo.classificationName"></el-input>
        </el-form-item>

        <el-form-item>
          <el-cascader
            style="width: 100%"
            placeholder="父级分类"
            ref="elCascader"
            v-model="addClassificationInfo.parentId"
            :options="ClassificationListTow"
            :props="cascaderProps"
            :clearable="true"
            :collapse-tags="true"
            @change="handleChange"
          >
          </el-cascader>


        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="judgment">确 定</el-button>
      </span>
    </el-dialog>
    <!--新增分类 end-->





  </div>
</template>

<script>
export default {
  name: "commodityClassification",

  data() {
    return{
      // 添加分类数据存储
      addClassificationInfo: {
        classificationId: null,
        parentId: null,
        classificationName: null,

      },

      // 级联属性绑定
      cascaderProps: {
        expandTrigger: 'hover',
        value: 'classificationId',
        label: 'classificationName',
        children: 'children',
        checkStrictly: true,
        // multiple: true
      },

      // 新增分类对话框打开关闭控制
      dialogVisible: false,

      // 分类数据存储
      ClassificationList: [],

      //存储前两级数据分类
      ClassificationListTow: [],

      // 为tables指定列的定义
      columns: [
        {
          label: '分类名称',
          prop: 'classificationName'
        },
        {
          label: "ID",
          prop: "classificationId",
          width: "200px"
        },
        {
          label: "是否有效",
          // 表示，将当前列定义为模版列
          type: 'template',
          // 表示当前这列使用模版名称
          template: 'isok'
        },
        {
          label: "排序",
          // 表示，将当前列定义为模版列
          type: 'template',
          // 表示当前这列使用模版名称
          template: 'order'
        },
        {
          label: "操作",
          // 表示，将当前列定义为模版列
          type: 'template',
          // 表示当前这列使用模版名称
          template: 'operate'
        },


      ]
    }
  },



  created() {
    this.getClassificationAll()
  },

  methods: {
    //根据id删除分类
    getByIdDelete(id){
      this.$axios({
        url: `/ification/${id}`,
        method: "delete",
        headers: {"token": sessionStorage.getItem("adminToken")},
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("删除成功")
          location.reload();
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 修改分类
    updateClassification(classificationId1,level)
    {
      this.addClassificationInfo.classificationId = classificationId1
      this.$axios({
        url:`/ification/${classificationId1}`,
        method: "get",
        headers: {"token": sessionStorage.getItem("adminToken")},
      }).then(msg => {
        if (msg.data.code === 200) {
          this.dialogVisible = true
          this.ClassificationListTow = msg.data.data
           //根据level判断要修改的分类是几级
          if (level === 1) {
            this.addClassificationInfo.classificationName = msg.data.data.classificationName
            this.getClassificationTow()

          } else if(level === 2) {
            let data = msg.data.data.children
            for (let i = 0; i < data.length; i++) {
              if (data[i].classificationId === classificationId1) {
                this.addClassificationInfo.classificationName = data[i].classificationName
                this.addClassificationInfo.parentId = data[i].parentId

              }
            }
            this.getClassificationTow()
          } else if (level === 3) {
            let data = msg.data.data
            let array = [0, data.parentId, 1, data.children[0].parentId];
            this.addClassificationInfo.parentId = [data.children[0].parentId, data.children[0].children[0].parentId]
            this.addClassificationInfo.classificationName = data.children[0].children[0].classificationName
            this.getClassificationTow()
          }

        }

      })
    },

    // 关闭级联选择器
    handleChange() {
      this.$refs.elCascader.dropDownVisible = false
    },

    //判断是新增还是修改
    judgment(){
      if (this.addClassificationInfo.classificationId === null) {
        this.submitClassification();
      } else {
        this.updateSubmitClassification()
      }
    },
    // 分类修改提交
    updateSubmitClassification() {
      let data = null;
      if (this.addClassificationInfo.parentId === null) {
        data = {
          classificationId: this.addClassificationInfo.classificationId,
          classificationName : this.addClassificationInfo.classificationName

        }
      } else {
        data = {
          classificationId: this.addClassificationInfo.classificationId,
          parentId : this.addClassificationInfo.parentId[this.addClassificationInfo.parentId.length - 1],
          classificationName : this.addClassificationInfo.classificationName
        }
      }

      this.$axios({
        url: "ification",
        method: "put",
        data: data,
        headers: {"token": sessionStorage.getItem("adminToken")}
      }).then(msg =>{
        if (msg.data.code === 200) {
          alert("修改成功")
          this.dialogVisible = false
          location.reload();
        } else {
          alert(msg.data.message)
        }
      })
    },


    // 分类添加提交函数
    submitClassification()
    {
      let data = null;
      if (this.addClassificationInfo.parentId === null) {
        data = {
          classificationName : this.addClassificationInfo.classificationName
        }
      } else {
        data = {
          parentId : this.addClassificationInfo.parentId[this.addClassificationInfo.parentId.length - 1],
          classificationName : this.addClassificationInfo.classificationName
        }
      }

      this.$axios({
        url: "/ification",
        method: "post",
        data: data,
        headers: {"token": sessionStorage.getItem("adminToken")}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("添加成功");
          this.dialogVisible = false
          location.reload();
        } else {
          alert(msg.data.message)
        }
      })



    },

    // 分类添加控制
    addClassification()
    {
      this.dialogVisible = true
      this.getClassificationTow()
    },

    // 请求商品分类的前两级
    getClassificationTow(){
      this.$axios({
        url: "/ification/tow",
        method: "get",
        headers: {"token": sessionStorage.getItem("adminToken")}
      }).then(msg => {
        if (msg.data.code === 200) {
          this.ClassificationListTow = msg.data.data
        }
      })
    },

    // 请求所有商品分类
    getClassificationAll() {
      this.$axios({
        url: "/ification",
        method: "get",
        headers: {"token": sessionStorage.getItem("adminToken")}
      }).then(msg => {
        if (msg.data.code === 200) {
          this.ClassificationList = msg.data.data

        }
      })
    }
  }
}
</script>

<style scoped>
.divider {
  width: 100%;
  height: 1px;
  border-top: solid #797070 1px;
  margin-bottom: 15px;
}

</style>
