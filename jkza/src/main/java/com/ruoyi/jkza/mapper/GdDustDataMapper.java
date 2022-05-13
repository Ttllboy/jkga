package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdDustData;

/**
 * 扬尘设备数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface GdDustDataMapper 
{
    /**
     * 查询扬尘设备数据
     * 
     * @param id 扬尘设备数据主键
     * @return 扬尘设备数据
     */
    public GdDustData selectGdDustDataById(Long id);

    /**
     * 查询扬尘设备数据列表
     * 
     * @param gdDustData 扬尘设备数据
     * @return 扬尘设备数据集合
     */
    public List<GdDustData> selectGdDustDataList(GdDustData gdDustData);

    /**
     * 新增扬尘设备数据
     * 
     * @param gdDustData 扬尘设备数据
     * @return 结果
     */
    public int insertGdDustData(GdDustData gdDustData);

    /**
     * 修改扬尘设备数据
     * 
     * @param gdDustData 扬尘设备数据
     * @return 结果
     */
    public int updateGdDustData(GdDustData gdDustData);

    /**
     * 删除扬尘设备数据
     * 
     * @param id 扬尘设备数据主键
     * @return 结果
     */
    public int deleteGdDustDataById(Long id);

    /**
     * 批量删除扬尘设备数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdDustDataByIds(Long[] ids);
}
