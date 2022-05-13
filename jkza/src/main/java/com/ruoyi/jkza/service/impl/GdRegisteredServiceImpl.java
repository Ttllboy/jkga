package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdRegisteredMapper;
import com.ruoyi.jkza.domain.GdRegistered;
import com.ruoyi.jkza.service.IGdRegisteredService;

/**
 * 登记信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class GdRegisteredServiceImpl implements IGdRegisteredService 
{
    @Autowired
    private GdRegisteredMapper gdRegisteredMapper;

    /**
     * 查询登记信息
     * 
     * @param id 登记信息主键
     * @return 登记信息
     */
    @Override
    public GdRegistered selectGdRegisteredById(Long id)
    {
        return gdRegisteredMapper.selectGdRegisteredById(id);
    }

    /**
     * 查询登记信息列表
     * 
     * @param gdRegistered 登记信息
     * @return 登记信息
     */
    @Override
    public List<GdRegistered> selectGdRegisteredList(GdRegistered gdRegistered)
    {
        return gdRegisteredMapper.selectGdRegisteredList(gdRegistered);
    }

    /**
     * 新增登记信息
     * 
     * @param gdRegistered 登记信息
     * @return 结果
     */
    @Override
    public int insertGdRegistered(GdRegistered gdRegistered)
    {
        return gdRegisteredMapper.insertGdRegistered(gdRegistered);
    }

    /**
     * 修改登记信息
     * 
     * @param gdRegistered 登记信息
     * @return 结果
     */
    @Override
    public int updateGdRegistered(GdRegistered gdRegistered)
    {
        return gdRegisteredMapper.updateGdRegistered(gdRegistered);
    }

    /**
     * 批量删除登记信息
     * 
     * @param ids 需要删除的登记信息主键
     * @return 结果
     */
    @Override
    public int deleteGdRegisteredByIds(Long[] ids)
    {
        return gdRegisteredMapper.deleteGdRegisteredByIds(ids);
    }

    /**
     * 删除登记信息信息
     * 
     * @param id 登记信息主键
     * @return 结果
     */
    @Override
    public int deleteGdRegisteredById(Long id)
    {
        return gdRegisteredMapper.deleteGdRegisteredById(id);
    }
}
