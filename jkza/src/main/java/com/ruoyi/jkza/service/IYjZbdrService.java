package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YjZbdr;

/**
 * 值班导入Service接口
 * 
 * @author ruoyi
 * @date 2022-05-25
 */
public interface IYjZbdrService 
{
    /**
     * 查询值班导入
     * 
     * @param id 值班导入主键
     * @return 值班导入
     */
    public YjZbdr selectYjZbdrById(Long id);

    /**
     * 查询值班导入列表
     * 
     * @param yjZbdr 值班导入
     * @return 值班导入集合
     */
    public List<YjZbdr> selectYjZbdrList(YjZbdr yjZbdr);

    /**
     * 新增值班导入
     * 
     * @param yjZbdr 值班导入
     * @return 结果
     */
    public int insertYjZbdr(YjZbdr yjZbdr);

    /**
     * 修改值班导入
     * 
     * @param yjZbdr 值班导入
     * @return 结果
     */
    public int updateYjZbdr(YjZbdr yjZbdr);

    /**
     * 批量删除值班导入
     * 
     * @param ids 需要删除的值班导入主键集合
     * @return 结果
     */
    public int deleteYjZbdrByIds(Long[] ids);

    /**
     * 删除值班导入信息
     * 
     * @param id 值班导入主键
     * @return 结果
     */
    public int deleteYjZbdrById(Long id);
}
