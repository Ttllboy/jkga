package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.YyCar;

/**
 * 医院车辆Service接口
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public interface IYyCarService 
{
    /**
     * 查询医院车辆
     * 
     * @param id 医院车辆主键
     * @return 医院车辆
     */
    public YyCar selectYyCarById(Long id);

    /**
     * 查询医院车辆列表
     * 
     * @param yyCar 医院车辆
     * @return 医院车辆集合
     */
    public List<YyCar> selectYyCarList(YyCar yyCar);

    /**
     * 新增医院车辆
     * 
     * @param yyCar 医院车辆
     * @return 结果
     */
    public int insertYyCar(YyCar yyCar);

    /**
     * 修改医院车辆
     * 
     * @param yyCar 医院车辆
     * @return 结果
     */
    public int updateYyCar(YyCar yyCar);

    /**
     * 批量删除医院车辆
     * 
     * @param ids 需要删除的医院车辆主键集合
     * @return 结果
     */
    public int deleteYyCarByIds(Long[] ids);

    /**
     * 删除医院车辆信息
     * 
     * @param id 医院车辆主键
     * @return 结果
     */
    public int deleteYyCarById(Long id);
}
