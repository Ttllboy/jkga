<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <!--<el-form-item label="人脸抓拍" prop="faceSnap">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.faceSnap"-->
      <!--    placeholder="请输入人脸抓拍"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="车辆抓拍" prop="carSnap">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.carSnap"-->
      <!--    placeholder="请输入车辆抓拍"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="测温人数" prop="temperatureSnap">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperatureSnap"-->
      <!--    placeholder="请输入测温人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['jkza:yyPerception:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['jkza:yyPerception:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['jkza:yyPerception:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyPerception:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyPerceptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
<!--      <el-table-column label="序号" align="center" prop="id" >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="人脸抓拍" align="center" prop="faceSnap" />
      <el-table-column label="车辆抓拍" align="center" prop="carSnap" />
      <el-table-column label="测温人数" align="center" prop="temperatureSnap" />
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
            v-hasPermi="['jkza:yyPerception:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['jkza:yyPerception:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改感知信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="人脸抓拍" prop="faceSnap">
          <el-input v-model="form.faceSnap" placeholder="请输入人脸抓拍" />
        </el-form-item>
        <el-form-item label="车辆抓拍" prop="carSnap">
          <el-input v-model="form.carSnap" placeholder="请输入车辆抓拍" />
        </el-form-item>
        <el-form-item label="测温人数" prop="temperatureSnap">
          <el-input v-model="form.temperatureSnap" placeholder="请输入测温人数" />
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
import { listYyPerception, getYyPerception, delYyPerception, addYyPerception, updateYyPerception } from "@/api/jkza/yyPerception";

export default {
  name: "YyPerception",
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
      // 感知信息表格数据
      yyPerceptionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        faceSnap: null,
        carSnap: null,
        temperatureSnap: null,
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
    /** 查询感知信息列表 */
    getList() {
      this.loading = true;
      listYyPerception(this.queryParams).then(response => {
        this.yyPerceptionList = response.rows;
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
        faceSnap: null,
        carSnap: null,
        temperatureSnap: null,
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
      this.title = "添加感知信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyPerception(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改感知信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyPerception(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyPerception(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除感知信息编号为"' + ids + '"的数据项？').then(function() {
        return delYyPerception(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyPerception/export', {
        ...this.queryParams
      }, `yyPerception_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
