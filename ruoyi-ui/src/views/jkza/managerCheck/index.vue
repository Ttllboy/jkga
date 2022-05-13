<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <!--<el-form-item label="项目经理总人数" prop="projectManagerSum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.projectManagerSum"-->
      <!--    placeholder="请输入项目经理总人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="项目经理考勤人数" prop="projectManagerCheck">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.projectManagerCheck"-->
      <!--    placeholder="请输入项目经理考勤人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="安全员总人数" prop="safetyWorkerSum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.safetyWorkerSum"-->
      <!--    placeholder="请输入安全员总人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="安全员考勤人数" prop="safetyWorkerCheck">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.safetyWorkerCheck"-->
      <!--    placeholder="请输入安全员考勤人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="注册监理总人数" prop="registeredSupervisionSum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.registeredSupervisionSum"-->
      <!--    placeholder="请输入注册监理总人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="注册监理考勤人数" prop="registeredSupervisionCheck">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.registeredSupervisionCheck"-->
      <!--    placeholder="请输入注册监理考勤人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="专业监理总人数" prop="professionalSupervisionSum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.professionalSupervisionSum"-->
      <!--    placeholder="请输入专业监理总人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="专业监理考勤人数" prop="professionalSupervisionCheck">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.professionalSupervisionCheck"-->
      <!--    placeholder="请输入专业监理考勤人数"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item label="所属工地" prop="buildingId">
        <!--<el-input-->
        <!--  v-model="queryParams.buildingId"-->
        <!--  placeholder="请输入所属工地"-->
        <!--  clearable-->
        <!--  @keyup.enter.native="handleQuery"-->
        <!--/>-->
        <el-select v-model="queryParams.buildingId" placeholder="请选择所属工地" @change="changeBuildingId" clearable>
          <el-option
            v-for="item in buildingNames"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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
          v-hasPermi="['jkza:managerCheck:add']"
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
          v-hasPermi="['jkza:managerCheck:edit']"
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
          v-hasPermi="['jkza:managerCheck:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:managerCheck:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="managerCheckList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目经理总人数" align="center" prop="projectManagerSum" />
      <el-table-column label="项目经理考勤人数" align="center" prop="projectManagerCheck" />
      <el-table-column label="安全员总人数" align="center" prop="safetyWorkerSum" />
      <el-table-column label="安全员考勤人数" align="center" prop="safetyWorkerCheck" />
      <el-table-column label="注册监理总人数" align="center" prop="registeredSupervisionSum" />
      <el-table-column label="注册监理考勤人数" align="center" prop="registeredSupervisionCheck" />
      <el-table-column label="专业监理总人数" align="center" prop="professionalSupervisionSum" />
      <el-table-column label="专业监理考勤人数" align="center" prop="professionalSupervisionCheck" />
      <el-table-column label="所属工地" align="center" prop="buildingId" >
        <template slot-scope="scope" v-if="scope.row.buildingId">
          <BuildingNames :buildingNames="buildingNames" :buildingId="scope.row.buildingId"></BuildingNames>
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
            v-hasPermi="['jkza:managerCheck:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:managerCheck:remove']"
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

    <!-- 添加或修改管理人员考勤对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="135px">
        <el-form-item label="项目经理总人数" prop="projectManagerSum">
          <el-input v-model="form.projectManagerSum" placeholder="请输入项目经理总人数" />
        </el-form-item>
        <el-form-item label="项目经理考勤人数" prop="projectManagerCheck">
          <el-input v-model="form.projectManagerCheck" placeholder="请输入项目经理考勤人数" />
        </el-form-item>
        <el-form-item label="安全员总人数" prop="safetyWorkerSum">
          <el-input v-model="form.safetyWorkerSum" placeholder="请输入安全员总人数" />
        </el-form-item>
        <el-form-item label="安全员考勤人数" prop="safetyWorkerCheck">
          <el-input v-model="form.safetyWorkerCheck" placeholder="请输入安全员考勤人数" />
        </el-form-item>
        <el-form-item label="注册监理总人数" prop="registeredSupervisionSum">
          <el-input v-model="form.registeredSupervisionSum" placeholder="请输入注册监理总人数" />
        </el-form-item>
        <el-form-item label="注册监理考勤人数" prop="registeredSupervisionCheck">
          <el-input v-model="form.registeredSupervisionCheck" placeholder="请输入注册监理考勤人数" />
        </el-form-item>
        <el-form-item label="专业监理总人数" prop="professionalSupervisionSum">
          <el-input v-model="form.professionalSupervisionSum" placeholder="请输入专业监理总人数" />
        </el-form-item>
        <el-form-item label="专业监理考勤人数" prop="professionalSupervisionCheck">
          <el-input v-model="form.professionalSupervisionCheck" placeholder="请输入专业监理考勤人数" />
        </el-form-item>
        <el-form-item label="所属工地" prop="buildingId">
          <!--<el-input v-model="form.buildingId" placeholder="请输入所属工地" />-->
          <el-select v-model="form.buildingId" placeholder="请选择所属工地" @change="changeFormBuildingId" clearable>
            <el-option
              v-for="item in buildingNames"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
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
import { listManagerCheck, getManagerCheck, delManagerCheck, addManagerCheck, updateManagerCheck } from "@/api/jkza/managerCheck";
import {listBuildingNames} from "@/api/jkza/synergy";
import BuildingNames from "@/views/components/buildingName";

export default {
  name: "ManagerCheck",
  dicts: ['data_type'],
  components:{BuildingNames},
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
      // 管理人员考勤表格数据
      managerCheckList: [],
      //所有工地名称
      buildingNames: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectManagerSum: null,
        projectManagerCheck: null,
        safetyWorkerSum: null,
        safetyWorkerCheck: null,
        registeredSupervisionSum: null,
        registeredSupervisionCheck: null,
        professionalSupervisionSum: null,
        professionalSupervisionCheck: null,
        buildingId: null,
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
    /** 查询管理人员考勤列表 */
    getList() {
      this.loading = true;
      listManagerCheck(this.queryParams).then(response => {
        this.managerCheckList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listBuildingNames().then(response => {
          this.buildingNames = response;
        }
      );
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
        projectManagerSum: null,
        projectManagerCheck: null,
        safetyWorkerSum: null,
        safetyWorkerCheck: null,
        registeredSupervisionSum: null,
        registeredSupervisionCheck: null,
        professionalSupervisionSum: null,
        professionalSupervisionCheck: null,
        buildingId: null,
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
      this.title = "添加管理人员考勤";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getManagerCheck(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改管理人员考勤";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateManagerCheck(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManagerCheck(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除管理人员考勤编号为"' + ids + '"的数据项？').then(function() {
        return delManagerCheck(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/managerCheck/export', {
        ...this.queryParams
      }, `managerCheck_${new Date().getTime()}.xlsx`)
    },
    //改变queryParams所属工地ID
    changeBuildingId(buildingId){
      this.queryParams.buildingId =buildingId;
    },
    //改变form所属工地ID
    changeFormBuildingId(buildingId){
      this.form.buildingId =buildingId;
    },
  }
};
</script>
