package com.ruoyi.jkza.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jkgaconfig")
@Data
public class JkgaConfig {
    private String tqyb;
    private String sfzh;
    private String tflj;
    private String jkm;

}
