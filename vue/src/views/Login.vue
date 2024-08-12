<template>
  <div>
    <div class="login-container">
      <div style="width: 350px" class="login-box">
        <div style="font-weight: bold; font-size: 25px;text-align: center; margin-bottom: 30px ">登 录</div>
        <el-form :model="data.from" :rules="rules" ref="ruleForm">
          <el-form-item prop="username">
            <el-input prefix-icon="User" v-model="data.from.username" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password prefix-icon="lock" v-model="data.from.password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="login">登 录</el-button>
          </el-form-item>
        </el-form>
        <div style="margin-top: 30px; text-align: right">
          还没有账号？请 <a href="/register">注册</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>

import {reactive, ref} from "vue"
import request from "../utils/request";
import {ElMessage} from "element-plus";
import router from "../router";

const data = reactive({
  from: {}

})

const rules = reactive({  //账号密码校验
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
})

const ruleForm = ref()  //登录校验

const login = () => {
  ruleForm.value.validate((valid) => {
    if(valid) {
      request.post('/login',data.from).then(res => {
        if (res.code === '200'){
          localStorage.setItem('student-user',JSON.stringify(res.data))
          ElMessage.success('登录成功')
          console.log(res.data)
          router.push('/home')//登录成功，跳转到主页
        } else {
          ElMessage.error(res.msg)  //登录失败提示
        }
      })
    }
  })
}
</script>

<style scoped>
.login-container{
  min-height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("src/assets/imgs/background.jpg");
  background-size: cover;


}

.login-box{
  background-color: #ffff;
  box-shadow: 0 0 0 rgba(0,0,0,0.1);
  border: 1px solid#dddddd;
  padding: 30px;
  border-radius: 15px;

}
</style>