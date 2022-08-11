package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyCwmj;

/**
 * 测温门禁Service接口
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
public interface IYyCwmjService 
{
    /**
     * 查询测温门禁
     * 
     * @param id 测温门禁主键
     * @return 测温门禁
     */
    public YyCwmj selectYyCwmjById(Long id);

    /**
     * 查询测温门禁列表
     * 
     * @param yyCwmj 测温门禁
     * @return 测温门禁集合
     */
    public List<YyCwmj> selectYyCwmjList(YyCwmj yyCwmj);

    /**
     * 新增测温门禁
     * 
     * @param yyCwmj 测温门禁
     * @return 结果
     */
    public int insertYyCwmj(YyCwmj yyCwmj);

    /**
     * 修改测温门禁
     * 
     * @param yyCwmj 测温门禁
     * @return 结果
     */
    public int updateYyCwmj(YyCwmj yyCwmj);

    /**
     * 批量删除测温门禁
     * 
     * @param ids 需要删除的测温门禁主键集合
     * @return 结果
     */
    public int deleteYyCwmjByIds(Long[] ids);

    /**
     * 删除测温门禁信息
     * 
     * @param id 测温门禁主键
     * @return 结果
     */
    public int deleteYyCwmjById(Long id);
}
