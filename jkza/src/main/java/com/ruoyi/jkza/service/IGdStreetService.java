package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.GdStreet;

/**
 * 街道信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface IGdStreetService 
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
     * 批量删除街道信息
     * 
     * @param ids 需要删除的街道信息主键集合
     * @return 结果
     */
    public int deleteGdStreetByIds(Long[] ids);

    /**
     * 删除街道信息信息
     * 
     * @param id 街道信息主键
     * @return 结果
     */
    public int deleteGdStreetById(Long id);

    public int selectGdStreetByName(String name);

}
