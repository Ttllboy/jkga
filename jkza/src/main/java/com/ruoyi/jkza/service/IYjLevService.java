package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjLev;

/**
 * 应急等级Service接口
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
public interface IYjLevService 
{
    /**
     * 查询应急等级
     * 
     * @param id 应急等级主键
     * @return 应急等级
     */
    public YjLev selectYjLevById(Long id);

    /**
     * 查询应急等级列表
     * 
     * @param yjLev 应急等级
     * @return 应急等级集合
     */
    public List<YjLev> selectYjLevList(YjLev yjLev);

    /**
     * 新增应急等级
     * 
     * @param yjLev 应急等级
     * @return 结果
     */
    public int insertYjLev(YjLev yjLev);

    /**
     * 修改应急等级
     * 
     * @param yjLev 应急等级
     * @return 结果
     */
    public int updateYjLev(YjLev yjLev);

    /**
     * 批量删除应急等级
     * 
     * @param ids 需要删除的应急等级主键集合
     * @return 结果
     */
    public int deleteYjLevByIds(Long[] ids);

    /**
     * 删除应急等级信息
     * 
     * @param id 应急等级主键
     * @return 结果
     */
    public int deleteYjLevById(Long id);
}
