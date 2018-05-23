/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.ot.akbp.rest.client.sample.client.util;

import java.util.Random;

public class Randoms {
    private static Random RANDOM = new Random();
    
    public static String nextString(int length) {
        StringBuilder sb = new StringBuilder();
        while(sb.length() < length) {
            switch(RANDOM.nextInt(3)) {
                case 0:
                    sb.append((char)('0' + RANDOM.nextInt(10)));
                    break;
                case 1:
                    sb.append((char)('a' + RANDOM.nextInt(26)));
                    break;
                case 2:
                    sb.append((char)('A' + RANDOM.nextInt(26)));
                    break;
            }
        }
        return sb.toString();
    }
}
