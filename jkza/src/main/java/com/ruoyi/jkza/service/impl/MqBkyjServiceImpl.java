package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.MqBkyjMapper;
import com.ruoyi.jkza.domain.MqBkyj;
import com.ruoyi.jkza.service.IMqBkyjService;

/**
 * 布控预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@Service
public class MqBkyjServiceImpl implements IMqBkyjService 
{
    @Autowired
    private MqBkyjMapper mqBkyjMapper;

    /**
     * 查询布控预警
     * 
     * @param id 布控预警主键
     * @return 布控预警
     */
    @Override
    public MqBkyj selectMqBkyjById(Long id)
    {
        return mqBkyjMapper.selectMqBkyjById(id);
    }

    /**
     * 查询布控预警列表
     * 
     * @param mqBkyj 布控预警
     * @return 布控预警
     */
    @Override
    public List<MqBkyj> selectMqBkyjList(MqBkyj mqBkyj)
    {
        return mqBkyjMapper.selectMqBkyjList(mqBkyj);
    }

    /**
     * 新增布控预警
     * 
     * @param mqBkyj 布控预警
     * @return 结果
     */
    @Override
    public int insertMqBkyj(MqBkyj mqBkyj)
    {
        return mqBkyjMapper.insertMqBkyj(mqBkyj);
    }

    /**
     * 修改布控预警
     * 
     * @param mqBkyj 布控预警
     * @return 结果
     */
    @Override
    public int updateMqBkyj(MqBkyj mqBkyj)
    {
        return mqBkyjMapper.updateMqBkyj(mqBkyj);
    }

    /**
     * 批量删除布控预警
     * 
     * @param ids 需要删除的布控预警主键
     * @return 结果
     */
    @Override
    public int deleteMqBkyjByIds(Long[] ids)
    {
        return mqBkyjMapper.deleteMqBkyjByIds(ids);
    }

    /**
     * 删除布控预警信息
     * 
     * @param id 布控预警主键
     * @return 结果
     */
    @Override
    public int deleteMqBkyjById(Long id)
    {
        return mqBkyjMapper.deleteMqBkyjById(id);
    }
}
