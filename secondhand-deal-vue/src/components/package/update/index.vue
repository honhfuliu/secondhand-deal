<template>
  <el-upload
    @change="changes"
    class="avatar-uploader"
    action="http://127.0.0.1"
    list-type="picture-card"
    :file-list="imgList"
    :auto-upload="false"
    :show-file-list="false"
    :on-change="handleChange"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    accept="image/jpeg,image/png">

    <i class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>

<script>
export default {
  name: "update",
  data(){
    return {
      imgList: []
      // imageUrl: this.imgFileList[0]
    }
  },

  props:{
    update:Object,
  },

  created() {


  },

  methods:{
    changes(val){
      console.log("傻逼1")
      this.update.result = this.imgList
    },

    handleChange(file, fileList) {
      this.imgList = fileList;
      console.log('handleChange: ', fileList);
    },
  }
}
</script>

<style scoped>

.avatar-uploader{
  border: 1px dashed #4d4141;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader {
  border-color: #78a3cb;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #989898;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}


</style>
