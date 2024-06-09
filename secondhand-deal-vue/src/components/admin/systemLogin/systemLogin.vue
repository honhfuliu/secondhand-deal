<template>
  <div style="width: 100%; height: 100%; background-color: dodgerblue">
    <div class="bj">
      <el-row>
        <el-col span="12">
          <div style="display: flex">
            <div>
              <img src="/static/favicon%20(1).ico" style="margin-top: 20px; margin-left: 45px">
            </div>
            <div style="margin-top: 15px; margin-left: 10px; font-family: cursive; font-size: 20px; font-weight: 600;">
              <p>二手交易</p>
            </div>


          </div>

        </el-col>
        <el-col span="12" style="margin-top: 100px">
          <div style="display: flex; margin-left: 60px">

            <p style="font-size: 30px;">欢迎登录</p>
            <p style="margin-top: 48px; margin-left: 5px; font-family: cursive; font-size: 20px">二手交易后端管理系统</p>
          </div>

          <el-form class="form" :model="loginData">
            <el-form-item style="margin-top: 10px">
              <el-input type="text" placeholder="账号" v-model="loginData.username" prefix-icon="icon iconfont icon-zhanghao"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input type="password" show-password placeholder="密码" v-model="loginData.password" prefix-icon="icon iconfont icon-mima"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 300px" type="primary" round @click="submitLogin">确认</el-button>
            </el-form-item>
          </el-form>

        </el-col>
      </el-row>

    </div>
  </div>
</template>

<script>
export default {
  name: "systemLogin",
  data() {
    return {
      loginData: {
        username: null,
        password: null,
      }
    }
  },
  created() {
  },

  methods: {
    // 管理员登录
    submitLogin(){
      this.$axios({
        url: "admin/login",
        method: "post",
        data: this.loginData
      }).then(msg =>{
        if (msg.data.code === 200) {
          // console.log(msg.data.data.token)
          sessionStorage.setItem("adminToken", msg.data.data.token)
          this.$router.push('/systemManage/systemHome');
        } else {
          alert(msg.data.message)
        }
      })
    }

  }
}
</script>

<style scoped>
.form{
  width: 300px;
  margin-left: 50px;
}
.bj {
  width: 880px;
  height: 500px;
  background: url("/static/689.png");
  background-size: 100% 100%;
  position: fixed;
  border-radius: 60px 10px 60px 10px;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;

}

</style>
