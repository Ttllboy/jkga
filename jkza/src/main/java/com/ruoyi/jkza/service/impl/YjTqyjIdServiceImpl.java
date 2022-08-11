package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjTqyjIdMapper;
import com.ruoyi.jkza.domain.YjTqyjId;
import com.ruoyi.jkza.service.IYjTqyjIdService;

/**
 * 天气预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-05
 */
@Service
public class YjTqyjIdServiceImpl implements IYjTqyjIdService 
{
    @Autowired
    private YjTqyjIdMapper yjTqyjIdMapper;

    /**
     * 查询天气预警
     * 
     * @param id 天气预警主键
     * @return 天气预警
     */
    @Override
    public YjTqyjId selectYjTqyjIdById(Long id)
    {
        return yjTqyjIdMapper.selectYjTqyjIdById(id);
    }

    /**
     * 查询天气预警列表
     * 
     * @param yjTqyjId 天气预警
     * @return 天气预警
     */
    @Override
    public List<YjTqyjId> selectYjTqyjIdList(YjTqyjId yjTqyjId)
    {
        return yjTqyjIdMapper.selectYjTqyjIdList(yjTqyjId);
    }

    /**
     * 新增天气预警
     * 
     * @param yjTqyjId 天气预警
     * @return 结果
     */
    @Override
    public int insertYjTqyjId(YjTqyjId yjTqyjId)
    {
        return yjTqyjIdMapper.insertYjTqyjId(yjTqyjId);
    }

    /**
     * 修改天气预警
     * 
     * @param yjTqyjId 天气预警
     * @return 结果
     */
    @Override
    public int updateYjTqyjId(YjTqyjId yjTqyjId)
    {
        return yjTqyjIdMapper.updateYjTqyjId(yjTqyjId);
    }

    /**
     * 批量删除天气预警
     * 
     * @param ids 需要删除的天气预警主键
     * @return 结果
     */
    @Override
    public int deleteYjTqyjIdByIds(Long[] ids)
    {
        return yjTqyjIdMapper.deleteYjTqyjIdByIds(ids);
    }

    /**
     * 删除天气预警信息
     * 
     * @param id 天气预警主键
     * @return 结果
     */
    @Override
    public int deleteYjTqyjIdById(Long id)
    {
        return yjTqyjIdMapper.deleteYjTqyjIdById(id);
    }
}
