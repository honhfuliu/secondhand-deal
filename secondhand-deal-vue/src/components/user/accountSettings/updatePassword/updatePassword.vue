<!--用户密码修改页面-->
<template>
  <div class="stop">
    <!--密码修改start-->
    <div style="width: 40%; margin: auto; padding: 70px">
      <div style="margin-bottom: 20px">
        <el-steps :space="200" :align-center="true" :active="this.step + 1">
          <el-step title="身份验证" icon="el-icon-edit"></el-step>
          <el-step title="设置操作" icon="el-icon-upload"></el-step>
          <el-step title="密码修改" icon="el-icon-picture"></el-step>
        </el-steps>
      </div>

      <div v-show="step === 0">
        <div style="margin-bottom: 20px">
          <img src="/static/auth-ico.png" >
        </div>

        <div style="margin-bottom: 20px">
          <span>为确认是您本人操作，请先完成身份认证</span>
        </div>
        <div>
          <el-button style="width: 290px; height: 50px" @click="emailGain()">使用邮箱短信验证</el-button>
        </div>
      </div>


    </div>

    <!--邮箱验证码发送start-->
    <div v-show="step == 1" style="width: 40%; margin: auto">
      <div style="margin-bottom: 20px">
        <i class="el-icon-message" style="font-size: 40px"></i>
      </div>
      <div  style="margin-bottom: 20px">
        <span style="font-size: 19px; font-weight: 600;">使用&nbsp;邮箱验证码验证</span>
      </div>

      <div style="float: left; margin-left: 18px; margin-bottom: 20px">
        <span>当前邮箱账号:&nbsp;{{ myEmail }}</span>
      </div>

      <div style="margin-bottom: 40px">
        <el-form style="display: inline-flex" ref="from" :rules="rules">
          <el-form-item  style="width: 300px; margin-right: 79px" prop="code">
            <el-input
              placeholder="请输入验证码"
              v-model="code"
              type="text"
              onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'',); this.value=this.value.replace(/[\u4E00-\u9FA5]/g,'')"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="height: 60px" @click="SendEmailCode">{{codeName}}</el-button>
          </el-form-item>
        </el-form>
      </div>

      <div>
        <el-button class="button-a" @click="verificationCode">提交认证</el-button>
      </div>
    </div>
    <!--邮箱验证码发送end-->

    <div v-show="step === 2" style="width: 40%; margin: auto">
      <div style="margin-bottom: 20px">
        <i class="el-icon-message" style="font-size: 40px"></i>
      </div>
      <div  style="margin-bottom: 20px">
        <span style="font-size: 19px; font-weight: 600;">密码修改</span>
      </div>

      <div>
        <el-form>
          <el-form-item label="新密码" >
            <el-input
              show-password
              type="password"
              v-model="password"
              onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'',); this.value=this.value.replace(/[\u4E00-\u9FA5]/g,'')"
            ></el-input>
          </el-form-item>

          <el-form-item label="确认密码">
            <el-input
              show-password
              type="password"
              v-model="repeatPassword"
              onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'',); this.value=this.value.replace(/[\u4E00-\u9FA5]/g,'')"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button class="button-a" @click="UpdatePasswordSubmit">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <!--密码修改end-->
  </div>

</template>

<script>
export default {
  name: "updatePassword",

  data(){
    return{
      // 步骤控制
      step: 0,

      // 邮箱接收
      myEmail: null,

      // 验证码接收
      code: null,

      // 密码接收
      password: null,
      repeatPassword: null,

      // 验证码相关属性
      isSend: false,  //禁用
      codeName: "发送验证码",
      totalTime: 60, //一般是60
      timer:'', //定时器

      rules: {
        code: [
          { required: true, message: "验证码不能为空", trigger: "blur" },
        ]
      },
    }
  },

  created() {
  },

  methods:{
    // 密码修改提交
    UpdatePasswordSubmit(){
      if (!this.password || !this.repeatPassword) {
        alert("密码不能为空");
        return false;
      }
      if  (this.password !== this.repeatPassword) {
        alert("两次输入密码不相同")
        return false;
      }

      this.$axios({
        url: "user/v/updatePasswd",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {"password": this.password, "email": this.myEmail}
      }).then(msg => {
        if (msg.data.code === 200) {
          alert("修改成功")
          sessionStorage.removeItem("token")
          this.$router.push({name: "Login"})
        } else {
          alert(msg.data.message)
        }
      })


    },

    // 验证码验证
    verificationCode(){
      if (this.code === null || this.code === "") {
        alert("验证码不能为空")
        return false;
      }

      this.$axios({
        url: "user/v/gainCode",
        method: "post",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        data: {'code': this.code, 'email': this.myEmail},
      }).then(msg => {
        if (msg.data.code === 200) {
          this.step = 2
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 发送验证码
    GainCode(){
      this.$axios({
        url: "user/v/gain",
        method: "post",
        data: {'email': this.myEmail},
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 发送验证码等待时间
    SendEmailCode(){
      if (this.isSend) return
      // 发送验证码到后端请求方法
      this.GainCode()
      this.isSend = true
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


    // 获取当前账号邮箱
    emailGain(){
      this.$axios({
        url: "user/v/code",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          this.myEmail = msg.data.data.email
          this.step = 1
        } else {
          alert(msg.data.message)
        }
      })

    }

  },
}
</script>

<style scoped>
.stop{
  position: fixed;
  top: 12%;
  left: 18%;
  width: 70%;
}


/deep/ .el-form-item__label{
  font-size: 18px;
}

/deep/ .el-input__inner{
  height: 60px;
}

.button-a{
  height: 60px;
  width: 517px;
  background-color: #c81623;
  color: #FFFFFF;
}


</style>
