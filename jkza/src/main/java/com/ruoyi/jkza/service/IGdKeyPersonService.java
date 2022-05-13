package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdKeyPerson;

/**
 * 重点人员预警Service接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface IGdKeyPersonService 
{
    /**
     * 查询重点人员预警
     * 
     * @param id 重点人员预警主键
     * @return 重点人员预警
     */
    public GdKeyPerson selectGdKeyPersonById(Long id);

    /**
     * 查询重点人员预警列表
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 重点人员预警集合
     */
    public List<GdKeyPerson> selectGdKeyPersonList(GdKeyPerson gdKeyPerson);

    /**
     * 新增重点人员预警
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 结果
     */
    public int insertGdKeyPerson(GdKeyPerson gdKeyPerson);

    /**
     * 修改重点人员预警
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 结果
     */
    public int updateGdKeyPerson(GdKeyPerson gdKeyPerson);

    /**
     * 批量删除重点人员预警
     * 
     * @param ids 需要删除的重点人员预警主键集合
     * @return 结果
     */
    public int deleteGdKeyPersonByIds(Long[] ids);

    /**
     * 删除重点人员预警信息
     * 
     * @param id 重点人员预警主键
     * @return 结果
     */
    public int deleteGdKeyPersonById(Long id);
}
