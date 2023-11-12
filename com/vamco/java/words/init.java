package com.vamco.java.words;

import com.vamco.java.jar.tools.fileReader;
import com.vamco.java.words.lib.AI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class init {
    static ArrayList<String[]> fileList = new ArrayList<>();
    //the date file list, String[]{path,type}

    static ArrayList<String> wordList = new ArrayList<>();
    //the word data

    static ArrayList<String> textList = new ArrayList<>();
    //Text-cn

    static ArrayList<String> textListJp = new ArrayList<>();
    //Text-jp

    static ArrayList<String> textListRu = new ArrayList<>();
    static ArrayList<String> textListEn = new ArrayList<>();
    static Scanner get = new Scanner(System.in);

    static int langId = 0;
    //0 = cn
    //1 = jp
    //2 = ru
    //3 = en

    public static void main(String[] args) {
        int fileInts = 0;
        long kbs = 0;

        //first, Read list.txt to get all data path
        try {
            //read list.txt
            fileReader listGet = new fileReader(".\\file\\list.txt");
            String r;
            while (!((r = listGet.readLine()) == null)){
                if (!r.startsWith("#")) {
                    String[] p = r.split(" ");
                    fileList.add(new String[]{
                            ".\\file\\" + p[1],
                            p[0],
                            p[2]
                    });
                }
            }

            //read words File
            fileReader words;
            for (String[] strings : fileList) {
                if (strings[1].equals("W")) {
                    words = new fileReader(strings[0]);
                    String w;
                    while (!((w = words.readLine()) == null)) {
                        wordList.add(w);
                    }
                }else if (strings[1].equals("T")){
                    words = new fileReader(strings[0]);
                    String l = words.readLine();
                    String type = strings[2];
                    switch (type){
                        case "{cn}":
                            textList.add(l);
                            break;

                        case "{jp}":
                            textListJp.add(l);
                            break;

                        case "{ru}":
                            textListRu.add(l);
                            break;

                        case "{en}":
                            textListEn.add(l);
                            break;
                    }
                    fileInts++;
                    kbs += l.getBytes().length;
                }
            }

            //read text file

            //start program
            boolean run = true;
            String getWord;
            StringBuilder sb = new StringBuilder();
            boolean systemMode = false;
            String[] langTag = {"cn","jp","ru","en"};
            while(run){
                if (!systemMode) {
                    System.out.print(langTag[langId] + ">");
                    getWord = get.nextLine();
                    if (getWord.equals("$sudo admin-mode on")){
                        systemMode = true;
                        continue;
                    }else if (getWord.startsWith("$sudo lang-set")){
                        String[] sp = getWord.split(" ");
                        switch (sp[2]){
                            case "cn":
                                sb = new StringBuilder();
                                langId = 0;
                                break;

                            case "jp":
                                sb = new StringBuilder();
                                langId = 1;
                                break;

                            case "ru":
                                sb = new StringBuilder();
                                langId = 2;
                                break;

                            case "en":
                                sb = new StringBuilder();
                                langId = 3;
                                break;
                        }
                        continue;
                    }else if (getWord.equals("$sudo exit")){
                        run = false;
                        continue;
                    }else if (getWord.equals("$sudo clear")){
                        sb = new StringBuilder();
                        System.out.println("System: The program is reset");
                        continue;
                    }
                    if (langId == 0) {
                        if (getWord.length() != 1) {
                            if (getWord.getBytes().length / 3 != 1) {
                                char[] l = getWord.toCharArray();
                                for (int i = 0; i < l.length - 1; i++) {
                                    sb.append(l[i]);
                                }
                                getWord = String.valueOf(l[l.length - 1]);
                            }
                        }
                    }
                    long start = System.currentTimeMillis();
                    switch (langId) {
                        case 0:
                            new AI(wordList, textList, sb, getWord, langId);
                            break;

                        case 1:
                            new AI(wordList, textListJp, sb, getWord, langId);
                            break;
                    }
                    long end = System.currentTimeMillis();
                    double use = (double) (end - start) / 1000;
                    System.out.println("分析完成，耗时" + use + "s");
                    sb.append(getWord);
                }else{
                    System.out.print("admin$>");
                    getWord = get.nextLine();
                    String[] ps = getWord.split(" ");
                    switch (ps[0]){
                        case "?":
                            System.out.println(
                                    """
                                            Administrator mode console, made by Vamco\s
                                            net - using to get any data in the program\s
                                            exit - using to quit mode"""
                            );
                            break;

                        case "exit":
                            systemMode = false;
                            break;

                        case "net":
                            switch (ps[1]){
                                case "?":
                                default:
                                    System.out.println("""
                                            Command "net"\s
                                            fileInt.get - get the number of files\s
                                            kbs.get - get the kb of files
                                            """
                                    );
                                    break;

                                case "fileInt.get":
                                    System.out.println("The program is running with " + fileInts + " files");
                                    break;

                                case "kbs.get":
                                    System.out.println("The files are use " + (double)kbs / 1000 + "KB diver");
                                    break;
                            }
                            break;

                        default:
                            System.out.println("Error: unknown code");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
