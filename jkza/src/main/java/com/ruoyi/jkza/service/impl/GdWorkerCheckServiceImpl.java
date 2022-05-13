package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdWorkerCheckMapper;
import com.ruoyi.jkza.domain.GdWorkerCheck;
import com.ruoyi.jkza.service.IGdWorkerCheckService;

/**
 * 考勤信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class GdWorkerCheckServiceImpl implements IGdWorkerCheckService 
{
    @Autowired
    private GdWorkerCheckMapper gdWorkerCheckMapper;

    /**
     * 查询考勤信息
     * 
     * @param id 考勤信息主键
     * @return 考勤信息
     */
    @Override
    public GdWorkerCheck selectGdWorkerCheckById(Long id)
    {
        return gdWorkerCheckMapper.selectGdWorkerCheckById(id);
    }

    /**
     * 查询考勤信息列表
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 考勤信息
     */
    @Override
    public List<GdWorkerCheck> selectGdWorkerCheckList(GdWorkerCheck gdWorkerCheck)
    {
        return gdWorkerCheckMapper.selectGdWorkerCheckList(gdWorkerCheck);
    }

    /**
     * 新增考勤信息
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 结果
     */
    @Override
    public int insertGdWorkerCheck(GdWorkerCheck gdWorkerCheck)
    {
        return gdWorkerCheckMapper.insertGdWorkerCheck(gdWorkerCheck);
    }

    /**
     * 修改考勤信息
     * 
     * @param gdWorkerCheck 考勤信息
     * @return 结果
     */
    @Override
    public int updateGdWorkerCheck(GdWorkerCheck gdWorkerCheck)
    {
        return gdWorkerCheckMapper.updateGdWorkerCheck(gdWorkerCheck);
    }

    /**
     * 批量删除考勤信息
     * 
     * @param ids 需要删除的考勤信息主键
     * @return 结果
     */
    @Override
    public int deleteGdWorkerCheckByIds(Long[] ids)
    {
        return gdWorkerCheckMapper.deleteGdWorkerCheckByIds(ids);
    }

    /**
     * 删除考勤信息信息
     * 
     * @param id 考勤信息主键
     * @return 结果
     */
    @Override
    public int deleteGdWorkerCheckById(Long id)
    {
        return gdWorkerCheckMapper.deleteGdWorkerCheckById(id);
    }
}
