package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.StSolidWasteMapper;
import com.ruoyi.jkza.domain.StSolidWaste;
import com.ruoyi.jkza.service.IStSolidWasteService;

/**
 * 固废点Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class StSolidWasteServiceImpl implements IStSolidWasteService 
{
    @Autowired
    private StSolidWasteMapper stSolidWasteMapper;

    /**
     * 查询固废点
     * 
     * @param id 固废点主键
     * @return 固废点
     */
    @Override
    public StSolidWaste selectStSolidWasteById(Long id)
    {
        return stSolidWasteMapper.selectStSolidWasteById(id);
    }

    /**
     * 查询固废点列表
     * 
     * @param stSolidWaste 固废点
     * @return 固废点
     */
    @Override
    public List<StSolidWaste> selectStSolidWasteList(StSolidWaste stSolidWaste)
    {
        return stSolidWasteMapper.selectStSolidWasteList(stSolidWaste);
    }

    /**
     * 新增固废点
     * 
     * @param stSolidWaste 固废点
     * @return 结果
     */
    @Override
    public int insertStSolidWaste(StSolidWaste stSolidWaste)
    {
        return stSolidWasteMapper.insertStSolidWaste(stSolidWaste);
    }

    /**
     * 修改固废点
     * 
     * @param stSolidWaste 固废点
     * @return 结果
     */
    @Override
    public int updateStSolidWaste(StSolidWaste stSolidWaste)
    {
        return stSolidWasteMapper.updateStSolidWaste(stSolidWaste);
    }

    /**
     * 批量删除固废点
     * 
     * @param ids 需要删除的固废点主键
     * @return 结果
     */
    @Override
    public int deleteStSolidWasteByIds(Long[] ids)
    {
        return stSolidWasteMapper.deleteStSolidWasteByIds(ids);
    }

    /**
     * 删除固废点信息
     * 
     * @param id 固废点主键
     * @return 结果
     */
    @Override
    public int deleteStSolidWasteById(Long id)
    {
        return stSolidWasteMapper.deleteStSolidWasteById(id);
    }
}
