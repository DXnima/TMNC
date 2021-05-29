package com.java.tmnc.pojo;

/**
 * NewMapNumber
 *新图幅经纬度差编号关系表
 * @author wnm
 * @date 2020/7/19
 */
public class NewMapNumber {

    private String scale;//比例尺
    private String scaleCode;//比例尺代号
    private Double longitude;//经差
    private Double latitude;//纬差
    private int row;//行数
    private int columns;//列数
    private int baseFraming;//基础分幅

    public NewMapNumber(String scale,String scaleCode,Double longitude,Double latitude,int row,int columns,int baseFraming) {
        this.scale = scale;
        this.scaleCode = scaleCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.row = row;
        this.columns = columns;
        this.baseFraming = baseFraming;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScaleCode() {
        return scaleCode;
    }

    public void setScaleCode(String scaleCode) {
        this.scaleCode = scaleCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getBaseFraming() {
        return baseFraming;
    }

    public void setBaseFraming(int baseFraming) {
        this.baseFraming = baseFraming;
    }
}
