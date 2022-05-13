package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjOperatorMapper;
import com.ruoyi.jkza.domain.YjOperator;
import com.ruoyi.jkza.service.IYjOperatorService;

/**
 * 值班人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-26
 */
@Service
public class YjOperatorServiceImpl implements IYjOperatorService 
{
    @Autowired
    private YjOperatorMapper yjOperatorMapper;

    /**
     * 查询值班人员
     * 
     * @param id 值班人员主键
     * @return 值班人员
     */
    @Override
    public YjOperator selectYjOperatorById(Long id)
    {
        return yjOperatorMapper.selectYjOperatorById(id);
    }

    /**
     * 查询值班人员列表
     * 
     * @param yjOperator 值班人员
     * @return 值班人员
     */
    @Override
    public List<YjOperator> selectYjOperatorList(YjOperator yjOperator)
    {
        return yjOperatorMapper.selectYjOperatorList(yjOperator);
    }

    /**
     * 新增值班人员
     * 
     * @param yjOperator 值班人员
     * @return 结果
     */
    @Override
    public int insertYjOperator(YjOperator yjOperator)
    {
        return yjOperatorMapper.insertYjOperator(yjOperator);
    }

    /**
     * 修改值班人员
     * 
     * @param yjOperator 值班人员
     * @return 结果
     */
    @Override
    public int updateYjOperator(YjOperator yjOperator)
    {
        return yjOperatorMapper.updateYjOperator(yjOperator);
    }

    /**
     * 批量删除值班人员
     * 
     * @param ids 需要删除的值班人员主键
     * @return 结果
     */
    @Override
    public int deleteYjOperatorByIds(Long[] ids)
    {
        return yjOperatorMapper.deleteYjOperatorByIds(ids);
    }

    /**
     * 删除值班人员信息
     * 
     * @param id 值班人员主键
     * @return 结果
     */
    @Override
    public int deleteYjOperatorById(Long id)
    {
        return yjOperatorMapper.deleteYjOperatorById(id);
    }
}
