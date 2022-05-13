<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="医院总数" prop="hosTotal">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.hosTotal"-->
      <!--    placeholder="请输入医院总数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="接入医院数" prop="hosAccess">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.hosAccess"-->
      <!--    placeholder="请输入接入医院数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="监控数量" prop="monitor">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.monitor"-->
      <!--    placeholder="请输入监控数量"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="在线监控数量" prop="monitorOnline">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.monitorOnline"-->
      <!--    placeholder="请输入在线监控数量"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="测温一体机数量" prop="temperature">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperature"-->
      <!--    placeholder="请输入测温一体机数量"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="测温一体机在线数" prop="temperatureOnline">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperatureOnline"-->
      <!--    placeholder="请输入测温一体机在线数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="人脸抓拍摄像头数" prop="faceCamera">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.faceCamera"-->
      <!--    placeholder="请输入人脸抓拍摄像头数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="人脸抓拍摄像头在线数" prop="faceCameraOnline">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.faceCameraOnline"-->
      <!--    placeholder="请输入人脸抓拍摄像头在线数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item label="数据类型" prop="dataType">
        <el-select v-model="queryParams.dataType" placeholder="请选择数据类型" clearable>
          <el-option
            v-for="dict in dict.type.data_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['jkza:yyBase:add']"
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
          v-hasPermi="['jkza:yyBase:edit']"
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
          v-hasPermi="['jkza:yyBase:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyBase:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyBaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="医院总数" align="center" prop="hosTotal" />
      <el-table-column label="接入医院数" align="center" prop="hosAccess" />
      <el-table-column label="监控数量" align="center" prop="monitor" />
      <el-table-column label="在线监控数量" align="center" prop="monitorOnline" />
      <el-table-column label="测温一体机数量" align="center" prop="temperature" />
      <el-table-column label="测温一体机在线数" align="center" prop="temperatureOnline" />
      <el-table-column label="人脸抓拍摄像头数" align="center" prop="faceCamera" />
      <el-table-column label="人脸抓拍摄像头在线数" align="center" prop="faceCameraOnline" />
      <el-table-column label="数据类型" align="center" prop="dataType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.data_type" :value="scope.row.dataType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:yyBase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyBase:remove']"
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

    <!-- 添加或修改基础数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="130px">
        <el-form-item label="医院总数" prop="hosTotal">
          <el-input v-model="form.hosTotal" placeholder="请输入医院总数" />
        </el-form-item>
        <el-form-item label="接入医院数" prop="hosAccess">
          <el-input v-model="form.hosAccess" placeholder="请输入接入医院数" />
        </el-form-item>
        <el-form-item label="监控数量" prop="monitor">
          <el-input v-model="form.monitor" placeholder="请输入监控数量" />
        </el-form-item>
        <el-form-item label="在线监控数量" prop="monitorOnline">
          <el-input v-model="form.monitorOnline" placeholder="请输入在线监控数量" />
        </el-form-item>
        <el-form-item label="测温一体机数量" prop="temperature">
          <el-input v-model="form.temperature" placeholder="请输入测温一体机数量" />
        </el-form-item>
        <el-form-item label="测温一体机在线数" prop="temperatureOnline">
          <el-input v-model="form.temperatureOnline" placeholder="请输入测温一体机在线数" />
        </el-form-item>
        <el-form-item label="人脸抓拍摄像头数" prop="faceCamera">
          <el-input v-model="form.faceCamera" placeholder="请输入人脸抓拍摄像头数" />
        </el-form-item>
        <el-form-item label="人脸抓拍摄像头在线数" prop="faceCameraOnline">
          <el-input v-model="form.faceCameraOnline" placeholder="请输入人脸抓拍摄像头在线数" />
        </el-form-item>
<!--        <el-form-item label="数据类型" prop="dataType">-->
<!--          <el-select v-model="form.dataType" placeholder="请选择数据类型">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.data_type"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--:value="parseInt(dict.value)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listYyBase, getYyBase, delYyBase, addYyBase, updateYyBase } from "@/api/jkza/yyBase";

export default {
  name: "YyBase",
  dicts: ['data_type'],
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
      // 基础数据表格数据
      yyBaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hosTotal: null,
        hosAccess: null,
        monitor: null,
        monitorOnline: null,
        temperature: null,
        temperatureOnline: null,
        faceCamera: null,
        faceCameraOnline: null,
        dataType: null
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
    /** 查询基础数据列表 */
    getList() {
      this.loading = true;
      listYyBase(this.queryParams).then(response => {
        this.yyBaseList = response.rows;
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
        hosTotal: null,
        hosAccess: null,
        monitor: null,
        monitorOnline: null,
        temperature: null,
        temperatureOnline: null,
        faceCamera: null,
        faceCameraOnline: null,
        dataType: null
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
      this.title = "添加基础数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyBase(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改基础数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyBase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyBase(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除基础数据编号为"' + ids + '"的数据项？').then(function() {
        return delYyBase(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyBase/export', {
        ...this.queryParams
      }, `yyBase_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
