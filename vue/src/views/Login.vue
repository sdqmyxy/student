<template>
  <div>
    <div class="login-container">
      <div style="width: 350px" class="login-box">
        <div style="font-weight: bold; font-size: 25px;text-align: center; margin-bottom: 30px ">登 录</div>
        <el-form ref="form" :model="data.from" >
          <el-form-item>
            <el-input prefix-icon="User" v-model="data.from.username" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item>
            <el-input prefix-icon="lock" v-model="data.from.password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%">登 录</el-button>
          </el-form-item>
        </el-form>
        <div style="margin-top: 30px; text-align: right">
          还没有账号？请<a href="/register">注册</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {reactive} from "vue"

const data = reactive({
  "from": {}
})
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
  box-shadow: 0 0 0px rgba(0,0,0,0.1);
  border: 1px solid#dddddd;
  padding: 30px;
  border-radius: 15px;

}
</style>