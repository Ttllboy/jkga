package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.StSolidWaste;

/**
 * 固废点Service接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IStSolidWasteService 
{
    /**
     * 查询固废点
     * 
     * @param id 固废点主键
     * @return 固废点
     */
    public StSolidWaste selectStSolidWasteById(Long id);

    /**
     * 查询固废点列表
     * 
     * @param stSolidWaste 固废点
     * @return 固废点集合
     */
    public List<StSolidWaste> selectStSolidWasteList(StSolidWaste stSolidWaste);

    /**
     * 新增固废点
     * 
     * @param stSolidWaste 固废点
     * @return 结果
     */
    public int insertStSolidWaste(StSolidWaste stSolidWaste);

    /**
     * 修改固废点
     * 
     * @param stSolidWaste 固废点
     * @return 结果
     */
    public int updateStSolidWaste(StSolidWaste stSolidWaste);

    /**
     * 批量删除固废点
     * 
     * @param ids 需要删除的固废点主键集合
     * @return 结果
     */
    public int deleteStSolidWasteByIds(Long[] ids);

    /**
     * 删除固废点信息
     * 
     * @param id 固废点主键
     * @return 结果
     */
    public int deleteStSolidWasteById(Long id);
}
