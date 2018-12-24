package com.lab.manage.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public enum Dictionary {

    RESIDUAL_TYPE("residual_type"),
    SERVICE_TYPE("service_type"),
    APTITUDE_TYPE("aptitude_type"),
    DETECTION_TYPE("detection_type"),
    REPORT_TYPE("report_type"),
    MODE_TYPE("mode_type"),
    LANGUAGE_TYPE("language_type"),
    SAMPLE_TYPE("sample_type");

    String type;

    Dictionary(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Dictionary byType(String type) {
        Dictionary[] values = Dictionary.values();
        for(Dictionary dictionary : values){
            if(StringUtils.isNotBlank(type)){
                if(dictionary.getType().equals(type)){
                    return dictionary;
                }
            }
        }
        return null;
    }
}
