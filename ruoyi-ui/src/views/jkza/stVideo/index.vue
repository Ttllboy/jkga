<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="固废点ID" prop="solidId">
        <el-input
          v-model="queryParams.solidId"
          placeholder="请输入固废点ID"
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
      <el-form-item label="视频设备名称" prop="channelName">
        <el-input
          v-model="queryParams.channelName"
          placeholder="请输入视频设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摄像机类型" prop="cameraType">
        <el-select v-model="queryParams.cameraType" placeholder="请选择摄像机类型" clearable>
          <el-option
            v-for="dict in dict.type.equipment_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="在线状态" prop="networkStatus">
        <el-select v-model="queryParams.networkStatus" placeholder="请选择在线状态" clearable>
          <el-option
            v-for="dict in dict.type.online_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="通道编码" prop="channelCode">
        <el-input
          v-model="queryParams.channelCode"
          placeholder="请输入通道编码"
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
          v-hasPermi="['jkza:stVideo:add']"
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
          v-hasPermi="['jkza:stVideo:edit']"
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
          v-hasPermi="['jkza:stVideo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:stVideo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stVideoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="固废点ID" align="center" prop="solidId" />
      <el-table-column label="驾驶舱ID" align="center" prop="jscId" />
      <el-table-column label="视频设备名称" align="center" prop="channelName" />
      <el-table-column label="摄像机类型" align="center" prop="cameraType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.equipment_type" :value="scope.row.cameraType"/>
        </template>
      </el-table-column>
      <el-table-column label="在线状态" align="center" prop="networkStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.online_type" :value="scope.row.networkStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="通道编码" align="center" prop="channelCode" />
      <el-table-column label="经度" align="center" prop="gpsX" />
      <el-table-column label="纬度" align="center" prop="gpsY" />
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
            v-hasPermi="['jkza:stVideo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:stVideo:remove']"
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

    <!-- 添加或修改视频设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="固废点ID" prop="solidId">
          <el-input v-model="form.solidId" placeholder="请输入固废点ID" />
        </el-form-item>
        <el-form-item label="驾驶舱ID" prop="jscId">
          <el-input v-model="form.jscId" placeholder="请输入驾驶舱ID" />
        </el-form-item>
        <el-form-item label="视频设备名称" prop="channelName">
          <el-input v-model="form.channelName" placeholder="请输入视频设备名称" />
        </el-form-item>
        <el-form-item label="摄像机类型" prop="cameraType">
          <el-select v-model="form.cameraType" placeholder="请选择摄像机类型">
            <el-option
              v-for="dict in dict.type.equipment_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="在线状态" prop="networkStatus">
          <el-select v-model="form.networkStatus" placeholder="请选择在线状态">
            <el-option
              v-for="dict in dict.type.online_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listStVideo, getStVideo, delStVideo, addStVideo, updateStVideo } from "@/api/jkza/stVideo";

export default {
  name: "StVideo",
  dicts: ['online_type', 'equipment_type', 'data_type'],
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
      // 视频设备表格数据
      stVideoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        solidId: null,
        jscId: null,
        channelName: null,
        cameraType: null,
        networkStatus: null,
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
    /** 查询视频设备列表 */
    getList() {
      this.loading = true;
      listStVideo(this.queryParams).then(response => {
        this.stVideoList = response.rows;
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
        solidId: null,
        jscId: null,
        channelName: null,
        cameraType: null,
        networkStatus: null,
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
      this.title = "添加视频设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStVideo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改视频设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStVideo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStVideo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除视频设备编号为"' + ids + '"的数据项？').then(function() {
        return delStVideo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/stVideo/export', {
        ...this.queryParams
      }, `stVideo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
