package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.StMassif;

/**
 * 地块数量Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-19
 */
public interface StMassifMapper 
{
    /**
     * 查询地块数量
     * 
     * @param id 地块数量主键
     * @return 地块数量
     */
    public StMassif selectStMassifById(Long id);

    /**
     * 查询地块数量列表
     * 
     * @param stMassif 地块数量
     * @return 地块数量集合
     */
    public List<StMassif> selectStMassifList(StMassif stMassif);

    /**
     * 新增地块数量
     * 
     * @param stMassif 地块数量
     * @return 结果
     */
    public int insertStMassif(StMassif stMassif);

    /**
     * 修改地块数量
     * 
     * @param stMassif 地块数量
     * @return 结果
     */
    public int updateStMassif(StMassif stMassif);

    /**
     * 删除地块数量
     * 
     * @param id 地块数量主键
     * @return 结果
     */
    public int deleteStMassifById(Long id);

    /**
     * 批量删除地块数量
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStMassifByIds(Long[] ids);
}
