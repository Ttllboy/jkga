<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="空气质量" prop="airQuality">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.airQuality"-->
      <!--    placeholder="请输入空气质量"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="固废点总容量" prop="solidWasteCapacity">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.solidWasteCapacity"-->
      <!--    placeholder="请输入固废点总容量"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="当前水质情况" prop="waterQuality">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.waterQuality"-->
      <!--    placeholder="请输入当前水质情况"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="水质情况均值" prop="waterQualityAverage">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.waterQualityAverage"-->
      <!--    placeholder="请输入水质情况均值"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="接入设备数" prop="accessEquipment">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.accessEquipment"-->
      <!--    placeholder="请输入接入设备数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="在线设备数" prop="onlineEquipment">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.onlineEquipment"-->
      <!--    placeholder="请输入在线设备数"-->
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
          v-hasPermi="['jkza:stBase:add']"
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
          v-hasPermi="['jkza:stBase:edit']"
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
          v-hasPermi="['jkza:stBase:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:stBase:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stBaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="空气质量" align="center" prop="airQuality" />
      <el-table-column label="固废点总容量" align="center" prop="solidWasteCapacity" />
      <el-table-column label="当前水质情况" align="center" prop="waterQuality" />
      <el-table-column label="水质情况均值" align="center" prop="waterQualityAverage" />
      <el-table-column label="接入设备数" align="center" prop="accessEquipment" />
      <el-table-column label="在线设备数" align="center" prop="onlineEquipment" />
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
            v-hasPermi="['jkza:stBase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:stBase:remove']"
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
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="空气质量" prop="airQuality">
          <el-input v-model="form.airQuality" placeholder="请输入空气质量" />
        </el-form-item>
        <el-form-item label="固废点总容量" prop="solidWasteCapacity">
          <el-input v-model="form.solidWasteCapacity" placeholder="请输入固废点总容量" />
        </el-form-item>
        <el-form-item label="当前水质情况" prop="waterQuality">
          <el-input v-model="form.waterQuality" placeholder="请输入当前水质情况" />
        </el-form-item>
        <el-form-item label="水质情况均值" prop="waterQualityAverage">
          <el-input v-model="form.waterQualityAverage" placeholder="请输入水质情况均值" />
        </el-form-item>
        <el-form-item label="接入设备数" prop="accessEquipment">
          <el-input v-model="form.accessEquipment" placeholder="请输入接入设备数" />
        </el-form-item>
        <el-form-item label="在线设备数" prop="onlineEquipment">
          <el-input v-model="form.onlineEquipment" placeholder="请输入在线设备数" />
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
import { listStBase, getStBase, delStBase, addStBase, updateStBase } from "@/api/jkza/stBase";

export default {
  name: "StBase",
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
      stBaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        airQuality: null,
        solidWasteCapacity: null,
        waterQuality: null,
        waterQualityAverage: null,
        accessEquipment: null,
        onlineEquipment: null,
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
      listStBase(this.queryParams).then(response => {
        this.stBaseList = response.rows;
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
        airQuality: null,
        solidWasteCapacity: null,
        waterQuality: null,
        waterQualityAverage: null,
        accessEquipment: null,
        onlineEquipment: null,
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
      getStBase(id).then(response => {
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
            updateStBase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStBase(this.form).then(response => {
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
        return delStBase(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/stBase/export', {
        ...this.queryParams
      }, `stBase_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
