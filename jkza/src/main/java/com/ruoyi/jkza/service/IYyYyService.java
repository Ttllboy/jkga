package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyYy;

/**
 * 医院信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IYyYyService 
{
    /**
     * 查询医院信息
     * 
     * @param id 医院信息主键
     * @return 医院信息
     */
    public YyYy selectYyYyById(Long id);

    /**
     * 查询医院信息列表
     * 
     * @param yyYy 医院信息
     * @return 医院信息集合
     */
    public List<YyYy> selectYyYyList(YyYy yyYy);

    /**
     * 新增医院信息
     * 
     * @param yyYy 医院信息
     * @return 结果
     */
    public int insertYyYy(YyYy yyYy);

    /**
     * 修改医院信息
     * 
     * @param yyYy 医院信息
     * @return 结果
     */
    public int updateYyYy(YyYy yyYy);

    /**
     * 批量删除医院信息
     * 
     * @param ids 需要删除的医院信息主键集合
     * @return 结果
     */
    public int deleteYyYyByIds(Integer[] ids);

    /**
     * 删除医院信息信息
     * 
     * @param id 医院信息主键
     * @return 结果
     */
    public int deleteYyYyById(Long id);
}
