package com.xuelin.coke.domain.fastjson;

import java.util.List;
import org.apache.commons.collections4.map.LinkedMap;

public class Query {
    private String id;
    private String key;
    private String tableName;
    private String className;
    private List<LinkedMap<String, Object>> column;
    private List<Column> columnList;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    //这里省略部分getter与setter方法
    public List<LinkedMap<String, Object>> getColumn() {
        return column;
    }
    public void setColumn(List<LinkedMap<String, Object>> column) {
        this.column = column;
    }
    public List<Column> getColumnList() {
        return columnList;
    }
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", tableName='" + tableName + '\'' +
                ", className='" + className + '\'' +
                ", column=" + column +
                ", columnList=" + columnList +
                '}';
    }
}
