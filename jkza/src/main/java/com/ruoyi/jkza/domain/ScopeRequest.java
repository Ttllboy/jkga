package com.ruoyi.jkza.domain;

import lombok.Data;

@Data
public class ScopeRequest {

    private Double longitude;

    private Double latitude;

    public ScopeRequest(){}

    public ScopeRequest(Double longitude,Double latitude){

        this.longitude = longitude;

        this.latitude = latitude;

    }
}
