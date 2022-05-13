package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyPerception;

/**
 * 感知信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyPerceptionMapper 
{
    /**
     * 查询感知信息
     * 
     * @param id 感知信息主键
     * @return 感知信息
     */
    public YyPerception selectYyPerceptionById(Long id);

    /**
     * 查询感知信息列表
     * 
     * @param yyPerception 感知信息
     * @return 感知信息集合
     */
    public List<YyPerception> selectYyPerceptionList(YyPerception yyPerception);

    /**
     * 新增感知信息
     * 
     * @param yyPerception 感知信息
     * @return 结果
     */
    public int insertYyPerception(YyPerception yyPerception);

    /**
     * 修改感知信息
     * 
     * @param yyPerception 感知信息
     * @return 结果
     */
    public int updateYyPerception(YyPerception yyPerception);

    /**
     * 删除感知信息
     * 
     * @param id 感知信息主键
     * @return 结果
     */
    public int deleteYyPerceptionById(Long id);

    /**
     * 批量删除感知信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyPerceptionByIds(Long[] ids);
}
