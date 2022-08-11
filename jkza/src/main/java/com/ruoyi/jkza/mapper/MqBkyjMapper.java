package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.MqBkyj;

/**
 * 布控预警Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public interface MqBkyjMapper 
{
    /**
     * 查询布控预警
     * 
     * @param id 布控预警主键
     * @return 布控预警
     */
    public MqBkyj selectMqBkyjById(Long id);

    /**
     * 查询布控预警列表
     * 
     * @param mqBkyj 布控预警
     * @return 布控预警集合
     */
    public List<MqBkyj> selectMqBkyjList(MqBkyj mqBkyj);

    /**
     * 新增布控预警
     * 
     * @param mqBkyj 布控预警
     * @return 结果
     */
    public int insertMqBkyj(MqBkyj mqBkyj);

    /**
     * 修改布控预警
     * 
     * @param mqBkyj 布控预警
     * @return 结果
     */
    public int updateMqBkyj(MqBkyj mqBkyj);

    /**
     * 删除布控预警
     * 
     * @param id 布控预警主键
     * @return 结果
     */
    public int deleteMqBkyjById(Long id);

    /**
     * 批量删除布控预警
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMqBkyjByIds(Long[] ids);
}
