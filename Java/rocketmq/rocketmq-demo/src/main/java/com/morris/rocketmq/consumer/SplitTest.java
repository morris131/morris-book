package com.morris.rocketmq.consumer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class SplitTest {
    public static void main(String[] args) {


        String xx = "123|&||&|456|&|";

        String[] split = splitByWholeSeparator(xx, "|&|");
        //String[] split = xx.split("|&|");

        int i = 0;
        for (String s : split) {
            System.out.println(i+++":"+s);
        }



    }

    private static String[] splitByWholeSeparator(String str, String separator) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return ArrayUtils.EMPTY_STRING_ARRAY;
            } else if (separator != null && !"".equals(separator)) {
                int separatorLength = separator.length();
                ArrayList<String> substrings = new ArrayList();
                int beg = 0;
                int end = 0;

                while(end < len) {
                    end = str.indexOf(separator, beg);
                    if (end > -1) {
                        if (end >= beg) {
                            substrings.add(str.substring(beg, end));
                            beg = end + separatorLength;
                        } else {
                            beg = end + separatorLength;
                        }
                    } else {
                        substrings.add(str.substring(beg));
                        end = len;
                    }
                }

                return substrings.toArray(new String[substrings.size()]);
            }
        }
        return null;
    }
}
