<template>
  <div class="home">
    <div class="el-login">
      <el-steps :active="this.step" simple>
        <el-step title="身份验证" icon="el-icon-edit"></el-step>
        <el-step title="输入验证码" icon="el-icon-upload"></el-step>
        <el-step title="设置新密码" icon="el-icon-picture"></el-step>
      </el-steps>

      <div style="margin: auto">
        <!--身份验证start-->
        <el-form v-show="step === 1" :model="RecoverInfo" ref="from" :rules="rules">
          <el-form-item prop="username">
            <el-input placeholder="账号名称" style="width: 350px" v-model="RecoverInfo.username" prefix-icon="icon iconfont icon-zhanghao"></el-input>
          </el-form-item>
          <el-form-item prop="email">
            <el-input placeholder="邮箱" v-model="RecoverInfo.email" prefix-icon="icon iconfont icon-icon-mail"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="usernameAndEmailSubmit">下一步</el-button>
          </el-form-item>
        </el-form>
        <!--身份验证end-->

        <!--输入验证码start-->
        <el-form v-show="step === 2" :model="RecoverInfo" ref="from1" :rules="rules">
          <el-form-item  prop="email">
            <el-input placeholder="邮箱" style="width: 350px" v-model="RecoverInfo.email"  prefix-icon="icon iconfont icon-icon-mail">
              <template slot="append">
                <el-button :disabled="isSend" @click="countDown">{{codeName}}</el-button>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="code">
            <el-input placeholder="验证码" v-model="RecoverInfo.code" prefix-icon="icon iconfont icon-yanzhengma"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="codeV">下一步</el-button>
          </el-form-item>
        </el-form>
        <!--输入验证码end-->

        <!--设置新密码start-->
        <el-form v-show="step === 3" :model="RecoverInfo">
          <el-form-item>
            <el-input placeholder="密码" style="width: 350px" v-model="RecoverInfo.password" show-password prefix-icon="icon iconfont icon-mima"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="确认密码" v-model="RecoverInfo.repeatPassword" show-password prefix-icon="icon iconfont icon-mima"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitPassword">提交</el-button>
          </el-form-item>
        </el-form>
        <!--设置新密码end-->
      </div>


    </div>
  </div>

</template>

<script>
export default {
  name: "recoverPassword",
  data(){
    return {

      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
        ],
        code: [
          { required: true, message: "验证码不能为空", trigger: "blur" },
        ]
      },

      // 数据绑定
      RecoverInfo: {
        username: null,
        email: null,
        code: null,
        password: null,
        repeatPassword: null,
      },

      // 验证码相关属性
      isSend: false,  //禁用
      codeName: "发送验证码",
      totalTime: 60, //一般是60
      timer:'', //定时器

      // 步骤控制
      step : 1
    }
  },
  created() {
  },

  methods:{
    // 密码提交
    submitPassword(){
      if (!this.RecoverInfo.password || !this.RecoverInfo.repeatPassword) {
        alert("密码不能为空");
        return false;
      }
      if  (this.RecoverInfo.password !== this.RecoverInfo.repeatPassword) {
        alert("两次输入密码不相同")
        return false;
      }
      const data = {
        username: this.RecoverInfo.username,
        email: this.RecoverInfo.email,
        password: this.RecoverInfo.password
      }
      this.$axios({
        url: "reg/recover/p",
        method: "post",
        data: data
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
          this.$router.push("/login");
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 验证码验证
    codeV(){
      this.$refs.from1.validate((valid) =>{
        if (valid){
          const data = {
            username: this.RecoverInfo.username,
            email: this.RecoverInfo.email,
            code: this.RecoverInfo.code
          }
          this.$axios({
            url: "reg/recover/code/v",
            method: "post",
            data: data
          }).then(msg => {
            if (msg.data.code === 200) {
              this.step = 3
            } else {
              alert(msg.data.message)
            }
          })
        }else {
          this.$message({
            showClose: true,
            message: '字段不能为空！',
            type: 'error'
          })
          return false; // 中断执行
        }
      })


    },

    // 账号邮箱提交验证
    usernameAndEmailSubmit(){
      this.$refs.from.validate((valid) =>{
        if (valid){
          const data = {
            username: this.RecoverInfo.username,
            email: this.RecoverInfo.email
          }
          this.$axios({
            url: "reg/recover",
            method: "post",
            data: data,
          }).then(msg => {
            if (msg.data.code === 200) {
              this.step = 2;
            } else {
              alert(msg.data.message);
            }
          })
        }else {
          this.$message({
            showClose: true,
            message: '字段不能为空！',
            type: 'error'
          })
          return false; // 中断执行
        }
      })

    },


    //请求验证码
    requestCode(){
      const data = {
        username: this.RecoverInfo.username,
        email: this.RecoverInfo.email
      }
      this.$axios({
        url: "reg/recover/code",
        method: "post",
        data: data,
      }).then(msg => {
        if (msg.data.code === 200) {
          alert(msg.data.message)
        } else {
          alert(msg.data.message)
        }
      })
    },

    // 验证码倒计时
    countDown() {
      if (this.isSend) return
      // 发送验证码到后端请求方法
      this.requestCode()
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

  },
}
</script>

<style scoped>
.home {
  background: url("../../../assets/pexels-pixabay-39284.jpg");
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%
}

.el-login {
  width: 600px;
  height: 350px;
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
  margin-top: 300px;
  margin-left: 300px;
  /*box-shadow: 5px 5px 10px #b3d4fc;*/
  /*background-color: rgba(181, 186, 246, 0.5);*/
  border-radius: 5px;
}

</style>
