package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjMaterialMapper;
import com.ruoyi.jkza.domain.YjMaterial;
import com.ruoyi.jkza.service.IYjMaterialService;

/**
 * 物资信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-17
 */
@Service
public class YjMaterialServiceImpl implements IYjMaterialService 
{
    @Autowired
    private YjMaterialMapper yjMaterialMapper;

    /**
     * 查询物资信息
     * 
     * @param id 物资信息主键
     * @return 物资信息
     */
    @Override
    public YjMaterial selectYjMaterialById(Long id)
    {
        return yjMaterialMapper.selectYjMaterialById(id);
    }

    /**
     * 查询物资信息列表
     * 
     * @param yjMaterial 物资信息
     * @return 物资信息
     */
    @Override
    public List<YjMaterial> selectYjMaterialList(YjMaterial yjMaterial)
    {
        return yjMaterialMapper.selectYjMaterialList(yjMaterial);
    }

    /**
     * 新增物资信息
     * 
     * @param yjMaterial 物资信息
     * @return 结果
     */
    @Override
    public int insertYjMaterial(YjMaterial yjMaterial)
    {
        return yjMaterialMapper.insertYjMaterial(yjMaterial);
    }

    /**
     * 修改物资信息
     * 
     * @param yjMaterial 物资信息
     * @return 结果
     */
    @Override
    public int updateYjMaterial(YjMaterial yjMaterial)
    {
        return yjMaterialMapper.updateYjMaterial(yjMaterial);
    }

    /**
     * 批量删除物资信息
     * 
     * @param ids 需要删除的物资信息主键
     * @return 结果
     */
    @Override
    public int deleteYjMaterialByIds(Long[] ids)
    {
        return yjMaterialMapper.deleteYjMaterialByIds(ids);
    }

    /**
     * 删除物资信息信息
     * 
     * @param id 物资信息主键
     * @return 结果
     */
    @Override
    public int deleteYjMaterialById(Long id)
    {
        return yjMaterialMapper.deleteYjMaterialById(id);
    }
}
