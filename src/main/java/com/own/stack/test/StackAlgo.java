package com.own.stack.test;

import java.util.Arrays;

public class StackAlgo {

    /**
     * 浏览器的前进后退
     * 方法:栈一保存新打开的网页，栈顶数据始终为当前网页，
     *      后退时将栈一中的数据取出存入栈二，
     *      前进时将栈二中的数据取出存入栈一，
     *      当在当前网页打开新的网页时，再无法前进
     */
    public void sampleBrowser(){


    }

    /**
     * 匹配左右括号
     * 方法：扫描到左括号时保存在栈中，扫描到右括号时从栈顶取出元素与之比较；
     *      若匹配继续扫描，若不匹配或栈中无数据，返回false
     *      扫描完所有数据后，若栈中无数据，说明完全匹配
     */
    public boolean matchBrackets(String[] str){
        StackOfLinkedList leftStack = new StackOfLinkedList();

        String leftRegx = "^[\\{\\[\\(]$";
        String rightRegx = "^[\\}\\]\\)]$";

        for (int i = 0; i < str.length; i++){
            String s = str[i];
            if (s.matches(leftRegx)){
                leftStack.push(s);
            }else if (s.matches(rightRegx)){
                String top = (String) leftStack.getTop();
                if ("[".equals(top)){
                   if ("]".equals(s)){
                       leftStack.pop();
                   }
                }else if ("{".equals(top)){
                    if ("}".equals(s)){
                       leftStack.pop();
                    }
                }else if ("(".equals(top)){
                    if (")".equals(s)){
                        leftStack.pop();
                    }
                }else {
                    return false;
                }

            }else {
                throw new IllegalArgumentException(s);
            }
        }

        if (!"-1".equals(leftStack.pop().toString())){
            return false;
        }

        return true;
    }

    /**
     * 表达式求值  未完成（算数运算符的优先级比较？）
     * 方法：1、栈一保存数据，栈二保存算数运算符
     *      2、若压入的运算符优先级低于或等于栈顶运算符，取栈顶运算符及两个数据进行运算，再将运算结果压入数据栈中
     *         若压入的运算符优先级高于栈顶运算符，直接压入
     *      3、若数据为最后一位，取运算符与数据进行计算
     */
    public void getValOfExpress(String[] str){
        StackOfLinkedList dataStack = new StackOfLinkedList();
        StackOfLinkedList operatorStack = new StackOfLinkedList();
        String dataRegx = "^\\d+\\.\\d+|\\d+$";
        String operatorRegx = "^\\+|\\-|\\*|\\/$";

        for (int i = 0; i < str.length; i++){
            String s = str[i];
            if (s.matches(dataRegx)){
                dataStack.push(s);
            }else if (s.matches(operatorRegx)){
                operatorStack.push(s);
            }else {
                throw new IllegalArgumentException(s);
            }
        }


    }

    public static void main(String[] args) {
        StackAlgo stackAlgo = new StackAlgo();
        //{}{[()]()}
        String[] s = {"[","{","(",")","}","]"};
        String[] a = {"]"};
        boolean b = stackAlgo.matchBrackets(a);
        System.out.println("是否匹配："+b);

    }

}
