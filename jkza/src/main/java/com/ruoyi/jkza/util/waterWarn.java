package com.ruoyi.jkza.util;

import com.dahuatech.hutool.http.Method;
import com.dahuatech.icc.exception.ClientException;
import com.dahuatech.icc.oauth.http.DefaultClient;
import com.dahuatech.icc.oauth.http.IClient;
import com.dahuatech.icc.oauth.model.v202010.GeneralRequest;
import com.dahuatech.icc.oauth.model.v202010.GeneralResponse;

public class waterWarn {

    public void lowPointWarn() throws ClientException {

        IClient iClient = new DefaultClient();
        /**
         * 1、请求地址是统一网关入口，以 /evo-apigw 开头
         * 2、方法参见 @see com.dahuatech.hutool.http.Method
         */
        GeneralRequest generalRequest = new GeneralRequest("/evo-apigw/evo-xxx/pathxxx", Method.GET);
        // set http post body
        generalRequest.body("json请求体");
        // set http get method param 例如?name=xiao&mobile=1xxx
        generalRequest.form("key", "value");
        // set header
        generalRequest.header("key", "value");
        // 发起请求处理应答
        GeneralResponse generalResponse =iClient.doAction(generalRequest, generalRequest.getResponseClass());
    }


}
