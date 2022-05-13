package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.GdWorkerMapper;
import com.ruoyi.jkza.domain.GdWorker;
import com.ruoyi.jkza.service.IGdWorkerService;

/**
 * 工人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
@Service
public class GdWorkerServiceImpl implements IGdWorkerService 
{
    @Autowired
    private GdWorkerMapper gdWorkerMapper;

    /**
     * 查询工人信息
     * 
     * @param id 工人信息主键
     * @return 工人信息
     */
    @Override
    public GdWorker selectGdWorkerById(Long id)
    {
        return gdWorkerMapper.selectGdWorkerById(id);
    }

    /**
     * 查询工人信息列表
     * 
     * @param gdWorker 工人信息
     * @return 工人信息
     */
    @Override
    public List<GdWorker> selectGdWorkerList(GdWorker gdWorker)
    {
        return gdWorkerMapper.selectGdWorkerList(gdWorker);
    }

    /**
     * 新增工人信息
     * 
     * @param gdWorker 工人信息
     * @return 结果
     */
    @Override
    public int insertGdWorker(GdWorker gdWorker)
    {
        return gdWorkerMapper.insertGdWorker(gdWorker);
    }

    /**
     * 修改工人信息
     * 
     * @param gdWorker 工人信息
     * @return 结果
     */
    @Override
    public int updateGdWorker(GdWorker gdWorker)
    {
        return gdWorkerMapper.updateGdWorker(gdWorker);
    }

    /**
     * 批量删除工人信息
     * 
     * @param ids 需要删除的工人信息主键
     * @return 结果
     */
    @Override
    public int deleteGdWorkerByIds(Long[] ids)
    {
        return gdWorkerMapper.deleteGdWorkerByIds(ids);
    }

    /**
     * 删除工人信息信息
     * 
     * @param id 工人信息主键
     * @return 结果
     */
    @Override
    public int deleteGdWorkerById(Long id)
    {
        return gdWorkerMapper.deleteGdWorkerById(id);
    }
}
