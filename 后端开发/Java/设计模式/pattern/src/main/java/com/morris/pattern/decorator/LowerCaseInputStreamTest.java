package com.morris.pattern.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class LowerCaseInputStreamTest {

    public static void main(String[] args) throws Exception{

        FileInputStream fis = new FileInputStream("d://a.txt");

        BufferedInputStream bis = new BufferedInputStream(fis);
        LowerCaseInputStream lci = new LowerCaseInputStream(bis);

        int c;
        while ((c = lci.read()) >= 0) {
            System.out.print((char)c);
        }
    }
}
