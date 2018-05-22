/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.client.util;

import org.springframework.util.StringUtils;

import static com.emc.documentum.rest.client.sample.client.util.Collections.isEmpty;

public class Strings {
    public static boolean contains(String[] params, String ... param) {
        for(int i=0;i<=params.length-param.length;++i) {
            if(params[i].equals(param[0])) {
                boolean contains = true;
                for(int j=1;j+i<params.length && j<param.length && contains;j++) {
                    if(!params[j+i].equals(param[j])) {
                        contains = false;
                    }
                }
                if(contains) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String[] combine(String[] params, String... anotherParams) {
        if(isEmpty(params)) {
            return anotherParams;
        }
        if(isEmpty(anotherParams)) {
            return params;
        }
        String[] combined = new String[params.length + anotherParams.length];
        System.arraycopy(params, 0, combined, 0, params.length);
        System.arraycopy(anotherParams, 0, combined, params.length, anotherParams.length);
        return combined;
    }
    
    public static String nextNameWithIndex(String name) {
        int point = name.lastIndexOf('.');
        if(point == -1) {
            int l = name.lastIndexOf('(');
            int index = 1;
            String nextName = null;
            if(l != -1) {
                int r = name.lastIndexOf(')');
                if(r != -1) {
                    String indexString = name.substring(l+1, r);
                    try {
                        index = Integer.parseInt(indexString) + 1;
                        nextName = name.substring(0, l+1) + index + ")";
                    } catch(NumberFormatException e) {
                    }
                }
            }
            return nextName == null?name+"("+index+")":nextName;
        } else {
            String nameWithoutExtension = name.substring(0, point);
            return nextNameWithIndex(nameWithoutExtension) + name.substring(point);
        }
    }
    
    public static String getFilenameExt(String name) {
        if(!StringUtils.isEmpty(name)) {
            String[] params = name.split(";");
            for(String s : params) {
                s = s.trim();
                if(s.startsWith("filename")) {
                    int extIndex = s.lastIndexOf(".");
                    if(extIndex != -1) {
                        String ext = s.substring(extIndex);
                        if(ext.endsWith("\"")) {
                            ext = ext.substring(0, ext.length() - 1);
                        }
                        return ext;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public static String getFilename(String name) {
        if(!StringUtils.isEmpty(name)) {
            String[] params = name.split(";");
            for(String s : params) {
                s = s.trim();
                if(s.startsWith("filename")) {
                    if(s.endsWith("\"")) {
                        return s.substring(s.indexOf("\"") + 1, s.length() - 1);
                    } else {
                        return s.substring(s.lastIndexOf("'") + 1);
                    }
                }
            }
        }
        return null;
    }
}
