package com.java.tmnc.test;

import com.java.tmnc.IOldMapNumberCalculate;
import com.java.tmnc.utils.NewMapNumberCalculate;
import com.java.tmnc.utils.OldMapNumberCalculate;

/**
 * test
 *
 * @author wnm
 * @date 2020/7/28
 */
public class test {

    public static void main(String[] args) {
        NewMapNumberCalculate newMapNumberCalculate = new NewMapNumberCalculate();
        System.out.print("根据图幅编号计算图幅四个角点经纬度: J49B001002\n");
        System.out.print(newMapNumberCalculate.NewNumberToJW("J49B001002"));
        System.out.print("\n112°46′, 27°56′旧图幅编号：\n");
        TestOldMap();
        System.out.print("\n112°46′, 27°56′新图幅编号：\n");
        TestNewMap();
    }

    public static void TestOldMap(){
        IOldMapNumberCalculate iolman=new OldMapNumberCalculate();
        String str1 = iolman.OldNumberCalculate("100", "112°46′", "27°56′");
        String str2 = iolman.OldNumberCalculate("50", "112°46′", "27°56′");
        String str3 = iolman.OldNumberCalculate("20", "112°46′", "27°56′");
        String str4 = iolman.OldNumberCalculate("10", "112°46′", "27°56′");
        String str5 = iolman.OldNumberCalculate("5", "112°46′", "27°56′");
        String str6 = iolman.OldNumberCalculate("2.5", "112°46′", "27°56′");
        String str7 = iolman.OldNumberCalculate("1", "112°46′", "27°56′");
        String str8 = iolman.OldNumberCalculate("5000", "112°46′", "27°56′");
        System.out.print(str1 + "\n" + str2 + "\n" + str3 + "\n" + str4 + "\n" + str5 + "\n" + str6 + "\n" + str7 + "\n" + str8);
    }

    public static void TestNewMap(){
        NewMapNumberCalculate iolman=new NewMapNumberCalculate();
        System.out.print(iolman.NewNumber_100Scale("116°28′25″","39°54′30″")+"\n");
        System.out.print(iolman.NewNumber_Scale("5","116°28′25″","39°54′30″")+"\n");
    }
}
