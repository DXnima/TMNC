package com.java.tmnc;

import java.util.Map;

/**
 * INewMapNumberCalculate
 *
 * @author wnm
 * @date 2020/7/19
 */
public interface INewMapNumberCalculate {

    /**
     *1：100万比例尺计算图幅编号
     * @author wnm
     * @date 2020/7/29
     * [经度，纬度]
     * @return java.lang.String
     */
    String NewNumber_100Scale(String longitude,String latitude);

    /**
     *1:100万以外图幅编号计算
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 25 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:25万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    String NewNumber_Scale(String Scalar,String longitude,String latitude);

    /**
     *所有比例尺图幅编号输出 包括临边
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 20 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    Map<String,String> NewNumberCalculateAround(String scale, String longitude, String latitude);

    /**
     *所有比例尺图幅编号输出
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 25 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:25万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    String NewNumberCalculate(String scale,String longitude,String latitude);
}
