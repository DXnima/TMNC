package com.java.tmnc.pojo;

/**
 * OldMapNumber
 *旧图幅经纬度差编号关系表
 * @author wnm
 * @date 2020/7/19
 */
public class OldMapNumber {

    private String scale;//比例尺
    private Double longitude;//经差
    private Double latitude;//纬差
    private String baseScale;//基础比例尺
    private int baseFraming;//基础分幅
    private String lastCode;//最后代号

    public OldMapNumber(String scale,Double longitude,Double latitude,String baseScale,int baseFraming,String lastCode) {
        this.scale = scale;
        this.longitude = longitude;
        this.latitude = latitude;
        this.baseScale = baseScale;
        this.baseFraming = baseFraming;
        this.lastCode = lastCode;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
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

    public String getBaseScale() {
        return baseScale;
    }

    public void setBaseScale(String baseScale) {
        this.baseScale = baseScale;
    }

    public int getBaseFraming() {
        return baseFraming;
    }

    public void setBaseFraming(int baseFraming) {
        this.baseFraming = baseFraming;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }
}
