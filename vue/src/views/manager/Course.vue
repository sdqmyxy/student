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

        <el-button type="primary" @click="handleAdd">新增</el-button>

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
              <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
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

    <el-dialog v-model="data.formVisible" title="课程信息" width="35%">
      <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 30px">
        <el-form-item label="课程名称">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课程编号">
          <el-input v-model="data.form.no" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课时">
          <el-input v-model="data.form.times" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课程描述">
          <el-input v-model="data.form.description" autocomplete="off" />
        </el-form-item>
        <el-form-item label="任课教师">
          <el-input v-model="data.form.teacher" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="data.formVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">保 存</el-button>
          </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive} from "vue";
import { Search } from '@element-plus/icons-vue'
import request from "../../utils/request";
import {ElMessage} from "element-plus";

const data = reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
  total: 0,
  pageNum:1,  //当前页码
  pageSize:10, //每页个数
  formVisible:false,
  form:{}

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

  //调用方法获取后台数据
  load()
const handelCurrentChange = () => {
  //翻页的时候要刷新数据
  load()
}
const reset = () => {
  //重置reset
  data.name = ''
  data.no = ''
  data.teacher = ''
  load()
}

const handleAdd = () => {
  //新增表单弹窗
  data.form = {}
  data.formVisible = true

}

// 保存数据
const save = () => {
  request.request({
    url: data.form.id ? '/course/update' : '/course/add',
    method:data.form.id ? 'PUT' : 'POST',
    data:data.form

  }).then(res => {
    if (res.code === '200') {
      load()    // 重新获取数据
      data.formVisible = false  // 关闭弹窗
      ElMessage.success("操作成功")
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

</script>