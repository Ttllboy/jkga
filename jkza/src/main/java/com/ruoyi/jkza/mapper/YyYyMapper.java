package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyYy;

/**
 * 医院信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyYyMapper 
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
     * 删除医院信息
     * 
     * @param id 医院信息主键
     * @return 结果
     */
    public int deleteYyYyById(Long id);

    /**
     * 批量删除医院信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyYyByIds(Integer[] ids);
}
