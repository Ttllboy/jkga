package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyWarnMapper;
import com.ruoyi.jkza.domain.YyWarn;
import com.ruoyi.jkza.service.IYyWarnService;

/**
 * 预警信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyWarnServiceImpl implements IYyWarnService 
{
    @Autowired
    private YyWarnMapper yyWarnMapper;

    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    @Override
    public YyWarn selectYyWarnById(Long id)
    {
        return yyWarnMapper.selectYyWarnById(id);
    }

    /**
     * 查询预警信息列表
     * 
     * @param yyWarn 预警信息
     * @return 预警信息
     */
    @Override
    public List<YyWarn> selectYyWarnList(YyWarn yyWarn)
    {
        return yyWarnMapper.selectYyWarnList(yyWarn);
    }

    /**
     * 新增预警信息
     * 
     * @param yyWarn 预警信息
     * @return 结果
     */
    @Override
    public int insertYyWarn(YyWarn yyWarn)
    {
        return yyWarnMapper.insertYyWarn(yyWarn);
    }

    /**
     * 修改预警信息
     * 
     * @param yyWarn 预警信息
     * @return 结果
     */
    @Override
    public int updateYyWarn(YyWarn yyWarn)
    {
        return yyWarnMapper.updateYyWarn(yyWarn);
    }

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的预警信息主键
     * @return 结果
     */
    @Override
    public int deleteYyWarnByIds(Long[] ids)
    {
        return yyWarnMapper.deleteYyWarnByIds(ids);
    }

    /**
     * 删除预警信息信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    @Override
    public int deleteYyWarnById(Long id)
    {
        return yyWarnMapper.deleteYyWarnById(id);
    }
}
