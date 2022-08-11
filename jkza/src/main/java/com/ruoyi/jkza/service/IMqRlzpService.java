package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.MqRlzp;

/**
 * 人脸抓怕Service接口
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
public interface IMqRlzpService 
{
    /**
     * 查询人脸抓怕
     * 
     * @param id 人脸抓怕主键
     * @return 人脸抓怕
     */
    public MqRlzp selectMqRlzpById(Long id);

    /**
     * 查询人脸抓怕列表
     * 
     * @param mqRlzp 人脸抓怕
     * @return 人脸抓怕集合
     */
    public List<MqRlzp> selectMqRlzpList(MqRlzp mqRlzp);

    /**
     * 新增人脸抓怕
     * 
     * @param mqRlzp 人脸抓怕
     * @return 结果
     */
    public int insertMqRlzp(MqRlzp mqRlzp);

    /**
     * 修改人脸抓怕
     * 
     * @param mqRlzp 人脸抓怕
     * @return 结果
     */
    public int updateMqRlzp(MqRlzp mqRlzp);

    /**
     * 批量删除人脸抓怕
     * 
     * @param ids 需要删除的人脸抓怕主键集合
     * @return 结果
     */
    public int deleteMqRlzpByIds(Long[] ids);

    /**
     * 删除人脸抓怕信息
     * 
     * @param id 人脸抓怕主键
     * @return 结果
     */
    public int deleteMqRlzpById(Long id);
}
