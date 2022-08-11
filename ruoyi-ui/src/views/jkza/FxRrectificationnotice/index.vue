<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目信息编号" prop="projectguid">
        <el-input
          v-model="queryParams.projectguid"
          placeholder="请输入项目信息编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整改编号" prop="noticenum">
        <el-input
          v-model="queryParams.noticenum"
          placeholder="请输入整改编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查部分" prop="checkposition">
        <el-input
          v-model="queryParams.checkposition"
          placeholder="请输入检查部分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查日期" prop="checkdatet">
        <el-date-picker clearable
          v-model="queryParams.checkdatet"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否回复" prop="isreply">
        <el-input
          v-model="queryParams.isreply"
          placeholder="请输入是否回复"
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
          v-hasPermi="['jkza:FxRrectificationnotice:add']"
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
          v-hasPermi="['jkza:FxRrectificationnotice:edit']"
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
          v-hasPermi="['jkza:FxRrectificationnotice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:FxRrectificationnotice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="FxRrectificationnoticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目信息编号" align="center" prop="projectguid" />
      <el-table-column label="整改编号" align="center" prop="noticenum" />
      <el-table-column label="检查部分" align="center" prop="checkposition" />
      <el-table-column label="通知书类型" align="center" prop="noticetype" />
      <el-table-column label="检查日期" align="center" prop="checkdatet" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkdatet, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否回复" align="center" prop="isreply" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:FxRrectificationnotice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:FxRrectificationnotice:remove']"
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

    <!-- 添加或修改整改信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目信息编号" prop="projectguid">
          <el-input v-model="form.projectguid" placeholder="请输入项目信息编号" />
        </el-form-item>
        <el-form-item label="整改编号" prop="noticenum">
          <el-input v-model="form.noticenum" placeholder="请输入整改编号" />
        </el-form-item>
        <el-form-item label="检查部分" prop="checkposition">
          <el-input v-model="form.checkposition" placeholder="请输入检查部分" />
        </el-form-item>
        <el-form-item label="通知书类型" prop="noticetype">
          <el-input v-model="form.noticetype" placeholder="请输入通知书类型" />
        </el-form-item>
        <el-form-item label="检查日期" prop="checkdatet">
          <el-date-picker clearable
            v-model="form.checkdatet"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否回复" prop="isreply">
          <el-input v-model="form.isreply" placeholder="请输入是否回复" />
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
import { listFxRrectificationnotice, getFxRrectificationnotice, delFxRrectificationnotice, addFxRrectificationnotice, updateFxRrectificationnotice,listProjectId } from "@/api/jkza/FxRrectificationnotice";

export default {
  name: "FxRrectificationnotice",
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
      // 整改信息表格数据
      FxRrectificationnoticeList: [],
      // 弹出层标题
      title: "",
      projectId:[],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectguid: null,
        noticenum: null,
        checkposition: null,
        noticetype: null,
        checkdatet: null,
        isreply: null,
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
    /** 查询整改信息列表 */
    getList() {
      this.loading = true;
      listFxRrectificationnotice(this.queryParams).then(response => {
        this.FxRrectificationnoticeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listProjectId().then(res => {
        this.projectId = res;
        console.log(res);
        this.ifExist();
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
        projectguid: null,
        noticenum: null,
        checkposition: null,
        noticetype: null,
        checkdatet: null,
        isreply: null,
        createTime: null
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
      this.ids = selection.map(item => item.projectguid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加整改信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectguid = row.projectguid || this.ids
      getFxRrectificationnotice(projectguid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // if (this.form.projectguid != null) {
          if (this.ifExist(this.form.projectguid)) {
            updateFxRrectificationnotice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFxRrectificationnotice(this.form).then(response => {
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
      const projectguids = row.projectguid || this.ids;
      this.$modal.confirm('是否确认删除整改信息编号为"' + projectguids + '"的数据项？').then(function() {
        return delFxRrectificationnotice(projectguids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/FxRrectificationnotice/export', {
        ...this.queryParams
      }, `FxRrectificationnotice_${new Date().getTime()}.xlsx`)
    },
    ifExist(projectId){
      for(let i = 0; i < this.projectId.length; i++){
        if (projectId == this.projectId[i].label){
          return true;
        }else {
          return false;
        }
      }

    }

  }
};
</script>
