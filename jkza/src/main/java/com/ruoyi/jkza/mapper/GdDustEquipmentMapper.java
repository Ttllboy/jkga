package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdDustEquipment;

/**
 * 扬尘设备Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
public interface GdDustEquipmentMapper 
{
    /**
     * 查询扬尘设备
     * 
     * @param id 扬尘设备主键
     * @return 扬尘设备
     */
    public GdDustEquipment selectGdDustEquipmentById(Long id);

    /**
     * 查询扬尘设备列表
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 扬尘设备集合
     */
    public List<GdDustEquipment> selectGdDustEquipmentList(GdDustEquipment gdDustEquipment);

    /**
     * 新增扬尘设备
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 结果
     */
    public int insertGdDustEquipment(GdDustEquipment gdDustEquipment);

    /**
     * 修改扬尘设备
     * 
     * @param gdDustEquipment 扬尘设备
     * @return 结果
     */
    public int updateGdDustEquipment(GdDustEquipment gdDustEquipment);

    /**
     * 删除扬尘设备
     * 
     * @param id 扬尘设备主键
     * @return 结果
     */
    public int deleteGdDustEquipmentById(Long id);

    /**
     * 批量删除扬尘设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdDustEquipmentByIds(Long[] ids);
}
