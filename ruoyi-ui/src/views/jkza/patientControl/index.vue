<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
<!--      <el-form-item label="所属街道" prop="streetId">-->
<!--        <el-input-->
<!--          v-model="queryParams.streetId"-->
<!--          placeholder="请输入所属街道"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="病人数" prop="patientCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.patientCount"-->
<!--          placeholder="请输入病人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="新增病人数" prop="addPatientCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.addPatientCount"-->
<!--          placeholder="请输入新增病人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="年份" prop="year">
<!--        <el-input-->
<!--          v-model="queryParams.year"-->
<!--          placeholder="请输入年份"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
        <el-select v-model="queryParams.year" placeholder="请选择年份" clearable>
          <el-option
            v-for="year in years"
            :key="year"
            :label="year"
            :value="year"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="月份" prop="month">
        <el-select v-model="queryParams.month" placeholder="请选择月份" clearable>
          <el-option
            v-for="dict in dict.type.month"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="街道名称" prop="streetName">
<!--        <el-input-->
<!--          v-model="queryParams.streetName"-->
<!--          placeholder="请输入街道名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
        <el-select v-model="queryParams.streetName" placeholder="请选择街道" clearable>
          <el-option
            v-for="street in streetNames"
            :key="street"
            :label="street"
            :value="street"
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
          v-hasPermi="['jkza:patientControl:add']"
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
          v-hasPermi="['jkza:patientControl:edit']"
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
          v-hasPermi="['jkza:patientControl:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:patientControl:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="patientControlList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="所属街道" align="center" prop="streetId" />-->
      <el-table-column label="病人数" align="center" prop="patientCount" />
      <el-table-column label="新增病人数" align="center" prop="addPatientCount" />
      <el-table-column label="年份" align="center" prop="year" />
      <el-table-column label="月份" align="center" prop="month">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.month" :value="scope.row.month"/>
        </template>
      </el-table-column>
      <el-table-column label="街道名称" align="center" prop="streetName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:patientControl:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:patientControl:remove']"
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

    <!-- 添加或修改病人管控对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
<!--        <el-form-item label="所属街道" prop="streetId">-->
<!--          <el-input v-model="form.streetId" placeholder="请输入所属街道" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="病人数" prop="patientCount">-->
<!--          <el-input v-model="form.patientCount" placeholder="请输入病人数" />-->
<!--        </el-form-item>-->
        <el-form-item label="新增病人数" prop="addPatientCount">
          <el-input v-model="form.addPatientCount" placeholder="请输入新增病人数" />
        </el-form-item>
        <el-form-item label="年份" prop="year">
<!--          <el-input v-model="form.year" placeholder="请输入年份" />-->
          <el-select v-model="form.year" placeholder="请选择年份" clearable>
            <el-option
              v-for="year in years"
              :key="year"
              :label="year"
              :value="year"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="月份" prop="month">
          <el-select v-model="form.month" placeholder="请选择月份">
            <el-option
              v-for="dict in dict.type.month"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="街道名称" prop="streetName">
<!--          <el-input v-model="form.streetName" placeholder="请输入街道名称" />-->
          <el-select v-model="form.streetName" placeholder="请选择街道" clearable>
            <el-option
              v-for="street in streetNames"
              :key="street"
              :label="street"
              :value="street"
            />
          </el-select>
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
import { listPatientControl, getPatientControl, delPatientControl, addPatientControl, updatePatientControl } from "@/api/jkza/patientControl";

export default {
  name: "PatientControl",
  dicts: ['month'],
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
      // 病人管控表格数据
      patientControlList: [],
      //街道名称
      streetNames:["城南街道","嘉北街道","塘汇街道","长水街道"],
      //年份
      years:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        streetId: null,
        patientCount: null,
        addPatientCount: null,
        year: null,
        month: null,
        streetName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        addPatientCount:[
          {required: true ,trigger: "change", message: "请输入新增病人数"}
        ],
        year:[
          {required: true ,trigger: "change", message: "请选择年份"}
        ],
        month:[
          {required: true ,trigger: "change", message: "请选择月份"}
        ],
        streetName:[
          {required: true ,trigger: "change", message: "请选择街道"}
        ],

      }
    };
  },
  created() {
    this.getList();
    this.getYears();
  },
  methods: {
    /** 查询病人管控列表 */
    getList() {
      this.loading = true;
      listPatientControl(this.queryParams).then(response => {
        this.patientControlList = response.rows;
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
        streetId: null,
        patientCount: null,
        addPatientCount: null,
        year: null,
        month: null,
        streetName: null
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
      this.title = "添加病人管控";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPatientControl(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改病人管控";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePatientControl(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPatientControl(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除病人管控编号为"' + ids + '"的数据项？').then(function() {
        return delPatientControl(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/patientControl/export', {
        ...this.queryParams
      }, `patientControl_${new Date().getTime()}.xlsx`)
    },
    getYears(){
      let initYear = 2020;
      for(let i = 0; i < 50; i ++){
        this.years.push(initYear);
        initYear = initYear + 1;
      }
    }
  }
};
</script>
