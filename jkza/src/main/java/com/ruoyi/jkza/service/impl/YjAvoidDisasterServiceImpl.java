package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjAvoidDisasterMapper;
import com.ruoyi.jkza.domain.YjAvoidDisaster;
import com.ruoyi.jkza.service.IYjAvoidDisasterService;

/**
 * 避灾场所Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
@Service
public class YjAvoidDisasterServiceImpl implements IYjAvoidDisasterService 
{
    @Autowired
    private YjAvoidDisasterMapper yjAvoidDisasterMapper;

    /**
     * 查询避灾场所
     * 
     * @param id 避灾场所主键
     * @return 避灾场所
     */
    @Override
    public YjAvoidDisaster selectYjAvoidDisasterById(Long id)
    {
        return yjAvoidDisasterMapper.selectYjAvoidDisasterById(id);
    }

    /**
     * 查询避灾场所列表
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 避灾场所
     */
    @Override
    public List<YjAvoidDisaster> selectYjAvoidDisasterList(YjAvoidDisaster yjAvoidDisaster)
    {
        return yjAvoidDisasterMapper.selectYjAvoidDisasterList(yjAvoidDisaster);
    }

    /**
     * 新增避灾场所
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 结果
     */
    @Override
    public int insertYjAvoidDisaster(YjAvoidDisaster yjAvoidDisaster)
    {
        return yjAvoidDisasterMapper.insertYjAvoidDisaster(yjAvoidDisaster);
    }

    /**
     * 修改避灾场所
     * 
     * @param yjAvoidDisaster 避灾场所
     * @return 结果
     */
    @Override
    public int updateYjAvoidDisaster(YjAvoidDisaster yjAvoidDisaster)
    {
        return yjAvoidDisasterMapper.updateYjAvoidDisaster(yjAvoidDisaster);
    }

    /**
     * 批量删除避灾场所
     * 
     * @param ids 需要删除的避灾场所主键
     * @return 结果
     */
    @Override
    public int deleteYjAvoidDisasterByIds(Long[] ids)
    {
        return yjAvoidDisasterMapper.deleteYjAvoidDisasterByIds(ids);
    }

    /**
     * 删除避灾场所信息
     * 
     * @param id 避灾场所主键
     * @return 结果
     */
    @Override
    public int deleteYjAvoidDisasterById(Long id)
    {
        return yjAvoidDisasterMapper.deleteYjAvoidDisasterById(id);
    }
}
