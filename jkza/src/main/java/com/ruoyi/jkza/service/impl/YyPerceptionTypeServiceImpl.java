package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyPerceptionTypeMapper;
import com.ruoyi.jkza.domain.YyPerceptionType;
import com.ruoyi.jkza.service.IYyPerceptionTypeService;

/**
 * 感知筛选Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-01
 */
@Service
public class YyPerceptionTypeServiceImpl implements IYyPerceptionTypeService 
{
    @Autowired
    private YyPerceptionTypeMapper yyPerceptionTypeMapper;

    /**
     * 查询感知筛选
     * 
     * @param id 感知筛选主键
     * @return 感知筛选
     */
    @Override
    public YyPerceptionType selectYyPerceptionTypeById(Long id)
    {
        return yyPerceptionTypeMapper.selectYyPerceptionTypeById(id);
    }

    /**
     * 查询感知筛选列表
     * 
     * @param yyPerceptionType 感知筛选
     * @return 感知筛选
     */
    @Override
    public List<YyPerceptionType> selectYyPerceptionTypeList(YyPerceptionType yyPerceptionType)
    {
        return yyPerceptionTypeMapper.selectYyPerceptionTypeList(yyPerceptionType);
    }

    /**
     * 新增感知筛选
     * 
     * @param yyPerceptionType 感知筛选
     * @return 结果
     */
    @Override
    public int insertYyPerceptionType(YyPerceptionType yyPerceptionType)
    {
        return yyPerceptionTypeMapper.insertYyPerceptionType(yyPerceptionType);
    }

    /**
     * 修改感知筛选
     * 
     * @param yyPerceptionType 感知筛选
     * @return 结果
     */
    @Override
    public int updateYyPerceptionType(YyPerceptionType yyPerceptionType)
    {
        return yyPerceptionTypeMapper.updateYyPerceptionType(yyPerceptionType);
    }

    /**
     * 批量删除感知筛选
     * 
     * @param ids 需要删除的感知筛选主键
     * @return 结果
     */
    @Override
    public int deleteYyPerceptionTypeByIds(Long[] ids)
    {
        return yyPerceptionTypeMapper.deleteYyPerceptionTypeByIds(ids);
    }

    /**
     * 删除感知筛选信息
     * 
     * @param id 感知筛选主键
     * @return 结果
     */
    @Override
    public int deleteYyPerceptionTypeById(Long id)
    {
        return yyPerceptionTypeMapper.deleteYyPerceptionTypeById(id);
    }
}
