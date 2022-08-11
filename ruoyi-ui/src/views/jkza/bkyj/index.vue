<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="布控库 ID" prop="repositoryCode">
        <el-input
          v-model="queryParams.repositoryCode"
          placeholder="请输入布控库 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抓拍人脸抠图" prop="imgUrl2">
        <el-input
          v-model="queryParams.imgUrl2"
          placeholder="请输入抓拍人脸抠图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时空数据类型" prop="recordType">
        <el-select v-model="queryParams.recordType" placeholder="请选择时空数据类型" clearable>
          <el-option
            v-for="dict in dict.type.bkyj_record"
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
      <el-form-item label="抓拍原图" prop="imgUrl1">
        <el-input
          v-model="queryParams.imgUrl1"
          placeholder="请输入抓拍原图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="布控库名称" prop="repositoryName">
        <el-input
          v-model="queryParams.repositoryName"
          placeholder="请输入布控库名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报警唯一 ID" prop="recordId">
        <el-input
          v-model="queryParams.recordId"
          placeholder="请输入报警唯一 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时空数据标识" prop="objId">
        <el-input
          v-model="queryParams.objId"
          placeholder="请输入时空数据标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抓拍点位名称" prop="channelName">
        <el-input
          v-model="queryParams.channelName"
          placeholder="请输入抓拍点位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="布控单位编码" prop="surveyOrg">
        <el-input
          v-model="queryParams.surveyOrg"
          placeholder="请输入布控单位编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时空数据类型" prop="objType">
        <el-select v-model="queryParams.objType" placeholder="请选择时空数据类型" clearable>
          <el-option
            v-for="dict in dict.type.bkyj_obj"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="抓拍时间" prop="capTime">
        <el-input
          v-model="queryParams.capTime"
          placeholder="请输入抓拍时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抓拍点位 ID" prop="channelId">
        <el-input
          v-model="queryParams.channelId"
          placeholder="请输入抓拍点位 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经度" prop="gpsX">
        <el-input
          v-model="queryParams.gpsX"
          placeholder="请输入经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度" prop="gpsY">
        <el-input
          v-model="queryParams.gpsY"
          placeholder="请输入纬度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件图" prop="imgUrl3">
        <el-input
          v-model="queryParams.imgUrl3"
          placeholder="请输入证件图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件号码" prop="cardNo">
        <el-input
          v-model="queryParams.cardNo"
          placeholder="请输入证件号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件类型" prop="cardType">
        <el-input
          v-model="queryParams.cardType"
          placeholder="请输入证件类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驾驶舱ID" prop="jscId">
        <el-input
          v-model="queryParams.jscId"
          placeholder="请输入驾驶舱ID"
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
          v-hasPermi="['jkza:bkyj:add']"
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
          v-hasPermi="['jkza:bkyj:edit']"
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
          v-hasPermi="['jkza:bkyj:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:bkyj:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bkyjList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="布控库 ID" align="center" prop="repositoryCode" />
      <el-table-column label="抓拍人脸抠图" align="center" prop="imgUrl2" />
      <el-table-column label="时空数据类型" align="center" prop="recordType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bkyj_record" :value="scope.row.recordType"/>
        </template>
      </el-table-column>
      <el-table-column label="报警时间" align="center" prop="alarmTime" />
      <el-table-column label="抓拍原图" align="center" prop="imgUrl1" />
      <el-table-column label="布控库名称" align="center" prop="repositoryName" />
      <el-table-column label="报警唯一 ID" align="center" prop="recordId" />
      <el-table-column label="时空数据标识" align="center" prop="objId" />
      <el-table-column label="抓拍点位名称" align="center" prop="channelName" />
      <el-table-column label="布控单位编码" align="center" prop="surveyOrg" />
      <el-table-column label="时空数据类型" align="center" prop="objType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bkyj_obj" :value="scope.row.objType"/>
        </template>
      </el-table-column>
      <el-table-column label="抓拍时间" align="center" prop="capTime" />
      <el-table-column label="抓拍点位 ID" align="center" prop="channelId" />
      <el-table-column label="经度" align="center" prop="gpsX" />
      <el-table-column label="纬度" align="center" prop="gpsY" />
      <el-table-column label="证件图" align="center" prop="imgUrl3" />
      <el-table-column label="证件号码" align="center" prop="cardNo" />
      <el-table-column label="证件类型" align="center" prop="cardType" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="驾驶舱ID" align="center" prop="jscId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:bkyj:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:bkyj:remove']"
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

    <!-- 添加或修改布控预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="布控库 ID" prop="repositoryCode">
          <el-input v-model="form.repositoryCode" placeholder="请输入布控库 ID" />
        </el-form-item>
        <el-form-item label="抓拍人脸抠图" prop="imgUrl2">
          <el-input v-model="form.imgUrl2" placeholder="请输入抓拍人脸抠图" />
        </el-form-item>
        <el-form-item label="时空数据类型" prop="recordType">
          <el-select v-model="form.recordType" placeholder="请选择时空数据类型">
            <el-option
              v-for="dict in dict.type.bkyj_record"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报警时间" prop="alarmTime">
          <el-input v-model="form.alarmTime" placeholder="请输入报警时间" />
        </el-form-item>
        <el-form-item label="抓拍原图" prop="imgUrl1">
          <el-input v-model="form.imgUrl1" placeholder="请输入抓拍原图" />
        </el-form-item>
        <el-form-item label="布控库名称" prop="repositoryName">
          <el-input v-model="form.repositoryName" placeholder="请输入布控库名称" />
        </el-form-item>
        <el-form-item label="报警唯一 ID" prop="recordId">
          <el-input v-model="form.recordId" placeholder="请输入报警唯一 ID" />
        </el-form-item>
        <el-form-item label="时空数据标识" prop="objId">
          <el-input v-model="form.objId" placeholder="请输入时空数据标识" />
        </el-form-item>
        <el-form-item label="抓拍点位名称" prop="channelName">
          <el-input v-model="form.channelName" placeholder="请输入抓拍点位名称" />
        </el-form-item>
        <el-form-item label="布控单位编码" prop="surveyOrg">
          <el-input v-model="form.surveyOrg" placeholder="请输入布控单位编码" />
        </el-form-item>
        <el-form-item label="时空数据类型" prop="objType">
          <el-select v-model="form.objType" placeholder="请选择时空数据类型">
            <el-option
              v-for="dict in dict.type.bkyj_obj"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="抓拍时间" prop="capTime">
          <el-input v-model="form.capTime" placeholder="请输入抓拍时间" />
        </el-form-item>
        <el-form-item label="抓拍点位 ID" prop="channelId">
          <el-input v-model="form.channelId" placeholder="请输入抓拍点位 ID" />
        </el-form-item>
        <el-form-item label="经度" prop="gpsX">
          <el-input v-model="form.gpsX" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="gpsY">
          <el-input v-model="form.gpsY" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="证件图" prop="imgUrl3">
          <el-input v-model="form.imgUrl3" placeholder="请输入证件图" />
        </el-form-item>
        <el-form-item label="证件号码" prop="cardNo">
          <el-input v-model="form.cardNo" placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="证件类型" prop="cardType">
          <el-input v-model="form.cardType" placeholder="请输入证件类型" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="驾驶舱ID" prop="jscId">
          <el-input v-model="form.jscId" placeholder="请输入驾驶舱ID" />
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
import { listBkyj, getBkyj, delBkyj, addBkyj, updateBkyj } from "@/api/jkza/bkyj";

