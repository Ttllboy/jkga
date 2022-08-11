<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="医院名称" prop="hosName">
        <el-input
          v-model="queryParams.hosName"
          placeholder="请输入医院名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="医院床位数" prop="bed">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.bed"-->
      <!--    placeholder="请输入医院床位数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="医院停车数" prop="car">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.car"-->
      <!--    placeholder="请输入医院停车数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="安保人员数" prop="securityStaff">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.securityStaff"-->
      <!--    placeholder="请输入安保人员数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="在岗安保数" prop="securityStaffOnline">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.securityStaffOnline"-->
      <!--    placeholder="请输入在岗安保数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--      <el-form-item label="坐标" prop="coordinate">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.coordinate"-->
      <!--          placeholder="请输入坐标"-->
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
          v-hasPermi="['jkza:yyYy:add']"
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
          v-hasPermi="['jkza:yyYy:edit']"
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
          v-hasPermi="['jkza:yyYy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyYy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyYyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="医院名称" align="center" prop="hosName" />
      <el-table-column label="医院床位数" align="center" prop="bed" />
      <el-table-column label="医院停车数" align="center" prop="car" />
      <el-table-column label="安保人员数" align="center" prop="securityStaff" />
      <el-table-column label="在岗安保数" align="center" prop="securityStaffOnline" />
      <el-table-column label="安保车辆数" align="center" prop="securityCar" />
      <el-table-column label="在岗安保车辆数" align="center" prop="securityCarOnline" />
      <el-table-column label="消控室" align="center" prop="disappearRoom" />
      <el-table-column label="危险源" align="center" prop="dangerSource" />
      <el-table-column label="坐标" align="center" prop="coordinate" />
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
            v-hasPermi="['jkza:yyYy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyYy:remove']"
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

    <!-- 添加或修改医院信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="医院名称" prop="hosName">
          <el-input v-model="form.hosName" placeholder="请输入医院名称" />
        </el-form-item>
        <el-form-item label="医院床位数" prop="bed">
          <el-input v-model="form.bed" placeholder="请输入医院床位数" />
        </el-form-item>
        <el-form-item label="医院停车数" prop="car">
          <el-input v-model="form.car" placeholder="请输入医院停车数" />
        </el-form-item>
        <el-form-item label="安保人员数" prop="securityStaff">
          <el-input v-model="form.securityStaff" placeholder="请输入安保人员数" />
        </el-form-item>
        <el-form-item label="在岗安保数" prop="securityStaffOnline">
          <el-input v-model="form.securityStaffOnline" placeholder="请输入在岗安保数" />
        </el-form-item>
        <el-form-item label="安保车辆数" prop="securityCar">
          <el-input v-model="form.securityCar" placeholder="请输入安保车辆数" />
        </el-form-item>
        <el-form-item label="在岗安保车辆数" prop="securityCarOnline">
          <el-input v-model="form.securityCarOnline" placeholder="请输入在岗安保车辆数" />
        </el-form-item>
        <el-form-item label="消控室" prop="disappearRoom">
          <el-input v-model="form.disappearRoom" placeholder="请输入消控室" />
        </el-form-item>
        <el-form-item label="危险源" prop="dangerSource">
          <el-input v-model="form.dangerSource" placeholder="请输入危险源" />
        </el-form-item>
        <el-form-item label="坐标" prop="coordinate">
          <el-input v-model="form.coordinate" placeholder="请输入坐标" />
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
import { listYyYy, getYyYy, delYyYy, addYyYy, updateYyYy } from "@/api/jkza/yyYy";

export default {
  name: "YyYy",
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
      // 医院信息表格数据
      yyYyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hosName: null,
        bed: null,
        car: null,
        securityStaff: null,
        securityStaffOnline: null,

        securityCar: null,
        securityCarOnline: null,
        disappearRoom: null,
        dangerSource: null,
        coordinate: null,
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
    /** 查询医院信息列表 */
    getList() {
      this.loading = true;
      listYyYy(this.queryParams).then(response => {
        this.yyYyList = response.rows;
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
        hosName: null,
        bed: null,
        car: null,
        securityStaff: null,
        securityStaffOnline: null,

        securityCar: null,
        securityCarOnline: null,
        disappearRoom: null,
        dangerSource: null,
        coordinate: null,
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
      this.title = "添加医院信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyYy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医院信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyYy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyYy(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除医院信息编号为"' + ids + '"的数据项？').then(function() {
        return delYyYy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyYy/export', {
        ...this.queryParams
      }, `yyYy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
