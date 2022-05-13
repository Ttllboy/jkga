package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyWarn;

/**
 * 预警信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IYyWarnService 
{
    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    public YyWarn selectYyWarnById(Long id);

    /**
     * 查询预警信息列表
     * 
     * @param yyWarn 预警信息
     * @return 预警信息集合
     */
    public List<YyWarn> selectYyWarnList(YyWarn yyWarn);

    /**
     * 新增预警信息
     * 
     * @param yyWarn 预警信息
     * @return 结果
     */
    public int insertYyWarn(YyWarn yyWarn);

    /**
     * 修改预警信息
     * 
     * @param yyWarn 预警信息
     * @return 结果
     */
    public int updateYyWarn(YyWarn yyWarn);

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的预警信息主键集合
     * @return 结果
     */
    public int deleteYyWarnByIds(Long[] ids);

    /**
     * 删除预警信息信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    public int deleteYyWarnById(Long id);
}
