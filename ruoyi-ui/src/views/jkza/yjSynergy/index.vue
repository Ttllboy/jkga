<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="所属街道" prop="belongStreet">
        <!--<el-input-->
        <!--  v-model="queryParams.belongStreet"-->
        <!--  placeholder="请输入所属街道"-->
        <!--  clearable-->
        <!--  @keyup.enter.native="handleQuery"-->
        <!--/>-->
        <el-select v-model="queryParams.belongStreet" placeholder="请选择所属街道" @change="changeStreetId" clearable>
          <el-option
            v-for="item in streetNames"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!--<el-form-item label="检查时间" prop="checkTime">-->
      <!--  <el-date-picker clearable-->
      <!--    v-model="queryParams.checkTime"-->
      <!--    type="date"-->
      <!--    value-format="yyyy-MM-dd"-->
      <!--    placeholder="请选择检查时间">-->
      <!--  </el-date-picker>-->
      <!--</el-form-item>-->
      <el-form-item label="事件描述" prop="incidentDescribe">
        <el-input
          v-model="queryParams.incidentDescribe"
          placeholder="请输入事件描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="事件类型" prop="incidentType">
        <el-select v-model="queryParams.incidentType" placeholder="请选择事件类型" clearable>
          <el-option
            v-for="dict in dict.type.incident_mold"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--<el-form-item label="查看详情" prop="checkInfo">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.checkInfo"-->
      <!--    placeholder="请输入查看详情"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="回复查看" prop="replyCheck">-->
      <!--  <el-select v-model="queryParams.replyCheck" placeholder="请选择回复查看" clearable>-->
      <!--    <el-option-->
      <!--      v-for="dict in dict.type.yj_check"-->
      <!--      :key="dict.value"-->
      <!--      :label="dict.label"-->
      <!--      :value="dict.value"-->
      <!--    />-->
      <!--  </el-select>-->
      <!--</el-form-item>-->
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
      <el-table-column label="所属街道" align="center" prop="belongStreet" >
        <template slot-scope="scope" v-if="scope.row.belongStreet">
          <StreetNames :streetNames="streetNames" :streetId="scope.row.belongStreet"></StreetNames>
        </template>
      </el-table-column>
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件描述" align="center" prop="incidentDescribe" />
      <el-table-column label="事件类型" align="center" prop="incidentType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.incident_mold" :value="scope.row.incidentType"/>
        </template>
      </el-table-column>
      <el-table-column label="查看详情" align="center" prop="checkInfo" />
      <el-table-column label="回复查看" align="center" prop="replyCheck">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yj_check" :value="scope.row.replyCheck"/>
        </template>
      </el-table-column>
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
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="所属街道" prop="belongStreet">
          <!--<el-input v-model="form.belongStreet" placeholder="请输入所属街道" />-->
          <el-select v-model="form.belongStreet" placeholder="请选择所属街道" @change="changeFormStreetId" clearable>
            <el-option
              v-for="item in streetNames"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
                          value-format="yyyy-MM-dd"
                          type="date"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="事件描述" prop="incidentDescribe">
          <el-input v-model="form.incidentDescribe" placeholder="请输入事件描述" />
        </el-form-item>
        <el-form-item label="事件类型" prop="incidentType">
          <el-select v-model="form.incidentType" placeholder="请选择事件类型">
            <el-option
              v-for="dict in dict.type.incident_mold"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="查看详情" prop="checkInfo">
          <el-input v-model="form.checkInfo" placeholder="请输入查看详情" />
        </el-form-item>
        <el-form-item label="回复查看" prop="replyCheck">
          <el-select v-model="form.replyCheck" placeholder="请选择回复查看">
            <el-option
              v-for="dict in dict.type.yj_check"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import {listStreetNames} from "@/api/jkza/building";
import StreetNames from "@/views/components/streetName";

export default {
  name: "YjSynergy",
  components:{StreetNames},
  dicts: ['incident_mold', 'yj_check', 'data_type'],
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
      //所有的街道名称
      streetNames: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        belongStreet: null,
        checkTime: null,
        incidentDescribe: null,
        incidentType: null,
        checkInfo: null,
        replyCheck: null,
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
    /** 查询多跨协同列表 */
    getList() {
      this.loading = true;
      listYjSynergy(this.queryParams).then(response => {
        this.yjSynergyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listStreetNames().then(response => {
        this.streetNames = response;
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
        belongStreet: null,
        checkTime: null,
        incidentDescribe: null,
        incidentType: null,
        checkInfo: null,
        replyCheck: null,
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
    },
    //改变queryParams所属街道ID
    changeStreetId(streetId){
      this.queryParams.belongStreet =streetId;
    },
    //改变form所属街道ID
    changeFormStreetId(streetId){
      this.form.belongStreet =streetId;
    },
  }
};
</script>
