<template>
  <div>
    <div class="card" style="margin-bottom: 15px">
      <el-input style="width: 300px" v-model="data.name" placeholder="请输入课程名称" :prefix-icon="Search"/>
      <el-button type="primary" style="margin-left: 15px">查询</el-button>
      <el-button type="info">重置</el-button>
    </div>
    <div class="card">
      <el-button type="primary">新增</el-button>
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="60" />
          <el-table-column prop="name" label="课程名称"  />
          <el-table-column prop="no" label="课程编号" />
          <el-table-column prop="times" label="课时"  />
          <el-table-column prop="description" label="课程描述" />
          <el-table-column prop="teacher" label="任课教师"  />

          <el-table-column>
            <template #default="scope">
              <el-button type="primary">编辑</el-button>
              <el-button type="danger">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div>
      <el-pagination background layout="prev, pager, next" :total="1000" />

    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import { Search } from '@element-plus/icons-vue'

const data = reactive({
  name:'',
  tableData:[
    {id:1,name:'基础英语',no:'kc001',times:'4课时',description:'不想学',teacher:'姚梅娟'},
    {id:2,name:'高等数学',no:'kc001',times:'4课时',description:'学不会',teacher:'张鹏涛'},

  ]
})
</script>