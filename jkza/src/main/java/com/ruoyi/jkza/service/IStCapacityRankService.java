package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StCapacityRank;

/**
 * 容量排名Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStCapacityRankService 
{
    /**
     * 查询容量排名
     * 
     * @param id 容量排名主键
     * @return 容量排名
     */
    public StCapacityRank selectStCapacityRankById(Long id);

    /**
     * 查询容量排名列表
     * 
     * @param stCapacityRank 容量排名
     * @return 容量排名集合
     */
    public List<StCapacityRank> selectStCapacityRankList(StCapacityRank stCapacityRank);

    /**
     * 新增容量排名
     * 
     * @param stCapacityRank 容量排名
     * @return 结果
     */
    public int insertStCapacityRank(StCapacityRank stCapacityRank);

    /**
     * 修改容量排名
     * 
     * @param stCapacityRank 容量排名
     * @return 结果
     */
    public int updateStCapacityRank(StCapacityRank stCapacityRank);

    /**
     * 批量删除容量排名
     * 
     * @param ids 需要删除的容量排名主键集合
     * @return 结果
     */
    public int deleteStCapacityRankByIds(Long[] ids);

    /**
     * 删除容量排名信息
     * 
     * @param id 容量排名主键
     * @return 结果
     */
    public int deleteStCapacityRankById(Long id);
}
