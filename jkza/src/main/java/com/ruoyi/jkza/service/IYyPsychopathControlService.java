package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyPsychopathControl;

/**
 * 病人管控Service接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface IYyPsychopathControlService 
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
     * 批量删除病人管控
     * 
     * @param ids 需要删除的病人管控主键集合
     * @return 结果
     */
    public int deleteYyPsychopathControlByIds(Long[] ids);

    /**
     * 删除病人管控信息
     * 
     * @param id 病人管控主键
     * @return 结果
     */
    public int deleteYyPsychopathControlById(Long id);
}
