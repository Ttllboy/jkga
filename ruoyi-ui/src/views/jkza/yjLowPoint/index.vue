<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="低洼点名称" prop="lowPointName" label-width="100">
        <el-input
          v-model="queryParams.lowPointName"
          placeholder="请输入低洼点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="当前水位" prop="nowWaterLevel">-->
<!--        <el-input-->
<!--          v-model="queryParams.nowWaterLevel"-->
<!--          placeholder="请输入当前水位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="水位预警线" prop="waterLevelWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.waterLevelWarn"-->
<!--          placeholder="请输入水位预警线"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="通道编码" prop="channelCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.channelCode"-->
<!--          placeholder="请输入通道编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="经度" prop="gpsX">-->
<!--        <el-input-->
<!--          v-model="queryParams.gpsX"-->
<!--          placeholder="请输入经度"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="纬度" prop="gpsY">-->
<!--        <el-input-->
<!--          v-model="queryParams.gpsY"-->
<!--          placeholder="请输入纬度"-->
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
          v-hasPermi="['jkza:yjLowPoint:add']"
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
          v-hasPermi="['jkza:yjLowPoint:edit']"
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
          v-hasPermi="['jkza:yjLowPoint:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yjLowPoint:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yjLowPointList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="低洼点名称" align="center" prop="lowPointName" />
      <el-table-column label="当前水位" align="center" prop="nowWaterLevel" />
      <el-table-column label="水位预警线" align="center" prop="waterLevelWarn" />
      <el-table-column label="通道编码" align="center" prop="channelCode" />
      <el-table-column label="经度" align="center" prop="gpsX" />
      <el-table-column label="纬度" align="center" prop="gpsY" />
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
            v-hasPermi="['jkza:yjLowPoint:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yjLowPoint:remove']"
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

    <!-- 添加或修改低洼点对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="低洼点名称" prop="lowPointName">
          <el-input v-model="form.lowPointName" placeholder="请输入低洼点名称" />
        </el-form-item>
        <el-form-item label="当前水位" prop="nowWaterLevel">
          <el-input v-model="form.nowWaterLevel" placeholder="请输入当前水位" />
        </el-form-item>
        <el-form-item label="水位预警线" prop="waterLevelWarn">
          <el-input v-model="form.waterLevelWarn" placeholder="请输入水位预警线" />
        </el-form-item>
        <el-form-item label="通道编码" prop="channelCode">
          <el-input v-model="form.channelCode" placeholder="请输入通道编码" />
        </el-form-item>
        <el-form-item label="经度" prop="gpsX">
          <el-input v-model="form.gpsX" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="gpsY">
          <el-input v-model="form.gpsY" placeholder="请输入纬度" />
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
import { listYjLowPoint, getYjLowPoint, delYjLowPoint, addYjLowPoint, updateYjLowPoint } from "@/api/jkza/yjLowPoint";

export default {
  name: "YjLowPoint",
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
      // 低洼点表格数据
      yjLowPointList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lowPointName: null,
        nowWaterLevel: null,
        waterLevelWarn: null,
        channelCode: null,
        gpsX: null,
        gpsY: null,
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
    /** 查询低洼点列表 */
    getList() {
      this.loading = true;
      listYjLowPoint(this.queryParams).then(response => {
        this.yjLowPointList = response.rows;
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
        lowPointName: null,
        nowWaterLevel: null,
        waterLevelWarn: null,
        channelCode: null,
        gpsX: null,
        gpsY: null,
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
      this.title = "添加低洼点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYjLowPoint(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改低洼点";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYjLowPoint(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYjLowPoint(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除低洼点编号为"' + ids + '"的数据项？').then(function() {
        return delYjLowPoint(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yjLowPoint/export', {
        ...this.queryParams
      }, `yjLowPoint_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
