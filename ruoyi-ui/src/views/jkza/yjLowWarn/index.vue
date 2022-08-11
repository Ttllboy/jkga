<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报警唯一ID" prop="alarmCode">
        <el-input
          v-model="queryParams.alarmCode"
          placeholder="请输入报警唯一ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报警图片" prop="alarmPicture">
        <el-input
          v-model="queryParams.alarmPicture"
          placeholder="请输入报警图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报警类型" prop="alarmType">
        <el-select v-model="queryParams.alarmType" placeholder="请选择报警类型" clearable>
          <el-option
            v-for="dict in dict.type.dwd_bj_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="报警时间" prop="alarmTime">
        <el-input
          v-model="queryParams.alarmTime"
          placeholder="请输入报警时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织编码" prop="orgCode">
        <el-input
          v-model="queryParams.orgCode"
          placeholder="请输入组织编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入组织名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通道编码" prop="channelCode">
        <el-input
          v-model="queryParams.channelCode"
          placeholder="请输入通道编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通道名称" prop="channelName">
        <el-input
          v-model="queryParams.channelName"
          placeholder="请输入通道名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预警状态" prop="alarmStat">
        <el-input
          v-model="queryParams.alarmStat"
          placeholder="请输入预警状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['jkza:yjLowWarn:add']"
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
          v-hasPermi="['jkza:yjLowWarn:edit']"
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
          v-hasPermi="['jkza:yjLowWarn:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yjLowWarn:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yjLowWarnList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="报警唯一ID" align="center" prop="alarmCode" />
      <el-table-column label="报警图片" align="center" prop="alarmPicture" />
      <el-table-column label="报警类型" align="center" prop="alarmType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dwd_bj_type" :value="scope.row.alarmType"/>
        </template>
      </el-table-column>
      <el-table-column label="报警时间" align="center" prop="alarmTime" />
      <el-table-column label="组织编码" align="center" prop="orgCode" />
      <el-table-column label="组织名称" align="center" prop="orgName" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="通道编码" align="center" prop="channelCode" />
      <el-table-column label="通道名称" align="center" prop="channelName" />
      <el-table-column label="预警状态" align="center" prop="alarmStat" />
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
            v-hasPermi="['jkza:yjLowWarn:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yjLowWarn:remove']"
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

    <!-- 添加或修改低洼点预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报警唯一ID" prop="alarmCode">
          <el-input v-model="form.alarmCode" placeholder="请输入报警唯一ID" />
        </el-form-item>
        <el-form-item label="报警图片" prop="alarmPicture">
          <el-input v-model="form.alarmPicture" placeholder="请输入报警图片" />
        </el-form-item>
        <el-form-item label="报警类型" prop="alarmType">
          <el-select v-model="form.alarmType" placeholder="请选择报警类型">
            <el-option
              v-for="dict in dict.type.dwd_bj_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报警时间" prop="alarmTime">
          <el-input v-model="form.alarmTime" placeholder="请输入报警时间" />
        </el-form-item>
        <el-form-item label="组织编码" prop="orgCode">
          <el-input v-model="form.orgCode" placeholder="请输入组织编码" />
        </el-form-item>
        <el-form-item label="组织名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入组织名称" />
        </el-form-item>
        <el-form-item label="设备编码" prop="deviceCode">
          <el-input v-model="form.deviceCode" placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="通道编码" prop="channelCode">
          <el-input v-model="form.channelCode" placeholder="请输入通道编码" />
        </el-form-item>
        <el-form-item label="通道名称" prop="channelName">
          <el-input v-model="form.channelName" placeholder="请输入通道名称" />
        </el-form-item>
        <el-form-item label="预警状态" prop="alarmStat">
          <el-input v-model="form.alarmStat" placeholder="请输入预警状态" />
        </el-form-item>
        <el-form-item label="数据类型" prop="dataType">
          <el-select v-model="form.dataType" placeholder="请选择数据类型">
            <el-option
              v-for="dict in dict.type.data_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
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
import { listYjLowWarn, getYjLowWarn, delYjLowWarn, addYjLowWarn, updateYjLowWarn } from "@/api/jkza/yjLowWarn";

export default {
  name: "YjLowWarn",
  dicts: ['dwd_bj_type', 'data_type'],
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
      // 低洼点预警表格数据
      yjLowWarnList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        alarmCode: null,
        alarmPicture: null,
        alarmType: null,
        alarmTime: null,
        orgCode: null,
        orgName: null,
        deviceCode: null,
        deviceName: null,
        channelCode: null,
        channelName: null,
        alarmStat: null,
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
    /** 查询低洼点预警列表 */
    getList() {
      this.loading = true;
      listYjLowWarn(this.queryParams).then(response => {
        this.yjLowWarnList = response.rows;
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
        alarmCode: null,
        alarmPicture: null,
        alarmType: null,
        alarmTime: null,
        orgCode: null,
        orgName: null,
        deviceCode: null,
        deviceName: null,
        channelCode: null,
        channelName: null,
        alarmStat: null,
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
      this.title = "添加低洼点预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYjLowWarn(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改低洼点预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYjLowWarn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYjLowWarn(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除低洼点预警编号为"' + ids + '"的数据项？').then(function() {
        return delYjLowWarn(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yjLowWarn/export', {
        ...this.queryParams
      }, `yjLowWarn_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
