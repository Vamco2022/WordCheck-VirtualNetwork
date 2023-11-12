package com.vamco.java.words.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AI {
    public AI(ArrayList<String> word, ArrayList<String> text, StringBuilder s, String news,int langId){
        if (langId == 0 || langId == 1) {
            int showInAll = 0;
            //所以可能次数
            Map<String, Integer> showAny = new HashMap<>();
            //每个字的可能性
            ArrayList<String> has = new ArrayList<>();
            //可能的字

            //结合上下文处理预测循环
            ArrayList<Integer> inText;
            //可能位置
            //循环主体
            for (String thisText : text) {
                inText = new ArrayList<>();
                //先对第一个字符处理
                int p = 0;
                if (!s.toString().isEmpty()) {
                    while (thisText.indexOf(s.toString().charAt(0), p) != -1) {
                        p = thisText.indexOf(s.toString().charAt(0), p);
                        inText.add(
                                thisText.indexOf(
                                        s.toString().charAt(0),
                                        p
                                ));
                        p++;
                    }
                } else {
                    while (thisText.indexOf(news, p) != -1) {
                        p = thisText.indexOf(news, p);
                        inText.add(p);
                        p++;
                    }
                }

                //然后对上下文每个字符进行筛选
                for (int i = 1; i < s.toString().length(); i++) {
                    ArrayList<Integer> sx = new ArrayList<>();
                    for (int a = 0; a < inText.size(); a++) {
                        String realNext = String.valueOf(thisText.charAt(inText.get(a) + 1));
                        String userNext = String.valueOf(s.toString().charAt(i));
                        if (realNext.equals(userNext)) {
                            int ps = inText.get(a);
                            sx.add(ps + 1);
                        }
                    }
                    inText = sx;
                }

                //新输入筛选
                if (!s.toString().isEmpty()) {
                    ArrayList<Integer> sx = new ArrayList<>();
                    for (int i = 0; i < inText.size(); i++) {
                        int newLine = inText.get(i) + 1;
                        String real = String.valueOf(thisText.charAt(newLine));
                        if (real.equals(news)) {
                            int ps = inText.get(i);
                            sx.add(ps + 1);
                        }
                    }
                    inText = sx;
                }

                //预测下一字符
                for (int a = 0; a < inText.size(); a++) {
                    int real = inText.get(a);
                    int nextLine = real + 1;
                    String next = String.valueOf(thisText.charAt(nextLine));
                    if (showAny.get(next) == null) {
                        showAny.put(next, 1);
                        has.add(next);
                    } else {
                        showAny.put(next, showAny.get(next) + 1);
                    }
                    showInAll++;
                }
            }

            //输出结果
            System.out.println("文本“" + s.toString() + news + "”的下一个字的预测结果是");
            for (int a = 0; a < has.size(); a++) {
                int show = showAny.get(has.get(a));
                String next = has.get(a);
                double about = (double) 100 * ((double) show / showInAll);
                System.out.println(next + "  " + about + "%");
            }
            if (has.isEmpty() || showInAll == 0) {
                System.out.println("无结果");
            }
        }
    }
}

/*
  事实上，我的旧算法代码其实是一坨屎
  没注释，全部混乱，不想改了，只好重写了...
 */