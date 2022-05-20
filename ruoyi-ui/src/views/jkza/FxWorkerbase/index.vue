<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="personname">
        <el-input
          v-model="queryParams.personname"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idcard">
        <el-input
          v-model="queryParams.idcard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="照片路径" prop="imgurl">
        <el-input
          v-model="queryParams.imgurl"
          placeholder="请输入照片路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员类型" prop="persontypes">
        <el-input
          v-model="queryParams.persontypes"
          placeholder="请输入人员类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目信息编号" prop="projectguid">
        <el-input
          v-model="queryParams.projectguid"
          placeholder="请输入项目信息编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工地名称" prop="projectname">
        <el-input
          v-model="queryParams.projectname"
          placeholder="请输入工地名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="籍贯" prop="jiguan">
        <el-input
          v-model="queryParams.jiguan"
          placeholder="请输入籍贯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="户籍所在地" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入户籍所在地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="personnation">
        <el-input
          v-model="queryParams.personnation"
          placeholder="请输入民族"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="personphone">
        <el-input
          v-model="queryParams.personphone"
          placeholder="请输入电话"
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
          v-hasPermi="['jkza:FxWorkerbase:add']"
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
          v-hasPermi="['jkza:FxWorkerbase:edit']"
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
          v-hasPermi="['jkza:FxWorkerbase:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jkza:FxWorkerbase:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="FxWorkerbaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="personname" />
      <el-table-column label="身份证号" align="center" prop="idcard" />
      <el-table-column label="照片路径" align="center" prop="imgurl" />
      <el-table-column label="人员类型" align="center" prop="persontypes" />
      <el-table-column label="项目信息编号" align="center" prop="projectguid" />
      <el-table-column label="工地名称" align="center" prop="projectname" />
      <el-table-column label="籍贯" align="center" prop="jiguan" />
      <el-table-column label="户籍所在地" align="center" prop="address" />
      <el-table-column label="民族" align="center" prop="personnation" />
      <el-table-column label="电话" align="center" prop="personphone" />
      <el-table-column label="照片" align="center" prop="jpgbuffer" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jkza:FxWorkerbase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jkza:FxWorkerbase:remove']"
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

    <!-- 添加或修改工人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="personname">
          <el-input v-model="form.personname" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idcard">
          <el-input v-model="form.idcard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="照片路径" prop="imgurl">
          <el-input v-model="form.imgurl" placeholder="请输入照片路径" />
        </el-form-item>
        <el-form-item label="人员类型" prop="persontypes">
          <el-input v-model="form.persontypes" placeholder="请输入人员类型" />
        </el-form-item>
        <el-form-item label="项目信息编号" prop="projectguid">
          <el-input v-model="form.projectguid" placeholder="请输入项目信息编号" />
        </el-form-item>
        <el-form-item label="工地名称" prop="projectname">
          <el-input v-model="form.projectname" placeholder="请输入工地名称" />
        </el-form-item>
        <el-form-item label="籍贯" prop="jiguan">
          <el-input v-model="form.jiguan" placeholder="请输入籍贯" />
        </el-form-item>
        <el-form-item label="户籍所在地" prop="address">
          <el-input v-model="form.address" placeholder="请输入户籍所在地" />
        </el-form-item>
        <el-form-item label="民族" prop="personnation">
          <el-input v-model="form.personnation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="电话" prop="personphone">
          <el-input v-model="form.personphone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="照片" prop="jpgbuffer">
          <el-input v-model="form.jpgbuffer" type="textarea" placeholder="请输入内容" />
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
import { listFxWorkerbase, getFxWorkerbase, delFxWorkerbase, addFxWorkerbase, updateFxWorkerbase } from "@/api/jkza/FxWorkerbase";

export default {
  name: "FxWorkerbase",
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
      // 工人信息表格数据
      FxWorkerbaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personname: null,
        idcard: null,
        imgurl: null,
        persontypes: null,
        projectguid: null,
        projectname: null,
        jiguan: null,
        address: null,
        personnation: null,
        personphone: null,
        jpgbuffer: null,
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
    /** 查询工人信息列表 */
    getList() {
      this.loading = true;
      listFxWorkerbase(this.queryParams).then(response => {
        this.FxWorkerbaseList = response.rows;
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
        personname: null,
        idcard: null,
        imgurl: null,
        persontypes: null,
        projectguid: null,
        projectname: null,
        jiguan: null,
        address: null,
        personnation: null,
        personphone: null,
        jpgbuffer: null,
        createTime: null
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
      this.ids = selection.map(item => item.personname)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工人信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const personname = row.personname || this.ids
      getFxWorkerbase(personname).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.personname != null) {
            updateFxWorkerbase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFxWorkerbase(this.form).then(response => {
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
      const personnames = row.personname || this.ids;
      this.$modal.confirm('是否确认删除工人信息编号为"' + personnames + '"的数据项？').then(function() {
        return delFxWorkerbase(personnames);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('jkza/FxWorkerbase/export', {
        ...this.queryParams
      }, `FxWorkerbase_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
