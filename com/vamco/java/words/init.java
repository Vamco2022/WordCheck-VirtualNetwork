package com.vamco.java.words;

import com.vamco.java.jar.tools.fileReader;
import com.vamco.java.words.lib.AI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class init {
    static ArrayList<String[]> fileList = new ArrayList<>();
    //the date file list, String[]{path,type}

    static ArrayList<String> wordList = new ArrayList<>();
    //the word data

    static ArrayList<String> textList = new ArrayList<>();
    //T
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
            fileReader listGet = new fileReader("D:\\IdeaWorks\\Word-1\\src\\file\\list.txt");
            String r;
            while (!((r = listGet.readLine()) == null)){
                if (!r.startsWith("#")) {
                    String[] p = r.split(" ");
                    fileList.add(new String[]{
                            p[1],
                            p[0]
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
                    textList.add(l);
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
            while(run){
                if (!systemMode) {
                    System.out.print(">");
                    getWord = get.nextLine();
                    if (getWord.equals("$sudo admin-mode on")){
                        systemMode = true;
                    }else if (getWord.startsWith("$sudo test-lang")){
                        String[] sp = getWord.split(" ");
                        //if (sp[2])
                    }else if (getWord.equals("$sudo exit")){
                        run = false;
                    }else if (getWord.equals("$sudo clear")){
                        sb = new StringBuilder();
                        System.out.println("System: The program is reset");
                    }
                    if (getWord.getBytes().length == 3 || getWord.getBytes().length == 1) {
                        long start = System.currentTimeMillis();
                        new AI(wordList, textList, sb, getWord);
                        long end = System.currentTimeMillis();
                        double use = (double) (end - start) / 1000;
                        System.out.println("分析完成，耗时" + use + "s");
                        sb.append(getWord);
                    }
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
