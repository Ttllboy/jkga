package com.ruoyi.jkza.domain;

import com.dahuatech.icc.exception.ClientException;
import com.dahuatech.icc.oauth.http.DefaultClient;
import com.dahuatech.icc.oauth.http.IClient;
import org.springframework.context.annotation.Bean;

public class defaultClient {

    @Bean
    public IClient iccDefaultClient() throws ClientException {
        return new DefaultClient();
    }

}
