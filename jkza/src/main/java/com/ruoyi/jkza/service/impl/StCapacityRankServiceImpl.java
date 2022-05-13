package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StCapacityRankMapper;
import com.ruoyi.jkza.domain.StCapacityRank;
import com.ruoyi.jkza.service.IStCapacityRankService;

/**
 * 容量排名Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StCapacityRankServiceImpl implements IStCapacityRankService 
{
    @Autowired
    private StCapacityRankMapper stCapacityRankMapper;

    /**
     * 查询容量排名
     * 
     * @param id 容量排名主键
     * @return 容量排名
     */
    @Override
    public StCapacityRank selectStCapacityRankById(Long id)
    {
        return stCapacityRankMapper.selectStCapacityRankById(id);
    }

    /**
     * 查询容量排名列表
     * 
     * @param stCapacityRank 容量排名
     * @return 容量排名
     */
    @Override
    public List<StCapacityRank> selectStCapacityRankList(StCapacityRank stCapacityRank)
    {
        return stCapacityRankMapper.selectStCapacityRankList(stCapacityRank);
    }

    /**
     * 新增容量排名
     * 
     * @param stCapacityRank 容量排名
     * @return 结果
     */
    @Override
    public int insertStCapacityRank(StCapacityRank stCapacityRank)
    {
        return stCapacityRankMapper.insertStCapacityRank(stCapacityRank);
    }

    /**
     * 修改容量排名
     * 
     * @param stCapacityRank 容量排名
     * @return 结果
     */
    @Override
    public int updateStCapacityRank(StCapacityRank stCapacityRank)
    {
        return stCapacityRankMapper.updateStCapacityRank(stCapacityRank);
    }

    /**
     * 批量删除容量排名
     * 
     * @param ids 需要删除的容量排名主键
     * @return 结果
     */
    @Override
    public int deleteStCapacityRankByIds(Long[] ids)
    {
        return stCapacityRankMapper.deleteStCapacityRankByIds(ids);
    }

    /**
     * 删除容量排名信息
     * 
     * @param id 容量排名主键
     * @return 结果
     */
    @Override
    public int deleteStCapacityRankById(Long id)
    {
        return stCapacityRankMapper.deleteStCapacityRankById(id);
    }
}
