package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.MqCarMapper;
import com.ruoyi.jkza.domain.MqCar;
import com.ruoyi.jkza.service.IMqCarService;

/**
 * 车辆卡口Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-23
 */
@Service
public class MqCarServiceImpl implements IMqCarService 
{
    @Autowired
    private MqCarMapper mqCarMapper;

    /**
     * 查询车辆卡口
     * 
     * @param id 车辆卡口主键
     * @return 车辆卡口
     */
    @Override
    public MqCar selectMqCarById(Long id)
    {
        return mqCarMapper.selectMqCarById(id);
    }

    /**
     * 查询车辆卡口列表
     * 
     * @param mqCar 车辆卡口
     * @return 车辆卡口
     */
    @Override
    public List<MqCar> selectMqCarList(MqCar mqCar)
    {
        return mqCarMapper.selectMqCarList(mqCar);
    }

    /**
     * 新增车辆卡口
     * 
     * @param mqCar 车辆卡口
     * @return 结果
     */
    @Override
    public int insertMqCar(MqCar mqCar)
    {
        return mqCarMapper.insertMqCar(mqCar);
    }

    /**
     * 修改车辆卡口
     * 
     * @param mqCar 车辆卡口
     * @return 结果
     */
    @Override
    public int updateMqCar(MqCar mqCar)
    {
        return mqCarMapper.updateMqCar(mqCar);
    }

    /**
     * 批量删除车辆卡口
     * 
     * @param ids 需要删除的车辆卡口主键
     * @return 结果
     */
    @Override
    public int deleteMqCarByIds(Long[] ids)
    {
        return mqCarMapper.deleteMqCarByIds(ids);
    }

    /**
     * 删除车辆卡口信息
     * 
     * @param id 车辆卡口主键
     * @return 结果
     */
    @Override
    public int deleteMqCarById(Long id)
    {
        return mqCarMapper.deleteMqCarById(id);
    }
}
