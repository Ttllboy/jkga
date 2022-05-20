package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.LtdqExcessivestatisticaldataMapper;
import com.ruoyi.jkza.domain.LtdqExcessivestatisticaldata;
import com.ruoyi.jkza.service.ILtdqExcessivestatisticaldataService;

/**
 * 扬尘数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@Service
public class LtdqExcessivestatisticaldataServiceImpl implements ILtdqExcessivestatisticaldataService 
{
    @Autowired
    private LtdqExcessivestatisticaldataMapper ltdqExcessivestatisticaldataMapper;

    /**
     * 查询扬尘数据
     * 
     * @param projectguid 扬尘数据主键
     * @return 扬尘数据
     */
    @Override
    public LtdqExcessivestatisticaldata selectLtdqExcessivestatisticaldataByProjectguid(String projectguid)
    {
        return ltdqExcessivestatisticaldataMapper.selectLtdqExcessivestatisticaldataByProjectguid(projectguid);
    }

    /**
     * 查询扬尘数据列表
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 扬尘数据
     */
    @Override
    public List<LtdqExcessivestatisticaldata> selectLtdqExcessivestatisticaldataList(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        return ltdqExcessivestatisticaldataMapper.selectLtdqExcessivestatisticaldataList(ltdqExcessivestatisticaldata);
    }

    /**
     * 新增扬尘数据
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 结果
     */
    @Override
    public int insertLtdqExcessivestatisticaldata(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        ltdqExcessivestatisticaldata.setCreateTime(DateUtils.getNowDate());
        return ltdqExcessivestatisticaldataMapper.insertLtdqExcessivestatisticaldata(ltdqExcessivestatisticaldata);
    }

    /**
     * 修改扬尘数据
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 结果
     */
    @Override
    public int updateLtdqExcessivestatisticaldata(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata)
    {
        return ltdqExcessivestatisticaldataMapper.updateLtdqExcessivestatisticaldata(ltdqExcessivestatisticaldata);
    }

    /**
     * 批量删除扬尘数据
     * 
     * @param projectguids 需要删除的扬尘数据主键
     * @return 结果
     */
    @Override
    public int deleteLtdqExcessivestatisticaldataByProjectguids(String[] projectguids)
    {
        return ltdqExcessivestatisticaldataMapper.deleteLtdqExcessivestatisticaldataByProjectguids(projectguids);
    }

    /**
     * 删除扬尘数据信息
     * 
     * @param projectguid 扬尘数据主键
     * @return 结果
     */
    @Override
    public int deleteLtdqExcessivestatisticaldataByProjectguid(String projectguid)
    {
        return ltdqExcessivestatisticaldataMapper.deleteLtdqExcessivestatisticaldataByProjectguid(projectguid);
    }
}
