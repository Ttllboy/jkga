<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目id" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据时间" prop="warnTime">
        <el-date-picker clearable
          v-model="queryParams.warnTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择数据时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备案号" prop="recordNo">
        <el-input
          v-model="queryParams.recordNo"
          placeholder="请输入备案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="力矩" prop="moment">-->
<!--        <el-input-->
<!--          v-model="queryParams.moment"-->
<!--          placeholder="请输入力矩"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="载重" prop="loadWeight">-->
<!--        <el-input-->
<!--          v-model="queryParams.loadWeight"-->
<!--          placeholder="请输入载重"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="幅度" prop="margin">-->
<!--        <el-input-->
<!--          v-model="queryParams.margin"-->
<!--          placeholder="请输入幅度"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="高度上" prop="heightUp">-->
<!--        <el-input-->
<!--          v-model="queryParams.heightUp"-->
<!--          placeholder="请输入高度上"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="高度下" prop="heightLower">-->
<!--        <el-input-->
<!--          v-model="queryParams.heightLower"-->
<!--          placeholder="请输入高度下"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="回转" prop="rotation">-->
<!--        <el-input-->
<!--          v-model="queryParams.rotation"-->
<!--          placeholder="请输入回转"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="风速" prop="windSpeed">-->
<!--        <el-input-->
<!--          v-model="queryParams.windSpeed"-->
<!--          placeholder="请输入风速"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="载重预警" prop="weightWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.weightWarn"-->
<!--          placeholder="请输入载重预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="力矩预警" prop="momentWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.momentWarn"-->
<!--          placeholder="请输入力矩预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="右回转预警" prop="rightRotationWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.rightRotationWarn"-->
<!--          placeholder="请输入右回转预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="左回转预警" prop="leftRotationWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.leftRotationWarn"-->
<!--          placeholder="请输入左回转预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="高度下预警" prop="heightLowerWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.heightLowerWarn"-->
<!--          placeholder="请输入高度下预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="高度上预警" prop="heightUpWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.heightUpWarn"-->
<!--          placeholder="请输入高度上预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Y轴倾角预警" prop="inclinationYWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.inclinationYWarn"-->
<!--          placeholder="请输入Y轴倾角预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="X轴倾角预警" prop="inclinationXWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.inclinationXWarn"-->
<!--          placeholder="请输入X轴倾角预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="风速预警" prop="windSpeedWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.windSpeedWarn"-->
<!--          placeholder="请输入风速预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="小幅预警" prop="smallMarginWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.smallMarginWarn"-->
<!--          placeholder="请输入小幅预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="大幅预警" prop="bigMarginWarn">-->
<!--        <el-input-->
<!--          v-model="queryParams.bigMarginWarn"-->
<!--          placeholder="请输入大幅预警"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="传感器类型1异常0正常" prop="sensorType" label-width="170px">
        <el-input
          v-model="queryParams.sensorType"
          placeholder="请输入传感器类型1异常0正常"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工地名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入工地名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['jkza:gdTower:add']"
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
          v-hasPermi="['jkza:gdTower:edit']"
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
          v-hasPermi="['jkza:gdTower:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:gdTower:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gdTowerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="项目id" align="center" prop="projectId" />
      <el-table-column label="数据时间" align="center" prop="warnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.warnTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备案号" align="center" prop="recordNo" />
      <el-table-column label="力矩" align="center" prop="moment" />
      <el-table-column label="载重" align="center" prop="loadWeight" />
      <el-table-column label="幅度" align="center" prop="margin" />
      <el-table-column label="高度上" align="center" prop="heightUp" />
      <el-table-column label="高度下" align="center" prop="heightLower" />
      <el-table-column label="回转" align="center" prop="rotation" />
      <el-table-column label="风速" align="center" prop="windSpeed" />
      <el-table-column label="载重预警" align="center" prop="weightWarn" />
      <el-table-column label="力矩预警" align="center" prop="momentWarn" />
      <el-table-column label="右回转预警" align="center" prop="rightRotationWarn" />
      <el-table-column label="左回转预警" align="center" prop="leftRotationWarn" />
      <el-table-column label="高度下预警" align="center" prop="heightLowerWarn" />
      <el-table-column label="高度上预警" align="center" prop="heightUpWarn" />
      <el-table-column label="Y轴倾角预警" align="center" prop="inclinationYWarn" />
      <el-table-column label="X轴倾角预警" align="center" prop="inclinationXWarn" />
      <el-table-column label="风速预警" align="center" prop="windSpeedWarn" />
      <el-table-column label="小幅预警" align="center" prop="smallMarginWarn" />
      <el-table-column label="大幅预警" align="center" prop="bigMarginWarn" />
