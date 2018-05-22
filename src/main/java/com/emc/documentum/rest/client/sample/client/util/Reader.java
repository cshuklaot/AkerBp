/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.client.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.util.StringUtils;

public class Reader {
    private static boolean silently = false;
    private static volatile Map<String, String> inputMap; 
    
    public static String read(String prompt) {
        String value = getDefaultInput(prompt);
        if(value == null) {
            byte[] bytes = new byte[100];
            while(value == null || value.length() == 0) {
                try {
                    System.out.println(prompt);
                    int readed;
                        readed = System.in.read(bytes);
                    while(readed <= 0) {
                        System.out.println(prompt);
                        readed = System.in.read(bytes);
                    }
                    value = new String(bytes, 0, readed).trim();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
    
    public static String read(String prompt, String defaultValue) {
        String value = getDefaultInput(prompt);
        if(value == null) {
            if(silently) {
                return defaultValue;
            }
            System.out.println(StringUtils.isEmpty(defaultValue)?prompt:(prompt + " [default " + defaultValue + "]")); 
            value = defaultValue;
            byte[] bytes = new byte[100];
            int readed;
            try {
                readed = System.in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                return defaultValue;
            }
            if(readed > 0) {
                String tmp = new String(bytes, 0, readed).trim();
                if(tmp.length() > 0) {
                    value = tmp;
                }
            }
        }
        return value;
    }
    
    public static void readEnterToContinue() {
        if(!silently) {
            read("press \"Enter\" to continue", "");
        }
    }
    
    public static int read(String prompt, int defaultValue) {
        while(true) {
            String value = read(prompt, String.valueOf(defaultValue));
            try {
                return Integer.parseInt(value);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean read(String prompt, boolean defaultValue) {
        while(true) {
            String value = read(prompt, String.valueOf(defaultValue));
            try {
                return Boolean.parseBoolean(value);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean isSilently() {
        return silently;
    }
    
    public static void setSilently(boolean silently) {
        Reader.silently = silently;
    }
    
    public static void setInputMap(Map<String, String> map) {
        Reader.inputMap = map;
    }
    
    private static String getDefaultInput(String prompt) {
        return inputMap == null ? null : inputMap.get(prompt);
    }
}
