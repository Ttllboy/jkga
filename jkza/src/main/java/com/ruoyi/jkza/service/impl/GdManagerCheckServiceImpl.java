package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdManagerCheckMapper;
import com.ruoyi.jkza.domain.GdManagerCheck;
import com.ruoyi.jkza.service.IGdManagerCheckService;

/**
 * 管理人员考勤Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@Service
public class GdManagerCheckServiceImpl implements IGdManagerCheckService 
{
    @Autowired
    private GdManagerCheckMapper gdManagerCheckMapper;

    /**
     * 查询管理人员考勤
     * 
     * @param id 管理人员考勤主键
     * @return 管理人员考勤
     */
    @Override
    public GdManagerCheck selectGdManagerCheckById(Long id)
    {
        return gdManagerCheckMapper.selectGdManagerCheckById(id);
    }

    /**
     * 查询管理人员考勤列表
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 管理人员考勤
     */
    @Override
    public List<GdManagerCheck> selectGdManagerCheckList(GdManagerCheck gdManagerCheck)
    {
        return gdManagerCheckMapper.selectGdManagerCheckList(gdManagerCheck);
    }

    /**
     * 新增管理人员考勤
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 结果
     */
    @Override
    public int insertGdManagerCheck(GdManagerCheck gdManagerCheck)
    {
        return gdManagerCheckMapper.insertGdManagerCheck(gdManagerCheck);
    }

    /**
     * 修改管理人员考勤
     * 
     * @param gdManagerCheck 管理人员考勤
     * @return 结果
     */
    @Override
    public int updateGdManagerCheck(GdManagerCheck gdManagerCheck)
    {
        return gdManagerCheckMapper.updateGdManagerCheck(gdManagerCheck);
    }

    /**
     * 批量删除管理人员考勤
     * 
     * @param ids 需要删除的管理人员考勤主键
     * @return 结果
     */
    @Override
    public int deleteGdManagerCheckByIds(Long[] ids)
    {
        return gdManagerCheckMapper.deleteGdManagerCheckByIds(ids);
    }

    /**
     * 删除管理人员考勤信息
     * 
     * @param id 管理人员考勤主键
     * @return 结果
     */
    @Override
    public int deleteGdManagerCheckById(Long id)
    {
        return gdManagerCheckMapper.deleteGdManagerCheckById(id);
    }
}
