package com.lab.manage.enums;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public enum Dictionary {

    RESIDUAL_TYPE("residual_type"),
    SERVICE_TYPE("service_type"),
    APTITUDE_TYPE("aptitude_type"),
    DETECTION_TYPE("detection_type"),
    SAMPLE_TYPE("sample_type");

    String type;

    Dictionary(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
