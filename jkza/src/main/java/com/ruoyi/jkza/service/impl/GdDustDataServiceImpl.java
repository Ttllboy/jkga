package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdDustDataMapper;
import com.ruoyi.jkza.domain.GdDustData;
import com.ruoyi.jkza.service.IGdDustDataService;

/**
 * 扬尘设备数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
@Service
public class GdDustDataServiceImpl implements IGdDustDataService 
{
    @Autowired
    private GdDustDataMapper gdDustDataMapper;

    /**
     * 查询扬尘设备数据
     * 
     * @param id 扬尘设备数据主键
     * @return 扬尘设备数据
     */
    @Override
    public GdDustData selectGdDustDataById(Long id)
    {
        return gdDustDataMapper.selectGdDustDataById(id);
    }

    /**
     * 查询扬尘设备数据列表
     * 
     * @param gdDustData 扬尘设备数据
     * @return 扬尘设备数据
     */
    @Override
    public List<GdDustData> selectGdDustDataList(GdDustData gdDustData)
    {
        return gdDustDataMapper.selectGdDustDataList(gdDustData);
    }

    /**
     * 新增扬尘设备数据
     * 
     * @param gdDustData 扬尘设备数据
     * @return 结果
     */
    @Override
    public int insertGdDustData(GdDustData gdDustData)
    {
        return gdDustDataMapper.insertGdDustData(gdDustData);
    }

    /**
     * 修改扬尘设备数据
     * 
     * @param gdDustData 扬尘设备数据
     * @return 结果
     */
    @Override
    public int updateGdDustData(GdDustData gdDustData)
    {
        return gdDustDataMapper.updateGdDustData(gdDustData);
    }

    /**
     * 批量删除扬尘设备数据
     * 
     * @param ids 需要删除的扬尘设备数据主键
     * @return 结果
     */
    @Override
    public int deleteGdDustDataByIds(Long[] ids)
    {
        return gdDustDataMapper.deleteGdDustDataByIds(ids);
    }

    /**
     * 删除扬尘设备数据信息
     * 
     * @param id 扬尘设备数据主键
     * @return 结果
     */
    @Override
    public int deleteGdDustDataById(Long id)
    {
        return gdDustDataMapper.deleteGdDustDataById(id);
    }
}
