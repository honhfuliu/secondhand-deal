<template>
<div style="width: 99%; height: 100%; background-color: #ffffff">
  <div style=" margin-bottom: 15px">
    <el-row>
      <el-col span="10">
        <!--标题start-->
        <h3 style="float: left; margin-left: 10px">
          <i class="icon iconfont icon-icon-mail"/>
          日志管理
        </h3>
        <!--标题end-->

      </el-col>
      <el-col :span="12">
        <el-button class="search" @click="clickIsNull">查找异常不为空</el-button>
      </el-col>

      <el-col :span="1" style="margin-top: 25px">
        <i class="icon iconfont icon-shuaxin" @click="abc"/>
      </el-col>
    </el-row>
  </div>

  <div class="divider"></div>


  <div style="margin-top: 15px">
    <!--表格start-->
    <el-table
      :header-cell-style="{ fontSize: '15px', background: '#60c1b9', color: 'white', textAlign: 'center'}"
      style="width: 97%; margin: auto"
      height="650px"
      :data="pageQueryList"
    >
      <el-table-column label="序号" align="center" width="80"></el-table-column>
      <el-table-column label="请求类型" prop="httpMethod"  align="center" width="80"></el-table-column>
      <el-table-column label="请求地址" prop="requestUrl"  align="center"></el-table-column>
      <el-table-column label="请求方法" prop="classMethod"  align="center"></el-table-column>
      <el-table-column label="IP地址" prop="remoteAddr"  align="center"></el-table-column>
      <el-table-column label="浏览器" prop="browser"  align="center"></el-table-column>
      <el-table-column label="异常信息" prop="exception"  align="center"></el-table-column>
      <el-table-column label="请求类型" prop="type"  align="center"></el-table-column>
      <el-table-column label="创建时间" prop="createTime"  align="center"></el-table-column>

    </el-table>
    <el-pagination
      :total="this.total"
      :page-size="this.PageData.PageSize"
      :current-page="this.PageData.PageNo"
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[5,10,15,20]"
      @current-change="currentChange"
      @size-change="sizeChange"
    />
    <!--表格end-->
  </div>


</div>
</template>

<script>
export default {
  name: "systemLog",
  data() {
    return {
      // 分页查询参数
      total: 0,
      PageData: {
        PageNo: 1,
        PageSize: 5,
        exception: null,
      },


      // 分页查询数据存储
      pageQueryList: [],


    }
  },
  created() {
    this.pageQuery()
  },
  methods: {
    // 异常不为空
    clickIsNull(){
      this.PageData.exception = "exception"
      this.pageQuery()
    },

    // 分页查询
    pageQuery() {
      this.$axios({
        url: "/log",
        method: "get",
        params:this.PageData,
        headers: {"token": sessionStorage.getItem("adminToken")}
      }).then(msg => {
        if (msg.data.code === 200) {
          this.total = msg.data.data.total
          this.pageQueryList = msg.data.data.list
        }else {
          alert(msg.data.message)
        }
      })
    },

    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    currentChange(page) {
      // console.log(page)
      this.PageData.PageNo = page;
      this.pageQuery()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    sizeChange(size){
      this.PageData.PageSize = size;
      this.pageQuery()
    },

    abc() {
      location.reload();
    }
  }
}
</script>

<style scoped>

.divider {
  width: 100%;
  height: 1px;
  border-top: solid #797070 1px;
}

.search{
  /*width: 55%;*/
  float: right;
  margin-top: 15px;
}


</style>
