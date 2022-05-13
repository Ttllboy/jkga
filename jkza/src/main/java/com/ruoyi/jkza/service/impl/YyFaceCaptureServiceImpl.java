package com.ruoyi.jkza.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyFaceCaptureMapper;
import com.ruoyi.jkza.domain.YyFaceCapture;
import com.ruoyi.jkza.service.IYyFaceCaptureService;

/**
 * 人脸抓拍Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class YyFaceCaptureServiceImpl implements IYyFaceCaptureService 
{
    @Autowired
    private YyFaceCaptureMapper yyFaceCaptureMapper;

    /**
     * 查询人脸抓拍
     * 
     * @param id 人脸抓拍主键
     * @return 人脸抓拍
     */
    @Override
    public YyFaceCapture selectYyFaceCaptureById(Long id)
    {
        return yyFaceCaptureMapper.selectYyFaceCaptureById(id);
    }

    /**
     * 查询人脸抓拍列表
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 人脸抓拍
     */
    @Override
    public List<YyFaceCapture> selectYyFaceCaptureList(YyFaceCapture yyFaceCapture)
    {
        return yyFaceCaptureMapper.selectYyFaceCaptureList(yyFaceCapture);
    }

    /**
     * 新增人脸抓拍
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 结果
     */
    @Override
    public int insertYyFaceCapture(YyFaceCapture yyFaceCapture)
    {
        return yyFaceCaptureMapper.insertYyFaceCapture(yyFaceCapture);
    }

    /**
     * 修改人脸抓拍
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 结果
     */
    @Override
    public int updateYyFaceCapture(YyFaceCapture yyFaceCapture)
    {
        return yyFaceCaptureMapper.updateYyFaceCapture(yyFaceCapture);
    }

    /**
     * 批量删除人脸抓拍
     * 
     * @param ids 需要删除的人脸抓拍主键
     * @return 结果
     */
    @Override
    public int deleteYyFaceCaptureByIds(Long[] ids)
    {
        return yyFaceCaptureMapper.deleteYyFaceCaptureByIds(ids);
    }

    /**
     * 删除人脸抓拍信息
     * 
     * @param id 人脸抓拍主键
     * @return 结果
     */
    @Override
    public int deleteYyFaceCaptureById(Long id)
    {
        return yyFaceCaptureMapper.deleteYyFaceCaptureById(id);
    }
}
