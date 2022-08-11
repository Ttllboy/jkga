package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdDustDataTotal;

/**
 * 扬尘数据总Service接口
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
public interface IGdDustDataTotalService 
{
    /**
     * 查询扬尘数据总
     * 
     * @param id 扬尘数据总主键
     * @return 扬尘数据总
     */
    public GdDustDataTotal selectGdDustDataTotalById(Long id);

    /**
     * 查询扬尘数据总列表
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 扬尘数据总集合
     */
    public List<GdDustDataTotal> selectGdDustDataTotalList(GdDustDataTotal gdDustDataTotal);

    /**
     * 新增扬尘数据总
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 结果
     */
    public int insertGdDustDataTotal(GdDustDataTotal gdDustDataTotal);

    /**
     * 修改扬尘数据总
     * 
     * @param gdDustDataTotal 扬尘数据总
     * @return 结果
     */
    public int updateGdDustDataTotal(GdDustDataTotal gdDustDataTotal);

    /**
     * 批量删除扬尘数据总
     * 
     * @param ids 需要删除的扬尘数据总主键集合
     * @return 结果
     */
    public int deleteGdDustDataTotalByIds(Long[] ids);

    /**
     * 删除扬尘数据总信息
     * 
     * @param id 扬尘数据总主键
     * @return 结果
     */
    public int deleteGdDustDataTotalById(Long id);
}
