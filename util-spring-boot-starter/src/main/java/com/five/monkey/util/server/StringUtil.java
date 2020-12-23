package com.five.monkey.util.server;

import java.util.StringJoiner;

/**
 * @author jim
 * @date 2020/12/22 19:55
 */
public class StringUtil {

    public String join(String... str) {
        return this.join(',', str);
    }

    public String join(char separator, String... str) {
        StringJoiner joiner = new StringJoiner(String.valueOf(separator));
        for (String s : str) {
            joiner.add(s);
        }
        return joiner.toString();
    }

}
