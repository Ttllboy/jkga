package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyVideoBaseMapper;
import com.ruoyi.jkza.domain.YyVideoBase;
import com.ruoyi.jkza.service.IYyVideoBaseService;

/**
 * 视频列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyVideoBaseServiceImpl implements IYyVideoBaseService 
{
    @Autowired
    private YyVideoBaseMapper yyVideoBaseMapper;

    /**
     * 查询视频列表
     * 
     * @param id 视频列表主键
     * @return 视频列表
     */
    @Override
    public YyVideoBase selectYyVideoBaseById(Long id)
    {
        return yyVideoBaseMapper.selectYyVideoBaseById(id);
    }

    /**
     * 查询视频列表列表
     * 
     * @param yyVideoBase 视频列表
     * @return 视频列表
     */
    @Override
    public List<YyVideoBase> selectYyVideoBaseList(YyVideoBase yyVideoBase)
    {
        return yyVideoBaseMapper.selectYyVideoBaseList(yyVideoBase);
    }

    /**
     * 新增视频列表
     * 
     * @param yyVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int insertYyVideoBase(YyVideoBase yyVideoBase)
    {
        return yyVideoBaseMapper.insertYyVideoBase(yyVideoBase);
    }

    /**
     * 修改视频列表
     * 
     * @param yyVideoBase 视频列表
     * @return 结果
     */
    @Override
    public int updateYyVideoBase(YyVideoBase yyVideoBase)
    {
        return yyVideoBaseMapper.updateYyVideoBase(yyVideoBase);
    }

    /**
     * 批量删除视频列表
     * 
     * @param ids 需要删除的视频列表主键
     * @return 结果
     */
    @Override
    public int deleteYyVideoBaseByIds(Integer[] ids)
    {
        return yyVideoBaseMapper.deleteYyVideoBaseByIds(ids);
    }

    /**
     * 删除视频列表信息
     * 
     * @param id 视频列表主键
     * @return 结果
     */
    @Override
    public int deleteYyVideoBaseById(Long id)
    {
        return yyVideoBaseMapper.deleteYyVideoBaseById(id);
    }
}
