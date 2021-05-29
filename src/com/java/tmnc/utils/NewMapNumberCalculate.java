package com.java.tmnc.utils;
import	java.util.HashMap;
import java.util.*;

import com.java.tmnc.INewMapNumberCalculate;
import com.java.tmnc.pojo.NewMapNumber;

/**
 * NewMapNumberCalculate
 *
 * @author wnm
 * @date 2020/7/19
 */
public class NewMapNumberCalculate implements INewMapNumberCalculate {

    //新图幅经差纬差表
    public final   List<NewMapNumber> listNewMapNumber = Collections.unmodifiableList(new ArrayList<NewMapNumber>() {
        private static final long serialVersionUID = 1L;
        {
            add(new NewMapNumber("100","A",6.0,4.0,1,1,1));
            add(new NewMapNumber("50","B",3.0,2.0,2,2,4));
            add(new NewMapNumber("25","C",1.5,1.0,4,4,16));
            add(new NewMapNumber("10","D",0.5,1.0/3.0,12,12,144));
            add(new NewMapNumber("5","E",0.25,1.0/6.0,24,24,576));
            add(new NewMapNumber("2.5","F",0.125,5.0/60.0,48,48,2304));
            add(new NewMapNumber("1","G",0.0625,2.0/60+30.0/3600,96,96,9216));
            add(new NewMapNumber("5000","H",0.03125,1.0/60+15.0/3600,192,192,36864));

        }
    });

    /**
     *获取指定比例尺的对象
     * @author wnm
     * @date 2020/7/29
     * [比例尺]
     * @return com.java.tmnc.pojo.OldMapNumber
     */
    public  NewMapNumber getNewMapNumberByScale(String scale) {
        for (int i = 0; i < listNewMapNumber.size();i++) {
            if (listNewMapNumber.get(i).getScale().equals(scale)){
                return listNewMapNumber.get(i);
            }
        }
        return null;
    }

    /**
     *1：100万比例尺计算图幅编号
     * @author wnm
     * @date 2020/7/29
     * [经度，纬度]
     * @return java.lang.String
     */
    @Override
    public  String NewNumber_100Scale(String longitude,String latitude){
        NewMapNumber newMap = getNewMapNumberByScale("100");
        //纬度计算行号
        double r=Utils.DFMtoDouble(latitude)/newMap.getLatitude()+1;
        int row=(int)(r);
        //经度计算列号
        double c=Utils.DFMtoDouble(longitude)/newMap.getLongitude()+31;
        int columns=(int)(c);
        return Utils.NumToA(row)+Utils.NumToStr_2(columns);
    }

    /**
     *1:100万以外图幅编号计算
     * @author wnm
     * @date 2020/7/30
     *  [比例尺, 经度, 纬度]
     *  比例尺包括 100 50 25 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:25万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    @Override
    public  String NewNumber_Scale(String Scalar,String longitude,String latitude) {
        NewMapNumber newMap=getNewMapNumberByScale(Scalar);
        //计算行号
        int c=(int)(4/newMap.getLatitude()-(int)((Utils.DFMtoDouble(latitude)%4)/newMap.getLatitude()));
        //计算列号
        int d=(int)((Utils.DFMtoDouble(longitude)%6)/newMap.getLongitude())+1;
        String newNumber = NewNumber_100Scale(longitude, latitude)+newMap.getScaleCode()+Utils.NumToStr_3(c)+Utils.NumToStr_3(d);
        return newNumber;
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
    public Map<String,String> NewNumberCalculateAround(String scale, String longitude, String latitude) {
        Map<String, String> map = new HashMap<>();
        //获取所求比例尺
        NewMapNumber newMap = getNewMapNumberByScale(scale);

        //上
        String top = NewNumberCalculate(scale, longitude, Utils.DtoDFM(Utils.DFMtoDouble(latitude) + newMap.getLatitude()));
        //中
        String middle = NewNumberCalculate(scale, longitude, latitude);
        //下
        String bottom = NewNumberCalculate(scale, longitude, Utils.DtoDFM(Utils.DFMtoDouble(latitude) - newMap.getLatitude()));
        //左
        String left = NewNumberCalculate(scale, Utils.DtoDFM(Utils.DFMtoDouble(longitude) - newMap.getLongitude()), latitude);
        //右
        String right = NewNumberCalculate(scale, Utils.DtoDFM(Utils.DFMtoDouble(longitude) + newMap.getLongitude()), latitude);
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
     *  比例尺包括 100 50 25 10 5 2.5 1 5000
     *  对应1:100万 1:50万 1:25万 1:10万 1:5万 1:2.5万 1:1万 1:5000
     * @return java.lang.String
     */
    @Override
    public  String NewNumberCalculate(String scale,String longitude,String latitude) {
        String number = "";
        switch (scale) {
            case "100":
                number = NewNumber_100Scale(longitude, latitude);
                break;
            case "50":
            case "25":
            case "10":
            case "5":
            case "2.5":
            case "1":
            case "5000":
                number =NewNumber_Scale(scale, longitude, latitude);
            break;
            default:
                number="输入的比例尺格式不对！";
                break;
        }
        return number;
    }

