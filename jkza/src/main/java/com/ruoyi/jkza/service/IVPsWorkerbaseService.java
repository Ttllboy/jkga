package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.VPsWorkerbase;

/**
 * 工人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public interface IVPsWorkerbaseService 
{
    /**
     * 查询工人信息
     * 
     * @param personname 工人信息主键
     * @return 工人信息
     */
    public VPsWorkerbase selectVPsWorkerbaseByPersonname(String personname);

    /**
     * 查询工人信息列表
     * 
     * @param vPsWorkerbase 工人信息
     * @return 工人信息集合
     */
    public List<VPsWorkerbase> selectVPsWorkerbaseList(VPsWorkerbase vPsWorkerbase);

    /**
     * 新增工人信息
     * 
     * @param vPsWorkerbase 工人信息
     * @return 结果
     */
    public int insertVPsWorkerbase(VPsWorkerbase vPsWorkerbase);

    /**
     * 修改工人信息
     * 
     * @param vPsWorkerbase 工人信息
     * @return 结果
     */
    public int updateVPsWorkerbase(VPsWorkerbase vPsWorkerbase);

    /**
     * 批量删除工人信息
     * 
     * @param personnames 需要删除的工人信息主键集合
     * @return 结果
     */
    public int deleteVPsWorkerbaseByPersonnames(String[] personnames);

    /**
     * 删除工人信息信息
     * 
     * @param personname 工人信息主键
     * @return 结果
     */
    public int deleteVPsWorkerbaseByPersonname(String personname);
}
