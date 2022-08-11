package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjLowPointWarnMapper;
import com.ruoyi.jkza.domain.YjLowPointWarn;
import com.ruoyi.jkza.service.IYjLowPointWarnService;

/**
 * 低洼点预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-24
 */
@Service
public class YjLowPointWarnServiceImpl implements IYjLowPointWarnService 
{
    @Autowired
    private YjLowPointWarnMapper yjLowPointWarnMapper;

    /**
     * 查询低洼点预警
     * 
     * @param id 低洼点预警主键
     * @return 低洼点预警
     */
    @Override
    public YjLowPointWarn selectYjLowPointWarnById(Long id)
    {
        return yjLowPointWarnMapper.selectYjLowPointWarnById(id);
    }

    /**
     * 查询低洼点预警列表
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 低洼点预警
     */
    @Override
    public List<YjLowPointWarn> selectYjLowPointWarnList(YjLowPointWarn yjLowPointWarn)
    {
        return yjLowPointWarnMapper.selectYjLowPointWarnList(yjLowPointWarn);
    }

    /**
     * 新增低洼点预警
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 结果
     */
    @Override
    public int insertYjLowPointWarn(YjLowPointWarn yjLowPointWarn)
    {
        return yjLowPointWarnMapper.insertYjLowPointWarn(yjLowPointWarn);
    }

    /**
     * 修改低洼点预警
     * 
     * @param yjLowPointWarn 低洼点预警
     * @return 结果
     */
    @Override
    public int updateYjLowPointWarn(YjLowPointWarn yjLowPointWarn)
    {
        return yjLowPointWarnMapper.updateYjLowPointWarn(yjLowPointWarn);
    }

    /**
     * 批量删除低洼点预警
     * 
     * @param ids 需要删除的低洼点预警主键
     * @return 结果
     */
    @Override
    public int deleteYjLowPointWarnByIds(Long[] ids)
    {
        return yjLowPointWarnMapper.deleteYjLowPointWarnByIds(ids);
    }

    /**
     * 删除低洼点预警信息
     * 
     * @param id 低洼点预警主键
     * @return 结果
     */
    @Override
    public int deleteYjLowPointWarnById(Long id)
    {
        return yjLowPointWarnMapper.deleteYjLowPointWarnById(id);
    }
}
