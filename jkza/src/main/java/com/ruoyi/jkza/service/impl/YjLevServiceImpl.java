package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjLevMapper;
import com.ruoyi.jkza.domain.YjLev;
import com.ruoyi.jkza.service.IYjLevService;

/**
 * 应急等级Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@Service
public class YjLevServiceImpl implements IYjLevService 
{
    @Autowired
    private YjLevMapper yjLevMapper;

    /**
     * 查询应急等级
     * 
     * @param id 应急等级主键
     * @return 应急等级
     */
    @Override
    public YjLev selectYjLevById(Long id)
    {
        return yjLevMapper.selectYjLevById(id);
    }

    /**
     * 查询应急等级列表
     * 
     * @param yjLev 应急等级
     * @return 应急等级
     */
    @Override
    public List<YjLev> selectYjLevList(YjLev yjLev)
    {
        return yjLevMapper.selectYjLevList(yjLev);
    }

    /**
     * 新增应急等级
     * 
     * @param yjLev 应急等级
     * @return 结果
     */
    @Override
    public int insertYjLev(YjLev yjLev)
    {
        return yjLevMapper.insertYjLev(yjLev);
    }

    /**
     * 修改应急等级
     * 
     * @param yjLev 应急等级
     * @return 结果
     */
    @Override
    public int updateYjLev(YjLev yjLev)
    {
        return yjLevMapper.updateYjLev(yjLev);
    }

    /**
     * 批量删除应急等级
     * 
     * @param ids 需要删除的应急等级主键
     * @return 结果
     */
    @Override
    public int deleteYjLevByIds(Long[] ids)
    {
        return yjLevMapper.deleteYjLevByIds(ids);
    }

    /**
     * 删除应急等级信息
     * 
     * @param id 应急等级主键
     * @return 结果
     */
    @Override
    public int deleteYjLevById(Long id)
    {
        return yjLevMapper.deleteYjLevById(id);
    }
}
