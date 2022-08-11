<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="物资库名称" prop="materialStoreName">
        <el-input
          v-model="queryParams.materialStoreName"
          placeholder="请输入物资库名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="物资等级" prop="materialLv">-->
<!--        <el-input-->
<!--          v-model="queryParams.materialLv"-->
<!--          placeholder="请输入物资等级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="详细地址" prop="detailAddress">-->
<!--        <el-input-->
<!--          v-model="queryParams.detailAddress"-->
<!--          placeholder="请输入详细地址"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="所属单位" prop="affiliatedUnit">-->
<!--        <el-input-->
<!--          v-model="queryParams.affiliatedUnit"-->
<!--          placeholder="请输入所属单位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="联系人姓名" prop="contactName">
        <el-input
          v-model="queryParams.contactName"
          placeholder="请输入联系人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="联系人联系方式" prop="contactPhone">-->
<!--        <el-input-->
<!--          v-model="queryParams.contactPhone"-->
<!--          placeholder="请输入联系人联系方式"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="应急物资名字" prop="emergencyMaterialName">
        <el-input
          v-model="queryParams.emergencyMaterialName"
          placeholder="请输入应急物资名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="规格" prop="specification">-->
<!--        <el-input-->
<!--          v-model="queryParams.specification"-->
<!--          placeholder="请输入规格"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="应急物资数量" prop="emergencyMaterialNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.emergencyMaterialNum"-->
<!--          placeholder="请输入应急物资数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="规格" prop="measuringUnit">-->
<!--        <el-input-->
<!--          v-model="queryParams.measuringUnit"-->
<!--          placeholder="请输入规格"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="数据类型" prop="dataType">-->
<!--        <el-select v-model="queryParams.dataType" placeholder="请选择数据类型" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.data_type"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
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
          v-hasPermi="['jkza:yjMaterial:add']"
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
          v-hasPermi="['jkza:yjMaterial:edit']"
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
          v-hasPermi="['jkza:yjMaterial:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yjMaterial:export']"
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

    <el-table v-loading="loading" :data="yjMaterialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="物资库名称" align="center" prop="materialStoreName" />
      <el-table-column label="物资等级" align="center" prop="materialLv" />
      <el-table-column label="详细地址" align="center" prop="detailAddress" />
      <el-table-column label="所属单位" align="center" prop="affiliatedUnit" />
      <el-table-column label="联系人姓名" align="center" prop="contactName" />
      <el-table-column label="联系人联系方式" align="center" prop="contactPhone" />
      <el-table-column label="应急物资名字" align="center" prop="emergencyMaterialName" />
      <el-table-column label="规格" align="center" prop="specification" />
      <el-table-column label="应急物资数量" align="center" prop="emergencyMaterialNum" />
      <el-table-column label="规格" align="center" prop="measuringUnit" />
<!--      <el-table-column label="数据类型" align="center" prop="dataType">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.data_type" :value="scope.row.dataType"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:yjMaterial:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yjMaterial:remove']"
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

    <!-- 添加或修改物资信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="物资库名称" prop="materialStoreName">
          <el-input v-model="form.materialStoreName" placeholder="请输入物资库名称" />
        </el-form-item>
        <el-form-item label="物资等级" prop="materialLv">
          <el-input v-model="form.materialLv" placeholder="请输入物资等级" />
        </el-form-item>
        <el-form-item label="详细地址" prop="detailAddress">
          <el-input v-model="form.detailAddress" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="所属单位" prop="affiliatedUnit">
          <el-input v-model="form.affiliatedUnit" placeholder="请输入所属单位" />
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactName">
          <el-input v-model="form.contactName" placeholder="请输入联系人姓名" />
        </el-form-item>
        <el-form-item label="联系人联系方式" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系人联系方式" />
        </el-form-item>
        <el-form-item label="应急物资名字" prop="emergencyMaterialName">
          <el-input v-model="form.emergencyMaterialName" placeholder="请输入应急物资名字" />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
          <el-input v-model="form.specification" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="应急物资数量" prop="emergencyMaterialNum">
          <el-input v-model="form.emergencyMaterialNum" placeholder="请输入应急物资数量" />
        </el-form-item>
        <el-form-item label="规格" prop="measuringUnit">
          <el-input v-model="form.measuringUnit" placeholder="请输入规格" />
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
import { listYjMaterial, getYjMaterial, delYjMaterial, addYjMaterial, updateYjMaterial } from "@/api/jkza/yjMaterial";

export default {
  name: "YjMaterial",
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
      // 物资信息表格数据
      yjMaterialList: [],
      fileUploadBtnText: "导入数据",
      drUrl:this.global.apiUrl + "/wzdr",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialStoreName: null,
        materialLv: null,
        detailAddress: null,
        affiliatedUnit: null,
        contactName: null,
        contactPhone: null,
        emergencyMaterialName: null,
        specification: null,
        emergencyMaterialNum: null,
        measuringUnit: null,
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
    /** 查询物资信息列表 */
    getList() {
      this.loading = true;
      listYjMaterial(this.queryParams).then(response => {
        this.yjMaterialList = response.rows;
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
        materialStoreName: null,
        materialLv: null,
        detailAddress: null,
        affiliatedUnit: null,
        contactName: null,
        contactPhone: null,
        emergencyMaterialName: null,
        specification: null,
        emergencyMaterialNum: null,
        measuringUnit: null,
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
      this.title = "添加物资信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYjMaterial(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物资信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYjMaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYjMaterial(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物资信息编号为"' + ids + '"的数据项？').then(function() {
        return delYjMaterial(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yjMaterial/export', {
        ...this.queryParams
      }, `yjMaterial_${new Date().getTime()}.xlsx`)
    },
    fileUploadSuccess(res) {
      if (res == 1) {
        this.enabledUploadBtn = true;
        this.uploadBtnIcon = "el-icon-upload2";
        this.fileUploadBtnText = "导入数据";
        this.$message.success("数据导入成功！");
        this.getList();
      }
      if (res == 0) {
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
