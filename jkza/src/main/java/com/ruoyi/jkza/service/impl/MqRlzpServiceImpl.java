package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.MqRlzpMapper;
import com.ruoyi.jkza.domain.MqRlzp;
import com.ruoyi.jkza.service.IMqRlzpService;

/**
 * 人脸抓怕Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@Service
public class MqRlzpServiceImpl implements IMqRlzpService 
{
    @Autowired
    private MqRlzpMapper mqRlzpMapper;

    /**
     * 查询人脸抓怕
     * 
     * @param id 人脸抓怕主键
     * @return 人脸抓怕
     */
    @Override
    public MqRlzp selectMqRlzpById(Long id)
    {
        return mqRlzpMapper.selectMqRlzpById(id);
    }

    /**
     * 查询人脸抓怕列表
     * 
     * @param mqRlzp 人脸抓怕
     * @return 人脸抓怕
     */
    @Override
    public List<MqRlzp> selectMqRlzpList(MqRlzp mqRlzp)
    {
        return mqRlzpMapper.selectMqRlzpList(mqRlzp);
    }

    /**
     * 新增人脸抓怕
     * 
     * @param mqRlzp 人脸抓怕
     * @return 结果
     */
    @Override
    public int insertMqRlzp(MqRlzp mqRlzp)
    {
        return mqRlzpMapper.insertMqRlzp(mqRlzp);
    }

    /**
     * 修改人脸抓怕
     * 
     * @param mqRlzp 人脸抓怕
     * @return 结果
     */
    @Override
    public int updateMqRlzp(MqRlzp mqRlzp)
    {
        return mqRlzpMapper.updateMqRlzp(mqRlzp);
    }

    /**
     * 批量删除人脸抓怕
     * 
     * @param ids 需要删除的人脸抓怕主键
     * @return 结果
     */
    @Override
    public int deleteMqRlzpByIds(Long[] ids)
    {
        return mqRlzpMapper.deleteMqRlzpByIds(ids);
    }

    /**
     * 删除人脸抓怕信息
     * 
     * @param id 人脸抓怕主键
     * @return 结果
     */
    @Override
    public int deleteMqRlzpById(Long id)
    {
        return mqRlzpMapper.deleteMqRlzpById(id);
    }
}
