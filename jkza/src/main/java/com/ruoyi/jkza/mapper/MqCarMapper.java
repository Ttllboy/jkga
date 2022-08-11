package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.MqCar;

/**
 * 车辆卡口Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-23
 */
public interface MqCarMapper 
{
    /**
     * 查询车辆卡口
     * 
     * @param id 车辆卡口主键
     * @return 车辆卡口
     */
    public MqCar selectMqCarById(Long id);

    /**
     * 查询车辆卡口列表
     * 
     * @param mqCar 车辆卡口
     * @return 车辆卡口集合
     */
    public List<MqCar> selectMqCarList(MqCar mqCar);
    public List<MqCar> selectMqCarList2(MqCar mqCar);

    /**
     * 新增车辆卡口
     * 
     * @param mqCar 车辆卡口
     * @return 结果
     */
    public int insertMqCar(MqCar mqCar);

    /**
     * 修改车辆卡口
     * 
     * @param mqCar 车辆卡口
     * @return 结果
     */
    public int updateMqCar(MqCar mqCar);

    /**
     * 删除车辆卡口
     * 
     * @param id 车辆卡口主键
     * @return 结果
     */
    public int deleteMqCarById(Long id);

    /**
     * 批量删除车辆卡口
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMqCarByIds(Long[] ids);
}
