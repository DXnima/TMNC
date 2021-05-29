package com.java.tmnc;

import java.util.Map;

/**
 * IOldMapNumberCalculate
 *旧图幅编号计算
 * @author wnm
 * @date 2020/7/19
 */
public interface IOldMapNumberCalculate {

    /**
     * W公式
     *
     * @return int
     * @author wnm
     * @date 2020/7/29
     * [比例尺, 经度, 纬度]
     * 比例尺包括 100 50 20 10 5 2.5 1 5000
     * 对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     */
    int W(String Scalar, String longitude, String latitude);

    /**
     * 1：100万比例尺计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_100Scale(String longitude, String latitude);

    /**
     * 1：50万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_50Scale(String longitude, String latitude);

    /**
     * 1：20万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_20Scale(String longitude, String latitude);

    /**
     * 1：10万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    int OldNumber_10Scale(String longitude, String latitude);

    /**
     * 1：5万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_5Scale(String longitude, String latitude);

    /**
     * 1：2.5万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    int OldNumber_2_5Scale(String longitude, String latitude);

    /**
     * 1：1万比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_1Scale(String longitude, String latitude);

    /**
     * 1：5000比例尺 最后一位编号计算
     *
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     */
    String OldNumber_5000Scale(String longitude, String latitude);

    /**
     *所有比例尺图幅编号输出 包括临边
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 20 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    Map<String,String> OldNumberCalculateAround(String scale, String longitude, String latitude);

    /**
     * 所有比例尺图幅编号输出
     *参数 [比例尺, 经度, 纬度]
     * @return java.lang.String
     * @author wnm
     * @date 2020/7/30
     * @param scale,longitude,latitude [比例尺, 经度, 纬度]
     * 比例尺包括 100 50 20 10 5 2.5 1 5000
     * 对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     */
    String OldNumberCalculate(String scale, String longitude, String latitude);
}
