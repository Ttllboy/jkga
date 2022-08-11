<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="人脸照片" prop="faceImg">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.faceImg"-->
      <!--    placeholder="请输入人脸照片"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="抓拍时间" prop="snapTime">-->
      <!--  <el-date-picker clearable-->
      <!--    v-model="queryParams.snapTime"-->
      <!--    type="date"-->
      <!--    value-format="yyyy-MM-dd"-->
      <!--    placeholder="请选择抓拍时间">-->
      <!--  </el-date-picker>-->
      <!--</el-form-item>-->
      <el-form-item label="抓拍位置" prop="snapPlace">
        <el-input
          v-model="queryParams.snapPlace"
          placeholder="请输入抓拍位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="体温数据" prop="temperature">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperature"-->
      <!--    placeholder="请输入体温数据"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item label="健康码" prop="healthCode">
        <el-select v-model="queryParams.healthCode" placeholder="请选择健康码" clearable>
          <el-option
            v-for="dict in dict.type.health_code"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['jkza:yyFaceCapture:add']"
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
          v-hasPermi="['jkza:yyFaceCapture:edit']"
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
          v-hasPermi="['jkza:yyFaceCapture:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyFaceCapture:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyFaceCaptureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="人脸照片" align="center" prop="faceImg" />
      <el-table-column label="抓拍时间" align="center" prop="snapTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.snapTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="抓拍位置" align="center" prop="snapPlace" />
      <el-table-column label="体温数据" align="center" prop="temperature" />
      <el-table-column label="健康码" align="center" prop="healthCode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.health_code" :value="scope.row.healthCode"/>
        </template>
      </el-table-column>
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
            v-hasPermi="['jkza:yyFaceCapture:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyFaceCapture:remove']"
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

    <!-- 添加或修改人脸抓拍对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="人脸照片" prop="faceImg">
          <el-input v-model="form.faceImg" placeholder="请输入人脸照片" />
        </el-form-item>
        <el-form-item label="抓拍时间" prop="snapTime">
          <el-date-picker clearable
            v-model="form.snapTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择抓拍时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="抓拍位置" prop="snapPlace">
          <el-input v-model="form.snapPlace" placeholder="请输入抓拍位置" />
        </el-form-item>
        <el-form-item label="体温数据" prop="temperature">
          <el-input v-model="form.temperature" placeholder="请输入体温数据" />
        </el-form-item>
        <el-form-item label="健康码" prop="healthCode">
          <el-select v-model="form.healthCode" placeholder="请选择健康码">
            <el-option
              v-for="dict in dict.type.health_code"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listYyFaceCapture, getYyFaceCapture, delYyFaceCapture, addYyFaceCapture, updateYyFaceCapture } from "@/api/jkza/yyFaceCapture";

export default {
  name: "YyFaceCapture",
  dicts: ['health_code', 'data_type'],
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
      // 人脸抓拍表格数据
      yyFaceCaptureList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        faceImg: null,
        snapTime: null,
        snapPlace: null,
        temperature: null,
        healthCode: null,
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
    /** 查询人脸抓拍列表 */
    getList() {
      this.loading = true;
      listYyFaceCapture(this.queryParams).then(response => {
        this.yyFaceCaptureList = response.rows;
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
        faceImg: null,
        snapTime: null,
        snapPlace: null,
        temperature: null,
        healthCode: null,
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
      this.title = "添加人脸抓拍";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyFaceCapture(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人脸抓拍";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyFaceCapture(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyFaceCapture(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除人脸抓拍编号为"' + ids + '"的数据项？').then(function() {
        return delYyFaceCapture(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyFaceCapture/export', {
        ...this.queryParams
      }, `yyFaceCapture_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
