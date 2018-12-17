package com.lab.manage.result;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/17.
 */
public class IndexMenu {

    private String name;

    private String url;

    private List<IndexMenu> node;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<IndexMenu> getNode() {
        return node;
    }

    public void setNode(List<IndexMenu> node) {
        this.node = node;
    }
}
