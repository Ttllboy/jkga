package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.StProject;

/**
 * 项目基本信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-09
 */
public interface StProjectMapper 
{
    /**
     * 查询项目基本信息
     * 
     * @param guid 项目基本信息主键
     * @return 项目基本信息
     */
    public StProject selectStProjectByGuid(String guid);

    /**
     * 查询项目基本信息列表
     * 
     * @param stProject 项目基本信息
     * @return 项目基本信息集合
     */
    public List<StProject> selectStProjectList(StProject stProject);

    /**
     * 新增项目基本信息
     * 
     * @param stProject 项目基本信息
     * @return 结果
     */
    public int insertStProject(StProject stProject);

    /**
     * 修改项目基本信息
     * 
     * @param stProject 项目基本信息
     * @return 结果
     */
    public int updateStProject(StProject stProject);

    /**
     * 删除项目基本信息
     * 
     * @param guid 项目基本信息主键
     * @return 结果
     */
    public int deleteStProjectByGuid(String guid);

    /**
     * 批量删除项目基本信息
     * 
     * @param guids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStProjectByGuids(String[] guids);
}
