package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdBase;

/**
 * 基础数据Service接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface IGdBaseService 
{
    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    public GdBase selectGdBaseById(Long id);

    /**
     * 查询基础数据列表
     * 
     * @param gdBase 基础数据
     * @return 基础数据集合
     */
    public List<GdBase> selectGdBaseList(GdBase gdBase);

    /**
     * 新增基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    public int insertGdBase(GdBase gdBase);

    /**
     * 修改基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    public int updateGdBase(GdBase gdBase);

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的基础数据主键集合
     * @return 结果
     */
    public int deleteGdBaseByIds(Long[] ids);

    /**
     * 删除基础数据信息
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    public int deleteGdBaseById(Long id);
}
