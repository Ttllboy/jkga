<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="工地名称" prop="buildingSiteName">
        <el-input
          v-model="queryParams.buildingSiteName"
          placeholder="请输入工地名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="工地编号" prop="buildingSiteNum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.buildingSiteNum"-->
      <!--    placeholder="请输入工地编号"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="工程编号" prop="projectNum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.projectNum"-->
      <!--    placeholder="请输入工程编号"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="项目信息编号" prop="projectInfoNum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.projectInfoNum"-->
      <!--    placeholder="请输入项目信息编号"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="施工许可证" prop="buildingLicense">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.buildingLicense"-->
      <!--    placeholder="请输入施工许可证"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="工地实有人口" prop="workerNum">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.workerNum"-->
      <!--    placeholder="请输入工地实有人口"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="工地地址" prop="buildingSiteLocation">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.buildingSiteLocation"-->
      <!--    placeholder="请输入工地地址"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="面积" prop="space">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.space"-->
      <!--    placeholder="请输入面积"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="造价" prop="price">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.price"-->
      <!--    placeholder="请输入造价"-->
      <!--    clearable-->
      <!--    @keyup.enter.native="handleQuery"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item label="所属街道" prop="streetId">
        <!--<el-input-->
        <!--  v-model="queryParams.streetId"-->
        <!--  placeholder="请输入所属街道"-->
        <!--  clearable-->
        <!--  @keyup.enter.native="handleQuery"-->
        <!--/>-->
        <el-select v-model="queryParams.streetId" placeholder="请选择所属街道" @change="changeStreetId" clearable>
          <el-option
            v-for="item in streetNames"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="坐标" prop="coordinate">
        <el-input
          v-model="queryParams.coordinate"
          placeholder="请输入坐标"
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
          v-hasPermi="['jkza:building:add']"
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
          v-hasPermi="['jkza:building:edit']"
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
          v-hasPermi="['jkza:building:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:building:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="buildingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="工地名称" align="center" prop="buildingSiteName" />
      <el-table-column label="工地编号" align="center" prop="buildingSiteNum" />
      <el-table-column label="工程编号" align="center" prop="projectNum" />
      <el-table-column label="项目信息编号" align="center" prop="projectInfoNum" />
      <el-table-column label="施工许可证" align="center" prop="buildingLicense" />
      <el-table-column label="工地实有人口" align="center" prop="workerNum" />
      <el-table-column label="工地地址" align="center" prop="buildingSiteLocation" />
      <el-table-column label="面积" align="center" prop="space" />
      <el-table-column label="造价" align="center" prop="price" />
      <el-table-column label="所属街道" align="center" prop="streetId" >
        <template slot-scope="scope" v-if="scope.row.streetId">
          <StreetNames :streetNames="streetNames" :streetId="scope.row.streetId"></StreetNames>
        </template>
      </el-table-column>
      <el-table-column label="坐标" align="center" prop="coordinate" />
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
            v-hasPermi="['jkza:building:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:building:remove']"
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

    <!-- 添加或修改工地信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="工地名称" prop="buildingSiteName">
          <el-input v-model="form.buildingSiteName" placeholder="请输入工地名称" />
        </el-form-item>
        <el-form-item label="工地编号" prop="buildingSiteNum">
          <el-input v-model="form.buildingSiteNum" placeholder="请输入工地编号" />
        </el-form-item>
        <el-form-item label="工程编号" prop="projectNum">
          <el-input v-model="form.projectNum" placeholder="请输入工程编号" />
        </el-form-item>
        <el-form-item label="项目信息编号" prop="projectInfoNum">
          <el-input v-model="form.projectInfoNum" placeholder="请输入项目信息编号" />
        </el-form-item>
        <el-form-item label="施工许可证" prop="buildingLicense">
          <el-input v-model="form.buildingLicense" placeholder="请输入施工许可证" />
        </el-form-item>
        <el-form-item label="工地实有人口" prop="workerNum">
          <el-input v-model="form.workerNum" placeholder="请输入工地实有人口" />
        </el-form-item>
        <el-form-item label="工地地址" prop="buildingSiteLocation">
          <el-input v-model="form.buildingSiteLocation" placeholder="请输入工地地址" />
        </el-form-item>
        <el-form-item label="面积" prop="space">
          <el-input v-model="form.space" placeholder="请输入面积" />
        </el-form-item>
        <el-form-item label="造价" prop="price">
          <el-input v-model="form.price" placeholder="请输入造价" />
        </el-form-item>
        <el-form-item label="所属街道" prop="streetId">
          <!--<el-input v-model="form.streetId" placeholder="请输入所属街道" />-->
          <el-select v-model="form.streetId" placeholder="请选择所属街道" @change="changeFormStreetId" clearable>
            <el-option
              v-for="item in streetNames"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="坐标" prop="coordinate">
          <el-input v-model="form.coordinate" placeholder="请输入坐标" />
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
import {
  listBuilding,
  getBuilding,
  delBuilding,
  addBuilding,
  updateBuilding,
  listStreetNames
} from "@/api/jkza/building";
import StreetNames from "@/views/components/streetName"

export default {
  name: "Building",
  components:{StreetNames},
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
      // 工地信息表格数据
      buildingList: [],
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
        buildingSiteName: null,
        buildingSiteNum: null,
        projectNum: null,
        projectInfoNum: null,
        buildingLicense: null,
        workerNum: null,
        buildingSiteLocation: null,
        space: null,
        price: null,
        streetId: null,
        coordinate: null,
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
    /** 查询工地信息列表 */
    getList() {
      this.loading = true;
      listBuilding(this.queryParams).then(response => {
        console.log(response);
        this.buildingList = response.rows;
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
        buildingSiteName: null,
        buildingSiteNum: null,
        projectNum: null,
        projectInfoNum: null,
        buildingLicense: null,
        workerNum: null,
        buildingSiteLocation: null,
        space: null,
        price: null,
        streetId: null,
        coordinate: null,
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
      this.title = "添加工地信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBuilding(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工地信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBuilding(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBuilding(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工地信息编号为"' + ids + '"的数据项？').then(function() {
        return delBuilding(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/building/export', {
        ...this.queryParams
      }, `building_${new Date().getTime()}.xlsx`)
    },
    //改变queryParams所属街道ID
    changeStreetId(streetId){
      this.queryParams.streetId =streetId;
    },
    //改变form所属街道ID
    changeFormStreetId(streetId){
      this.form.streetId =streetId;
    },
  }
};
</script>
