package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YjAvoidDisaster;

/**
 * 避灾场所Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public interface YjAvoidDisasterMapper 
{
    /**
     * 查询避灾场所
     * 
     * @param id 避灾场所主键
     * @return 避灾场所
     */
    public YjAvoidDisaster selectYjAvoidDisasterById(Long id);

    /**
     * 查询避灾场所列表
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 避灾场所集合
     */
    public List<YjAvoidDisaster> selectYjAvoidDisasterList(YjAvoidDisaster yjAvoidDisaster);

    /**
     * 新增避灾场所
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 结果
     */
    public int insertYjAvoidDisaster(YjAvoidDisaster yjAvoidDisaster);

    /**
     * 修改避灾场所
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 结果
     */
    public int updateYjAvoidDisaster(YjAvoidDisaster yjAvoidDisaster);

    /**
     * 删除避灾场所
     * 
     * @param id 避灾场所主键
     * @return 结果
     */
    public int deleteYjAvoidDisasterById(Long id);

    /**
     * 批量删除避灾场所
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjAvoidDisasterByIds(Long[] ids);
}
