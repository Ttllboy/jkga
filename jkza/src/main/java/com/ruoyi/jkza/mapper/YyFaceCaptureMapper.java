package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.YyFaceCapture;

/**
 * 人脸抓拍Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-19
 */
public interface YyFaceCaptureMapper 
{
    /**
     * 查询人脸抓拍
     * 
     * @param id 人脸抓拍主键
     * @return 人脸抓拍
     */
    public YyFaceCapture selectYyFaceCaptureById(Long id);

    /**
     * 查询人脸抓拍列表
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 人脸抓拍集合
     */
    public List<YyFaceCapture> selectYyFaceCaptureList(YyFaceCapture yyFaceCapture);

    /**
     * 新增人脸抓拍
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 结果
     */
    public int insertYyFaceCapture(YyFaceCapture yyFaceCapture);

    /**
     * 修改人脸抓拍
     * 
     * @param yyFaceCapture 人脸抓拍
     * @return 结果
     */
    public int updateYyFaceCapture(YyFaceCapture yyFaceCapture);

    /**
     * 删除人脸抓拍
     * 
     * @param id 人脸抓拍主键
     * @return 结果
     */
    public int deleteYyFaceCaptureById(Long id);

    /**
     * 批量删除人脸抓拍
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYyFaceCaptureByIds(Long[] ids);
}
