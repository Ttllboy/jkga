package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyBaseMapper;
import com.ruoyi.jkza.domain.YyBase;
import com.ruoyi.jkza.service.IYyBaseService;

/**
 * 基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyBaseServiceImpl implements IYyBaseService 
{
    @Autowired
    private YyBaseMapper yyBaseMapper;

    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    @Override
    public YyBase selectYyBaseById(Long id)
    {
        return yyBaseMapper.selectYyBaseById(id);
    }

    /**
     * 查询基础数据列表
     * 
     * @param yyBase 基础数据
     * @return 基础数据
     */
    @Override
    public List<YyBase> selectYyBaseList(YyBase yyBase)
    {
        return yyBaseMapper.selectYyBaseList(yyBase);
    }

    /**
     * 新增基础数据
     * 
     * @param yyBase 基础数据
     * @return 结果
     */
    @Override
    public int insertYyBase(YyBase yyBase)
    {
        return yyBaseMapper.insertYyBase(yyBase);
    }

    /**
     * 修改基础数据
     * 
     * @param yyBase 基础数据
     * @return 结果
     */
    @Override
    public int updateYyBase(YyBase yyBase)
    {
        return yyBaseMapper.updateYyBase(yyBase);
    }

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的基础数据主键
     * @return 结果
     */
    @Override
    public int deleteYyBaseByIds(Long[] ids)
    {
        return yyBaseMapper.deleteYyBaseByIds(ids);
    }

    /**
     * 删除基础数据信息
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    @Override
    public int deleteYyBaseById(Long id)
    {
        return yyBaseMapper.deleteYyBaseById(id);
    }
}
