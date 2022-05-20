package com.ruoyi.jkza.service;

import java.util.List;
import com.ruoyi.jkza.domain.DtMachine;

/**
 * 起重设备Service接口
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
public interface IDtMachineService 
{
    /**
     * 查询起重设备
     * 
     * @param install 起重设备主键
     * @return 起重设备
     */
    public DtMachine selectDtMachineByInstall(Long install);

    /**
     * 查询起重设备列表
     * 
     * @param dtMachine 起重设备
     * @return 起重设备集合
     */
    public List<DtMachine> selectDtMachineList(DtMachine dtMachine);

    /**
     * 新增起重设备
     * 
     * @param dtMachine 起重设备
     * @return 结果
     */
    public int insertDtMachine(DtMachine dtMachine);

    /**
     * 修改起重设备
     * 
     * @param dtMachine 起重设备
     * @return 结果
     */
    public int updateDtMachine(DtMachine dtMachine);

    /**
     * 批量删除起重设备
     * 
     * @param installs 需要删除的起重设备主键集合
     * @return 结果
     */
    public int deleteDtMachineByInstalls(Long[] installs);

    /**
     * 删除起重设备信息
     * 
     * @param install 起重设备主键
     * @return 结果
     */
    public int deleteDtMachineByInstall(Long install);
}
