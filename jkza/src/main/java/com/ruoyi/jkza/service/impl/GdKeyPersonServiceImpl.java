package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdKeyPersonMapper;
import com.ruoyi.jkza.domain.GdKeyPerson;
import com.ruoyi.jkza.service.IGdKeyPersonService;

/**
 * 重点人员预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@Service
public class GdKeyPersonServiceImpl implements IGdKeyPersonService 
{
    @Autowired
    private GdKeyPersonMapper gdKeyPersonMapper;

    /**
     * 查询重点人员预警
     * 
     * @param id 重点人员预警主键
     * @return 重点人员预警
     */
    @Override
    public GdKeyPerson selectGdKeyPersonById(Long id)
    {
        return gdKeyPersonMapper.selectGdKeyPersonById(id);
    }

    /**
     * 查询重点人员预警列表
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 重点人员预警
     */
    @Override
    public List<GdKeyPerson> selectGdKeyPersonList(GdKeyPerson gdKeyPerson)
    {
        return gdKeyPersonMapper.selectGdKeyPersonList(gdKeyPerson);
    }

    /**
     * 新增重点人员预警
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 结果
     */
    @Override
    public int insertGdKeyPerson(GdKeyPerson gdKeyPerson)
    {
        return gdKeyPersonMapper.insertGdKeyPerson(gdKeyPerson);
    }

    /**
     * 修改重点人员预警
     * 
     * @param gdKeyPerson 重点人员预警
     * @return 结果
     */
    @Override
    public int updateGdKeyPerson(GdKeyPerson gdKeyPerson)
    {
        return gdKeyPersonMapper.updateGdKeyPerson(gdKeyPerson);
    }

    /**
     * 批量删除重点人员预警
     * 
     * @param ids 需要删除的重点人员预警主键
     * @return 结果
     */
    @Override
    public int deleteGdKeyPersonByIds(Long[] ids)
    {
        return gdKeyPersonMapper.deleteGdKeyPersonByIds(ids);
    }

    /**
     * 删除重点人员预警信息
     * 
     * @param id 重点人员预警主键
     * @return 结果
     */
    @Override
    public int deleteGdKeyPersonById(Long id)
    {
        return gdKeyPersonMapper.deleteGdKeyPersonById(id);
    }
}
