<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
<!--      <el-form-item label="事件ID" prop="eventId">-->
<!--        <el-input-->
<!--          v-model="queryParams.eventId"-->
<!--          placeholder="请输入事件ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="所属街道" prop="belongStreet">-->
<!--        <el-input-->
<!--          v-model="queryParams.belongStreet"-->
<!--          placeholder="请输入所属街道"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="街道名称" prop="streetName">
        <el-input
          v-model="queryParams.streetName"
          placeholder="请输入街道名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="检查时间" prop="checkTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.checkTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择检查时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="事件类型" prop="incidentType">
        <el-input
          v-model="queryParams.incidentType"
          placeholder="请输入事件类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="回复查看" prop="replyCheck">-->
<!--        <el-input-->
<!--          v-model="queryParams.replyCheck"-->
<!--          placeholder="请输入回复查看"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="事件大类型" prop="eventType">
        <el-select v-model="queryParams.eventType" placeholder="请选择事件大类型" clearable>
          <el-option
            v-for="dict in dict.type.yj_event_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="报警人" prop="alarmName">-->
<!--        <el-input-->
<!--          v-model="queryParams.alarmName"-->
<!--          placeholder="请输入报警人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="联系电话" prop="alarmPhone">-->
<!--        <el-input-->
<!--          v-model="queryParams.alarmPhone"-->
<!--          placeholder="请输入联系电话"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="主管单位" prop="competentUnit">-->
<!--        <el-input-->
<!--          v-model="queryParams.competentUnit"-->
<!--          placeholder="请输入主管单位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="警情地址" prop="address">-->
<!--        <el-input-->
<!--          v-model="queryParams.address"-->
<!--          placeholder="请输入警情地址"-->
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
          v-hasPermi="['jkza:yjSynergy:add']"
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
          v-hasPermi="['jkza:yjSynergy:edit']"
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
          v-hasPermi="['jkza:yjSynergy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yjSynergy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yjSynergyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件ID" align="center" prop="eventId" />
<!--      <el-table-column label="所属街道" align="center" prop="belongStreet" />-->
      <el-table-column label="街道名称" align="center" prop="streetName" />
      <el-table-column label="检查时间" align="center" prop="checkTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件描述" align="center" prop="incidentDescribe" width="300" />
      <el-table-column label="警情地址" align="center" prop="address" width="200" />
      <el-table-column label="事件类型" align="center" prop="incidentType" />
<!--      <el-table-column label="回复查看" align="center" prop="replyCheck" />-->
<!--      <el-table-column label="查看详情" align="center" prop="checkInfo" />-->
      <el-table-column label="事件大类型" align="center" prop="eventType" width="180">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yj_event_type" :value="scope.row.eventType"/>
        </template>
      </el-table-column>
      <el-table-column label="报警人" align="center" prop="alarmName" />
      <el-table-column label="联系电话" align="center" prop="alarmPhone" />
      <el-table-column label="处理结果" align="center" prop="treatmentResult" />
      <el-table-column label="主管单位" align="center" prop="competentUnit" />

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
            v-hasPermi="['jkza:yjSynergy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yjSynergy:remove']"
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

    <!-- 添加或修改多跨协同对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="事件ID" prop="eventId">
          <el-input v-model="form.eventId" placeholder="请输入事件ID" />
        </el-form-item>
<!--        <el-form-item label="所属街道" prop="belongStreet">-->
<!--          <el-input v-model="form.belongStreet" placeholder="请输入所属街道" />-->
<!--        </el-form-item>-->
        <el-form-item label="街道名称" prop="streetName">
          <el-input v-model="form.streetName" placeholder="请输入街道名称" />
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="事件描述" prop="incidentDescribe">
          <el-input v-model="form.incidentDescribe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="警情地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入警情地址" />
        </el-form-item>
        <el-form-item label="事件类型" prop="incidentType">
          <el-input v-model="form.incidentType" placeholder="请输入事件类型" />
        </el-form-item>
<!--        <el-form-item label="回复查看" prop="replyCheck">-->
<!--          <el-input v-model="form.replyCheck" placeholder="请输入回复查看" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="查看详情" prop="checkInfo">-->
<!--          <el-input v-model="form.checkInfo" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
        <el-form-item label="事件大类型" prop="eventType">
          <el-select v-model="form.eventType" placeholder="请选择事件大类型">
            <el-option
              v-for="dict in dict.type.yj_event_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报警人" prop="alarmName">
          <el-input v-model="form.alarmName" placeholder="请输入报警人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="alarmPhone">
          <el-input v-model="form.alarmPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="处理结果" prop="treatmentResult">
          <el-input v-model="form.treatmentResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="主管单位" prop="competentUnit">
          <el-input v-model="form.competentUnit" placeholder="请输入主管单位" />
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
import { listYjSynergy, getYjSynergy, delYjSynergy, addYjSynergy, updateYjSynergy } from "@/api/jkza/yjSynergy";

export default {
  name: "YjSynergy",
  dicts: ['data_type', 'yj_event_type'],
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
      // 多跨协同表格数据
      yjSynergyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventId: null,
        belongStreet: null,
        streetName: null,
        checkTime: null,
        incidentDescribe: null,
        incidentType: null,
        replyCheck: null,
        checkInfo: null,
        eventType: null,
        alarmName: null,
        alarmPhone: null,
        treatmentResult: null,
        competentUnit: null,
        address: null,
        dataType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        eventId: [
          { required: true, message: "事件ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询多跨协同列表 */
    getList() {
      this.loading = true;
      listYjSynergy(this.queryParams).then(response => {
        this.yjSynergyList = response.rows;
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
        eventId: null,
        belongStreet: null,
        streetName: null,
        checkTime: null,
        incidentDescribe: null,
        incidentType: null,
        replyCheck: null,
        checkInfo: null,
        eventType: null,
        alarmName: null,
        alarmPhone: null,
        treatmentResult: null,
        competentUnit: null,
        address: null,
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
      this.title = "添加多跨协同";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYjSynergy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改多跨协同";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYjSynergy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYjSynergy(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除多跨协同编号为"' + ids + '"的数据项？').then(function() {
        return delYjSynergy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yjSynergy/export', {
        ...this.queryParams
      }, `yjSynergy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
