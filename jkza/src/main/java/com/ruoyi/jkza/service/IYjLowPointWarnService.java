package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjLowPointWarn;

/**
 * 低洼点预警Service接口
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
public interface IYjLowPointWarnService 
{
    /**
     * 查询低洼点预警
     * 
     * @param id 低洼点预警主键
     * @return 低洼点预警
     */
    public YjLowPointWarn selectYjLowPointWarnById(Long id);

    /**
     * 查询低洼点预警列表
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 低洼点预警集合
     */
    public List<YjLowPointWarn> selectYjLowPointWarnList(YjLowPointWarn yjLowPointWarn);

    /**
     * 新增低洼点预警
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 结果
     */
    public int insertYjLowPointWarn(YjLowPointWarn yjLowPointWarn);

    /**
     * 修改低洼点预警
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 结果
     */
    public int updateYjLowPointWarn(YjLowPointWarn yjLowPointWarn);

    /**
     * 批量删除低洼点预警
     * 
     * @param ids 需要删除的低洼点预警主键集合
     * @return 结果
     */
    public int deleteYjLowPointWarnByIds(Long[] ids);

    /**
     * 删除低洼点预警信息
     * 
     * @param id 低洼点预警主键
     * @return 结果
     */
    public int deleteYjLowPointWarnById(Long id);
}
