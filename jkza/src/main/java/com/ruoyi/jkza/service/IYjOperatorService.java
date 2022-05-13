package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjOperator;

/**
 * 值班人员Service接口
 * 
 * @author ruoyi
 * @date 2022-04-26
 */
public interface IYjOperatorService 
{
    /**
     * 查询值班人员
     * 
     * @param id 值班人员主键
     * @return 值班人员
     */
    public YjOperator selectYjOperatorById(Long id);

    /**
     * 查询值班人员列表
     * 
     * @param yjOperator 值班人员
     * @return 值班人员集合
     */
    public List<YjOperator> selectYjOperatorList(YjOperator yjOperator);

    /**
     * 新增值班人员
     * 
     * @param yjOperator 值班人员
     * @return 结果
     */
    public int insertYjOperator(YjOperator yjOperator);

    /**
     * 修改值班人员
     * 
     * @param yjOperator 值班人员
     * @return 结果
     */
    public int updateYjOperator(YjOperator yjOperator);

    /**
     * 批量删除值班人员
     * 
     * @param ids 需要删除的值班人员主键集合
     * @return 结果
     */
    public int deleteYjOperatorByIds(Long[] ids);

    /**
     * 删除值班人员信息
     * 
     * @param id 值班人员主键
     * @return 结果
     */
    public int deleteYjOperatorById(Long id);
}
