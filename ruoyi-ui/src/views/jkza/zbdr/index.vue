<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="日期" prop="zbDate">
        <el-input
          v-model="queryParams.zbDate"
          placeholder="请输入日期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="星期" prop="weekDate">
        <el-input
          v-model="queryParams.weekDate"
          placeholder="请输入星期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="带班领导" prop="leaderName">
        <el-input
          v-model="queryParams.leaderName"
          placeholder="请输入带班领导"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="带班领导电话" prop="leaderPhone">
        <el-input
          v-model="queryParams.leaderPhone"
          placeholder="请输入带班领导电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="值班长" prop="chiefName">
        <el-input
          v-model="queryParams.chiefName"
          placeholder="请输入值班长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="值班长电话" prop="chiefPhone">
        <el-input
          v-model="queryParams.chiefPhone"
          placeholder="请输入值班长电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="白班一" prop="dayNameOne">
        <el-input
          v-model="queryParams.dayNameOne"
          placeholder="请输入白班一"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="白班二" prop="dayNameTwo">
        <el-input
          v-model="queryParams.dayNameTwo"
          placeholder="请输入白班二"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="夜班一" prop="nightNameOne">
        <el-input
          v-model="queryParams.nightNameOne"
          placeholder="请输入夜班一"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="夜班二" prop="nightNameTwo">
        <el-input
          v-model="queryParams.nightNameTwo"
          placeholder="请输入夜班二"
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
          v-hasPermi="['jkza:zbdr:add']"
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
          v-hasPermi="['jkza:zbdr:edit']"
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
          v-hasPermi="['jkza:zbdr:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:zbdr:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-upload
          :show-file-list="false"
          accept="application/vnd.ms-excel,.xlsx"
          :action=this.drUrl
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="fileUploadBtnText == '正在导入'"
          :before-upload="beforeFileUpload"
        >
          <el-button
            type="success"
            plain
            icon="el-icon-upload2"
            size="mini"
            :loading="fileUploadBtnText == '正在导入'"
          >导入
          </el-button>
        </el-upload>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="zbdrList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="日期" align="center" prop="zbDate" />
      <el-table-column label="星期" align="center" prop="weekDate" />
      <el-table-column label="带班领导" align="center" prop="leaderName" />
      <el-table-column label="带班领导电话" align="center" prop="leaderPhone" />
      <el-table-column label="值班长" align="center" prop="chiefName" />
      <el-table-column label="值班长电话" align="center" prop="chiefPhone" />
      <el-table-column label="白班一" align="center" prop="dayNameOne" />
      <el-table-column label="白班二" align="center" prop="dayNameTwo" />
      <el-table-column label="夜班一" align="center" prop="nightNameOne" />
      <el-table-column label="夜班二" align="center" prop="nightNameTwo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:zbdr:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:zbdr:remove']"
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

    <!-- 添加或修改值班导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="日期" prop="zbDate">
          <el-input v-model="form.zbDate" placeholder="请输入日期" />
        </el-form-item>
        <el-form-item label="星期" prop="weekDate">
          <el-input v-model="form.weekDate" placeholder="请输入星期" />
        </el-form-item>
        <el-form-item label="带班领导" prop="leaderName">
          <el-input v-model="form.leaderName" placeholder="请输入带班领导" />
        </el-form-item>
        <el-form-item label="带班领导电话" prop="leaderPhone">
          <el-input v-model="form.leaderPhone" placeholder="请输入带班领导电话" />
        </el-form-item>
        <el-form-item label="值班长" prop="chiefName">
          <el-input v-model="form.chiefName" placeholder="请输入值班长" />
        </el-form-item>
        <el-form-item label="值班长电话" prop="chiefPhone">
          <el-input v-model="form.chiefPhone" placeholder="请输入值班长电话" />
        </el-form-item>
        <el-form-item label="白班一" prop="dayNameOne">
          <el-input v-model="form.dayNameOne" placeholder="请输入白班一" />
        </el-form-item>
        <el-form-item label="白班二" prop="dayNameTwo">
          <el-input v-model="form.dayNameTwo" placeholder="请输入白班二" />
        </el-form-item>
        <el-form-item label="夜班一" prop="nightNameOne">
          <el-input v-model="form.nightNameOne" placeholder="请输入夜班一" />
        </el-form-item>
        <el-form-item label="夜班二" prop="nightNameTwo">
          <el-input v-model="form.nightNameTwo" placeholder="请输入夜班二" />
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
import { listZbdr, getZbdr, delZbdr, addZbdr, updateZbdr } from "@/api/jkza/zbdr";

export default {
  name: "Zbdr",
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
      // 值班导入表格数据
      zbdrList: [],
      fileUploadBtnText: "导入数据",
      drUrl:this.global.apiUrl + "/zbdr",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        zbDate: null,
        weekDate: null,
        leaderName: null,
        leaderPhone: null,
        chiefName: null,
        chiefPhone: null,
        dayNameOne: null,
        dayNameTwo: null,
        nightNameOne: null,
        nightNameTwo: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    console.log(this.global.apiUrl)
    this.getList();
  },
  methods: {
    /** 查询值班导入列表 */
    getList() {
      this.loading = true;
      listZbdr(this.queryParams).then(response => {
        this.zbdrList = response.rows;
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
        zbDate: null,
        weekDate: null,
        leaderName: null,
        leaderPhone: null,
        chiefName: null,
        chiefPhone: null,
        dayNameOne: null,
        dayNameTwo: null,
        nightNameOne: null,
        nightNameTwo: null
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
      this.title = "添加值班导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getZbdr(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改值班导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateZbdr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addZbdr(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除值班导入编号为"' + ids + '"的数据项？').then(function() {
        return delZbdr(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/zbdr/export', {
        ...this.queryParams
      }, `zbdr_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleUpload() {
      $.table.importExcel();
    },
    fileUploadSuccess(res) {
      if(res == 1){
        this.enabledUploadBtn = true;
        this.uploadBtnIcon = "el-icon-upload2";
        this.fileUploadBtnText = "导入数据";
        this.$message.success("数据导入成功！");
        this.getList();
      }
      if(res == 0){
        this.enabledUploadBtn = true;
        this.uploadBtnIcon = "el-icon-upload2";
        this.fileUploadBtnText = "导入数据";
        this.$message.error("数据导入失败");
        this.getList();
      }
    },
    fileUploadError() {
      this.enabledUploadBtn = true;
      this.uploadBtnIcon = "el-icon-upload2";
      this.$message.success("数据导入失败！");
      this.fileUploadBtnText = "导入数据";
    },
    beforeFileUpload(file) {
      this.enabledUploadBtn = false;
      this.uploadBtnIcon = "el-icon-loading";
      this.fileUploadBtnText = "正在导入";
    },
  }
};
</script>
