package com.ruoyi.jkza.lib.structure;

import com.ruoyi.jkza.lib.NetSDKLib;

public class NET_OUT_STARTSERACH_DEVICE extends NetSDKLib.SdkStructure {

	/**
	 *  结构体大小
	 */
	public 	int                   dwSize;							
	
	public NET_OUT_STARTSERACH_DEVICE()
	    {
	     this.dwSize = this.size();
	    }// 此结构体大小
}
