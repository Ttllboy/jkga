package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.LtdqExcessivestatisticaldata;

/**
 * 扬尘数据Service接口
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public interface ILtdqExcessivestatisticaldataService 
{
    /**
     * 查询扬尘数据
     * 
     * @param projectguid 扬尘数据主键
     * @return 扬尘数据
     */
    public LtdqExcessivestatisticaldata selectLtdqExcessivestatisticaldataByProjectguid(String projectguid);

    /**
     * 查询扬尘数据列表
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 扬尘数据集合
     */
    public List<LtdqExcessivestatisticaldata> selectLtdqExcessivestatisticaldataList(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata);

    /**
     * 新增扬尘数据
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 结果
     */
    public int insertLtdqExcessivestatisticaldata(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata);

    /**
     * 修改扬尘数据
     * 
     * @param ltdqExcessivestatisticaldata 扬尘数据
     * @return 结果
     */
    public int updateLtdqExcessivestatisticaldata(LtdqExcessivestatisticaldata ltdqExcessivestatisticaldata);

    /**
     * 批量删除扬尘数据
     * 
     * @param projectguids 需要删除的扬尘数据主键集合
     * @return 结果
     */
    public int deleteLtdqExcessivestatisticaldataByProjectguids(String[] projectguids);

    /**
     * 删除扬尘数据信息
     * 
     * @param projectguid 扬尘数据主键
     * @return 结果
     */
    public int deleteLtdqExcessivestatisticaldataByProjectguid(String projectguid);
}
