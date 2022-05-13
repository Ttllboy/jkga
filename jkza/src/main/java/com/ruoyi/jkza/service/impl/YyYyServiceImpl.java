package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyYyMapper;
import com.ruoyi.jkza.domain.YyYy;
import com.ruoyi.jkza.service.IYyYyService;

/**
 * 医院信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyYyServiceImpl implements IYyYyService 
{
    @Autowired
    private YyYyMapper yyYyMapper;

    /**
     * 查询医院信息
     * 
     * @param id 医院信息主键
     * @return 医院信息
     */
    @Override
    public YyYy selectYyYyById(Long id)
    {
        return yyYyMapper.selectYyYyById(id);
    }

    /**
     * 查询医院信息列表
     * 
     * @param yyYy 医院信息
     * @return 医院信息
     */
    @Override
    public List<YyYy> selectYyYyList(YyYy yyYy)
    {
        return yyYyMapper.selectYyYyList(yyYy);
    }

    /**
     * 新增医院信息
     * 
     * @param yyYy 医院信息
     * @return 结果
     */
    @Override
    public int insertYyYy(YyYy yyYy)
    {
        return yyYyMapper.insertYyYy(yyYy);
    }

    /**
     * 修改医院信息
     * 
     * @param yyYy 医院信息
     * @return 结果
     */
    @Override
    public int updateYyYy(YyYy yyYy)
    {
        return yyYyMapper.updateYyYy(yyYy);
    }

    /**
     * 批量删除医院信息
     * 
     * @param ids 需要删除的医院信息主键
     * @return 结果
     */
    @Override
    public int deleteYyYyByIds(Integer[] ids)
    {
        return yyYyMapper.deleteYyYyByIds(ids);
    }

    /**
     * 删除医院信息信息
     * 
     * @param id 医院信息主键
     * @return 结果
     */
    @Override
    public int deleteYyYyById(Long id)
    {
        return yyYyMapper.deleteYyYyById(id);
    }
}
