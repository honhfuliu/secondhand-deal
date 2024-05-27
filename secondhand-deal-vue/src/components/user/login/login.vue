<template>
  <div class="home">
    <div class="el-login">
      <div class="div-button">
        <div style="padding-right: 10px" @click="changeLoginType('password')">密码登录</div>
        <div @click="changeLoginType('sms')">短信登录</div>
      </div>

      <!--账号密码表单start-->
      <el-form :model="loginForm"  label-width="10%" v-show="loginType === 'password'">
        <el-form-item>
          <el-input placeholder="账号" v-model="loginForm.username" prefix-icon="icon iconfont icon-zhanghao"></el-input>
        </el-form-item>

        <el-form-item label-width="10%">
          <el-input placeholder="密码" v-model="loginForm.password" type="password" show-password prefix-icon="icon iconfont icon-mima"></el-input>
        </el-form-item>

        <el-form-item label-width="10%" style="display: flex; flex-direction: row-reverse; margin-top: -20px">
          <el-link :underline="false" href="/recover">
            <span style="font-size: 15px; margin-right: 30px">忘记密码</span>
          </el-link>
        </el-form-item>

        <el-form-item label-width="10%" style="margin-top: -20px; margin-bottom: 40px">
          <el-button type="primary" style="width: 100%; margin-left: -40px" @click="loginFormSubmit()">确定</el-button>
        </el-form-item>

        <div class="divider"></div>

        <el-form-item  style="display: flex; flex-direction: row-reverse; margin-top: 5px">
          <el-link :underline="false" href="/reg">
            <span style="font-size: 15px; margin-right: 30px">立即注册</span>
          </el-link>
        </el-form-item>

      </el-form>
      <!--账号密码表单end-->

      <!--验证码登录表单start-->
      <el-form :model="smsForm" label-width="10%" v-show="loginType === 'sms'">
        <el-form-item>
          <el-input type="text" placeholder="邮箱" v-model="smsForm.email" prefix-icon="icon iconfont icon-icon-mail"></el-input>
        </el-form-item>

        <el-form-item label-width="10%" style="margin-bottom: 60px">
          <el-input type="text"  placeholder="请输入验证码" v-model="smsForm.code" prefix-icon="icon iconfont icon-yanzhengma">
            <template slot="append">
              <el-button :disabled="isSend" @click="countDown">{{codeName}}
              </el-button>
            </template>
          </el-input>

        </el-form-item>

        <el-form-item label-width="10%" style="margin-top: -20px; margin-bottom: 40px">
          <el-button type="primary" style="width: 100%; margin-left: -40px" @click="submitEmail">确定</el-button>
        </el-form-item>

        <div class="divider"></div>

        <el-form-item  style="display: flex; flex-direction: row-reverse; margin-top: 5px">
          <el-link :underline="false" href="/reg">
            <span style="font-size: 15px; margin-right: 30px">立即注册</span>
          </el-link>
        </el-form-item>

      </el-form>
      <!--验证码登录表单end-->


    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "login",
  data() {
    return {
      // 验证码相关属性
      isSend: false,  //禁用
      codeName: "发送验证码",
      totalTime: 60, //一般是60
      timer:'', //定时器

      //登录类型
      loginType: 'password',

      // 密码数据接收
      loginForm: {
        username: null,
        password: null
      },

      // 验证码数据接收
      smsForm: {
        email : null,
        code: null,
      }
    }

  },

  created() {
  },

  methods:  {
    // 发送登录请求
    submitEmail() {
      this.$axios({
        url: "user/code",
        method: "post",
        data: this.smsForm
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("登录成功")
          sessionStorage.setItem("token", msg.data.data.token)
        } else {
          alert(msg.data.message)
        }
      })
    },


    // 验证码倒计时
    countDown() {
      if (this.isSend) return
      // 发送验证码逻辑
      this.sendCode()
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

    // 发送验证码
    sendCode() {
      this.$axios({
        url: "user/send",
        method: "post",
        data: this.smsForm
      }).then(msg => {
        if (msg.data.code === 200) {
          this.isSend = true
          alert(msg.data.message)
        }else {
          alert(msg.data.message)
        }
      })
    },


    // 密码登录提交
    loginFormSubmit() {
      this.$axios({
        url: "/user",
        method: "post",
        data: this.loginForm
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
          // console.log(msg.data)
          sessionStorage.setItem("token", msg.data.data.token)
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 判断使用的是什么方式登录
    changeLoginType(type){
      this.loginType = type
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

.div-button {
  margin-top: 50px;
  display: flex;
  font-weight: bold;
  font-size: 25px;
  margin-bottom: 20px;
  justify-content: center;
}

.home {
  background: url("../../../assets/pexels-pixabay-39284.jpg");
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%
}
.el-login {
  width: 400px;
  height: 400px;
  background-color: #77c5e3;
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
  margin-top: 300px;
  margin-left: 300px;
  /*box-shadow: 5px 5px 10px #b3d4fc;*/
  background-color: rgba(181, 186, 246, 0.5);
  border-radius: 20px;
}
.el-input {
  margin-left: -40px;
}
</style>
