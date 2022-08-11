package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyPsychopathControl;

/**
 * 病人管控Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface YyPsychopathControlMapper 
{
    /**
     * 查询病人管控
     * 
     * @param id 病人管控主键
     * @return 病人管控
     */
    public YyPsychopathControl selectYyPsychopathControlById(Long id);

    /**
     * 查询病人管控列表
     * 
     * @param yyPsychopathControl 病人管控
     * @return 病人管控集合
     */
    public List<YyPsychopathControl> selectYyPsychopathControlList(YyPsychopathControl yyPsychopathControl);
    public List<YyPsychopathControl> selectTotal(YyPsychopathControl yyPsychopathControl);

    /**
     * 新增病人管控
     * 
     * @param yyPsychopathControl 病人管控
     * @return 结果
     */
    public int insertYyPsychopathControl(YyPsychopathControl yyPsychopathControl);

    /**
     * 修改病人管控
     * 
     * @param yyPsychopathControl 病人管控
     * @return 结果
     */
    public int updateYyPsychopathControl(YyPsychopathControl yyPsychopathControl);

    /**
     * 删除病人管控
     * 
     * @param id 病人管控主键
     * @return 结果
     */
    public int deleteYyPsychopathControlById(Long id);

    /**
     * 批量删除病人管控
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyPsychopathControlByIds(Long[] ids);
}
