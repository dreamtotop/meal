package org.top.meal.utils;
/*
 * 比较两个double数值的大小
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;
    public static boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1 - d2);
        if(result<MONEY_RANGE){
            return true;
        }
        return false;
    }
}
