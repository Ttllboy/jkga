<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="总数" prop="total">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.total"-->
      <!--    placeholder="请输入总数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="非法捕捞" prop="illegalFish">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.illegalFish"-->
      <!--    placeholder="请输入非法捕捞"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="非法倾倒" prop="illegalDump">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.illegalDump"-->
      <!--    placeholder="请输入非法倾倒"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="重点人员" prop="keyPerson">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.keyPerson"-->
      <!--    placeholder="请输入重点人员"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="重点车辆" prop="keyCar">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.keyCar"-->
      <!--    placeholder="请输入重点车辆"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="非法捕捞比例" prop="illegalFishProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.illegalFishProportion"-->
      <!--    placeholder="请输入非法捕捞比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="非法倾倒比例" prop="illegalDumpProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.illegalDumpProportion"-->
      <!--    placeholder="请输入非法倾倒比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="重点人员比例" prop="keyPersonProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.keyPersonProportion"-->
      <!--    placeholder="请输入重点人员比例"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="重点车辆比例" prop="keyCarProportion">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.keyCarProportion"-->
      <!--    placeholder="请输入重点车辆比例"-->
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
          v-hasPermi="['jkza:stWarnProportion:add']"
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
          v-hasPermi="['jkza:stWarnProportion:edit']"
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
          v-hasPermi="['jkza:stWarnProportion:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:stWarnProportion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stWarnProportionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="总数" align="center" prop="total" />
      <el-table-column label="非法捕捞" align="center" prop="illegalFish" />
      <el-table-column label="非法倾倒" align="center" prop="illegalDump" />
      <el-table-column label="重点人员" align="center" prop="keyPerson" />
      <el-table-column label="重点车辆" align="center" prop="keyCar" />
      <el-table-column label="非法捕捞比例" align="center" prop="illegalFishProportion" />
      <el-table-column label="非法倾倒比例" align="center" prop="illegalDumpProportion" />
      <el-table-column label="重点人员比例" align="center" prop="keyPersonProportion" />
      <el-table-column label="重点车辆比例" align="center" prop="keyCarProportion" />
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
            v-hasPermi="['jkza:stWarnProportion:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:stWarnProportion:remove']"
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

    <!-- 添加或修改预警占比对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="总数" prop="total">
          <el-input v-model="form.total" placeholder="请输入总数" />
        </el-form-item>
        <el-form-item label="非法捕捞" prop="illegalFish">
          <el-input v-model="form.illegalFish" placeholder="请输入非法捕捞" />
        </el-form-item>
        <el-form-item label="非法倾倒" prop="illegalDump">
          <el-input v-model="form.illegalDump" placeholder="请输入非法倾倒" />
        </el-form-item>
        <el-form-item label="重点人员" prop="keyPerson">
          <el-input v-model="form.keyPerson" placeholder="请输入重点人员" />
        </el-form-item>
        <el-form-item label="重点车辆" prop="keyCar">
          <el-input v-model="form.keyCar" placeholder="请输入重点车辆" />
        </el-form-item>
        <el-form-item label="非法捕捞比例" prop="illegalFishProportion">
          <el-input v-model="form.illegalFishProportion" placeholder="请输入非法捕捞比例" />
        </el-form-item>
        <el-form-item label="非法倾倒比例" prop="illegalDumpProportion">
          <el-input v-model="form.illegalDumpProportion" placeholder="请输入非法倾倒比例" />
        </el-form-item>
        <el-form-item label="重点人员比例" prop="keyPersonProportion">
          <el-input v-model="form.keyPersonProportion" placeholder="请输入重点人员比例" />
        </el-form-item>
        <el-form-item label="重点车辆比例" prop="keyCarProportion">
          <el-input v-model="form.keyCarProportion" placeholder="请输入重点车辆比例" />
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
import { listStWarnProportion, getStWarnProportion, delStWarnProportion, addStWarnProportion, updateStWarnProportion } from "@/api/jkza/stWarnProportion";

export default {
  name: "StWarnProportion",
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
      // 预警占比表格数据
      stWarnProportionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        total: null,
        illegalFish: null,
        illegalDump: null,
        keyPerson: null,
        keyCar: null,
        illegalFishProportion: null,
        illegalDumpProportion: null,
        keyPersonProportion: null,
        keyCarProportion: null,
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
    /** 查询预警占比列表 */
    getList() {
      this.loading = true;
      listStWarnProportion(this.queryParams).then(response => {
        this.stWarnProportionList = response.rows;
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
        total: null,
        illegalFish: null,
        illegalDump: null,
        keyPerson: null,
        keyCar: null,
        illegalFishProportion: null,
        illegalDumpProportion: null,
        keyPersonProportion: null,
        keyCarProportion: null,
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
      this.title = "添加预警占比";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStWarnProportion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预警占比";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStWarnProportion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStWarnProportion(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除预警占比编号为"' + ids + '"的数据项？').then(function() {
        return delStWarnProportion(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/stWarnProportion/export', {
        ...this.queryParams
      }, `stWarnProportion_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
