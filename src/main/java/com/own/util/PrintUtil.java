package com.own.util;

public class PrintUtil {
    public static void print(int[] a){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++){
            sb.append(",");
            sb.append(a[i]);
        }
        String s = sb.toString();
        System.out.println(s.substring(1,s.length()));
    }
}
