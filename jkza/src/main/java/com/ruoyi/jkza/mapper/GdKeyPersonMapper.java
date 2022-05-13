package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdKeyPerson;

/**
 * 重点人员预警Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface GdKeyPersonMapper 
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
     * 删除重点人员预警
     * 
     * @param id 重点人员预警主键
     * @return 结果
     */
    public int deleteGdKeyPersonById(Long id);

    /**
     * 批量删除重点人员预警
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdKeyPersonByIds(Long[] ids);
}
