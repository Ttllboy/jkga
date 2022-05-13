package com.ruoyi.jkza.util;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("第二次鉴权")
public class SecondAuthRequest {
    private String clientType;
    private String encryptType = "MD5";
    private String password;
    private String randomKey;
    private String realm;
    private String signature;
    private String userName;
    private int expiredTime = 86400;

    public SecondAuthRequest() {
    }

    public static String encryptPassword(String userName, String realm, String password, boolean hasBeanMd5First) {
        if (!hasBeanMd5First) {
            password = SecureUtil.md5(password);
        }
        return SecureUtil.md5(userName.concat(":").concat(realm).concat(":").concat(SecureUtil.md5(password)));
    }

    public static String encryptSignature(String userName, String realm, String password, String randomKey) {
        return SecureUtil.md5(SecureUtil.md5(userName.concat(":").concat(realm).concat(":").concat(password)).concat(":").concat(randomKey));
    }
}