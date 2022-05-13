package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyBase;

/**
 * 基础数据Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IYyBaseService 
{
    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    public YyBase selectYyBaseById(Long id);

    /**
     * 查询基础数据列表
     * 
     * @param yyBase 基础数据
     * @return 基础数据集合
     */
    public List<YyBase> selectYyBaseList(YyBase yyBase);

    /**
     * 新增基础数据
     * 
     * @param yyBase 基础数据
     * @return 结果
     */
    public int insertYyBase(YyBase yyBase);

    /**
     * 修改基础数据
     * 
     * @param yyBase 基础数据
     * @return 结果
     */
    public int updateYyBase(YyBase yyBase);

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的基础数据主键集合
     * @return 结果
     */
    public int deleteYyBaseByIds(Long[] ids);

    /**
     * 删除基础数据信息
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    public int deleteYyBaseById(Long id);
}
