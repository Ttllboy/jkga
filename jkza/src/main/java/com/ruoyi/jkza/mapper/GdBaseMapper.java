package com.ruoyi.jkza.mapper;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.jkza.domain.GdBase;
import com.ruoyi.jkza.domain.YjTqyb;
import org.apache.ibatis.annotations.Param;

/**
 * 基础数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-11
 */
public interface GdBaseMapper 
{
    /**
     * 查询基础数据
     * 
     * @param id 基础数据主键
     * @return 基础数据
     */
    public GdBase selectGdBaseById(Long id);

    /**
     * 查询基础数据列表
     * 
     * @param gdBase 基础数据
     * @return 基础数据集合
     */
    public List<GdBase> selectGdBaseList(GdBase gdBase);

    /**
     * 新增基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    public int insertGdBase(GdBase gdBase);

    /**
     * 修改基础数据
     * 
     * @param gdBase 基础数据
     * @return 结果
     */
    public int updateGdBase(GdBase gdBase);

    /**
     * 删除基础数据
     * 
     * @param id 基础数据主键
     * @return 结果
     */
    public int deleteGdBaseById(Long id);
    public int truncateTabel(@Param("tabel") String tabel);
    public List<HashMap> ifGd(@Param("channelCode") String channelCode);
    public List<HashMap> selectChannelCode(@Param("deviceName") String deviceName);
    public List<HashMap> selectAdmTdcjgl();
    public int insertYjTqyb(YjTqyb yjTqyb);

    /**
     * 批量删除基础数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdBaseByIds(Long[] ids);
}
