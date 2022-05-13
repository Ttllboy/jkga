package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.GdWorker;

/**
 * 工人信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-12
 */
public interface GdWorkerMapper 
{
    /**
     * 查询工人信息
     * 
     * @param id 工人信息主键
     * @return 工人信息
     */
    public GdWorker selectGdWorkerById(Long id);

    /**
     * 查询工人信息列表
     * 
     * @param gdWorker 工人信息
     * @return 工人信息集合
     */
    public List<GdWorker> selectGdWorkerList(GdWorker gdWorker);

    /**
     * 新增工人信息
     * 
     * @param gdWorker 工人信息
     * @return 结果
     */
    public int insertGdWorker(GdWorker gdWorker);

    /**
     * 修改工人信息
     * 
     * @param gdWorker 工人信息
     * @return 结果
     */
    public int updateGdWorker(GdWorker gdWorker);

    /**
     * 删除工人信息
     * 
     * @param id 工人信息主键
     * @return 结果
     */
    public int deleteGdWorkerById(Long id);

    /**
     * 批量删除工人信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGdWorkerByIds(Long[] ids);
}
