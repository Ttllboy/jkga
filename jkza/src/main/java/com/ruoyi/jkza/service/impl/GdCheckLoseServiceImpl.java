package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdCheckLoseMapper;
import com.ruoyi.jkza.domain.GdCheckLose;
import com.ruoyi.jkza.service.IGdCheckLoseService;

/**
 * 考勤缺失Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class GdCheckLoseServiceImpl implements IGdCheckLoseService 
{
    @Autowired
    private GdCheckLoseMapper gdCheckLoseMapper;

    /**
     * 查询考勤缺失
     * 
     * @param id 考勤缺失主键
     * @return 考勤缺失
     */
    @Override
    public GdCheckLose selectGdCheckLoseById(Long id)
    {
        return gdCheckLoseMapper.selectGdCheckLoseById(id);
    }

    /**
     * 查询考勤缺失列表
     * 
     * @param gdCheckLose 考勤缺失
     * @return 考勤缺失
     */
    @Override
    public List<GdCheckLose> selectGdCheckLoseList(GdCheckLose gdCheckLose)
    {
        return gdCheckLoseMapper.selectGdCheckLoseList(gdCheckLose);
    }

    /**
     * 新增考勤缺失
     * 
     * @param gdCheckLose 考勤缺失
     * @return 结果
     */
    @Override
    public int insertGdCheckLose(GdCheckLose gdCheckLose)
    {
        return gdCheckLoseMapper.insertGdCheckLose(gdCheckLose);
    }

    /**
     * 修改考勤缺失
     * 
     * @param gdCheckLose 考勤缺失
     * @return 结果
     */
    @Override
    public int updateGdCheckLose(GdCheckLose gdCheckLose)
    {
        return gdCheckLoseMapper.updateGdCheckLose(gdCheckLose);
    }

    /**
     * 批量删除考勤缺失
     * 
     * @param ids 需要删除的考勤缺失主键
     * @return 结果
     */
    @Override
    public int deleteGdCheckLoseByIds(Long[] ids)
    {
        return gdCheckLoseMapper.deleteGdCheckLoseByIds(ids);
    }

    /**
     * 删除考勤缺失信息
     * 
     * @param id 考勤缺失主键
     * @return 结果
     */
    @Override
    public int deleteGdCheckLoseById(Long id)
    {
        return gdCheckLoseMapper.deleteGdCheckLoseById(id);
    }
}
