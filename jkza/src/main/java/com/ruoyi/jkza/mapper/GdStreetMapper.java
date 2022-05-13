package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdStreet;

/**
 * 街道信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface GdStreetMapper 
{
    /**
     * 查询街道信息
     * 
     * @param id 街道信息主键
     * @return 街道信息
     */
    public GdStreet selectGdStreetById(Long id);

    /**
     * 查询街道信息列表
     * 
     * @param gdStreet 街道信息
     * @return 街道信息集合
     */
    public List<GdStreet> selectGdStreetList(GdStreet gdStreet);

    /**
     * 新增街道信息
     * 
     * @param gdStreet 街道信息
     * @return 结果
     */
    public int insertGdStreet(GdStreet gdStreet);

    /**
     * 修改街道信息
     * 
     * @param gdStreet 街道信息
     * @return 结果
     */
    public int updateGdStreet(GdStreet gdStreet);

    /**
     * 删除街道信息
     * 
     * @param id 街道信息主键
     * @return 结果
     */
    public int deleteGdStreetById(Long id);

    /**
     * 批量删除街道信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdStreetByIds(Long[] ids);
    public int selectGdStreetByName(String name);
}
