package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjWarnMapper;
import com.ruoyi.jkza.domain.YjWarn;
import com.ruoyi.jkza.service.IYjWarnService;

/**
 * 预警信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-18
 */
@Service
public class YjWarnServiceImpl implements IYjWarnService 
{
    @Autowired
    private YjWarnMapper yjWarnMapper;

    /**
     * 查询预警信息
     * 
     * @param id 预警信息主键
     * @return 预警信息
     */
    @Override
    public YjWarn selectYjWarnById(Long id)
    {
        return yjWarnMapper.selectYjWarnById(id);
    }

    /**
     * 查询预警信息列表
     * 
     * @param yjWarn 预警信息
     * @return 预警信息
     */
    @Override
    public List<YjWarn> selectYjWarnList(YjWarn yjWarn)
    {
        return yjWarnMapper.selectYjWarnList(yjWarn);
    }

    /**
     * 新增预警信息
     * 
     * @param yjWarn 预警信息
     * @return 结果
     */
    @Override
    public int insertYjWarn(YjWarn yjWarn)
    {
        return yjWarnMapper.insertYjWarn(yjWarn);
    }

    /**
     * 修改预警信息
     * 
     * @param yjWarn 预警信息
     * @return 结果
     */
    @Override
    public int updateYjWarn(YjWarn yjWarn)
    {
        return yjWarnMapper.updateYjWarn(yjWarn);
    }

    /**
     * 批量删除预警信息
     * 
     * @param ids 需要删除的预警信息主键
     * @return 结果
     */
    @Override
    public int deleteYjWarnByIds(Long[] ids)
    {
        return yjWarnMapper.deleteYjWarnByIds(ids);
    }

    /**
     * 删除预警信息信息
     * 
     * @param id 预警信息主键
     * @return 结果
     */
    @Override
    public int deleteYjWarnById(Long id)
    {
        return yjWarnMapper.deleteYjWarnById(id);
    }
}
