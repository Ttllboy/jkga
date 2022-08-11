package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjTqyjId;

/**
 * 天气预警Service接口
 * 
 * @author ruoyi
 * @date 2022-08-05
 */
public interface IYjTqyjIdService 
{
    /**
     * 查询天气预警
     * 
     * @param id 天气预警主键
     * @return 天气预警
     */
    public YjTqyjId selectYjTqyjIdById(Long id);

    /**
     * 查询天气预警列表
     * 
     * @param yjTqyjId 天气预警
     * @return 天气预警集合
     */
    public List<YjTqyjId> selectYjTqyjIdList(YjTqyjId yjTqyjId);

    /**
     * 新增天气预警
     * 
     * @param yjTqyjId 天气预警
     * @return 结果
     */
    public int insertYjTqyjId(YjTqyjId yjTqyjId);

    /**
     * 修改天气预警
     * 
     * @param yjTqyjId 天气预警
     * @return 结果
     */
    public int updateYjTqyjId(YjTqyjId yjTqyjId);

    /**
     * 批量删除天气预警
     * 
     * @param ids 需要删除的天气预警主键集合
     * @return 结果
     */
    public int deleteYjTqyjIdByIds(Long[] ids);

    /**
     * 删除天气预警信息
     * 
     * @param id 天气预警主键
     * @return 结果
     */
    public int deleteYjTqyjIdById(Long id);
}