export default {
  name: "Bkyj",
  dicts: ['bkyj_record', 'bkyj_obj'],
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
      // 布控预警表格数据
      bkyjList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        repositoryCode: null,
        imgUrl2: null,
        recordType: null,
        alarmTime: null,
        imgUrl1: null,
        repositoryName: null,
        recordId: null,
        objId: null,
        channelName: null,
        surveyOrg: null,
        objType: null,
        capTime: null,
        channelId: null,
        gpsX: null,
        gpsY: null,
        imgUrl3: null,
        cardNo: null,
        cardType: null,
        name: null,
        jscId: null
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
    /** 查询布控预警列表 */
    getList() {
      this.loading = true;
      listBkyj(this.queryParams).then(response => {
        this.bkyjList = response.rows;
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
        repositoryCode: null,
        imgUrl2: null,
        recordType: null,
        alarmTime: null,
        imgUrl1: null,
        repositoryName: null,
        recordId: null,
        objId: null,
        channelName: null,
        surveyOrg: null,
        objType: null,
        capTime: null,
        channelId: null,
        gpsX: null,
        gpsY: null,
        imgUrl3: null,
        cardNo: null,
        cardType: null,
        name: null,
        jscId: null
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
      this.title = "添加布控预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBkyj(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改布控预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBkyj(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBkyj(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除布控预警编号为"' + ids + '"的数据项？').then(function() {
        return delBkyj(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/bkyj/export', {
        ...this.queryParams
      }, `bkyj_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
