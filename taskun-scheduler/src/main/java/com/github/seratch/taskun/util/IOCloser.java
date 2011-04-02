package com.github.seratch.taskun.util;

import java.io.BufferedReader;
import java.io.InputStream;

public final class IOCloser {

    public static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (Exception e2) {
            }
        }
    }

    public static void close(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (Exception e2) {
            }
        }
    }

}
