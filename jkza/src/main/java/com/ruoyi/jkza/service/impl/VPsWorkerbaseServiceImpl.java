package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.VPsWorkerbaseMapper;
import com.ruoyi.jkza.domain.VPsWorkerbase;
import com.ruoyi.jkza.service.IVPsWorkerbaseService;

/**
 * 工人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@Service
public class VPsWorkerbaseServiceImpl implements IVPsWorkerbaseService 
{
    @Autowired
    private VPsWorkerbaseMapper vPsWorkerbaseMapper;

    /**
     * 查询工人信息
     * 
     * @param personname 工人信息主键
     * @return 工人信息
     */
    @Override
    public VPsWorkerbase selectVPsWorkerbaseByPersonname(String personname)
    {
        return vPsWorkerbaseMapper.selectVPsWorkerbaseByPersonname(personname);
    }

    /**
     * 查询工人信息列表
     * 
     * @param vPsWorkerbase 工人信息
     * @return 工人信息
     */
    @Override
    public List<VPsWorkerbase> selectVPsWorkerbaseList(VPsWorkerbase vPsWorkerbase)
    {
        return vPsWorkerbaseMapper.selectVPsWorkerbaseList(vPsWorkerbase);
    }

    /**
     * 新增工人信息
     * 
     * @param vPsWorkerbase 工人信息
     * @return 结果
     */
    @Override
    public int insertVPsWorkerbase(VPsWorkerbase vPsWorkerbase)
    {
        vPsWorkerbase.setCreateTime(DateUtils.getNowDate());
        return vPsWorkerbaseMapper.insertVPsWorkerbase(vPsWorkerbase);
    }

    /**
     * 修改工人信息
     * 
     * @param vPsWorkerbase 工人信息
     * @return 结果
     */
    @Override
    public int updateVPsWorkerbase(VPsWorkerbase vPsWorkerbase)
    {
        return vPsWorkerbaseMapper.updateVPsWorkerbase(vPsWorkerbase);
    }

    /**
     * 批量删除工人信息
     * 
     * @param personnames 需要删除的工人信息主键
     * @return 结果
     */
    @Override
    public int deleteVPsWorkerbaseByPersonnames(String[] personnames)
    {
        return vPsWorkerbaseMapper.deleteVPsWorkerbaseByPersonnames(personnames);
    }

    /**
     * 删除工人信息信息
     * 
     * @param personname 工人信息主键
     * @return 结果
     */
    @Override
    public int deleteVPsWorkerbaseByPersonname(String personname)
    {
        return vPsWorkerbaseMapper.deleteVPsWorkerbaseByPersonname(personname);
    }
}
