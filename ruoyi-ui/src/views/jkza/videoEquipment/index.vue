<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="所属工地" prop="buildingSiteId">
        <!--<el-input-->
        <!--  v-model="queryParams.buildingSiteId"-->
        <!--  placeholder="请输入所属工地"-->
        <!--  clearable-->
        <!--  @keyup.enter.native="handleQuery"-->
        <!--/>-->
        <el-select v-model="queryParams.buildingSiteId" placeholder="请选择所属工地" @change="changeBuildingId" clearable>
          <el-option
            v-for="item in buildingNames"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="视频设备名称" prop="videoName">
        <el-input
          v-model="queryParams.videoName"
          placeholder="请输入视频设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="equipmentType">
        <el-select v-model="queryParams.equipmentType" placeholder="请选择设备类型" clearable>
          <el-option
            v-for="dict in dict.type.equipment_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="在线状态" prop="onlineType">
        <el-select v-model="queryParams.onlineType" placeholder="请选择在线状态" clearable>
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
      <el-form-item label="经度" prop="gpsx">
        <el-input
          v-model="queryParams.gpsx"
          placeholder="请输入经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度" prop="gpsy">
        <el-input
          v-model="queryParams.gpsy"
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
          v-hasPermi="['jkza:videoEquipment:add']"
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
          v-hasPermi="['jkza:videoEquipment:edit']"
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
          v-hasPermi="['jkza:videoEquipment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:videoEquipment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="videoEquipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属工地" align="center" prop="buildingSiteId" >
        <template slot-scope="scope" v-if="scope.row.buildingSiteId">
          <BuildingNames :buildingNames="buildingNames" :buildingId="scope.row.buildingSiteId"></BuildingNames>
        </template>
      </el-table-column>
      <el-table-column label="视频设备名称" align="center" prop="videoName" />
      <el-table-column label="设备类型" align="center" prop="equipmentType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.equipment_type" :value="scope.row.equipmentType"/>
        </template>
      </el-table-column>
      <el-table-column label="在线状态" align="center" prop="onlineType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.online_type" :value="scope.row.onlineType"/>
        </template>
      </el-table-column>
      <el-table-column label="通道编码" align="center" prop="channelCode" />
      <el-table-column label="经度" align="center" prop="gpsx" />
      <el-table-column label="纬度" align="center" prop="gpsy" />
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
            v-hasPermi="['jkza:videoEquipment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:videoEquipment:remove']"
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
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="所属工地" prop="buildingSiteId">
          <!--<el-input v-model="form.buildingSiteId" placeholder="请输入所属工地" />-->
          <el-select v-model="form.buildingSiteId" placeholder="请选择所属工地" @change="changeFormBuildingId" clearable>
            <el-option
              v-for="item in buildingNames"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="视频设备名称" prop="videoName">
          <el-input v-model="form.videoName" placeholder="请输入视频设备名称" />
        </el-form-item>
        <el-form-item label="设备类型" prop="equipmentType">
          <el-select v-model="form.equipmentType" placeholder="请选择设备类型">
            <el-option
              v-for="dict in dict.type.equipment_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="在线状态" prop="onlineType">
          <el-select v-model="form.onlineType" placeholder="请选择在线状态">
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
        <el-form-item label="经度" prop="gpsx">
          <el-input v-model="form.gpsx" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="gpsy">
          <el-input v-model="form.gpsy" placeholder="请输入纬度" />
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
import { listVideoEquipment, getVideoEquipment, delVideoEquipment, addVideoEquipment, updateVideoEquipment } from "@/api/jkza/videoEquipment";
import {listBuildingNames} from "@/api/jkza/synergy";
import BuildingNames from "@/views/components/buildingName";

export default {
  name: "VideoEquipment",
  dicts: ['online_type', 'equipment_type','data_type'],
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
      // 视频设备表格数据
      videoEquipmentList: [],
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
        buildingSiteId: null,
        videoName: null,
        equipmentType: null,
        onlineType: null,
        channelCode: null,
        gpsx: null,
        gpsy: null,
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
      listVideoEquipment(this.queryParams).then(response => {
        this.videoEquipmentList = response.rows;
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
        buildingSiteId: null,
        videoName: null,
        equipmentType: null,
        onlineType: null,
        channelCode: null,
        gpsx: null,
        gpsy: null,
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
      getVideoEquipment(id).then(response => {
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
            updateVideoEquipment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVideoEquipment(this.form).then(response => {
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
        return delVideoEquipment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/videoEquipment/export', {
        ...this.queryParams
      }, `videoEquipment_${new Date().getTime()}.xlsx`)
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
