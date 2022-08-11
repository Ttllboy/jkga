package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdSynergy;

/**
 * 整改通知Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
public interface GdSynergyMapper 
{
    /**
     * 查询整改通知
     * 
     * @param id 整改通知主键
     * @return 整改通知
     */
    public GdSynergy selectGdSynergyById(Long id);

    /**
     * 查询整改通知列表
     * 
     * @param gdSynergy 整改通知
     * @return 整改通知集合
     */
    public List<GdSynergy> selectGdSynergyList(GdSynergy gdSynergy);

    /**
     * 新增整改通知
     * 
     * @param gdSynergy 整改通知
     * @return 结果
     */
    public int insertGdSynergy(GdSynergy gdSynergy);

    /**
     * 修改整改通知
     * 
     * @param gdSynergy 整改通知
     * @return 结果
     */
    public int updateGdSynergy(GdSynergy gdSynergy);

    /**
     * 删除整改通知
     * 
     * @param id 整改通知主键
     * @return 结果
     */
    public int deleteGdSynergyById(Long id);

    /**
     * 批量删除整改通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdSynergyByIds(Long[] ids);
    public int freshGdSynergy();
}
