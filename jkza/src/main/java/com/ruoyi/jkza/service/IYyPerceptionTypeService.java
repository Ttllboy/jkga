package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyPerceptionType;

/**
 * 感知筛选Service接口
 * 
 * @author ruoyi
 * @date 2022-08-01
 */
public interface IYyPerceptionTypeService 
{
    /**
     * 查询感知筛选
     * 
     * @param id 感知筛选主键
     * @return 感知筛选
     */
    public YyPerceptionType selectYyPerceptionTypeById(Long id);

    /**
     * 查询感知筛选列表
     * 
     * @param yyPerceptionType 感知筛选
     * @return 感知筛选集合
     */
    public List<YyPerceptionType> selectYyPerceptionTypeList(YyPerceptionType yyPerceptionType);

    /**
     * 新增感知筛选
     * 
     * @param yyPerceptionType 感知筛选
     * @return 结果
     */
    public int insertYyPerceptionType(YyPerceptionType yyPerceptionType);

    /**
     * 修改感知筛选
     * 
     * @param yyPerceptionType 感知筛选
     * @return 结果
     */
    public int updateYyPerceptionType(YyPerceptionType yyPerceptionType);

    /**
     * 批量删除感知筛选
     * 
     * @param ids 需要删除的感知筛选主键集合
     * @return 结果
     */
    public int deleteYyPerceptionTypeByIds(Long[] ids);

    /**
     * 删除感知筛选信息
     * 
     * @param id 感知筛选主键
     * @return 结果
     */
    public int deleteYyPerceptionTypeById(Long id);
}