    /**
     *根据图幅编号计算图幅四个角点经纬度
     * 参数 图幅编号
     * 返回 Map类型
     * key分别对应 WN西北 WS西南 EN东北 ES东南
     */
    public  Map<String,String> NewNumberToJW(String number){
        Map<String,String> map = new HashMap<String, String> ();
        //获取所求比例尺
        NewMapNumber newMap = listNewMapNumber.get(Utils.StrToInt(number,3,4)-1);
        //a为1：100万图幅所在纬度带的字符的数字码
        int a=Utils.StrToInt(number,0,1);
        //b为1：100万图幅所在经度带的数字码
        int b=Utils.StrToInt(number,1,3);
        //c为该比例尺地形图在1：100万地形图编号后的行号
        int c=Utils.StrToInt(number,4,7);
        //d为该比例尺地形图在1：100万地形图编号后的列号
        int d=Utils.StrToInt(number,7,10);
        //根据公式计算西南图廓点经度
        double longitude=(b-31)*6+(d-1)*newMap.getLongitude();
        //根据公式计算西南图廓点纬度
        double latitude=(a-1)*4+(4/newMap.getLatitude()-c)*newMap.getLatitude();
        //西北图廓点经纬度
        String nw=Utils.DtoDFM(longitude)+";"+Utils.DtoDFM(latitude+newMap.getLatitude());
        map.put("WN",nw);
        //西南图廓点经纬度
        String sw=Utils.DtoDFM(longitude)+";"+Utils.DtoDFM(latitude);
        map.put("WS",sw);
        //东北图廓点经纬度
        String ne=Utils.DtoDFM(longitude+newMap.getLongitude())+";"+Utils.DtoDFM(latitude+newMap.getLatitude());
        map.put("EN",ne);
        //东南图廓点经纬度
        String se=Utils.DtoDFM(longitude+newMap.getLongitude())+";"+Utils.DtoDFM(latitude);
        map.put("ES",se);
        return map;
    }

    /**
     *根据图幅编号计算图幅四个角点经纬度
     * 参数 图幅编号
     * 返回 Map类型
     * key分别对应 WN西北 WS西南 EN东北 ES东南
     */
    public  Map<String,String> NewNumberCalculateAround(String scale,String longitude1,String latitude1,String longitude2,String latitude2,String longitude3,String latitude3,String longitude4,String latitude4){
        Map<String,String> map = new HashMap<String, String> ();
        //获取所求比例尺
        NewMapNumber newMap = getNewMapNumberByScale(scale);
        String number1=NewNumberCalculate(scale, longitude1, latitude1);
        String number2=NewNumberCalculate(scale, longitude2, latitude2);
        String number3=NewNumberCalculate(scale, longitude3, latitude3);
        String number4=NewNumberCalculate(scale, longitude4, latitude4);
        //a为1：100万图幅所在纬度带的字符的数字码
        int a=Utils.StrToInt(number1,0,1);
        //b为1：100万图幅所在经度带的数字码
        int b=Utils.StrToInt(number1,1,3);
        //c为该比例尺地形图在1：100万地形图编号后的行号
        int c=Utils.StrToInt(number1,4,7);
        //d为该比例尺地形图在1：100万地形图编号后的列号
        int d=Utils.StrToInt(number1,7,10);
        return map;
    }

}

