package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdSynergy;

/**
 * 整改通知Service接口
 * 
 * @author ruoyi
 * @date 2022-06-26
 */
public interface IGdSynergyService 
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
     * 批量删除整改通知
     * 
     * @param ids 需要删除的整改通知主键集合
     * @return 结果
     */
    public int deleteGdSynergyByIds(Long[] ids);

    /**
     * 删除整改通知信息
     * 
     * @param id 整改通知主键
     * @return 结果
     */
    public int deleteGdSynergyById(Long id);
}
