package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyPerceptionMapper;
import com.ruoyi.jkza.domain.YyPerception;
import com.ruoyi.jkza.service.IYyPerceptionService;

/**
 * 感知信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyPerceptionServiceImpl implements IYyPerceptionService 
{
    @Autowired
    private YyPerceptionMapper yyPerceptionMapper;

    /**
     * 查询感知信息
     * 
     * @param id 感知信息主键
     * @return 感知信息
     */
    @Override
    public YyPerception selectYyPerceptionById(Long id)
    {
        return yyPerceptionMapper.selectYyPerceptionById(id);
    }

    /**
     * 查询感知信息列表
     * 
     * @param yyPerception 感知信息
     * @return 感知信息
     */
    @Override
    public List<YyPerception> selectYyPerceptionList(YyPerception yyPerception)
    {
        return yyPerceptionMapper.selectYyPerceptionList(yyPerception);
    }

    /**
     * 新增感知信息
     * 
     * @param yyPerception 感知信息
     * @return 结果
     */
    @Override
    public int insertYyPerception(YyPerception yyPerception)
    {
        return yyPerceptionMapper.insertYyPerception(yyPerception);
    }

    /**
     * 修改感知信息
     * 
     * @param yyPerception 感知信息
     * @return 结果
     */
    @Override
    public int updateYyPerception(YyPerception yyPerception)
    {
        return yyPerceptionMapper.updateYyPerception(yyPerception);
    }

    /**
     * 批量删除感知信息
     * 
     * @param ids 需要删除的感知信息主键
     * @return 结果
     */
    @Override
    public int deleteYyPerceptionByIds(Long[] ids)
    {
        return yyPerceptionMapper.deleteYyPerceptionByIds(ids);
    }

    /**
     * 删除感知信息信息
     * 
     * @param id 感知信息主键
     * @return 结果
     */
    @Override
    public int deleteYyPerceptionById(Long id)
    {
        return yyPerceptionMapper.deleteYyPerceptionById(id);
    }
}
