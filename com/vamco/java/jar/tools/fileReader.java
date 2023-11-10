package com.vamco.java.jar.tools;

import java.io.*;

public class fileReader {
    String path;
    FileInputStream fin;
    InputStreamReader reader;
    BufferedReader bufferReader;
    
    public fileReader(String path) throws FileNotFoundException {
        this.path = path;
        this.fin = new FileInputStream(path);
        this.reader = new InputStreamReader(this.fin);
        this.bufferReader = new BufferedReader(this.reader);
    }
    
    public fileReader(File file) throws FileNotFoundException{
        this.path = file.getPath();
        this.fin = new FileInputStream(this.path);
        this.reader = new InputStreamReader(this.fin);
        this.bufferReader = new BufferedReader(this.reader);
    }
    
    public String readLine() throws IOException {
        return bufferReader.readLine();
    }
}
