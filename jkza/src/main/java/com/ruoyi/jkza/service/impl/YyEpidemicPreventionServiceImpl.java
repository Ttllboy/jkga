package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyEpidemicPreventionMapper;
import com.ruoyi.jkza.domain.YyEpidemicPrevention;
import com.ruoyi.jkza.service.IYyEpidemicPreventionService;

/**
 * 防疫详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyEpidemicPreventionServiceImpl implements IYyEpidemicPreventionService 
{
    @Autowired
    private YyEpidemicPreventionMapper yyEpidemicPreventionMapper;

    /**
     * 查询防疫详情
     * 
     * @param id 防疫详情主键
     * @return 防疫详情
     */
    @Override
    public YyEpidemicPrevention selectYyEpidemicPreventionById(Long id)
    {
        return yyEpidemicPreventionMapper.selectYyEpidemicPreventionById(id);
    }

    /**
     * 查询防疫详情列表
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 防疫详情
     */
    @Override
    public List<YyEpidemicPrevention> selectYyEpidemicPreventionList(YyEpidemicPrevention yyEpidemicPrevention)
    {
        return yyEpidemicPreventionMapper.selectYyEpidemicPreventionList(yyEpidemicPrevention);
    }

    /**
     * 新增防疫详情
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 结果
     */
    @Override
    public int insertYyEpidemicPrevention(YyEpidemicPrevention yyEpidemicPrevention)
    {
        return yyEpidemicPreventionMapper.insertYyEpidemicPrevention(yyEpidemicPrevention);
    }

    /**
     * 修改防疫详情
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 结果
     */
    @Override
    public int updateYyEpidemicPrevention(YyEpidemicPrevention yyEpidemicPrevention)
    {
        return yyEpidemicPreventionMapper.updateYyEpidemicPrevention(yyEpidemicPrevention);
    }

    /**
     * 批量删除防疫详情
     * 
     * @param ids 需要删除的防疫详情主键
     * @return 结果
     */
    @Override
    public int deleteYyEpidemicPreventionByIds(Integer[] ids)
    {
        return yyEpidemicPreventionMapper.deleteYyEpidemicPreventionByIds(ids);
    }

    /**
     * 删除防疫详情信息
     * 
     * @param id 防疫详情主键
     * @return 结果
     */
    @Override
    public int deleteYyEpidemicPreventionById(Long id)
    {
        return yyEpidemicPreventionMapper.deleteYyEpidemicPreventionById(id);
    }
}
