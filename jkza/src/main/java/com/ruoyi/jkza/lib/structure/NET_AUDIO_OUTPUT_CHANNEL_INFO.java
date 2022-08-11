package com.ruoyi.jkza.lib.structure;

import com.ruoyi.jkza.lib.NetSDKLib;

import static com.ruoyi.jkza.lib.constant.SDKStructureFieldLenth.MAX_CHANNEL_ID_LEN;

/**
 * 音频输出通道相关信息
 *
 * @author 47040
 * @version 1.0.0
 * @since Created in 2021/3/9 9:24
 */
public class NET_AUDIO_OUTPUT_CHANNEL_INFO extends NetSDKLib.SdkStructure {

    /**
     * 通道编号
     */
    public byte[] szID = new byte[MAX_CHANNEL_ID_LEN];
    /**
     * 保留字节
     */
    public byte[] byReserved = new byte[1024];

}
