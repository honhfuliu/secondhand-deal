<template>
  <div style="background-color: #ededed; height: 100%">
    <div class="div-body">
      <el-container>
        <!--头部start-->
        <el-header class="header-a" height="70px">
          <div style="display: flex" >
            <div>
              <img :src="`${URL}/${myInfo.path}`" class="img-a">
            </div>
            <div style="line-height: 70px; width: 240px">
              <span style="margin-left: 15px; color: #FFFFFF; font-size: 17px">{{ myInfo.name }}</span>
            </div>
            <div style="width: 100%">
              <div style="margin-top: 20px">
                <span style="font-size: 18px; color: #FFFFFF;" v-if="chatUser !== null">{{ chatUser.nickName }}</span>
                <span style="font-size: 18px; color: #FFFFFF;" v-if="chatUser === null">&nbsp;</span>

              </div>
              <div style="float: right; margin: -30px -8px">
                <el-button @click="closeChat">退出聊天</el-button>
              </div>
            </div>
          </div>
        </el-header>
        <!--头部end-->
        <el-container>
          <!--信息列表start-->
          <el-aside width="320px" class="aside-a">
            <div >
              <div @click="getChatMessage(item, $event)" tabindex="1" class="chat-list" v-for="item in chatList" :key="item">
                <div>
                  <img :src="`${URL}/${item.img}`" style="height: 60px; margin-top: 10px; margin-left: 13px">
                </div>

                <div style="margin-left: 20px; width: 100px">
                  <div style="line-height: 42px">
                    <span style="font-weight: 600" class="span-nick-name">{{ item.nickName }}</span>
                  </div>
                  <div style="line-height: 15px">
                    <span style="color: #92959a" class="span-nick-name">{{ item.endMessage }}</span>
                  </div>
                </div>
                <div style="margin-left: 30px; line-height: 77px">
                  <span >{{ item.endTime }}</span>
                </div>
              </div>
            </div>
          </el-aside>
          <!--信息列表end-->

          <!--聊天框start-->
          <el-main class="main-a">
            <div class="position">
              <div style=" height: 400px; overflow:auto; display: flex">
                <div class="chatBox" >
                  <div  v-for="item in my_message" :key="item">
                    <div class="message frnd_message"  v-if="item.sort === 0">
                      <img :src="`${URL}/${chatUser.img}`" style="height: 60px; border-radius: 50%; margin: 9px 7px">
                      <p> {{item.message}}</p>
                    </div>

                    <div class="message my_message"  v-show="item.sort === 1">
                      <p>{{item.message}}</p>
                      <img :src="`${URL}/${myInfo.path}`" style="height: 60px; border-radius: 50%; margin: 9px 7px">
                    </div>

                  </div>

                </div>
              </div>

              <div  style=" height: 250px">
                <div class="infoIcon">
                  <i  class="el-icon-picture-outline-round"></i>
                  <i @click="extend('发送商品')" class="el-icon-sell"></i>
                  <i @click="extend('设置')" class="el-icon-setting"></i>
                  <i @click="extend('聊天记录')" class="el-icon-chat-dot-round"></i>
                  <i @click="extend('更多选项')" class="el-icon-more-outline"></i>
                </div>
                <div class="input-with-button">
                  <el-input :rows="8" type="text" placeholder="输入聊天信息" v-model="message">
                  </el-input>
                  <el-button @click="send" class="send-button">发送</el-button>
                </div>

              </div>
            </div>
          </el-main>
          <!--聊天框end-->
        </el-container>
      </el-container>
    </div>

  </div>




</template>

<script>

