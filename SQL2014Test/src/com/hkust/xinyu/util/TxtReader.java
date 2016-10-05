package com.hkust.xinyu.util;

import java.io.*;   


public class TxtReader {   
    public TxtReader() {           
    }    
    public String getTextFromTxt(String filePath) throws Exception {   
           
        FileReader fr = new FileReader(filePath);   
        BufferedReader br = new BufferedReader(fr);   
        StringBuffer buff = new StringBuffer();   
        String temp = null;   
        while((temp = br.readLine()) != null){   
            buff.append(temp + "\r\n");   
        }   
        br.close();        
        return buff.toString();        
    }  
   
}  
