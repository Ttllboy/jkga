package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyEpidemicPrevention;

/**
 * 防疫详情Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyEpidemicPreventionMapper 
{
    /**
     * 查询防疫详情
     * 
     * @param id 防疫详情主键
     * @return 防疫详情
     */
    public YyEpidemicPrevention selectYyEpidemicPreventionById(Long id);

    /**
     * 查询防疫详情列表
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 防疫详情集合
     */
    public List<YyEpidemicPrevention> selectYyEpidemicPreventionList(YyEpidemicPrevention yyEpidemicPrevention);

    /**
     * 新增防疫详情
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 结果
     */
    public int insertYyEpidemicPrevention(YyEpidemicPrevention yyEpidemicPrevention);

    /**
     * 修改防疫详情
     * 
     * @param yyEpidemicPrevention 防疫详情
     * @return 结果
     */
    public int updateYyEpidemicPrevention(YyEpidemicPrevention yyEpidemicPrevention);

    /**
     * 删除防疫详情
     * 
     * @param id 防疫详情主键
     * @return 结果
     */
    public int deleteYyEpidemicPreventionById(Long id);

    /**
     * 批量删除防疫详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyEpidemicPreventionByIds(Integer[] ids);
}
