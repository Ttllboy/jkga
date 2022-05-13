package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjSynergy;

/**
 * 多跨协同Service接口
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public interface IYjSynergyService 
{
    /**
     * 查询多跨协同
     * 
     * @param id 多跨协同主键
     * @return 多跨协同
     */
    public YjSynergy selectYjSynergyById(Long id);

    /**
     * 查询多跨协同列表
     * 
     * @param yjSynergy 多跨协同
     * @return 多跨协同集合
     */
    public List<YjSynergy> selectYjSynergyList(YjSynergy yjSynergy);

    /**
     * 新增多跨协同
     * 
     * @param yjSynergy 多跨协同
     * @return 结果
     */
    public int insertYjSynergy(YjSynergy yjSynergy);

    /**
     * 修改多跨协同
     * 
     * @param yjSynergy 多跨协同
     * @return 结果
     */
    public int updateYjSynergy(YjSynergy yjSynergy);

    /**
     * 批量删除多跨协同
     * 
     * @param ids 需要删除的多跨协同主键集合
     * @return 结果
     */
    public int deleteYjSynergyByIds(Long[] ids);

    /**
     * 删除多跨协同信息
     * 
     * @param id 多跨协同主键
     * @return 结果
     */
    public int deleteYjSynergyById(Long id);
}
