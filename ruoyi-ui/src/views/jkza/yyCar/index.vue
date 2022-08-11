<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="设备编码" prop="deviceId">-->
<!--        <el-input-->
<!--          v-model="queryParams.deviceId"-->
<!--          placeholder="请输入设备编码"-->
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
<!--      <el-form-item label="抓拍记录的唯一 id" prop="recordId">-->
<!--        <el-input-->
<!--          v-model="queryParams.recordId"-->
<!--          placeholder="请输入抓拍记录的唯一 id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车牌号" prop="plateNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.plateNum"-->
<!--          placeholder="请输入车牌号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="抓拍时间" prop="capTime">-->
<!--        <el-input-->
<!--          v-model="queryParams.capTime"-->
<!--          placeholder="请输入抓拍时间"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车牌类型" prop="plateType">-->
<!--        <el-select v-model="queryParams.plateType" placeholder="请选择车牌类型" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.mq_car_type"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车辆抓拍图片" prop="carImgUrl">-->
<!--        <el-input-->
<!--          v-model="queryParams.carImgUrl"-->
<!--          placeholder="请输入车辆抓拍图片"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车牌照图片" prop="plateNumUrl">-->
<!--        <el-input-->
<!--          v-model="queryParams.plateNumUrl"-->
<!--          placeholder="请输入车牌照图片"-->
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
          v-hasPermi="['jkza:yyCar:add']"
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
          v-hasPermi="['jkza:yyCar:edit']"
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
          v-hasPermi="['jkza:yyCar:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yyCar:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yyCarList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备编码" align="center" prop="deviceId" />
      <el-table-column label="通道编码" align="center" prop="channelCode" />
      <el-table-column label="抓拍记录的唯一 id" align="center" prop="recordId" />
      <el-table-column label="车牌号" align="center" prop="plateNum" />
      <el-table-column label="抓拍时间" align="center" prop="capTime" >
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.capTime)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="车牌类型" align="center" prop="plateType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mq_car_type" :value="scope.row.plateType"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆抓拍图片" align="center" prop="carImgUrl" >
        <template slot-scope="scope">
          <img :src="scope.row.carImgUrl" width="100px" height="100px" />
        </template>
      </el-table-column>
      <el-table-column label="车牌照图片" align="center" prop="plateNumUrl" >
        <template slot-scope="scope">
          <img :src="scope.row.plateNumUrl" width="100px" height="100px" />
        </template>
      </el-table-column>
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
            v-hasPermi="['jkza:yyCar:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yyCar:remove']"
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

    <!-- 添加或修改车辆卡口对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备编码" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="通道编码" prop="channelCode">
          <el-input v-model="form.channelCode" placeholder="请输入通道编码" />
        </el-form-item>
        <el-form-item label="抓拍记录的唯一 id" prop="recordId">
          <el-input v-model="form.recordId" placeholder="请输入抓拍记录的唯一 id" />
        </el-form-item>
        <el-form-item label="车牌号" prop="plateNum">
          <el-input v-model="form.plateNum" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="抓拍时间" prop="capTime">
          <el-input v-model="form.capTime" placeholder="请输入抓拍时间" />
        </el-form-item>
        <el-form-item label="车牌类型" prop="plateType">
          <el-select v-model="form.plateType" placeholder="请选择车牌类型">
            <el-option
              v-for="dict in dict.type.mq_car_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆抓拍图片" prop="carImgUrl">
          <el-input v-model="form.carImgUrl" placeholder="请输入车辆抓拍图片" />
        </el-form-item>
        <el-form-item label="车牌照图片" prop="plateNumUrl">
          <el-input v-model="form.plateNumUrl" placeholder="请输入车牌照图片" />
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
import { listYyCar, getYyCar, delYyCar, addYyCar, updateYyCar } from "@/api/jkza/yyCar";

export default {
  name: "YyCar",
  dicts: ['mq_car_type', 'data_type'],
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
      // 车辆卡口表格数据
      yyCarList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        channelCode: null,
        recordId: null,
        plateNum: null,
        capTime: null,
        plateType: null,
        carImgUrl: null,
        plateNumUrl: null,
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
    /** 查询车辆卡口列表 */
    getList() {
      this.loading = true;
      listYyCar(this.queryParams).then(response => {
        this.yyCarList = response.rows;
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
        deviceId: null,
        channelCode: null,
        recordId: null,
        plateNum: null,
        capTime: null,
        plateType: null,
        carImgUrl: null,
        plateNumUrl: null,
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
      this.title = "添加车辆卡口";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYyCar(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆卡口";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYyCar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYyCar(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车辆卡口编号为"' + ids + '"的数据项？').then(function() {
        return delYyCar(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yyCar/export', {
        ...this.queryParams
      }, `yyCar_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
