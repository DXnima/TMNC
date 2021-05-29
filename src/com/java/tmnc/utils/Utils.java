package com.java.tmnc.utils;
import	java.util.HashMap;
import java.util.Map;

/**
 * Utils
 * 工具类
 * @author wnm
 * @date 2020/7/29
 */
public class Utils {

    /**
     *数字转大写字母
     * @author wnm
     * @date 2020/7/29
     * @param
     * @return java.lang.String
     */
    public static String NumToA(int number) {
        return (char)(number+64)+"";
    }

    /**
     *大写字母转数字
     * @author wnm
     * @date 2020/7/29
     * @param
     * @return java.lang.String
     */
    public static int AToNum(char number) {
        return (int)number-64;
    }

    /**
     *数字转小写字母
     * @author wnm
     * @date 2020/7/29
     * @param
     * @return java.lang.String
     */
    public static String NumToa(int number) {
        return (char)(number+96)+"";
    }

    /**
     *获取指定位置字符串并转换为整型
     */
    public static int StrToInt(String number,int beginIndex,int endIndex){
        String str=number.substring(beginIndex,endIndex);
        if (beginIndex == 0 || beginIndex == 3) {
            return AToNum(str.charAt(0));
        }
        return Integer.valueOf(str).intValue();
    }

    /**
     *不足两位数补零
     * @author wnm
     * @date 2020/7/30
     * @param
     * @return java.lang.String
     */
    public static String NumToStr_2(int number) {
        if(0<=number&&number<=9) {
            return "0"+number;
        }
        else {
            return number+"";
        }
    }

    /**
     *不足三位数补零
     * @author wnm
     * @date 2020/7/30
     * @param
     * @return java.lang.String
     */
    public static String NumToStr_3(int number) {
        if(0<=number&&number<=9) {
            return "00"+number;
        }
        else if(10<=number&&number<=99){
            return "0"+number;
        }
        else {
            return number+"";
        }
    }

    /**
     *获取度分秒
     * @author wnm
     * @date 2020/7/29
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Double>
     */
    public static Double DFMtoDouble(String str) {
        Map<String, Double> map = new HashMap<String, Double>();
        //是否有°
        if (str.contains("°")) {
            String dStr = str.substring(str.indexOf(""), str.indexOf("°"));
            double d = Double.parseDouble(dStr);
            map.put("d", d);
            //是否有′
            if (str.contains("′")) {
                String fStr = str.substring(str.indexOf("°") + 1, str.indexOf("′"));
                double f = Double.parseDouble(fStr);
                map.put("f", f);
                String mStr = str.substring(str.indexOf("′") + 1);
                if (mStr.equals("")) {
                    map.put("m", 0.00);
                }
                else {
                    double m = Double.parseDouble(mStr);
                    map.put("m", m);
                }
            } else {
                map.put("f", 0.00);
                map.put("m", 0.00);
            }
        } else {
            map.put("d", 0.00);
            map.put("f", 0.00);
            map.put("m", 0.00);
        }
        double d = map.get("d") + map.get("f") / 60.0 + map.get("m") / 3600.0;
        return d;
    }

    /**
     *获取度分秒
     * @author wnm
     * @date 2020/7/29
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Double>
     */
    public static Map getDFM(String str) {
        Map<String, Double> map = new HashMap<String, Double>();
        //是否有°
        if (str.contains("°")) {
            String dStr = str.substring(str.indexOf(""), str.indexOf("°"));
            double d = Double.parseDouble(dStr);
            map.put("d", d);
            //是否有′
            if (str.contains("′")) {
                String fStr = str.substring(str.indexOf("°") + 1, str.indexOf("′"));
                double f = Double.parseDouble(fStr);
                map.put("f", f);
                String mStr = str.substring(str.indexOf("′") + 1);
                if (mStr.equals("")) {
                    map.put("m", 0.00);
                }
                else {
                    double m = Double.parseDouble(mStr);
                    map.put("m", m);
                }
            } else {
                map.put("f", 0.00);
                map.put("m", 0.00);
            }
        } else {
            map.put("d", 0.00);
            map.put("f", 0.00);
            map.put("m", 0.00);
        }
        return map;
    }

    /**
     * 度转换成度分秒
     * @author wnm
     * @date 2020/8/6
     * @param
     * @return
     */
    public static String DtoDFM(double number) {
        int d = (int) number;
        String s = number + "";
        String result = "0." + s.split("\\.")[1];
        double dF = Double.parseDouble(result) * 60;
        int f = (int) dF;
        s = dF + "";
        result = "0." + s.split("\\.")[1];
        double dM = Double.parseDouble(result) * 60;
        String m = String.format("%.2f", dM);
        if (m.equals("60.00")){
            f=f+1;
            return d + "°" + f + "′" + 0;
        }
        else {
            return d + "°" + f + "′" + m;
        }
    }

    public static String DFMCalculate(String str1, String str2, String code) {
        Map<String, Double> map1 = getDFM(str1);
        Map<String, Double> map2 = getDFM(str2);
        if (code.equals("+")) {
            int d = (int) (map1.get("d") + map2.get("d"));
            int f = (int) (map1.get("f") + map2.get("f"));
            double m = map1.get("m") + map2.get("m");
            return d + "°" + f + "′" + m;
        }
        else {
            int d = (int) (map1.get("d") - map2.get("d"));
            int f = (int) (map1.get("f") - map2.get("f"));
            double m = map1.get("m") - map2.get("m");
            return d + "°" + f + "′" + m;
        }
    }
}
