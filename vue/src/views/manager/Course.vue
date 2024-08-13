<template>
  <div>
    <div class="card" style="margin-bottom: 15px">
      <el-input style="width: 200px;margin-right:10px" v-model="data.name" placeholder="请输入课程名称" :prefix-icon="Search"/>
      <el-input style="width: 200px;margin-right:10px" v-model="data.no" placeholder="请输入课程编号" :prefix-icon="Search"/>
      <el-input style="width: 200px;" v-model="data.teacher" placeholder="请输入任课老师" :prefix-icon="Search"/>
      <el-button type="primary" style="margin-left: 15px" @click="load">查询</el-button>
      <el-button type="info" @click="reset()">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">

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
      <el-pagination v-model:current-page="data.pageNum" v-model:page-size="data.pageSize"
                     @current-change="handelCurrentChange"
                     background layout="prev, pager, next" :total="data.total" />

    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import { Search } from '@element-plus/icons-vue'
import request from "../../utils/request";

const data = reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
  total: 0,
  pageNum:1,  //当前页码
  pageSize:5  //每页个数
})

const load = () => {
  request.get('/course/selectPage', {
    params: {
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      name:data.name,
      no:data.no,
      teacher:data.teacher
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total || 0
  })
}
// 调用方法获取后台数据
load()
  //调用方法获取后台数据
  load()
const handelCurrentChange = () => {
  //翻页的时候要刷新数据
  load()
}
const reset = () => {
  data.name = ''
  data.no = ''
  data.teacher = ''
  load()
}

</script>