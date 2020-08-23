package com.own.stringmatch.test;

import javafx.scene.control.Tab;

import java.util.Arrays;

public class StringMatchTest {

    /**
     * Brute Force
     * each of primary string matches each of pattern string
     *
     * 暴力匹配算法
     * 主串中的每一个字符串与模式串进行匹配
     *
     *
     * @param src primary string 主串
     * @param tar pattern string 模式串
     * @return index of first match
     */
    public static int BF(String src, String tar){
        char[] scrChar = src.toCharArray();
        char[] tarChar = tar.toCharArray();
        int scrLen = src.length(), tarLen = tar.length();

        for (int i = 0; i < scrLen - tarLen + 1; i++){
            int j = 0;
            for (; j < tarLen; j++){
                if (scrChar[i+j] == tarChar[j]){
                   continue;
                }else {
                    break;
                }
            }

            if (j == tarLen)
                return i;
        }
        return -1;
    }

    /**
     * rk Rabin-Karp algorithm
     *   main idea:   compute n-m+1 hashcode of primary string,compute hashTab[0]，then hashcode[1],hashcode[2].... get by
     *              hashTab1[t] = 26*hashTab1[t-1] - tab[tarLen]*(srcChar[t-1] - 'a') + srcChar[t+tarLen-1] - 'a';
     *              then compare with hashcode of pattern string
     *  time complexity:O(n)
     *
     * rk算法
     *      求主串中n-m_1个模式串的hashcode,计算出第一个hashcode,后面的由前一个得出，公式如下
     *      hashTab1[t] = 26*hashTab1[t-1] - tab[tarLen]*(srcChar[t-1] - 'a') + srcChar[t+tarLen-1] - 'a';
     *      再与模式串的hashcode比较
     *
     *  时间复杂度为O(n)
     *
     * @param src
     * @param tar
     * @return
     */
    public static int rkV1(String src, String tar){
        int srcLen = src.length(), tarLen = tar.length();
        char[] srcChar = src.toCharArray(), tarChar = tar.toCharArray();
        int[] hashTab1 = new int[srcLen - tarLen + 1];

        //tab中保存26^t
        int[] tab = new int[tarLen+1];
        int tVal = 1;
        for (int t = 0; t <= tarLen; t++){
            tab[t] = tVal;
            tVal *= 26;
        }

        //求模式串的hash值
        int tarHash = 0;
        for (int j = 0; j < tarLen; j++){
            tarHash += (tarChar[j]-'a')*tab[tarLen -1 -j];
        }

        //计算主串中的所有子串的hash值
        int src0Hash = 0;
        for (int s = 0; s < tarLen; s++){
            src0Hash += (srcChar[s]- 'a')*tab[tarLen -1 -s];
        }
        //计算得出第一个子串的hash值
        hashTab1[0] = src0Hash;
        //由第一个子串的hash值计算其他子串的hash值
        for (int t = 1; t < srcLen - tarLen + 1; t++){
            hashTab1[t] = 26*hashTab1[t-1] - tab[tarLen]*(srcChar[t-1] - 'a') + srcChar[t+tarLen-1] - 'a';
        }
        System.out.println("get hashcode by pre="+Arrays.toString(hashTab1));

        for (int i = 0; i < hashTab1.length; i++){
            if (hashTab1[i] == tarHash)
                return i;
        }
        return -1;
    }

    /**
     * rk Rabin-Karp algorithm
     * main idea:
     *           compute n-m+1 hashcode of primary string, get by
     *           s[0]*26^(n-1) + s[1]*26^(n-2) + ... + s[n-1], 26^n save in array, index is n,value is 26^n
     *           then each of them compare with hashcode of target string
     * time complexity:O(n*m)   n: length of src string,    m: length of tar string
     * @param src  primary string
     * @param tar  pattern string
     * @return
     */
    public static int rkV2(String src, String tar){
        int srcLen = src.length(), tarLen = tar.length();
        char[] srcChar = src.toCharArray(), tarChar = tar.toCharArray();
        int[] hashTab = new int[srcLen - tarLen + 1];
        int[] hashTab1 = new int[srcLen - tarLen + 1];

        //tab中保存26^t
        int[] tab = new int[tarLen+1];
        int tVal = 1;
        for (int t = 0; t <= tarLen; t++){
            tab[t] = tVal;
            tVal *= 26;
        }

        //求模式串的hash值
        int tarHash = 0;
        for (int j = 0; j < tarLen; j++){
            tarHash += (tarChar[j]-'a')*tab[tarLen -1 -j];
        }


        for (int i = 0; i < srcLen - tarLen + 1; i++){
            int hash = 0;
            for (int j = 0; j < tarLen && (i+j) < srcLen; j++){
                hash += (srcChar[i+j]-'a')*tab[tarLen -1 -j];
            }
            hashTab[i] = hash;

            if (hash == tarHash)
                return i;
        }

        System.out.println("get hashcode by calculating every="+Arrays.toString(hashTab));
        return -1;

    }

    public static void main(String[] args) {
        String src = "abcdefgh";
        String tar = "acfg";

       // int bf = BF(src, tar);
      // System.out.println("匹配到的下标为：" + bf);

        Integer integer = Integer.valueOf(1);
        System.out.println("int="+integer);
        Long.valueOf(1l);

    }

}
