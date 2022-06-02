package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjMaterial;

/**
 * 物资信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-27
 */
public interface IYjMaterialService 
{
    /**
     * 查询物资信息
     * 
     * @param id 物资信息主键
     * @return 物资信息
     */
    public YjMaterial selectYjMaterialById(Long id);

    /**
     * 查询物资信息列表
     * 
     * @param yjMaterial 物资信息
     * @return 物资信息集合
     */
    public List<YjMaterial> selectYjMaterialList(YjMaterial yjMaterial);

    /**
     * 新增物资信息
     * 
     * @param yjMaterial 物资信息
     * @return 结果
     */
    public int insertYjMaterial(YjMaterial yjMaterial);

    /**
     * 修改物资信息
     * 
     * @param yjMaterial 物资信息
     * @return 结果
     */
    public int updateYjMaterial(YjMaterial yjMaterial);

    /**
     * 批量删除物资信息
     * 
     * @param ids 需要删除的物资信息主键集合
     * @return 结果
     */
    public int deleteYjMaterialByIds(Long[] ids);

    /**
     * 删除物资信息信息
     * 
     * @param id 物资信息主键
     * @return 结果
     */
    public int deleteYjMaterialById(Long id);
}
