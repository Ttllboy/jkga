<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医院名称" prop="hosName">
        <el-input
          v-model="queryParams.hosName"
          placeholder="请输入医院名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抓拍时间" prop="capTime">
        <el-input
          v-model="queryParams.capTime"
          placeholder="请输入抓拍时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['jkza:yyPeceptionTtype:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['jkza:yyPeceptionTtype:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['jkza:yyPeceptionTtype:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyPeceptionTtype:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyPeceptionTtypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="序号" align="center" prop="id" >
              <template slot-scope="scope">
                <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
              </template>
            </el-table-column>
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="医院名称" align="center" prop="hosName" />
      <el-table-column label="车辆抓拍图片" align="center" prop="imgUrl" >
        <template slot-scope="scope">
          <img :src="scope.row.imgUrl" width="100px" height="100px" />
        </template>
      </el-table-column>
      <el-table-column label="抓拍时间" align="center" prop="capTime" >
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.capTime)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:yyPeceptionTtype:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyPeceptionTtype:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改感知筛选对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型" prop="type">
          <el-input v-model="form.type" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="医院名称" prop="hosName">
          <el-input v-model="form.hosName" placeholder="请输入医院名称" />
        </el-form-item>
        <el-form-item label="车辆抓拍图片" prop="imgUrl">
          <el-input v-model="form.imgUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="抓拍时间" prop="capTime">
          <el-input v-model="form.capTime" placeholder="请输入抓拍时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listYyPeceptionTtype, getYyPeceptionTtype, delYyPeceptionTtype, addYyPeceptionTtype, updateYyPeceptionTtype } from "@/api/jkza/yyPeceptionTtype";

export default {
  name: "YyPeceptionTtype",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 感知筛选表格数据
      yyPeceptionTtypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        hosName: null,
        imgUrl: null,
        capTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询感知筛选列表 */
    getList() {
      this.loading = true;
      listYyPeceptionTtype(this.queryParams).then(response => {
        this.yyPeceptionTtypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        hosName: null,
        imgUrl: null,
        capTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加感知筛选";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyPeceptionTtype(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改感知筛选";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyPeceptionTtype(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyPeceptionTtype(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除感知筛选编号为"' + ids + '"的数据项？').then(function() {
        return delYyPeceptionTtype(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyPeceptionTtype/export', {
        ...this.queryParams
      }, `yyPeceptionTtype_${new Date().getTime()}.xlsx`)
    },

  }
};
</script>
