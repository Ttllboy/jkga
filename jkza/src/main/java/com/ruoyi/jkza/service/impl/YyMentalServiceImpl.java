package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyMentalMapper;
import com.ruoyi.jkza.domain.YyMental;
import com.ruoyi.jkza.service.IYyMentalService;

/**
 * 精神病人Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyMentalServiceImpl implements IYyMentalService 
{
    @Autowired
    private YyMentalMapper yyMentalMapper;

    /**
     * 查询精神病人
     * 
     * @param id 精神病人主键
     * @return 精神病人
     */
    @Override
    public YyMental selectYyMentalById(Long id)
    {
        return yyMentalMapper.selectYyMentalById(id);
    }

    /**
     * 查询精神病人列表
     * 
     * @param yyMental 精神病人
     * @return 精神病人
     */
    @Override
    public List<YyMental> selectYyMentalList(YyMental yyMental)
    {
        return yyMentalMapper.selectYyMentalList(yyMental);
    }

    /**
     * 新增精神病人
     * 
     * @param yyMental 精神病人
     * @return 结果
     */
    @Override
    public int insertYyMental(YyMental yyMental)
    {
        return yyMentalMapper.insertYyMental(yyMental);
    }

    /**
     * 修改精神病人
     * 
     * @param yyMental 精神病人
     * @return 结果
     */
    @Override
    public int updateYyMental(YyMental yyMental)
    {
        return yyMentalMapper.updateYyMental(yyMental);
    }

    /**
     * 批量删除精神病人
     * 
     * @param ids 需要删除的精神病人主键
     * @return 结果
     */
    @Override
    public int deleteYyMentalByIds(Long[] ids)
    {
        return yyMentalMapper.deleteYyMentalByIds(ids);
    }

    /**
     * 删除精神病人信息
     * 
     * @param id 精神病人主键
     * @return 结果
     */
    @Override
    public int deleteYyMentalById(Long id)
    {
        return yyMentalMapper.deleteYyMentalById(id);
    }
}
