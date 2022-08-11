<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="通道所属组织名称" prop="orgName">-->
<!--        <el-input-->
<!--          v-model="queryParams.orgName"-->
<!--          placeholder="请输入通道所属组织名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="进出门类型" prop="enterOrExit">-->
<!--        <el-input-->
<!--          v-model="queryParams.enterOrExit"-->
<!--          placeholder="请输入进出门类型"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="健康码" prop="healthMode">
        <el-input
          v-model="queryParams.healthMode"
          placeholder="请输入健康码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="卡号" prop="cardNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.cardNumber"-->
<!--          placeholder="请输入卡号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="体温" prop="curTemp">-->
<!--        <el-input-->
<!--          v-model="queryParams.curTemp"-->
<!--          placeholder="请输入体温"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->


<!--      <el-form-item label="抓图" prop="picture1">-->
<!--        <el-input-->
<!--          v-model="queryParams.picture1"-->
<!--          placeholder="请输入抓图"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="设备编码" prop="deviceCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.deviceCode"-->
<!--          placeholder="请输入设备编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人员编码" prop="personCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.personCode"-->
<!--          placeholder="请输入人员编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人员身份证号" prop="idNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.idNumber"-->
<!--          placeholder="请输入人员身份证号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="设备名称" prop="deviceName">-->
<!--        <el-input-->
<!--          v-model="queryParams.deviceName"-->
<!--          placeholder="请输入设备名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="人员姓名" prop="personName">-->
<!--        <el-input-->
<!--          v-model="queryParams.personName"-->
<!--          placeholder="请输入人员姓名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="记录的唯一 id" prop="recordId">-->
<!--        <el-input-->
<!--          v-model="queryParams.recordId"-->
<!--          placeholder="请输入记录的唯一 id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否超温" prop="isOverTemp">-->
<!--        <el-input-->
<!--          v-model="queryParams.isOverTemp"-->
<!--          placeholder="请输入是否超温"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="通道所属组织 SaaS 编码" prop="orgCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.orgCode"-->
<!--          placeholder="请输入通道所属组织 SaaS 编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="开门结果" prop="openResult">-->
<!--        <el-input-->
<!--          v-model="queryParams.openResult"-->
<!--          placeholder="请输入开门结果"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="通道名称" prop="channelName">-->
<!--        <el-input-->
<!--          v-model="queryParams.channelName"-->
<!--          placeholder="请输入通道名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="卡的介质类型" prop="category">-->
<!--        <el-input-->
<!--          v-model="queryParams.category"-->
<!--          placeholder="请输入卡的介质类型"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="刷卡时间" prop="capTime">-->
<!--        <el-input-->
<!--          v-model="queryParams.capTime"-->
<!--          placeholder="请输入刷卡时间"-->
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
      <el-form-item label="医院名称" prop="hosName">
        <el-input
          v-model="queryParams.hosName"
          placeholder="请输入医院名称"
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
          v-hasPermi="['jkza:cwmj:add']"
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
          v-hasPermi="['jkza:cwmj:edit']"
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
          v-hasPermi="['jkza:cwmj:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:cwmj:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cwmjList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="通道所属组织名称" align="center" prop="orgName" />-->
<!--      <el-table-column label="进出门类型" align="center" prop="enterOrExit" />-->
      <el-table-column label="健康码" align="center" prop="healthMode" />
<!--      <el-table-column label="卡号" align="center" prop="cardNumber" />-->
      <el-table-column label="体温" align="center" prop="curTemp" />
<!--      <el-table-column label="卡片类型" align="center" prop="cardType" />-->
      <el-table-column label="抓图" align="center" prop="picture1" />
<!--      <el-table-column label="设备编码" align="center" prop="deviceCode" />-->
<!--      <el-table-column label="人员编码" align="center" prop="personCode" />-->
      <el-table-column label="人员身份证号" align="center" prop="idNumber" />
<!--      <el-table-column label="设备名称" align="center" prop="deviceName" />-->
<!--      <el-table-column label="开门方式" align="center" prop="openType" />-->
      <el-table-column label="人员姓名" align="center" prop="personName" />
<!--      <el-table-column label="记录的唯一 id" align="center" prop="recordId" />-->
<!--      <el-table-column label="是否超温" align="center" prop="isOverTemp" />-->
<!--      <el-table-column label="通道所属组织 SaaS 编码" align="center" prop="orgCode" />-->
<!--      <el-table-column label="开门结果" align="center" prop="openResult" />-->
<!--      <el-table-column label="通道名称" align="center" prop="channelName" />-->
<!--      <el-table-column label="卡的介质类型" align="center" prop="category" />-->
      <el-table-column label="刷卡时间" align="center" prop="capTime" >
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.capTime)}}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="卡片状态" align="center" prop="cardStatus" />-->
<!--      <el-table-column label="通道编码" align="center" prop="channelCode" />-->
      <el-table-column label="医院名称" align="center" prop="hosName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:cwmj:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:cwmj:remove']"
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

    <!-- 添加或修改测温门禁对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="通道所属组织名称" prop="orgName">-->
