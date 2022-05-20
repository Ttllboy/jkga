package com.ruoyi.jkza.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.DtMachineMapper;
import com.ruoyi.jkza.domain.DtMachine;
import com.ruoyi.jkza.service.IDtMachineService;

/**
 * 起重设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-15
 */
@Service
public class DtMachineServiceImpl implements IDtMachineService 
{
    @Autowired
    private DtMachineMapper dtMachineMapper;

    /**
     * 查询起重设备
     * 
     * @param install 起重设备主键
     * @return 起重设备
     */
    @Override
    public DtMachine selectDtMachineByInstall(Long install)
    {
        return dtMachineMapper.selectDtMachineByInstall(install);
    }

    /**
     * 查询起重设备列表
     * 
     * @param dtMachine 起重设备
     * @return 起重设备
     */
    @Override
    public List<DtMachine> selectDtMachineList(DtMachine dtMachine)
    {
        return dtMachineMapper.selectDtMachineList(dtMachine);
    }

    /**
     * 新增起重设备
     * 
     * @param dtMachine 起重设备
     * @return 结果
     */
    @Override
    public int insertDtMachine(DtMachine dtMachine)
    {
        dtMachine.setCreateTime(DateUtils.getNowDate());
        return dtMachineMapper.insertDtMachine(dtMachine);
    }

    /**
     * 修改起重设备
     * 
     * @param dtMachine 起重设备
     * @return 结果
     */
    @Override
    public int updateDtMachine(DtMachine dtMachine)
    {
        return dtMachineMapper.updateDtMachine(dtMachine);
    }

    /**
     * 批量删除起重设备
     * 
     * @param installs 需要删除的起重设备主键
     * @return 结果
     */
    @Override
    public int deleteDtMachineByInstalls(Long[] installs)
    {
        return dtMachineMapper.deleteDtMachineByInstalls(installs);
    }

    /**
     * 删除起重设备信息
     * 
     * @param install 起重设备主键
     * @return 结果
     */
    @Override
    public int deleteDtMachineByInstall(Long install)
    {
        return dtMachineMapper.deleteDtMachineByInstall(install);
    }
}