export default {
  name: "userChat",
  data(){
    return{
      // 个人信息存储
      myInfo: null,

      // 用户聊天用户列表
      chatList: [],

      // 当前聊天用户信息存储
      chatUser: null,

      content: null,
      message: null,
      username: null,

      webSocket: null,


      // 当前聊天用户信息存储
      my_message: [],

      URL: null,
    }

  },

  created() {
    this.getUserInfo()
    this.getChatList()
    this.connect()
    this.URL = this.$axios.defaults.baseURL
  },

  methods:{
    // 获取个人信息
    getUserInfo(){
      this.$axios({
        url: "/user/info",
        method: "get",
        headers: {token: sessionStorage.getItem("token")}
      }).then(msg => {
        if (msg.data.code === 200) {
          this.myInfo = msg.data.data
        }else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },

    // 关闭聊天
    closeChat(){
      this.webSocket.close()
      window.close()
    },

    // 获取到当前聊天用户信息
    getChatMessage(chatInfo, event){
      const chatListItems = document.querySelectorAll('.chat-list');
      chatListItems.forEach(item => {
        item.style.backgroundColor = ''; // 清除背景颜色
      });

      // 设置当前选中项的背景颜色
      event.currentTarget.style.backgroundColor = '#bbb4b4'; // 设置背景颜色

      // 将聊天用户的信息单独存储
      this.chatUser = chatInfo;

      // 将选中的聊天用户信息放在 chatList 的第一位
      const index = this.chatList.findIndex(item => item.id === chatInfo.id);
      if (index !== -1) {
        // 移除该元素并将其添加到列表开头
        const [selectedChat] = this.chatList.splice(index, 1);
        this.chatList.unshift(selectedChat);
      }

      this.$axios({
        url: "chat/info",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
        params: { friendId: this.chatUser.id }
      }).then(msg => {
        if (msg.data.code === 200) {
          this.my_message = msg.data.data
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message,
          })
        }
      })
    },

    // 获取到用户的聊天列表
    getChatList(){
      this.$axios({
        url: "chat",
        method: "get",
        headers: {
          "token": sessionStorage.getItem("token")
        },
      }).then(msg => {
        if (msg.data.code === 200) {
          console.log(msg.data.data)
          this.chatList = msg.data.data;
        } else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message,
          })
        }
      })
    },


    // 发送用户信息
    send(){
      const data = {
        message: this.message,
        name: this.chatUser.name,
        type: "text",
        id: this.chatUser.id,
      }
      this.my_message.push({message: this.message, sort: 1, type: "text"})
      const message =  JSON.stringify(data)
      this.webSocket.send(message)
    },


    // 创建连接
    connect(){
      let token =  sessionStorage.getItem("token")
      if (token === null) {
        this.$notify.warning({
          title: "警告",
          message: "用户未登录",
        })
        this.$router.push({name:"Login"})
      }

      // 建立webSocket连接
      this.webSocket =  new WebSocket("ws://localhost:8989/chatServer?token=" +  sessionStorage.getItem("token"))



      // 接收消息
      this.webSocket.onmessage = e => {
        this.my_message.push({message: e.data, sort: 0, type: "text"})
      };
    }

  }
}
</script>
`
<style scoped>



.chat-list{
  height: 80px;
  display: flex;
  cursor: pointer;
}
/*.chat-list:focus{*/
/*  background-color: #FFFFFF;*/

/*}*/



.infoIcon {
  height: 40px;
  width: 100%;
  display: flex;
  align-items: center;
}
.infoIcon i {
  font-size: 24px;
  color: #676767;
  margin-left: 15px;
  cursor: pointer;
}


/*------------*/

.span-nick-name{
  display: block;
  width: 100px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.chatBox{
  position: relative;
  width: 100%;
  height: calc(100% - 120px);
  padding: 50px;
  overflow-y: auto;
}

.message {
  position: relative;
  overflow:auto;
  display: flex;
  width: 100%;
  margin: 5px 0;
}

.frnd_message p {
  position: relative;
  right: 0;
  text-align: left;
  max-width: 60%;
  padding: 12px;
  background: #b3d4fc;
  border-radius: 10px;
  overflow: hidden;
}

.my_message p {
  position: relative;
  right: 0;
  text-align: left;
  max-width: 60%;
  padding: 12px;
  background: #aaeea7;
  border-radius: 10px;
  overflow: hidden;
}

.my_message{
  justify-content: flex-end;
}

.frnd_message {
  justify-content: flex-start;
}

::-webkit-scrollbar {
  width: 5px;

}

::-webkit-scrollbar-thumb {
  background: #ccc;
  border-radius: 5px;
}

::-webkit-scrollbar-thumb:hover {
  background: #f40;
}


.img-a{
  width: 60px;
  border-radius: 50%;
  margin-top: 5px;
}
.main-a{
  background-color: #FFFFFF;
  border-radius: 0 0 15px 0;
  height: 700px
}

.aside-a{
  background-color: #eae8e7;
  border-radius: 0 0 0 15px;
  height: 700px
}
.header-a{
  background-color: #468cc8;
  border-radius: 15px 15px 0 0;
}

.div-body{
  width: 80%;
  height: 80%;
  position: fixed;
  top: 10%;
  left: 10%;

}

</style>