<!--          <el-input v-model="form.orgName" placeholder="请输入通道所属组织名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="进出门类型" prop="enterOrExit">-->
<!--          <el-input v-model="form.enterOrExit" placeholder="请输入进出门类型" />-->
<!--        </el-form-item>-->
        <el-form-item label="健康码" prop="healthMode">
          <el-input v-model="form.healthMode" placeholder="请输入健康码" />
        </el-form-item>
<!--        <el-form-item label="卡号" prop="cardNumber">-->
<!--          <el-input v-model="form.cardNumber" placeholder="请输入卡号" />-->
<!--        </el-form-item>-->
        <el-form-item label="抓图" prop="picture1">
          <el-input v-model="form.picture1" placeholder="请输入抓图" />
        </el-form-item>
        <el-form-item label="体温" prop="curTemp">
          <el-input v-model="form.curTemp" placeholder="请输入体温" />
        </el-form-item>
<!--        <el-form-item label="设备编码" prop="deviceCode">-->
<!--          <el-input v-model="form.deviceCode" placeholder="请输入设备编码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="人员编码" prop="personCode">-->
<!--          <el-input v-model="form.personCode" placeholder="请输入人员编码" />-->
<!--        </el-form-item>-->
        <el-form-item label="人员身份证号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入人员身份证号" />
        </el-form-item>
<!--        <el-form-item label="设备名称" prop="deviceName">-->
<!--          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />-->
<!--        </el-form-item>-->
        <el-form-item label="人员姓名" prop="personName">
          <el-input v-model="form.personName" placeholder="请输入人员姓名" />
        </el-form-item>
<!--        <el-form-item label="记录的唯一 id" prop="recordId">-->
<!--          <el-input v-model="form.recordId" placeholder="请输入记录的唯一 id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否超温" prop="isOverTemp">-->
<!--          <el-input v-model="form.isOverTemp" placeholder="请输入是否超温" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="通道所属组织 SaaS 编码" prop="orgCode">-->
<!--          <el-input v-model="form.orgCode" placeholder="请输入通道所属组织 SaaS 编码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="开门结果" prop="openResult">-->
<!--          <el-input v-model="form.openResult" placeholder="请输入开门结果" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="通道名称" prop="channelName">-->
<!--          <el-input v-model="form.channelName" placeholder="请输入通道名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="卡的介质类型" prop="category">-->
<!--          <el-input v-model="form.category" placeholder="请输入卡的介质类型" />-->
<!--        </el-form-item>-->
        <el-form-item label="刷卡时间" prop="capTime">
          <el-input v-model="form.capTime" placeholder="请输入刷卡时间" />
        </el-form-item>
<!--        <el-form-item label="通道编码" prop="channelCode">-->
<!--          <el-input v-model="form.channelCode" placeholder="请输入通道编码" />-->
<!--        </el-form-item>-->
        <el-form-item label="医院名称" prop="hosName">
          <el-input v-model="form.hosName" placeholder="请输入医院名称" />
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
import { listCwmj, getCwmj, delCwmj, addCwmj, updateCwmj } from "@/api/jkza/cwmj";

export default {
  name: "Cwmj",
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
      // 测温门禁表格数据
      cwmjList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgName: null,
        enterOrExit: null,
        healthMode: null,
        cardNumber: null,
        cardType: null,
        curTemp: null,
        picture1: null,
        deviceCode: null,
        personCode: null,
        idNumber: null,
        deviceName: null,
        openType: null,
        personName: null,
        recordId: null,
        isOverTemp: null,
        orgCode: null,
        openResult: null,
        channelName: null,
        category: null,
        capTime: null,
        cardStatus: null,
        channelCode: null,
        hosName: null
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
    /** 查询测温门禁列表 */
    getList() {
      this.loading = true;
      listCwmj(this.queryParams).then(response => {
        this.cwmjList = response.rows;
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
        orgName: null,
        enterOrExit: null,
        healthMode: null,
        cardNumber: null,
        cardType: null,
        curTemp: null,
        picture1: null,
        deviceCode: null,
        personCode: null,
        idNumber: null,
        deviceName: null,
        openType: null,
        personName: null,
        recordId: null,
        isOverTemp: null,
        orgCode: null,
        openResult: null,
        channelName: null,
        category: null,
        capTime: null,
        cardStatus: null,
        channelCode: null,
        hosName: null
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
      this.title = "添加测温门禁";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCwmj(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改测温门禁";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCwmj(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCwmj(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除测温门禁编号为"' + ids + '"的数据项？').then(function() {
        return delCwmj(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/cwmj/export', {
        ...this.queryParams
      }, `cwmj_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
