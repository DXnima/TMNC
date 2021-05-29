package com.java.tmnc.utils;

import com.java.tmnc.IOldMapNumberCalculate;
import com.java.tmnc.pojo.OldMapNumber;

import java.util.*;

/**
 * OldMapNumberCalculate
 *旧图幅编号计算
 * @author wnm
 * @date 2020/7/19
 */
public class OldMapNumberCalculate implements IOldMapNumberCalculate {

    //旧图幅经差纬差表
    public  final List<OldMapNumber> listOldMapNumber = Collections.unmodifiableList(new ArrayList<OldMapNumber>() {
        private  final long serialVersionUID = 1L;
        {
            add(new OldMapNumber("100",6.0,4.0,"100",1,""));
            add(new OldMapNumber("50",3.0,2.0,"100",2,"A"));
            add(new OldMapNumber("20",1.0,40.0/60,"100",6,"["));
            add(new OldMapNumber("10",0.5,20.0/60,"100",12,"1"));
            add(new OldMapNumber("5",0.25,10.0/60,"10",2,"A"));
            add(new OldMapNumber("2.5",7.0/60+30.0/3600,5.0/60,"5",2,"1"));
            add(new OldMapNumber("1",3.0/60+45.0/3600,2.0/60+30.0/3600,"10",8,"("));
            add(new OldMapNumber("5000",1.0/60+52.5/3600,1.0/60+15.0/3600,"1",2,"a"));

        }
    });

    /**
     *获取指定比例尺的对象
     * @author wnm
     * @date 2020/7/29
     * 比例尺
     * @return com.java.tmnc.pojo.OldMapNumber
     */
    public   OldMapNumber getOldMapNumberByScale(String scale) {
        for (int i = 0; i < listOldMapNumber.size();i++) {
            if (listOldMapNumber.get(i).getScale().equals(scale)){
                return listOldMapNumber.get(i);
            }
        }
        return null;
    }

    /**
     *1：100万比例尺计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_100Scale(String longitude,String latitude){
        OldMapNumber oldMap = getOldMapNumberByScale("100");
        //纬度计算行号
        double r=Utils.DFMtoDouble(latitude)/oldMap.getLatitude()+1;
        int row=(int)(r);
        //经度计算列号
        double c=Utils.DFMtoDouble(longitude)/oldMap.getLongitude()+31;
        int columns=(int)(c);
        return Utils.NumToA(row)+"-"+columns;
    }

    /**
     *W公式
     * @author wnm
     * @date 2020/7/29
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 20 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return int
     */
    @Override
    public  int W(String Scalar,String longitude,String latitude) {
        //获取所求比例尺
        OldMapNumber oldMap = getOldMapNumberByScale(Scalar);
        //获取基础比例尺
        OldMapNumber baseMap=getOldMapNumberByScale(oldMap.getBaseScale());
        int V=oldMap.getBaseFraming()*(oldMap.getBaseFraming()-1)+1;
        int n=oldMap.getBaseFraming();
        double a=(Utils.DFMtoDouble(latitude)%baseMap.getLatitude())/oldMap.getLatitude();//纬度
        double b=(Utils.DFMtoDouble(longitude)%baseMap.getLongitude())/oldMap.getLongitude();//经度
        int W=V-(int)(a)*n+(int)(b);
        return W;
    }

    /**
     *1：50万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_50Scale(String longitude,String latitude) {
        int W=W("50",longitude,latitude);
        return Utils.NumToA(W);
    }

    /**
     *1：20万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_20Scale(String longitude,String latitude) {
        int W=W("20",longitude,latitude);
        return "["+W+"]";
    }

    /**
     *1：10万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  int OldNumber_10Scale(String longitude,String latitude) {
        int W=W("10",longitude,latitude);
        return W;
    }

    /**
     *1：5万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_5Scale(String longitude,String latitude) {
        int W=W("5",longitude,latitude);
        return Utils.NumToA(W);
    }

    /**
     *1：2.5万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  int OldNumber_2_5Scale(String longitude,String latitude) {
        int W=W("2.5",longitude,latitude);
        return W;
    }

    /**
     *1：1万比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_1Scale(String longitude,String latitude) {
        int W=W("1",longitude,latitude);
        return "("+W+")";
    }

    /**
     *1：5000比例尺 最后一位编号计算
     * @author wnm
     * @date 2020/7/29
     * [经度,纬度]
     * @return java.lang.String
     */
    @Override
    public  String OldNumber_5000Scale(String longitude,String latitude) {
        int W=W("5000",longitude,latitude);
        return Utils.NumToa(W);
    }

    /**
     *所有比例尺图幅编号输出 包括临边
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 20 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    @Override
    public Map<String,String> OldNumberCalculateAround(String scale, String longitude, String latitude) {
        Map<String, String> map = new HashMap<>();
        //获取所求比例尺
        OldMapNumber oldMap = getOldMapNumberByScale(scale);

        //上
        String top = OldNumberCalculate(scale, longitude, Utils.DtoDFM(Utils.DFMtoDouble(latitude) + oldMap.getLatitude()));
        //中
        String middle = OldNumberCalculate(scale, longitude, latitude);
        //下
        String bottom = OldNumberCalculate(scale, longitude, Utils.DtoDFM(Utils.DFMtoDouble(latitude) - oldMap.getLatitude()));
        //左
        String left = OldNumberCalculate(scale, Utils.DtoDFM(Utils.DFMtoDouble(longitude) - oldMap.getLongitude()), latitude);
        //右
        String right = OldNumberCalculate(scale, Utils.DtoDFM(Utils.DFMtoDouble(longitude) + oldMap.getLongitude()), latitude);
        map.put("top", top);
        map.put("middle", middle);
        map.put("bottom", bottom);
        map.put("left", left);
        map.put("right", right);
        return map;
    }

    /**
     *所有比例尺图幅编号输出
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 20 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:20万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    @Override
    public  String OldNumberCalculate(String scale,String longitude,String latitude) {
        String number = "";
        switch (scale) {
            case "100":
                number = OldNumber_100Scale(longitude, latitude);
                break;
            case "50":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_50Scale(longitude, latitude);
                break;
            case "20":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_20Scale(longitude, latitude);
                break;
            case "10":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_10Scale(longitude, latitude);
                break;
            case "5":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_10Scale(longitude, latitude) + "-" + OldNumber_5Scale(longitude, latitude);
                break;
            case "2.5":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_10Scale(longitude, latitude) + "-" + OldNumber_5Scale(longitude, latitude) + "-" + OldNumber_2_5Scale(longitude, latitude);
                break;
            case "1":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_10Scale(longitude, latitude) + "-" + OldNumber_1Scale(longitude, latitude);
                break;
            case "5000":
                number = OldNumber_100Scale(longitude, latitude) + "-" + OldNumber_10Scale(longitude, latitude) + "-" + OldNumber_1Scale(longitude, latitude) + "-" + OldNumber_5000Scale(longitude, latitude);
                break;
            default:
                number="输入的比例尺格式不对！";
                break;
        }
        return number;
    }

}
