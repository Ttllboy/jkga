package com.ruoyi.jkza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.jkza.mapper.YyPsychopathControlMapper;
import com.ruoyi.jkza.domain.YyPsychopathControl;
import com.ruoyi.jkza.service.IYyPsychopathControlService;

/**
 * 病人管控Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Service
public class YyPsychopathControlServiceImpl implements IYyPsychopathControlService 
{
    @Autowired
    private YyPsychopathControlMapper yyPsychopathControlMapper;

    /**
     * 查询病人管控
     * 
     * @param id 病人管控主键
     * @return 病人管控
     */
    @Override
    public YyPsychopathControl selectYyPsychopathControlById(Long id)
    {
        return yyPsychopathControlMapper.selectYyPsychopathControlById(id);
    }

    /**
     * 查询病人管控列表
     * 
     * @param yyPsychopathControl 病人管控
     * @return 病人管控
     */
    @Override
    public List<YyPsychopathControl> selectYyPsychopathControlList(YyPsychopathControl yyPsychopathControl)
    {

        List<YyPsychopathControl> list = yyPsychopathControlMapper.selectYyPsychopathControlList(yyPsychopathControl);
        List<YyPsychopathControl> reList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YyPsychopathControl yyPsychopathControl2 = list.get(i);
            Long year = yyPsychopathControl2.getYear();
            Long month = yyPsychopathControl2.getMonth();
            String streetName = yyPsychopathControl.getStreetName();
            List<YyPsychopathControl> list2 = getBefore(year,month,streetName);
            Long p = list2.stream().collect(Collectors.summingLong(YyPsychopathControl::getAddPatientCount));
            yyPsychopathControl2.setPatientCount(p);
            reList.add(yyPsychopathControl2);
        }
//        return yyPsychopathControlMapper.selectYyPsychopathControlList(yyPsychopathControl);
        return reList;
    }
    public List<YyPsychopathControl> getBefore(Long year,Long month,String streetName){
        YyPsychopathControl yyPsychopathControl = new YyPsychopathControl();
        yyPsychopathControl.setMonth(month);
        yyPsychopathControl.setYear(year);
        if(streetName != null){
            yyPsychopathControl.setStreetName(streetName);
        }
        List<YyPsychopathControl> list = yyPsychopathControlMapper.selectTotal(yyPsychopathControl);
        return list;
    }

    /**
     * 新增病人管控
     * 
     * @param yyPsychopathControl 病人管控
     * @return 结果
     */
    @Override
    public int insertYyPsychopathControl(YyPsychopathControl yyPsychopathControl)
    {
        return yyPsychopathControlMapper.insertYyPsychopathControl(yyPsychopathControl);
    }

    /**
     * 修改病人管控
     * 
     * @param yyPsychopathControl 病人管控
     * @return 结果
     */
    @Override
    public int updateYyPsychopathControl(YyPsychopathControl yyPsychopathControl)
    {
        return yyPsychopathControlMapper.updateYyPsychopathControl(yyPsychopathControl);
    }

    /**
     * 批量删除病人管控
     * 
     * @param ids 需要删除的病人管控主键
     * @return 结果
     */
    @Override
    public int deleteYyPsychopathControlByIds(Long[] ids)
    {
        return yyPsychopathControlMapper.deleteYyPsychopathControlByIds(ids);
    }

    /**
     * 删除病人管控信息
     * 
     * @param id 病人管控主键
     * @return 结果
     */
    @Override
    public int deleteYyPsychopathControlById(Long id)
    {
        return yyPsychopathControlMapper.deleteYyPsychopathControlById(id);
    }
}