<!--      <el-table-column label="传感器状态" align="center" prop="sensorStatus" />-->
      <el-table-column label="传感器类型1异常0正常" align="center" prop="sensorType" />
      <el-table-column label="工地名称" align="center" prop="projectName" />
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
            v-hasPermi="['jkza:gdTower:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:gdTower:remove']"
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

    <!-- 添加或修改塔吊预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="项目id" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="数据时间" prop="warnTime">
          <el-date-picker clearable
            v-model="form.warnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择数据时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备案号" prop="recordNo">
          <el-input v-model="form.recordNo" placeholder="请输入备案号" />
        </el-form-item>
        <el-form-item label="力矩" prop="moment">
          <el-input v-model="form.moment" placeholder="请输入力矩" />
        </el-form-item>
        <el-form-item label="载重" prop="loadWeight">
          <el-input v-model="form.loadWeight" placeholder="请输入载重" />
        </el-form-item>
        <el-form-item label="幅度" prop="margin">
          <el-input v-model="form.margin" placeholder="请输入幅度" />
        </el-form-item>
        <el-form-item label="高度上" prop="heightUp">
          <el-input v-model="form.heightUp" placeholder="请输入高度上" />
        </el-form-item>
        <el-form-item label="高度下" prop="heightLower">
          <el-input v-model="form.heightLower" placeholder="请输入高度下" />
        </el-form-item>
        <el-form-item label="回转" prop="rotation">
          <el-input v-model="form.rotation" placeholder="请输入回转" />
        </el-form-item>
        <el-form-item label="风速" prop="windSpeed">
          <el-input v-model="form.windSpeed" placeholder="请输入风速" />
        </el-form-item>
        <el-form-item label="载重预警" prop="weightWarn">
          <el-input v-model="form.weightWarn" placeholder="请输入载重预警" />
        </el-form-item>
        <el-form-item label="力矩预警" prop="momentWarn">
          <el-input v-model="form.momentWarn" placeholder="请输入力矩预警" />
        </el-form-item>
        <el-form-item label="右回转预警" prop="rightRotationWarn">
          <el-input v-model="form.rightRotationWarn" placeholder="请输入右回转预警" />
        </el-form-item>
        <el-form-item label="左回转预警" prop="leftRotationWarn">
          <el-input v-model="form.leftRotationWarn" placeholder="请输入左回转预警" />
        </el-form-item>
        <el-form-item label="高度下预警" prop="heightLowerWarn">
          <el-input v-model="form.heightLowerWarn" placeholder="请输入高度下预警" />
        </el-form-item>
        <el-form-item label="高度上预警" prop="heightUpWarn">
          <el-input v-model="form.heightUpWarn" placeholder="请输入高度上预警" />
        </el-form-item>
        <el-form-item label="Y轴倾角预警" prop="inclinationYWarn">
          <el-input v-model="form.inclinationYWarn" placeholder="请输入Y轴倾角预警" />
        </el-form-item>
        <el-form-item label="X轴倾角预警" prop="inclinationXWarn">
          <el-input v-model="form.inclinationXWarn" placeholder="请输入X轴倾角预警" />
        </el-form-item>
        <el-form-item label="风速预警" prop="windSpeedWarn">
          <el-input v-model="form.windSpeedWarn" placeholder="请输入风速预警" />
        </el-form-item>
        <el-form-item label="小幅预警" prop="smallMarginWarn">
          <el-input v-model="form.smallMarginWarn" placeholder="请输入小幅预警" />
        </el-form-item>
        <el-form-item label="大幅预警" prop="bigMarginWarn">
          <el-input v-model="form.bigMarginWarn" placeholder="请输入大幅预警" />
        </el-form-item>
        <el-form-item label="传感器类型1异常0正常" prop="sensorType">
          <el-input v-model="form.sensorType" placeholder="请输入传感器类型1异常0正常" />
        </el-form-item>
        <el-form-item label="工地名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入工地名称" />
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
import { listGdTower, getGdTower, delGdTower, addGdTower, updateGdTower } from "@/api/jkza/gdTower";

export default {
  name: "GdTower",
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
      // 塔吊预警表格数据
      gdTowerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        warnTime: null,
        recordNo: null,
        moment: null,
        loadWeight: null,
        margin: null,
        heightUp: null,
        heightLower: null,
        rotation: null,
        windSpeed: null,
        weightWarn: null,
        momentWarn: null,
        rightRotationWarn: null,
        leftRotationWarn: null,
        heightLowerWarn: null,
        heightUpWarn: null,
        inclinationYWarn: null,
        inclinationXWarn: null,
        windSpeedWarn: null,
        smallMarginWarn: null,
        bigMarginWarn: null,
        sensorStatus: null,
        sensorType: null,
        projectName: null,
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
    /** 查询塔吊预警列表 */
    getList() {
      this.loading = true;
      listGdTower(this.queryParams).then(response => {
        this.gdTowerList = response.rows;
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
        projectId: null,
        warnTime: null,
        recordNo: null,
        moment: null,
        loadWeight: null,
        margin: null,
        heightUp: null,
        heightLower: null,
        rotation: null,
        windSpeed: null,
        weightWarn: null,
        momentWarn: null,
        rightRotationWarn: null,
        leftRotationWarn: null,
        heightLowerWarn: null,
        heightUpWarn: null,
        inclinationYWarn: null,
        inclinationXWarn: null,
        windSpeedWarn: null,
        smallMarginWarn: null,
        bigMarginWarn: null,
        sensorStatus: "0",
        sensorType: null,
        projectName: null,
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
      this.title = "添加塔吊预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGdTower(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改塔吊预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGdTower(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGdTower(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除塔吊预警编号为"' + ids + '"的数据项？').then(function() {
        return delGdTower(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/gdTower/export', {
        ...this.queryParams
      }, `gdTower_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
