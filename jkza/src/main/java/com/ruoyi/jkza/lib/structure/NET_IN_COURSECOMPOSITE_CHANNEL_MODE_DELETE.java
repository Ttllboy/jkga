package com.ruoyi.jkza.lib.structure;

import com.ruoyi.jkza.lib.NetSDKLib;

import static com.ruoyi.jkza.lib.NetSDKLib.NET_MAX_MODE_NUMBER;

/**
 * 录播主机删除模式入参
 *
 * @author ： 47040
 * @since ： Created in 2020/9/27 17:01
 */
public class NET_IN_COURSECOMPOSITE_CHANNEL_MODE_DELETE extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小
     */
    public int dwSize;
    /**
     * 删除的模式个数
     */
    public int nModeNum;
    /**
     * 删除的模式编号
     */
    public int[] nMode = new int[NET_MAX_MODE_NUMBER];

    public NET_IN_COURSECOMPOSITE_CHANNEL_MODE_DELETE() {
        dwSize = this.size();
    }
}
