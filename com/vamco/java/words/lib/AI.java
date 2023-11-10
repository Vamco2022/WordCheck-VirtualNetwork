package com.vamco.java.words.lib;

import com.vamco.java.jar.tools.stringWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AI {
    public AI(ArrayList<String> word, ArrayList<String> text, StringBuilder s, String news){
        int l = 0;
        Map<String,Integer> wordAll = new HashMap<>();
        ArrayList<String> has = new ArrayList<>();
//        for (String strings : text) {
//            if (strings.contains(news)) {
//                p = 0;
//                while (!(strings.indexOf(news, p) == -1)) {
//                    int pa = strings.indexOf(news, p);
//                    String next = String.valueOf(strings.charAt(pa + 1));
//                    p = pa + 1;
//                    if (wordAll.get(next) == null){
//                        wordAll.put(next,1);
//                        has.add(next);
//                    }
//                    else{
//                        wordAll.put(next, wordAll.get(next) + 1);
//                    }
//                    l++;
//                    //wordAll.merge(next, 1, Integer::sum);
//
//                }
//            }

        int p = 0;

        ArrayList<String> map = text;
//        for (int i = 0;i < s.toString().length();i++){
//            for (int a = 0;a < map.size();a++){
//                String g = String.valueOf(s.toString().charAt(i));
//                String getStrings = map.get(a);
//                if (!getStrings.contains(s)) {
//                    map.remove(a);
//                }
//            }
//        }
        for (int a = 0;a < map.size();a++){
            if (stringWorker.stringAt(map.get(a),s.toString()) == -1){
                map.remove(a);
            }
        }

        for (String strings : map){
            p = stringWorker.stringAt(strings, s.toString());
            while (!(strings.indexOf(news,p) == -1)){
//                int ps = strings.indexOf(news,p);
//                String next = String.valueOf(strings.charAt(ps + 1));
//                if (wordAll.get(next) == null){
//                    wordAll.put(next,1);
//                    has.add(next);
//                }else{
//                    wordAll.put(next, wordAll.get(next) + 1);
//                }
//                //wordAll.merge(next, 1, Integer::sum);
//                l++;
//                //p = -1;
//                p = ps + 1;
//                //break;

                String next = String.valueOf(strings.charAt(p + 1));
                if (wordAll.get(next) == null){
                    wordAll.put(next,1);
                    has.add(next);
                }else{
                    wordAll.put(next, wordAll.get(next) + 1);
                }
                l++;
                p = stringWorker.stringAt(strings, s.toString(), p + 1);
            }
        }

        for (int a =0;a < wordAll.size();a++){
            String gs = has.get(a);
            int m = wordAll.get(gs);
            double bf = (double) 100 * ((double) m / l);
            //float bf = (float) 100 * ((float) m / l);
            System.out.println(gs + "\t" + bf + "%");
        }
    }
}
