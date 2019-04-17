package com.morris.pattern.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputStream extends FilterInputStream {

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return -1 == c ? c : (char)(c | 32);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int c = super.read(b, off, len);
        for(int i = off; i < off + c; i ++) {
           b[i] = (byte)(b[i] | 32);
        }
        return c;
    }
}
