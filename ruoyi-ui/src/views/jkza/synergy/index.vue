<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="检测日期" prop="checkTime">-->
      <!--  <el-date-picker clearable-->
      <!--    v-model="queryParams.checkTime"-->
      <!--    type="date"-->
      <!--    value-format="yyyy-MM-dd"-->
      <!--    placeholder="请选择检测日期">-->
      <!--  </el-date-picker>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="所属街道" prop="streetId">-->
      <!--  &lt;!&ndash;<el-input&ndash;&gt;-->
      <!--  &lt;!&ndash;  v-model="queryParams.streetId"&ndash;&gt;-->
      <!--  &lt;!&ndash;  placeholder="请选择所属街道"&ndash;&gt;-->
      <!--  &lt;!&ndash;  clearable&ndash;&gt;-->
      <!--  &lt;!&ndash;  @keyup.enter.native="handleQuery"&ndash;&gt;-->
      <!--  &lt;!&ndash;/>&ndash;&gt;-->
      <!--  <el-select v-model="queryParams.streetId" placeholder="请选择所属街道" @change="changeStreetId" clearable>-->
      <!--    <el-option-->
      <!--      v-for="item in streetNames"-->
      <!--      :key="item.value"-->
      <!--      :label="item.label"-->
      <!--      :value="item.value"-->
      <!--    >-->
      <!--    </el-option>-->
      <!--  </el-select>-->
      <!--</el-form-item>-->
      <el-form-item label="所属工地" prop="buildingId">
        <!--<el-input-->
        <!--  v-model="queryParams.buildingId"-->
        <!--  placeholder="请选择所属工地"-->
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
      <el-form-item label="通知书类型" prop="notificationType">
        <el-select v-model="queryParams.notificationType" placeholder="请选择通知书类型" clearable>
          <el-option
            v-for="dict in dict.type.notice_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="回复查看" prop="replyCheck">
        <el-select v-model="queryParams.replyCheck" placeholder="请选择回复查看" clearable>
          <el-option
            v-for="dict in dict.type.reply_check"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--<el-form-item label="回复信息" prop="replyInfo">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.replyInfo"-->
      <!--    placeholder="请输入回复信息"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
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
          v-hasPermi="['jkza:synergy:add']"
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
          v-hasPermi="['jkza:synergy:edit']"
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
          v-hasPermi="['jkza:synergy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:synergy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="synergyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="检测日期" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <!--<el-table-column label="所属街道" align="center" prop="streetId" >-->
      <!--  <template slot-scope="scope" v-if="scope.row.streetId">-->
      <!--    <StreetNames :streetNames="streetNames" :streetId="scope.row.streetId"></StreetNames>-->
      <!--  </template>-->
      <!--</el-table-column>-->
      <el-table-column label="所属工地" align="center" prop="buildingId" >
        <template slot-scope="scope" v-if="scope.row.buildingId">
          <BuildingNames :buildingNames="buildingNames" :buildingId="scope.row.buildingId"></BuildingNames>
        </template>
      </el-table-column>
      <el-table-column label="检查部位" align="center" prop="checkPosition" />
      <el-table-column label="通知书类型" align="center" prop="notificationType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.notice_type" :value="scope.row.notificationType"/>
        </template>
      </el-table-column>
      <el-table-column label="回复查看" align="center" prop="replyCheck">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.reply_check" :value="scope.row.replyCheck"/>
        </template>
      </el-table-column>
      <!--<el-table-column label="回复信息" align="center" prop="replyInfo" />-->

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
            v-hasPermi="['jkza:synergy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:synergy:remove']"
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
    <!--value-format="yyyy-MM-dd HH:mm:ss"-->
    <!-- 添加或修改整改通知对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="检测日期" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            default-time="12:00:00"
            placeholder="请选择检测日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检查部位" prop="checkPosition">
          <el-input v-model="form.checkPosition" placeholder="请输入检查部位" />
        </el-form-item>
        <!--<el-form-item label="所属街道" prop="streetId">-->
        <!--  &lt;!&ndash;<el-input v-model="form.streetId" placeholder="请选择所属街道" />&ndash;&gt;-->
        <!--  <el-select v-model="form.streetId" placeholder="请选择所属街道" @change="changeFormStreetId" clearable>-->
        <!--    <el-option-->
        <!--      v-for="item in streetNames"-->
        <!--      :key="item.value"-->
        <!--      :label="item.label"-->
        <!--      :value="item.value"-->
        <!--    >-->
        <!--    </el-option>-->
        <!--  </el-select>-->
        <!--</el-form-item>-->
        <el-form-item label="所属工地" prop="buildingId">
          <!--<el-input v-model="form.buildingId" placeholder="请选择所属工地" />-->
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
        <el-form-item label="通知书类型" prop="notificationType">
          <el-select v-model="form.notificationType" placeholder="请选择通知书类型">
            <el-option
              v-for="dict in dict.type.notice_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回复查看" prop="replyCheck">
          <el-select v-model="form.replyCheck" placeholder="请选择回复查看">
            <el-option
              v-for="dict in dict.type.reply_check"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <!--<el-form-item label="回复信息" prop="replyInfo">-->
        <!--  <el-input v-model="form.replyInfo" placeholder="请输入回复信息" />-->
        <!--</el-form-item>-->
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
import { listSynergy, getSynergy, delSynergy, addSynergy, updateSynergy,listBuildingNames } from "@/api/jkza/synergy";
import {
  listStreetNames
} from "@/api/jkza/building";
import StreetNames from "@/views/components/streetName";
import BuildingNames from "@/views/components/buildingName"

export default {
  name: "Synergy",
  dicts: ['notice_type', 'reply_check', 'data_type'],
  components:{StreetNames,BuildingNames},
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
      // 整改通知表格数据
      synergyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //所有的街道名称
      streetNames: [],
      //所有工地名称
      buildingNames: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        checkTime: null,
        streetId: null,
        buildingId: null,
        notificationType: null,
        replyCheck: null,
        replyInfo: null,
        checkPosition: null,
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
    /** 查询整改通知列表 */
    getList() {
      this.loading = true;
      listSynergy(this.queryParams).then(response => {
        this.synergyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listBuildingNames().then(response => {
        this.buildingNames = response;
        }
      );
      listStreetNames().then(res => {
        this.streetNames = res;
      })
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
        projectName: null,
        checkTime: null,
        streetId: null,
        buildingId: null,
        notificationType: null,
        replyCheck: null,
        replyInfo: null,
        checkPosition: null,
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
      this.title = "添加整改通知";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSynergy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改通知";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSynergy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSynergy(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除整改通知编号为"' + ids + '"的数据项？').then(function() {
        return delSynergy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/synergy/export', {
        ...this.queryParams
      }, `synergy_${new Date().getTime()}.xlsx`)
    },
    //改变queryParams所属街道ID
    changeStreetId(streetId){
      this.queryParams.streetId =streetId;
    },
    //改变form所属街道ID
    changeFormStreetId(streetId){
      this.form.streetId =streetId;
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
