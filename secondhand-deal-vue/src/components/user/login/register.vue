<template>
  <div class="bg">
    <div class="el-reg">

      <el-form v-show="regType === 'smsReg'" :model="smsRegData">
        <div style="margin-bottom: 50px; margin-top: 40px">
          <h2>邮箱</h2>
        </div>
        <el-form-item>
          <el-input type="text" placeholder="请输入邮箱" style="width: 90%" v-model="smsRegData.email" prefix-icon="icon iconfont icon-icon-mail"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input type="text"  placeholder="请输入验证码" style="width: 90%" v-model="smsRegData.code" prefix-icon="icon iconfont icon-yanzhengma">
            <template slot="append">
              <el-button :disabled="isSend" @click="countDown">{{codeName}}</el-button>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" style="width: 90%; margin-bottom: 20px" @click="submitCode">确认</el-button>
        </el-form-item>

        <div class="divider"></div>

        <el-form-item style="display: flex; flex-direction: row-reverse; margin-right: 20px">
          <el-link :underline="false" href="/login">
            <span style="font-size: 15px">立即登录</span>
          </el-link>
        </el-form-item>
      </el-form>

      <el-form v-show="regType === 'RegInfo'" :model="smsRegDataInfo">
        <div style="margin-bottom: 40px; margin-top: 30px">
          <h2>账号注册</h2>
        </div>
        <el-form-item>
          <el-input type="text" placeholder="账号" style="width: 90%" v-model="smsRegDataInfo.username" prefix-icon="icon iconfont icon-zhanghao"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input type="password"  placeholder="密码" show-password style="width: 90%" v-model="smsRegDataInfo.password" prefix-icon="icon iconfont icon-mima"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input type="password"  placeholder="再次确认密码" show-password style="width: 90%" v-model="smsRegDataInfo.againPassword" prefix-icon="icon iconfont icon-mima"></el-input>
        </el-form-item>

        <el-form-item >
          <el-button type="primary" style="width: 90%; margin-bottom: 20px" @click="submitInfo">确认</el-button>
        </el-form-item>

      </el-form>

    </div>

  </div>

</template>

<script>
export default {
  name: "register",

  data() {
    return {
      // 验证码相关属性
      isSend: false,  //禁用
      codeName: "发送验证码",
      totalTime: 60, //一般是60
      timer:'', //定时器

      // 步骤控制
      regType: "smsReg",

      smsRegData: {
        email : null,
        code: null,
      },

      smsRegDataInfo: {
        username: null,
        password: null,
        againPassword: null
      }
    }
  },

  created() {
  },

  methods: {
    // 提交用户名和密码
    submitInfo(){
      const data = {
        email : this.smsRegData.email,
        username: this.smsRegDataInfo.username,
        password: this.smsRegDataInfo.password,
      }
      this.$axios({
        url: "/reg",
        method: "post",
        data: data,
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("注册成功")
          this.$router.push("/login");
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 提交验证码
    submitCode(){
      this.$axios({
        url: "/reg/code",
        method: "post",
        data: this.smsRegData,
      }).then(msg=>{
        if (msg.data.code === 200) {
          this.regType = "RegInfo"
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 发送验证码
    sendEmail() {
      this.$axios({
        url: "/reg/send",
        method: "post",
        data: this.smsRegData
      }).then(msg=>{
        console.log(msg.data)
        if (msg.data.code === 200) {
          this.isSend = true
        } else {
          alert(msg.data.message)
          // 刷新页面
          location.reload()
        }
      })
    },

    // 验证码倒计时
    countDown() {
      if (this.isSend) return
      // 发送验证码到后端请求方法
      this.sendEmail()
      // this.isSend = true
      this.codeName = this.totalTime + 's后重新发送'
      this.timer = setInterval(() => {
        this.totalTime--
        this.codeName = this.totalTime + 's后重新发送'
        if (this.totalTime < 0) {
          clearInterval(this.timer)
          this.codeName = '重新发送验证码'
          this.totalTime = 60
          this.isSend = false
        }
      }, 1000)
    },

  }
}
</script>

<style scoped>

.divider {
  width: 100%;
  height: 1px;
  border-top: solid #797070 1px;
}

.el-reg {
  width: 400px;
  height: 400px;
  display: flex;
  flex-direction: column;
  margin-top: 300px;
  margin-left: 300px;
  background-color: rgba(181, 186, 246, 0.5);
  border-radius: 20px;

}

.bg {
  background: url("../../../assets/pexels-pixabay-39284.jpg");
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%
}

</style>
