package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyCarMapper;
import com.ruoyi.jkza.domain.YyCar;
import com.ruoyi.jkza.service.IYyCarService;

/**
 * 医院车辆Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@Service
public class YyCarServiceImpl implements IYyCarService 
{
    @Autowired
    private YyCarMapper yyCarMapper;

    /**
     * 查询医院车辆
     * 
     * @param id 医院车辆主键
     * @return 医院车辆
     */
    @Override
    public YyCar selectYyCarById(Long id)
    {
        return yyCarMapper.selectYyCarById(id);
    }

    /**
     * 查询医院车辆列表
     * 
     * @param yyCar 医院车辆
     * @return 医院车辆
     */
    @Override
    public List<YyCar> selectYyCarList(YyCar yyCar)
    {
        return yyCarMapper.selectYyCarList(yyCar);
    }

    /**
     * 新增医院车辆
     * 
     * @param yyCar 医院车辆
     * @return 结果
     */
    @Override
    public int insertYyCar(YyCar yyCar)
    {
        return yyCarMapper.insertYyCar(yyCar);
    }

    /**
     * 修改医院车辆
     * 
     * @param yyCar 医院车辆
     * @return 结果
     */
    @Override
    public int updateYyCar(YyCar yyCar)
    {
        return yyCarMapper.updateYyCar(yyCar);
    }

    /**
     * 批量删除医院车辆
     * 
     * @param ids 需要删除的医院车辆主键
     * @return 结果
     */
    @Override
    public int deleteYyCarByIds(Long[] ids)
    {
        return yyCarMapper.deleteYyCarByIds(ids);
    }

    /**
     * 删除医院车辆信息
     * 
     * @param id 医院车辆主键
     * @return 结果
     */
    @Override
    public int deleteYyCarById(Long id)
    {
        return yyCarMapper.deleteYyCarById(id);
    }
}
