package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YjZbdr;
import com.ruoyi.system.domain.SysUserRole;

/**
 * 值班导入Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-25
 */
public interface YjZbdrMapper 
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
     * 删除值班导入
     * 
     * @param id 值班导入主键
     * @return 结果
     */
    public int deleteYjZbdrById(Long id);

    /**
     * 批量删除值班导入
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjZbdrByIds(Long[] ids);

    public int batchYjZbdr(List<YjZbdr> list);
}
