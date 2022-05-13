package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdDustEquipmentMapper;
import com.ruoyi.jkza.domain.GdDustEquipment;
import com.ruoyi.jkza.service.IGdDustEquipmentService;

/**
 * 扬尘设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
@Service
public class GdDustEquipmentServiceImpl implements IGdDustEquipmentService 
{
    @Autowired
    private GdDustEquipmentMapper gdDustEquipmentMapper;

    /**
     * 查询扬尘设备
     * 
     * @param id 扬尘设备主键
     * @return 扬尘设备
     */
    @Override
    public GdDustEquipment selectGdDustEquipmentById(Long id)
    {
        return gdDustEquipmentMapper.selectGdDustEquipmentById(id);
    }

    /**
     * 查询扬尘设备列表
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 扬尘设备
     */
    @Override
    public List<GdDustEquipment> selectGdDustEquipmentList(GdDustEquipment gdDustEquipment)
    {
        return gdDustEquipmentMapper.selectGdDustEquipmentList(gdDustEquipment);
    }

    /**
     * 新增扬尘设备
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 结果
     */
    @Override
    public int insertGdDustEquipment(GdDustEquipment gdDustEquipment)
    {
        return gdDustEquipmentMapper.insertGdDustEquipment(gdDustEquipment);
    }

    /**
     * 修改扬尘设备
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 结果
     */
    @Override
    public int updateGdDustEquipment(GdDustEquipment gdDustEquipment)
    {
        return gdDustEquipmentMapper.updateGdDustEquipment(gdDustEquipment);
    }

    /**
     * 批量删除扬尘设备
     * 
     * @param ids 需要删除的扬尘设备主键
     * @return 结果
     */
    @Override
    public int deleteGdDustEquipmentByIds(Long[] ids)
    {
        return gdDustEquipmentMapper.deleteGdDustEquipmentByIds(ids);
    }

    /**
     * 删除扬尘设备信息
     * 
     * @param id 扬尘设备主键
     * @return 结果
     */
    @Override
    public int deleteGdDustEquipmentById(Long id)
    {
        return gdDustEquipmentMapper.deleteGdDustEquipmentById(id);
    }
}
