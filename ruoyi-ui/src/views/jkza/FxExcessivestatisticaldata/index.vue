<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目信息编号" prop="projectguid">
        <el-input
          v-model="queryParams.projectguid"
          placeholder="请输入项目信息编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="devicesn">
        <el-input
          v-model="queryParams.devicesn"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="createdatet">
        <el-date-picker clearable
          v-model="queryParams.createdatet"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择统计时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="PM2.5" prop="pm25">
        <el-input
          v-model="queryParams.pm25"
          placeholder="请输入PM2.5"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="PM10" prop="pm10">
        <el-input
          v-model="queryParams.pm10"
          placeholder="请输入PM10"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="温度" prop="pd04">
        <el-input
          v-model="queryParams.pd04"
          placeholder="请输入温度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="湿度" prop="pd05">
        <el-input
          v-model="queryParams.pd05"
          placeholder="请输入湿度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="噪音" prop="pd09">
        <el-input
          v-model="queryParams.pd09"
          placeholder="请输入噪音"
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
          v-hasPermi="['jkza:FxExcessivestatisticaldata:add']"
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
          v-hasPermi="['jkza:FxExcessivestatisticaldata:edit']"
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
          v-hasPermi="['jkza:FxExcessivestatisticaldata:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:FxExcessivestatisticaldata:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="FxExcessivestatisticaldataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目信息编号" align="center" prop="projectguid" />
      <el-table-column label="设备编号" align="center" prop="devicesn" />
      <el-table-column label="统计时间" align="center" prop="createdatet" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdatet, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="PM2.5" align="center" prop="pm25" />
      <el-table-column label="PM10" align="center" prop="pm10" />
      <el-table-column label="温度" align="center" prop="pd04" />
      <el-table-column label="湿度" align="center" prop="pd05" />
      <el-table-column label="噪音" align="center" prop="pd09" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:FxExcessivestatisticaldata:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:FxExcessivestatisticaldata:remove']"
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

    <!-- 添加或修改扬尘数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目信息编号" prop="projectguid">
          <el-input v-model="form.projectguid" placeholder="请输入项目信息编号" />
        </el-form-item>
        <el-form-item label="设备编号" prop="devicesn">
          <el-input v-model="form.devicesn" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="统计时间" prop="createdatet">
          <el-date-picker clearable
            v-model="form.createdatet"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择统计时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="PM2.5" prop="pm25">
          <el-input v-model="form.pm25" placeholder="请输入PM2.5" />
        </el-form-item>
        <el-form-item label="PM10" prop="pm10">
          <el-input v-model="form.pm10" placeholder="请输入PM10" />
        </el-form-item>
        <el-form-item label="温度" prop="pd04">
          <el-input v-model="form.pd04" placeholder="请输入温度" />
        </el-form-item>
        <el-form-item label="湿度" prop="pd05">
          <el-input v-model="form.pd05" placeholder="请输入湿度" />
        </el-form-item>
        <el-form-item label="噪音" prop="pd09">
          <el-input v-model="form.pd09" placeholder="请输入噪音" />
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
import { listFxExcessivestatisticaldata, getFxExcessivestatisticaldata, delFxExcessivestatisticaldata, addFxExcessivestatisticaldata, updateFxExcessivestatisticaldata } from "@/api/jkza/FxExcessivestatisticaldata";

export default {
  name: "FxExcessivestatisticaldata",
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
      // 扬尘数据表格数据
      FxExcessivestatisticaldataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectguid: null,
        devicesn: null,
        createdatet: null,
        pm25: null,
        pm10: null,
        pd04: null,
        pd05: null,
        pd09: null,
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
    /** 查询扬尘数据列表 */
    getList() {
      this.loading = true;
      listFxExcessivestatisticaldata(this.queryParams).then(response => {
        this.FxExcessivestatisticaldataList = response.rows;
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
        projectguid: null,
        devicesn: null,
        createdatet: null,
        pm25: null,
        pm10: null,
        pd04: null,
        pd05: null,
        pd09: null,
        createTime: null
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
      this.ids = selection.map(item => item.projectguid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加扬尘数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectguid = row.projectguid || this.ids
      getFxExcessivestatisticaldata(projectguid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改扬尘数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectguid != null) {
            updateFxExcessivestatisticaldata(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFxExcessivestatisticaldata(this.form).then(response => {
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
      const projectguids = row.projectguid || this.ids;
      this.$modal.confirm('是否确认删除扬尘数据编号为"' + projectguids + '"的数据项？').then(function() {
        return delFxExcessivestatisticaldata(projectguids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/FxExcessivestatisticaldata/export', {
        ...this.queryParams
      }, `FxExcessivestatisticaldata_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
