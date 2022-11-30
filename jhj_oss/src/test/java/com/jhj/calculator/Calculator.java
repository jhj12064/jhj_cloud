package com.jhj.calculator;

import org.apache.commons.collections.ArrayStack;

/**
 * 计算器
 *
 * @author jeremyji
 * @date 2022年 11月30日 15:28:48
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //System.out.println(calculator.convert("9+(3-1)*3+6/2"));
        System.out.println(calculator.jisuan(calculator.convert("9+(3-1)*3+6/2")));
    }

    private Double jisuan(String str){
        ArrayStack stack = new ArrayStack(100);
        for(int i=0;i < str.length();i++) {
            char c = str.charAt(i);
            char [] chars1= {c};
            String s = new String(chars1);
            //如果是数字
            if(!isOpera(s) && !"(".equals(s)  && !")".equals(s)){
                stack.push(Double.valueOf(s));
            }else {
                Double pop = (Double) stack.pop();
                Double pop2 = (Double) stack.pop();
                Double pop3 = jisuan2(pop2, pop, s);
                stack.push(pop3);
            }
        }
        Object pop = stack.pop();
        return (Double)pop;
    }

    private Double jisuan2(Double pop, Double pop2, String type){
        if("+".equals(type)){
            return pop + pop2;
        }
        if("-".equals(type)){
            return pop - pop2;
        }
        if("*".equals(type)){
            return pop * pop2;
        }
        if("/".equals(type)){
            return pop / pop2;
        }
        return 0d;
    }

    private String convert(String str){
        ArrayStack stack = new ArrayStack(100);
        StringBuilder result = new StringBuilder();
        for(int i=0;i < str.length();i++) {
            char c = str.charAt(i);
            char [] chars1= {c};
            String s = new String(chars1);
            //如果是数字
            if(!isOpera(s) && !"(".equals(s)  && !")".equals(s)){
                result.append(s);
                continue;
            }
            //符号
            if("(".equals(s)){
                stack.push(s);
                continue;
            }
            //右括号
            if(")".equals(s)){
                //循环出栈，直到输出左括号（
                while (true){
                    if(!stack.isEmpty()){
                        String pop = (String)stack.pop();
                        if("(".equals(pop)){
                            break;
                        }else {
                            result.append(pop);
                        }
                    }else {
                        result.append(s);
                    }
                }
            }else {
                //其他的运算符号，判断优先级 优先级比第一个高-压站  优先级低或等于？-全部输出，然后自己压站
                if(!stack.isEmpty()){
                    String pop = (String)stack.pop();
                    if(priority(s)>priority(pop)){
                        stack.push(pop);
                        stack.push(s);
                    }else {
                        stack.push(pop);
                        //全部输出，最后然后自己压站
                        while (!stack.isEmpty()){
                            result.append(stack.pop());
                        }
                        stack.push(s);
                    }
                }else {
                    stack.push(s);
                }
            }
        }
        if(!stack.isEmpty()){
            while (!stack.isEmpty()){
                result.append(stack.pop());
            }
        }
        return result.toString();//.replace("(","").replace(")","");
    }

    /**
     * 判断是不是一个运算符
     * true 符号 false 数字
     */
    public boolean isOpera(String val) {
        return ((val.equals("+") ) || (val.equals("-")) || (val.equals("*")) || (val.equals("/")));
    }

    /**
     * 运算符优先级判断
     */
    public int priority(String opera) {
        if("*".equals(opera) || "/".equals(opera)){
            return 2;
        }
        if("+".equals(opera) || "-".equals(opera)){
            return 1;
        }
        return 0;
    }



}
