package me.tongfei.progressbar;

import static org.jline.utils.WCWidth.wcwidth;

/**
 * Contains methods to compute the display lengths of characters and strings on a terminal.
 * @author Tongfei Chen
 * @since 0.9.1
 */
class StringDisplayUtils {

    /**
     * Returns the display width of a Unicode character on terminal.
     */
    static int getCharDisplayLength(char c) {
        // wcwidth is actually implemented in jline3: no need to implement our own
        // control characters will have -1 wcwidth, but actually 0 when displayed
        return Math.max(wcwidth(c), 0);
    }

    /**
     * Returns the display width of a Unicode string on terminal.
     */
    static int getStringDisplayLength(String s) {
        int displayWidth = 0;
        for (int i = 0; i < s.length(); i++)
            displayWidth += getCharDisplayLength(s.charAt(i));
        return displayWidth;
    }

    static String trimDisplayLength(String s, int maxDisplayLength) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int totalLength = 0;
        while (totalLength < maxDisplayLength) {
            char c = s.charAt(i);
            totalLength += getCharDisplayLength(c);
            if (totalLength > maxDisplayLength) break;
            i++;
            sb.append(c);
        }
        return sb.toString();
    }

}
