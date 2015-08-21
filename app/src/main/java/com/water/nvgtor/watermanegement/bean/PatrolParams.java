package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/21.
 */
public class PatrolParams {
    private String fieldType;
    private int enable;
    private String fieldAttribute;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getFieldAttribute() {
        return fieldAttribute;
    }

    public void setFieldAttribute(String fieldAttribute) {
        this.fieldAttribute = fieldAttribute;
    }

    @Override
    public String toString() {
        return "PatrolParams{" +
                "fieldType='" + fieldType + '\'' +
                ", enable=" + enable +
                ", fieldAttribute='" + fieldAttribute + '\'' +
                '}';
    }
}
