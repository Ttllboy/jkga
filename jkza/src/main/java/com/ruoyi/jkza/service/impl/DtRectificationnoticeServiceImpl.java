package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.DtRectificationnoticeMapper;
import com.ruoyi.jkza.domain.DtRectificationnotice;
import com.ruoyi.jkza.service.IDtRectificationnoticeService;

/**
 * 整改信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@Service
public class DtRectificationnoticeServiceImpl implements IDtRectificationnoticeService 
{
    @Autowired
    private DtRectificationnoticeMapper dtRectificationnoticeMapper;

    /**
     * 查询整改信息
     * 
     * @param projectguid 整改信息主键
     * @return 整改信息
     */
    @Override
    public DtRectificationnotice selectDtRectificationnoticeByProjectguid(String projectguid)
    {
        return dtRectificationnoticeMapper.selectDtRectificationnoticeByProjectguid(projectguid);
    }

    /**
     * 查询整改信息列表
     * 
     * @param dtRectificationnotice 整改信息
     * @return 整改信息
     */
    @Override
    public List<DtRectificationnotice> selectDtRectificationnoticeList(DtRectificationnotice dtRectificationnotice)
    {
        return dtRectificationnoticeMapper.selectDtRectificationnoticeList(dtRectificationnotice);
    }

    /**
     * 新增整改信息
     * 
     * @param dtRectificationnotice 整改信息
     * @return 结果
     */
    @Override
    public int insertDtRectificationnotice(DtRectificationnotice dtRectificationnotice)
    {
        dtRectificationnotice.setCreateTime(DateUtils.getNowDate());
        return dtRectificationnoticeMapper.insertDtRectificationnotice(dtRectificationnotice);
    }

    /**
     * 修改整改信息
     * 
     * @param dtRectificationnotice 整改信息
     * @return 结果
     */
    @Override
    public int updateDtRectificationnotice(DtRectificationnotice dtRectificationnotice)
    {
        return dtRectificationnoticeMapper.updateDtRectificationnotice(dtRectificationnotice);
    }

    /**
     * 批量删除整改信息
     * 
     * @param projectguids 需要删除的整改信息主键
     * @return 结果
     */
    @Override
    public int deleteDtRectificationnoticeByProjectguids(String[] projectguids)
    {
        return dtRectificationnoticeMapper.deleteDtRectificationnoticeByProjectguids(projectguids);
    }

    /**
     * 删除整改信息信息
     * 
     * @param projectguid 整改信息主键
     * @return 结果
     */
    @Override
    public int deleteDtRectificationnoticeByProjectguid(String projectguid)
    {
        return dtRectificationnoticeMapper.deleteDtRectificationnoticeByProjectguid(projectguid);
    }
}
