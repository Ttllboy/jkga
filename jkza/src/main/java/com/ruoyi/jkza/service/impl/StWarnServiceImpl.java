package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StWarnMapper;
import com.ruoyi.jkza.domain.StWarn;
import com.ruoyi.jkza.service.IStWarnService;

/**
 * 预警信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StWarnServiceImpl implements IStWarnService 
{
    @Autowired
    private StWarnMapper stWarnMapper;

    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    @Override
    public StWarn selectStWarnById(Long id)
    {
        return stWarnMapper.selectStWarnById(id);
    }

    /**
     * 查询预警信息列表
     * 
     * @param stWarn 预警信息
     * @return 预警信息
     */
    @Override
    public List<StWarn> selectStWarnList(StWarn stWarn)
    {
        return stWarnMapper.selectStWarnList(stWarn);
    }

    /**
     * 新增预警信息
     * 
     * @param stWarn 预警信息
     * @return 结果
     */
    @Override
    public int insertStWarn(StWarn stWarn)
    {
        return stWarnMapper.insertStWarn(stWarn);
    }

    /**
     * 修改预警信息
     * 
     * @param stWarn 预警信息
     * @return 结果
     */
    @Override
    public int updateStWarn(StWarn stWarn)
    {
        return stWarnMapper.updateStWarn(stWarn);
    }

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的预警信息主键
     * @return 结果
     */
    @Override
    public int deleteStWarnByIds(Integer[] ids)
    {
        return stWarnMapper.deleteStWarnByIds(ids);
    }

    /**
     * 删除预警信息信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    @Override
    public int deleteStWarnById(Long id)
    {
        return stWarnMapper.deleteStWarnById(id);
    }
}
