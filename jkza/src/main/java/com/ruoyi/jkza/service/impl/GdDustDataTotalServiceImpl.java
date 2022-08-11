package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdDustDataTotalMapper;
import com.ruoyi.jkza.domain.GdDustDataTotal;
import com.ruoyi.jkza.service.IGdDustDataTotalService;

/**
 * 扬尘数据总Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
@Service
public class GdDustDataTotalServiceImpl implements IGdDustDataTotalService 
{
    @Autowired
    private GdDustDataTotalMapper gdDustDataTotalMapper;

    /**
     * 查询扬尘数据总
     * 
     * @param id 扬尘数据总主键
     * @return 扬尘数据总
     */
    @Override
    public GdDustDataTotal selectGdDustDataTotalById(Long id)
    {
        return gdDustDataTotalMapper.selectGdDustDataTotalById(id);
    }

    /**
     * 查询扬尘数据总列表
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 扬尘数据总
     */
    @Override
    public List<GdDustDataTotal> selectGdDustDataTotalList(GdDustDataTotal gdDustDataTotal)
    {
        return gdDustDataTotalMapper.selectGdDustDataTotalList(gdDustDataTotal);
    }

    /**
     * 新增扬尘数据总
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 结果
     */
    @Override
    public int insertGdDustDataTotal(GdDustDataTotal gdDustDataTotal)
    {
        return gdDustDataTotalMapper.insertGdDustDataTotal(gdDustDataTotal);
    }

    /**
     * 修改扬尘数据总
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 结果
     */
    @Override
    public int updateGdDustDataTotal(GdDustDataTotal gdDustDataTotal)
    {
        return gdDustDataTotalMapper.updateGdDustDataTotal(gdDustDataTotal);
    }

    /**
     * 批量删除扬尘数据总
     * 
     * @param ids 需要删除的扬尘数据总主键
     * @return 结果
     */
    @Override
    public int deleteGdDustDataTotalByIds(Long[] ids)
    {
        return gdDustDataTotalMapper.deleteGdDustDataTotalByIds(ids);
    }

    /**
     * 删除扬尘数据总信息
     * 
     * @param id 扬尘数据总主键
     * @return 结果
     */
    @Override
    public int deleteGdDustDataTotalById(Long id)
    {
        return gdDustDataTotalMapper.deleteGdDustDataTotalById(id);
    }
}
