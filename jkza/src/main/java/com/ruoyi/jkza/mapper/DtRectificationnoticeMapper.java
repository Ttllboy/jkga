package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.DtRectificationnotice;

/**
 * 整改信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public interface DtRectificationnoticeMapper 
{
    /**
     * 查询整改信息
     * 
     * @param projectguid 整改信息主键
     * @return 整改信息
     */
    public DtRectificationnotice selectDtRectificationnoticeByProjectguid(String projectguid);

    /**
     * 查询整改信息列表
     * 
     * @param dtRectificationnotice 整改信息
     * @return 整改信息集合
     */
    public List<DtRectificationnotice> selectDtRectificationnoticeList(DtRectificationnotice dtRectificationnotice);
    public List<DtRectificationnotice> selectList(DtRectificationnotice dtRectificationnotice);

    /**
     * 新增整改信息
     * 
     * @param dtRectificationnotice 整改信息
     * @return 结果
     */
    public int insertDtRectificationnotice(DtRectificationnotice dtRectificationnotice);

    /**
     * 修改整改信息
     * 
     * @param dtRectificationnotice 整改信息
     * @return 结果
     */
    public int updateDtRectificationnotice(DtRectificationnotice dtRectificationnotice);

    /**
     * 删除整改信息
     * 
     * @param projectguid 整改信息主键
     * @return 结果
     */
    public int deleteDtRectificationnoticeByProjectguid(String projectguid);

    /**
     * 批量删除整改信息
     * 
     * @param projectguids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDtRectificationnoticeByProjectguids(String[] projectguids);
}
