package com.vamco.java.jar.tools;

public class stringWorker {
    public static int stringAt(String str, String w){
        char[] c = w.toCharArray();
        int p = -1;
        int op = -1;
        for (char chars : c){
            p = str.indexOf(chars, p);
            if (!(p == op + 1) && !(op == -1)){
                p = -1;
                break;
            }
            op = p;
        }
        return p;
    }
    public static int stringAt(String str, String w, int start){
        char[] c = w.toCharArray();
        int p = start + 1;
        int op = -1;
        for (char chars : c){
            p = str.indexOf(chars, p);
            if (!(p == op + 1) && !(op == -1)){
                p = -1;
                break;
            }
            op = p;
        }
        return p;
    }

}
