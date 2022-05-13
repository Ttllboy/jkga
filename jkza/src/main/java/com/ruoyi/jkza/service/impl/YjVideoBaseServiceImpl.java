package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YjVideoBaseMapper;
import com.ruoyi.jkza.domain.YjVideoBase;
import com.ruoyi.jkza.service.IYjVideoBaseService;

/**
 * 视频列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-21
 */
@Service
public class YjVideoBaseServiceImpl implements IYjVideoBaseService 
{
    @Autowired
    private YjVideoBaseMapper yjVideoBaseMapper;

    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    @Override
    public YjVideoBase selectYjVideoBaseById(Long id)
    {
        return yjVideoBaseMapper.selectYjVideoBaseById(id);
    }

    /**
     * 查询视频列表列表
     * 
     * @param yjVideoBase 视频列表
     * @return 视频列表
     */
    @Override
    public List<YjVideoBase> selectYjVideoBaseList(YjVideoBase yjVideoBase)
    {
        return yjVideoBaseMapper.selectYjVideoBaseList(yjVideoBase);
    }

    /**
     * 新增视频列表
     * 
     * @param yjVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int insertYjVideoBase(YjVideoBase yjVideoBase)
    {
        return yjVideoBaseMapper.insertYjVideoBase(yjVideoBase);
    }

    /**
     * 修改视频列表
     * 
     * @param yjVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int updateYjVideoBase(YjVideoBase yjVideoBase)
    {
        return yjVideoBaseMapper.updateYjVideoBase(yjVideoBase);
    }

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键
     * @return 结果
     */
    @Override
    public int deleteYjVideoBaseByIds(Integer[] ids)
    {
        return yjVideoBaseMapper.deleteYjVideoBaseByIds(ids);
    }

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    @Override
    public int deleteYjVideoBaseById(Long id)
    {
        return yjVideoBaseMapper.deleteYjVideoBaseById(id);
    }
}
