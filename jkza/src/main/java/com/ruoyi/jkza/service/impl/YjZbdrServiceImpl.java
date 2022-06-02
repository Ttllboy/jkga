package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjZbdrMapper;
import com.ruoyi.jkza.domain.YjZbdr;
import com.ruoyi.jkza.service.IYjZbdrService;

/**
 * 值班导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-25
 */
@Service
public class YjZbdrServiceImpl implements IYjZbdrService 
{
    @Autowired
    private YjZbdrMapper yjZbdrMapper;

    /**
     * 查询值班导入
     * 
     * @param id 值班导入主键
     * @return 值班导入
     */
    @Override
    public YjZbdr selectYjZbdrById(Long id)
    {
        return yjZbdrMapper.selectYjZbdrById(id);
    }

    /**
     * 查询值班导入列表
     * 
     * @param yjZbdr 值班导入
     * @return 值班导入
     */
    @Override
    public List<YjZbdr> selectYjZbdrList(YjZbdr yjZbdr)
    {
        return yjZbdrMapper.selectYjZbdrList(yjZbdr);
    }

    /**
     * 新增值班导入
     * 
     * @param yjZbdr 值班导入
     * @return 结果
     */
    @Override
    public int insertYjZbdr(YjZbdr yjZbdr)
    {
        return yjZbdrMapper.insertYjZbdr(yjZbdr);
    }

    /**
     * 修改值班导入
     * 
     * @param yjZbdr 值班导入
     * @return 结果
     */
    @Override
    public int updateYjZbdr(YjZbdr yjZbdr)
    {
        return yjZbdrMapper.updateYjZbdr(yjZbdr);
    }

    /**
     * 批量删除值班导入
     * 
     * @param ids 需要删除的值班导入主键
     * @return 结果
     */
    @Override
    public int deleteYjZbdrByIds(Long[] ids)
    {
        return yjZbdrMapper.deleteYjZbdrByIds(ids);
    }

    /**
     * 删除值班导入信息
     * 
     * @param id 值班导入主键
     * @return 结果
     */
    @Override
    public int deleteYjZbdrById(Long id)
    {
        return yjZbdrMapper.deleteYjZbdrById(id);
    }
}
