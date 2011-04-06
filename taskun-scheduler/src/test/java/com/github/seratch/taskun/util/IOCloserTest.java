package com.github.seratch.taskun.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class IOCloserTest {

    @Test
    public void type() throws Exception {
        assertNotNull(IOCloser.class);
    }

    @Test
    public void close_A$InputStream() throws Exception {
        InputStream is = null;
        IOCloser.close(is);
    }

    @Test
    public void close_A$BufferedReader() throws Exception {
        BufferedReader br = null;
        IOCloser.close(br);
    }

}
