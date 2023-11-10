package com.vamco.java.words;

import com.vamco.java.jar.tools.stringWorker;

public class debugs {
    public static void main(String[] args) {
        String t = "啊，我这个可是极品";
        String p = "这个";
        int r = stringWorker.stringAt(t,p);
        System.out.print(r);
    }
}
