package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.AdmYyCwMapper;
import com.ruoyi.jkza.domain.AdmYyCw;
import com.ruoyi.jkza.service.IAdmYyCwService;

/**
 * 医院对应Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-04
 */
@Service
public class AdmYyCwServiceImpl implements IAdmYyCwService 
{
    @Autowired
    private AdmYyCwMapper admYyCwMapper;

    /**
     * 查询医院对应
     * 
     * @param id 医院对应主键
     * @return 医院对应
     */
    @Override
    public AdmYyCw selectAdmYyCwById(Long id)
    {
        return admYyCwMapper.selectAdmYyCwById(id);
    }

    /**
     * 查询医院对应列表
     * 
     * @param admYyCw 医院对应
     * @return 医院对应
     */
    @Override
    public List<AdmYyCw> selectAdmYyCwList(AdmYyCw admYyCw)
    {
        return admYyCwMapper.selectAdmYyCwList(admYyCw);
    }

    /**
     * 新增医院对应
     * 
     * @param admYyCw 医院对应
     * @return 结果
     */
    @Override
    public int insertAdmYyCw(AdmYyCw admYyCw)
    {
        return admYyCwMapper.insertAdmYyCw(admYyCw);
    }

    /**
     * 修改医院对应
     * 
     * @param admYyCw 医院对应
     * @return 结果
     */
    @Override
    public int updateAdmYyCw(AdmYyCw admYyCw)
    {
        return admYyCwMapper.updateAdmYyCw(admYyCw);
    }

    /**
     * 批量删除医院对应
     * 
     * @param ids 需要删除的医院对应主键
     * @return 结果
     */
    @Override
    public int deleteAdmYyCwByIds(Long[] ids)
    {
        return admYyCwMapper.deleteAdmYyCwByIds(ids);
    }

    /**
     * 删除医院对应信息
     * 
     * @param id 医院对应主键
     * @return 结果
     */
    @Override
    public int deleteAdmYyCwById(Long id)
    {
        return admYyCwMapper.deleteAdmYyCwById(id);
    }
}
