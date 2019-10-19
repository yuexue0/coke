package com.xuelin.coke.domain.fastjson;

public class Column {
    private String key;
    private String header;
    private String width;
    private Boolean allowSort;
    private Boolean hidden;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getAllowSort() {
        return allowSort;
    }

    public void setAllowSort(Boolean allowSort) {
        this.allowSort = allowSort;
    }

    @Override
    public String toString() {
        return "Column{" +
                "key='" + key + '\'' +
                ", header='" + header + '\'' +
                ", width='" + width + '\'' +
                ", allowSort=" + allowSort +
                ", hidden=" + hidden +
                '}';
    }
}
