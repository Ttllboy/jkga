<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="避灾场所名字" prop="placeName">
        <el-input
          v-model="queryParams.placeName"
          placeholder="请输入避灾场所名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="场所状态" prop="placeType">
        <el-select v-model="queryParams.placeType" placeholder="请选择场所状态" clearable>
          <el-option
            v-for="dict in dict.type.place_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="当前转入人数" prop="intoNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.intoNum"-->
<!--          placeholder="请输入当前转入人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="当前转出人数" prop="outNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.outNum"-->
<!--          placeholder="请输入当前转出人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="当前场内人数" prop="innerNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.innerNum"-->
<!--          placeholder="请输入当前场内人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="还可容纳人数" prop="remainNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.remainNum"-->
<!--          placeholder="请输入还可容纳人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="坐标" prop="coordinate">-->
<!--        <el-input-->
<!--          v-model="queryParams.coordinate"-->
<!--          placeholder="请输入坐标"-->
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
          v-hasPermi="['jkza:yjDisaster:add']"
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
          v-hasPermi="['jkza:yjDisaster:edit']"
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
          v-hasPermi="['jkza:yjDisaster:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:yjDisaster:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yjDisasterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--<el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="id" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="避灾场所名字" align="center" prop="placeName" />
      <el-table-column label="场所状态" align="center" prop="placeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.place_type" :value="scope.row.placeType"/>
        </template>
      </el-table-column>
      <el-table-column label="当前转入人数" align="center" prop="intoNum" />
      <el-table-column label="当前转出人数" align="center" prop="outNum" />
      <el-table-column label="当前场内人数" align="center" prop="innerNum" />
      <el-table-column label="还可容纳人数" align="center" prop="remainNum" />
      <el-table-column label="坐标" align="center" prop="coordinate" />
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
            v-hasPermi="['jkza:yjDisaster:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:yjDisaster:remove']"
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

    <!-- 添加或修改避灾场所对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="避灾场所名字" prop="placeName">
          <el-input v-model="form.placeName" placeholder="请输入避灾场所名字" />
        </el-form-item>
        <el-form-item label="场所状态" prop="placeType">
          <el-select v-model="form.placeType" placeholder="请选择场所状态">
            <el-option
              v-for="dict in dict.type.place_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前转入人数" prop="intoNum">
          <el-input v-model="form.intoNum" placeholder="请输入当前转入人数" />
        </el-form-item>
        <el-form-item label="当前转出人数" prop="outNum">
          <el-input v-model="form.outNum" placeholder="请输入当前转出人数" />
        </el-form-item>
        <el-form-item label="当前场内人数" prop="innerNum">
          <el-input v-model="form.innerNum" placeholder="请输入当前场内人数" />
        </el-form-item>
        <el-form-item label="还可容纳人数" prop="remainNum">
          <el-input v-model="form.remainNum" placeholder="请输入还可容纳人数" />
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
import { listYjDisaster, getYjDisaster, delYjDisaster, addYjDisaster, updateYjDisaster } from "@/api/jkza/yjDisaster";

export default {
  name: "YjDisaster",
  dicts: ['place_type', 'data_type'],
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
      // 避灾场所表格数据
      yjDisasterList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        placeType: null,
        intoNum: null,
        outNum: null,
        innerNum: null,
        remainNum: null,
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
    /** 查询避灾场所列表 */
    getList() {
      this.loading = true;
      listYjDisaster(this.queryParams).then(response => {
        this.yjDisasterList = response.rows;
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
        placeName: null,
        placeType: null,
        intoNum: null,
        outNum: null,
        innerNum: null,
        remainNum: null,
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
      this.title = "添加避灾场所";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYjDisaster(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改避灾场所";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYjDisaster(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYjDisaster(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除避灾场所编号为"' + ids + '"的数据项？').then(function() {
        return delYjDisaster(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/yjDisaster/export', {
        ...this.queryParams
      }, `yjDisaster_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
