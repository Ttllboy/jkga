package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YjWarn;

/**
 * 预警信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
public interface YjWarnMapper 
{
    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    public YjWarn selectYjWarnById(Long id);

    /**
     * 查询预警信息列表
     * 
     * @param yjWarn 预警信息
     * @return 预警信息集合
     */
    public List<YjWarn> selectYjWarnList(YjWarn yjWarn);

    /**
     * 新增预警信息
     * 
     * @param yjWarn 预警信息
     * @return 结果
     */
    public int insertYjWarn(YjWarn yjWarn);

    /**
     * 修改预警信息
     * 
     * @param yjWarn 预警信息
     * @return 结果
     */
    public int updateYjWarn(YjWarn yjWarn);

    /**
     * 删除预警信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    public int deleteYjWarnById(Long id);

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjWarnByIds(Long[] ids);
}
