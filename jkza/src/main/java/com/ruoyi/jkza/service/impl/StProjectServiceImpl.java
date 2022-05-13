package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StProjectMapper;
import com.ruoyi.jkza.domain.StProject;
import com.ruoyi.jkza.service.IStProjectService;

/**
 * 项目基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-09
 */
@Service
public class StProjectServiceImpl implements IStProjectService 
{
    @Autowired
    private StProjectMapper stProjectMapper;

    /**
     * 查询项目基本信息
     * 
     * @param guid 项目基本信息主键
     * @return 项目基本信息
     */
    @Override
    public StProject selectStProjectByGuid(String guid)
    {
        return stProjectMapper.selectStProjectByGuid(guid);
    }

    /**
     * 查询项目基本信息列表
     * 
     * @param stProject 项目基本信息
     * @return 项目基本信息
     */
    @Override
    public List<StProject> selectStProjectList(StProject stProject)
    {
        return stProjectMapper.selectStProjectList(stProject);
    }

    /**
     * 新增项目基本信息
     * 
     * @param stProject 项目基本信息
     * @return 结果
     */
    @Override
    public int insertStProject(StProject stProject)
    {
        stProject.setCreateTime(DateUtils.getNowDate());
        return stProjectMapper.insertStProject(stProject);
    }

    /**
     * 修改项目基本信息
     * 
     * @param stProject 项目基本信息
     * @return 结果
     */
    @Override
    public int updateStProject(StProject stProject)
    {
        return stProjectMapper.updateStProject(stProject);
    }

    /**
     * 批量删除项目基本信息
     * 
     * @param guids 需要删除的项目基本信息主键
     * @return 结果
     */
    @Override
    public int deleteStProjectByGuids(String[] guids)
    {
        return stProjectMapper.deleteStProjectByGuids(guids);
    }

    /**
     * 删除项目基本信息信息
     * 
     * @param guid 项目基本信息主键
     * @return 结果
     */
    @Override
    public int deleteStProjectByGuid(String guid)
    {
        return stProjectMapper.deleteStProjectByGuid(guid);
    }
}
