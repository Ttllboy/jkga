<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="绿码人数" prop="green">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.green"-->
      <!--    placeholder="请输入绿码人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="黄码人数" prop="yellow">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.yellow"-->
      <!--    placeholder="请输入黄码人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="红码人数" prop="red">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.red"-->
      <!--    placeholder="请输入红码人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="测温异常人数" prop="temperatureAbnormal">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperatureAbnormal"-->
      <!--    placeholder="请输入测温异常人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="绿码人数比例" prop="greenProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.greenProportion"-->
      <!--    placeholder="请输入绿码人数比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="黄码人数比例" prop="yellowProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.yellowProportion"-->
      <!--    placeholder="请输入黄码人数比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="红码人数比例" prop="redProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.redProportion"-->
      <!--    placeholder="请输入红码人数比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="测温异常人数比例" prop="temperatureProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperatureProportion"-->
      <!--    placeholder="请输入测温异常人数比例"-->
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
          v-hasPermi="['jkza:yyEpidemicPrevention:add']"
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
          v-hasPermi="['jkza:yyEpidemicPrevention:edit']"
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
          v-hasPermi="['jkza:yyEpidemicPrevention:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyEpidemicPrevention:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyEpidemicPreventionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="绿码人数" align="center" prop="green" />
      <el-table-column label="黄码人数" align="center" prop="yellow" />
      <el-table-column label="红码人数" align="center" prop="red" />
      <el-table-column label="测温异常人数" align="center" prop="temperatureAbnormal" />
      <el-table-column label="绿码人数比例" align="center" prop="greenProportion" />
      <el-table-column label="黄码人数比例" align="center" prop="yellowProportion" />
      <el-table-column label="红码人数比例" align="center" prop="redProportion" />
      <el-table-column label="测温异常人数比例" align="center" prop="temperatureProportion" />
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
            v-hasPermi="['jkza:yyEpidemicPrevention:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyEpidemicPrevention:remove']"
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

    <!-- 添加或修改防疫详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="绿码人数" prop="green">
          <el-input v-model="form.green" placeholder="请输入绿码人数" />
        </el-form-item>
        <el-form-item label="黄码人数" prop="yellow">
          <el-input v-model="form.yellow" placeholder="请输入黄码人数" />
        </el-form-item>
        <el-form-item label="红码人数" prop="red">
          <el-input v-model="form.red" placeholder="请输入红码人数" />
        </el-form-item>
        <el-form-item label="测温异常人数" prop="temperatureAbnormal">
          <el-input v-model="form.temperatureAbnormal" placeholder="请输入测温异常人数" />
        </el-form-item>
        <el-form-item label="绿码人数比例" prop="greenProportion">
          <el-input v-model="form.greenProportion" placeholder="请输入绿码人数比例" />
        </el-form-item>
        <el-form-item label="黄码人数比例" prop="yellowProportion">
          <el-input v-model="form.yellowProportion" placeholder="请输入黄码人数比例" />
        </el-form-item>
        <el-form-item label="红码人数比例" prop="redProportion">
          <el-input v-model="form.redProportion" placeholder="请输入红码人数比例" />
        </el-form-item>
        <el-form-item label="测温异常人数比例" prop="temperatureProportion">
          <el-input v-model="form.temperatureProportion" placeholder="请输入测温异常人数比例" />
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
import { listYyEpidemicPrevention, getYyEpidemicPrevention, delYyEpidemicPrevention, addYyEpidemicPrevention, updateYyEpidemicPrevention } from "@/api/jkza/yyEpidemicPrevention";

export default {
  name: "YyEpidemicPrevention",
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
      // 防疫详情表格数据
      yyEpidemicPreventionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        green: null,
        yellow: null,
        red: null,
        temperatureAbnormal: null,
        greenProportion: null,
        yellowProportion: null,
        redProportion: null,
        temperatureProportion: null,
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
    /** 查询防疫详情列表 */
    getList() {
      this.loading = true;
      listYyEpidemicPrevention(this.queryParams).then(response => {
        this.yyEpidemicPreventionList = response.rows;
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
        green: null,
        yellow: null,
        red: null,
        temperatureAbnormal: null,
        greenProportion: null,
        yellowProportion: null,
        redProportion: null,
        temperatureProportion: null,
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
      this.title = "添加防疫详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyEpidemicPrevention(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改防疫详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyEpidemicPrevention(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyEpidemicPrevention(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除防疫详情编号为"' + ids + '"的数据项？').then(function() {
        return delYyEpidemicPrevention(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyEpidemicPrevention/export', {
        ...this.queryParams
      }, `yyEpidemicPrevention_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
