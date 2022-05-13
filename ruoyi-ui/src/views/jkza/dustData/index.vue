<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
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
      <!--<el-form-item label="设备编号" prop="equipmentNum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.equipmentNum"-->
      <!--    placeholder="请输入设备编号"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="统计时间" prop="date">-->
      <!--  <el-date-picker clearable-->
      <!--    v-model="queryParams.date"-->
      <!--    type="date"-->
      <!--    value-format="yyyy-MM-dd"-->
      <!--    placeholder="请选择统计时间">-->
      <!--  </el-date-picker>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="PM2.5值" prop="pm25">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.pm25"-->
      <!--    placeholder="请输入PM2.5值"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="PM10值" prop="pm10">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.pm10"-->
      <!--    placeholder="请输入PM10值"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="温度" prop="temperature">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.temperature"-->
      <!--    placeholder="请输入温度"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="湿度" prop="humidity">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.humidity"-->
      <!--    placeholder="请输入湿度"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="噪音" prop="noise">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.noise"-->
      <!--    placeholder="请输入噪音"-->
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
          v-hasPermi="['jkza:dustData:add']"
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
          v-hasPermi="['jkza:dustData:edit']"
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
          v-hasPermi="['jkza:dustData:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:dustData:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dustDataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属工地" align="center" prop="buildingId" >
        <template slot-scope="scope" v-if="scope.row.buildingId">
          <BuildingNames :buildingNames="buildingNames" :buildingId="scope.row.buildingId"></BuildingNames>
        </template>
      </el-table-column>
      <el-table-column label="设备编号" align="center" prop="equipmentNum" />
      <el-table-column label="统计时间" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="PM2.5值" align="center" prop="pm25" />
      <el-table-column label="PM10值" align="center" prop="pm10" />
      <el-table-column label="温度" align="center" prop="temperature" />
      <el-table-column label="湿度" align="center" prop="humidity" />
      <el-table-column label="噪音" align="center" prop="noise" />
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
            v-hasPermi="['jkza:dustData:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:dustData:remove']"
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

    <!-- 添加或修改扬尘设备数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
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
        <el-form-item label="设备编号" prop="equipmentNum">
          <el-input v-model="form.equipmentNum" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="统计时间" prop="date">
          <el-date-picker clearable
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择统计时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="PM2.5值" prop="pm25">
          <el-input v-model="form.pm25" placeholder="请输入PM2.5值" />
        </el-form-item>
        <el-form-item label="PM10值" prop="pm10">
          <el-input v-model="form.pm10" placeholder="请输入PM10值" />
        </el-form-item>
        <el-form-item label="温度" prop="temperature">
          <el-input v-model="form.temperature" placeholder="请输入温度" />
        </el-form-item>
        <el-form-item label="湿度" prop="humidity">
          <el-input v-model="form.humidity" placeholder="请输入湿度" />
        </el-form-item>
        <el-form-item label="噪音" prop="noise">
          <el-input v-model="form.noise" placeholder="请输入噪音" />
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
import { listDustData, getDustData, delDustData, addDustData, updateDustData } from "@/api/jkza/dustData";
import {listBuildingNames} from "@/api/jkza/synergy";
import StreetNames from "@/views/components/streetName";
import BuildingNames from "@/views/components/buildingName";
export default {
  name: "DustData",
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
      // 扬尘设备数据表格数据
      dustDataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //所有工地名称
      buildingNames: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        buildingId: null,
        equipmentNum: null,
        date: null,
        pm25: null,
        pm10: null,
        temperature: null,
        humidity: null,
        noise: null,
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
    /** 查询扬尘设备数据列表 */
    getList() {
      this.loading = true;
      listDustData(this.queryParams).then(response => {
        this.dustDataList = response.rows;
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
        buildingId: null,
        equipmentNum: null,
        date: null,
        pm25: null,
        pm10: null,
        temperature: null,
        humidity: null,
        noise: null,
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
      this.title = "添加扬尘设备数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDustData(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改扬尘设备数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDustData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDustData(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除扬尘设备数据编号为"' + ids + '"的数据项？').then(function() {
        return delDustData(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/dustData/export', {
        ...this.queryParams
      }, `dustData_${new Date().getTime()}.xlsx`)
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
