package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyMental;

/**
 * 精神病人Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IYyMentalService 
{
    /**
     * 查询精神病人
     * 
     * @param id 精神病人主键
     * @return 精神病人
     */
    public YyMental selectYyMentalById(Long id);

    /**
     * 查询精神病人列表
     * 
     * @param yyMental 精神病人
     * @return 精神病人集合
     */
    public List<YyMental> selectYyMentalList(YyMental yyMental);

    /**
     * 新增精神病人
     * 
     * @param yyMental 精神病人
     * @return 结果
     */
    public int insertYyMental(YyMental yyMental);

    /**
     * 修改精神病人
     * 
     * @param yyMental 精神病人
     * @return 结果
     */
    public int updateYyMental(YyMental yyMental);

    /**
     * 批量删除精神病人
     * 
     * @param ids 需要删除的精神病人主键集合
     * @return 结果
     */
    public int deleteYyMentalByIds(Long[] ids);

    /**
     * 删除精神病人信息
     * 
     * @param id 精神病人主键
     * @return 结果
     */
    public int deleteYyMentalById(Long id);
}
