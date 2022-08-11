package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyCwmjMapper;
import com.ruoyi.jkza.domain.YyCwmj;
import com.ruoyi.jkza.service.IYyCwmjService;

/**
 * 测温门禁Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@Service
public class YyCwmjServiceImpl implements IYyCwmjService 
{
    @Autowired
    private YyCwmjMapper yyCwmjMapper;

    /**
     * 查询测温门禁
     * 
     * @param id 测温门禁主键
     * @return 测温门禁
     */
    @Override
    public YyCwmj selectYyCwmjById(Long id)
    {
        return yyCwmjMapper.selectYyCwmjById(id);
    }

    /**
     * 查询测温门禁列表
     * 
     * @param yyCwmj 测温门禁
     * @return 测温门禁
     */
    @Override
    public List<YyCwmj> selectYyCwmjList(YyCwmj yyCwmj)
    {
        return yyCwmjMapper.selectYyCwmjList(yyCwmj);
    }

    /**
     * 新增测温门禁
     * 
     * @param yyCwmj 测温门禁
     * @return 结果
     */
    @Override
    public int insertYyCwmj(YyCwmj yyCwmj)
    {
        return yyCwmjMapper.insertYyCwmj(yyCwmj);
    }

    /**
     * 修改测温门禁
     * 
     * @param yyCwmj 测温门禁
     * @return 结果
     */
    @Override
    public int updateYyCwmj(YyCwmj yyCwmj)
    {
        return yyCwmjMapper.updateYyCwmj(yyCwmj);
    }

    /**
     * 批量删除测温门禁
     * 
     * @param ids 需要删除的测温门禁主键
     * @return 结果
     */
    @Override
    public int deleteYyCwmjByIds(Long[] ids)
    {
        return yyCwmjMapper.deleteYyCwmjByIds(ids);
    }

    /**
     * 删除测温门禁信息
     * 
     * @param id 测温门禁主键
     * @return 结果
     */
    @Override
    public int deleteYyCwmjById(Long id)
    {
        return yyCwmjMapper.deleteYyCwmjById(id);
    }
}
