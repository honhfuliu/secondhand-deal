<template>
  <div class="stop">
    <el-tabs v-model="activeName">
      <!--基本信息start-->
      <el-tab-pane label="基本信息" name="first">
        <el-row >
          <el-col :span="15" >
            <div style="height: 60px"></div>
            <div style="width: 80%; margin: auto">
              <el-form :model="info">
                <el-form-item label="昵称：">
                  <el-input placeholder="昵称" style="width: 50%" class="display-a" v-model="info.name"></el-input>
                </el-form-item>

                <el-form-item label="性别：">
                  <div class="display-a" style="margin-top: 11px">
                    <el-radio v-model="info.sex" label="0">男</el-radio>
                    <el-radio v-model="info.sex" label="1">女</el-radio>
                  </div>

                </el-form-item>

                <el-form-item label="生日：">
                  <el-date-picker
                    class="display-a"
                    v-model="info.birthday"
                    type="datetime"
                    placeholder="选择日期时间">
                  </el-date-picker>
                </el-form-item>

                <el-form-item style="margin: 0 92px">
                  <el-button class="display-a" @click="updateInfo">提交</el-button>
                </el-form-item>



              </el-form>


            </div>
          </el-col>
          <el-col :span="9" style="border-radius: 15px; border: 1px solid #ddd">
            <div>
              <div class="div-dd">
                <span>今天是：</span>
                <span>{{ today }}</span>
              </div>

              <div>
                <img src="/static/20240706173430.png" alt="">
              </div>

              <div>
                <el-row>
                  <el-col :span="10">
                    <div>
                      <div style="margin: 14px -27px 14px -75px; line-height: 30px">
                        <span>昵称:&nbsp;{{ userInfo.name }}</span>

                      </div>
                      <div style="margin: 14px -4px 14px -59px; line-height: 30px">
                        <span>积分:&nbsp;{{ userInfo.score }}</span>

                      </div>
                      <div style="line-height: 30px; margin-left: 16px;">
                        <span>账号类型:&nbsp;个人账号</span>
                      </div>
                    </div>
                  </el-col>
                  <el-col :span="14">
                    <div>
                      <img :src="`http://127.0.0.1:8989/${userInfo.path}`" style="width: 134px; border-radius: 5px">
                    </div>
                  </el-col>
                </el-row>
              </div>

              <div style="margin-bottom: 30px">
                <img src="/static/20240706173430.png" alt="">
              </div>


              <div>
                <el-button class="button-a" type="primary" @click="skipRouterPath('CommodityShow')">我的商品</el-button>
                <el-button class="button-a" type="success" @click="signIn">签到</el-button>
              </div>

              <div style="height: 20px"></div>


            </div>
          </el-col>
        </el-row>
      </el-tab-pane>
      <!--基本信息end-->


      <!--头像上传start-->
      <el-tab-pane label="头像上传" name="second">
        <div>
          <el-upload
            class="avatar-uploader"
            action="#"
            list-type="picture-card"
            :limit="1"
            :file-list="fileImg"
            :auto-upload="false"
            :on-exceed="masterFileMax"
            :show-file-list="true"
            :on-change="handleChange"
            :on-remove="handleRemove"
            accept="image/jpeg,image/png">
            <i   class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>

          <el-button @click="avatarSubmit">保存</el-button>
        </div>
      </el-tab-pane>
      <!--头像上传end-->
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "UpdateInfo",

  data(){
    return{

      imageUploaded: false,

      // 图片信息存储
      fileImg: [],

      // 用户个人信息存储
      userInfo: [],

      // 卡片默认显示
      activeName:'first',

      // 个人信息上传存储
      info:{
        name: null,
        sex: null,
        birthday: null,
      },
      // 获取当前时间
      today: null,

    }
  },

  created() {
    this.today = this.getToDay()
    this.getInfo()
  },

  methods:{
    // 用户签到
    signIn(){
      this.$axios({
        url: "user/signIn",
        method: "post",
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg => {
        if (msg.data.code === 200) {
          this.$notify.warning({
            title: '成功',
            message: "签到成功"
          })
          location.reload()
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },

    // 头像数据提交
    avatarSubmit(){
      if (this.fileImg.length === 0) {
        alert("商品主图不能为空")
        return false;
      }
      if ((this.fileImg[0].type === 'image/png' || this.fileImg[0].type === 'image/jpg' || this.fileImg[0].type === 'image/jpeg')) {
        this.$notify.warning({
          title: '警告',
          message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
        })
      }

      let size = this.fileImg[0].size / 1024 / 1024 / 2
      if (size > 2) {
        this.$notify.warning({
          title: '警告',
          message: '图片大小必须小于2M'
        })
      }

      let formData = new FormData();
      formData.append("file", this.fileImg[0].raw)

      this.$axios({
        url:"user/avatar",
        method: "post",
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data',
          token: sessionStorage.getItem("token")
        }
      }).then(msg =>{
        if (msg.data.code === 200) {
          alert("上传成功")
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })

    },


    masterFileMax(file, fileList){
      alert("只能选择一张图片")
    },

    // 头像上传相关函数
    handleChange(file, fileList) {
      this.fileImg = fileList;

      if (fileList.length > 0) {
        this.imageUploaded = true;
      }
    },
    handleRemove(file, fileList){
      this.fileImg = fileList;
      if (fileList.length === 0) {
        this.imageUploaded = false;
      }
    },


    // 个人信息修改
    updateInfo(){
      this.$axios({
        url: "user/u",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: this.info
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("修改成功")
          // 刷新页面
          location.reload()
        } else {
          alert(msg.data.message)
        }
      })

    },

    // 个人信息获取
    getInfo(){
      this.$axios({
        url: "user/info",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.userInfo = msg.data.data
        } else {
          alert(msg.data.message)
        }
      })

    },



    //路由跳转
    skipRouterPath(path) {
      this.$router.push({name: path})
    },

    // 今日时间获取
    getToDay(){
      let date = new Date();
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      return year + '-' + month  + '-' + day
    }
  }
}
</script>

<style scoped>
.stop{
  position: fixed;
  width: 70%;
  top: 14%;
  left: 18%;
}

.display-a{
  display: flex;
}

.button-a{
  width: 100px;
  height: 50px;

}

.div-dd{
  float: left;
  margin-left: 47px;
  margin-bottom: 20px;
  margin-top: 20px;
  font-size: 24px;
  color: #5a8676;
}

</style>
