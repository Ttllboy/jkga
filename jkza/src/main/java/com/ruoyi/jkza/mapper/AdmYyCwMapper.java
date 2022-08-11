package com.ruoyi.jkza.mapper;

import java.util.List;
import com.ruoyi.jkza.domain.AdmYyCw;

/**
 * 医院对应Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-04
 */
public interface AdmYyCwMapper 
{
    /**
     * 查询医院对应
     * 
     * @param id 医院对应主键
     * @return 医院对应
     */
    public AdmYyCw selectAdmYyCwById(Long id);

    /**
     * 查询医院对应列表
     * 
     * @param admYyCw 医院对应
     * @return 医院对应集合
     */
    public List<AdmYyCw> selectAdmYyCwList(AdmYyCw admYyCw);

    /**
     * 新增医院对应
     * 
     * @param admYyCw 医院对应
     * @return 结果
     */
    public int insertAdmYyCw(AdmYyCw admYyCw);

    /**
     * 修改医院对应
     * 
     * @param admYyCw 医院对应
     * @return 结果
     */
    public int updateAdmYyCw(AdmYyCw admYyCw);

    /**
     * 删除医院对应
     * 
     * @param id 医院对应主键
     * @return 结果
     */
    public int deleteAdmYyCwById(Long id);

    /**
     * 批量删除医院对应
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmYyCwByIds(Long[] ids);
}
