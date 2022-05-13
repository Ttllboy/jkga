package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdRegistered;

/**
 * 登记信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface GdRegisteredMapper 
{
    /**
     * 查询登记信息
     * 
     * @param id 登记信息主键
     * @return 登记信息
     */
    public GdRegistered selectGdRegisteredById(Long id);

    /**
     * 查询登记信息列表
     * 
     * @param gdRegistered 登记信息
     * @return 登记信息集合
     */
    public List<GdRegistered> selectGdRegisteredList(GdRegistered gdRegistered);

    /**
     * 新增登记信息
     * 
     * @param gdRegistered 登记信息
     * @return 结果
     */
    public int insertGdRegistered(GdRegistered gdRegistered);

    /**
     * 修改登记信息
     * 
     * @param gdRegistered 登记信息
     * @return 结果
     */
    public int updateGdRegistered(GdRegistered gdRegistered);

    /**
     * 删除登记信息
     * 
     * @param id 登记信息主键
     * @return 结果
     */
    public int deleteGdRegisteredById(Long id);

    /**
     * 批量删除登记信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdRegisteredByIds(Long[] ids);
}
